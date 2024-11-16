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
            List<Ride> availableRides = rideDAO.getAvailableRides(); // Fetch rides with all fields populated

            // Print ride details to the console for debugging
            for (Ride ride : availableRides) {
                System.out.println("Ride details: " + ride.getDriverName() + ", " + ride.getDepart() + " to " + ride.getDestination());
            }

            // Set the list of available rides as a request attribute
            request.setAttribute("availableRides", availableRides);

            // Forward to passengerHome.jsp with the rides data
            request.getRequestDispatcher("passengerHome.jsp").forward(request, response);
        } catch (SQLException e) {
            // Log the exception for debugging
            e.printStackTrace();

            // Handle database access error
            throw new ServletException("Database access error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null || !"Passenger".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        int rideId = Integer.parseInt(request.getParameter("rideId"));
        Integer passengerId = (Integer) session.getAttribute("user_id");

        if ("accept".equals(action)) {
            rideDAO.acceptRide(passengerId, rideId);
        } else if ("decline".equals(action)) {
            rideDAO.declineRide(passengerId, rideId);
        }
        response.sendRedirect("PassengerHomeServlet"); // Redirect to refresh available rides
    }
}
