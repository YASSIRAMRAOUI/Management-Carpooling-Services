package controller;

import database.RideDAO;
import models.Ride;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/PassengerHomeServlet")
public class PassengerHomeServlet extends HttpServlet {
    private RideDAO rideDAO;

    @Override
    public void init() {
        rideDAO = new RideDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer PassengerId = (Integer) session.getAttribute("user_id");

        if (PassengerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Ride> availableRides = rideDAO.getAvailableRides();

            request.setAttribute("availableRides", availableRides);

            request.getRequestDispatcher("passengerHome.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();

            throw new ServletException("Database access error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null
                || !"passenger".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        String rideIdParam = request.getParameter("rideId");
        String placeParam = request.getParameter("place");
        Integer passengerId = (Integer) session.getAttribute("user_id");

        if (action == null || rideIdParam == null) {
            response.sendRedirect("PassengerHomeServlet?error=invalid_action");
            return;
        }

        try {
            int rideId = Integer.parseInt(rideIdParam);
            int place = Integer.parseInt(placeParam);

            if ("accept".equals(action)) {
                rideDAO.acceptRide(passengerId, rideId, place);
            } else if ("decline".equals(action)) {
                rideDAO.declineRide(passengerId, rideId);
            } else {
                response.sendRedirect("PassengerHomeServlet?error=invalid_action");
                return;
            }

            response.sendRedirect("PassengerHomeServlet");
        } catch (NumberFormatException e) {
            response.sendRedirect("PassengerHomeServlet?error=invalid_ride_id");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("PassengerHomeServlet?error=database_error");
            throw new ServletException("Database access error", e);
        }
    }

}
