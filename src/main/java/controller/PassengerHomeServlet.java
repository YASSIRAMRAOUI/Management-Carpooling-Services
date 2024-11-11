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

    private final RideDAO rideDAO = new RideDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer passengerId = (Integer) session.getAttribute("user_id");

        if (passengerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Ride> availableRides = rideDAO.getAvailableRidesForPassenger(passengerId);

            // Check for messages (if a ride was accepted/declined)
            String rideResponseMessage = (String) session.getAttribute("rideResponseMessage");
            if (rideResponseMessage != null) {
                request.setAttribute("rideResponseMessage", rideResponseMessage);
                session.removeAttribute("rideResponseMessage");
            }

            // Set attributes for JSP
            request.setAttribute("availableRides", availableRides);

            // Forward to passengerHome.jsp
            request.getRequestDispatcher("passengerHome.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int rideId = Integer.parseInt(request.getParameter("rideId"));
        HttpSession session = request.getSession();
        int passengerId = (Integer) session.getAttribute("passengerId");

        try {
            if ("accept".equals(action)) {
                rideDAO.acceptRide(passengerId, rideId);
            } else if ("decline".equals(action)) {
                rideDAO.declineRide(passengerId, rideId);
            }
            response.sendRedirect("PassengerHomeServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
