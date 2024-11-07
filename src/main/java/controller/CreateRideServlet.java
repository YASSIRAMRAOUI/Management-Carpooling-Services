package controller;

import database.RideDAO;
import models.Ride;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/CreateRideServlet")
public class CreateRideServlet extends HttpServlet {
    private RideDAO rideDAO;

    public void init() {
        rideDAO = new RideDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer driverId = (Integer) session.getAttribute("user_id");

        if (driverId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve form parameters
        String dateStr = request.getParameter("date");
        String destination = request.getParameter("destination");
        String status = request.getParameter("status");
        double fare = Double.parseDouble(request.getParameter("fare"));

        try {
            // Parse date and create a new Ride object
            Date date = Date.valueOf(dateStr);
            Ride ride = new Ride();
            ride.setDriverId(driverId);
            ride.setDate(date);
            ride.setDestination(destination);
            ride.setStatus(status);
            ride.setFare(fare);

            // Save the new ride using RideDAO
            rideDAO.createRide(ride);

            session.setAttribute("rideAdded", "Ride created successfully!");
            response.sendRedirect("DriverHomeServlet");
        } catch (SQLException e) {
            throw new ServletException("Error creating ride", e);
        }
    }
}
