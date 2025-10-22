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
        HttpSession session = request.getSession(false);
        
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        Integer driverId = (Integer) session.getAttribute("user_id");

        if (driverId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String dateStr = request.getParameter("date");
        String depart = request.getParameter("depart");
        String destination = request.getParameter("destination");
        String fareStr = request.getParameter("fare");
        String numberOfPlacesStr = request.getParameter("numberOfPlaces");
        
        // Validate inputs
        if (dateStr == null || depart == null || destination == null || 
            fareStr == null || numberOfPlacesStr == null ||
            dateStr.isEmpty() || depart.trim().isEmpty() || destination.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("createRide.jsp").forward(request, response);
            return;
        }
        
        try {
            double fare = Double.parseDouble(fareStr);
            int numberOfPlaces = Integer.parseInt(numberOfPlacesStr);
            
            if (fare <= 0 || numberOfPlaces <= 0) {
                request.setAttribute("errorMessage", "Fare and number of places must be positive values.");
                request.getRequestDispatcher("createRide.jsp").forward(request, response);
                return;
            }
            
            String status = "In Progress";
            Date date = Date.valueOf(dateStr);
            
            Ride ride = new Ride();
            ride.setDriverId(driverId);
            ride.setDate(date);
            ride.setDepart(depart);
            ride.setDestination(destination);
            ride.setFare(fare);
            ride.setNumberOfPlaces(numberOfPlaces);
            ride.setStatus(status);

            rideDAO.createRide(ride);

            session.setAttribute("rideAdded", "Ride created successfully!");
            response.sendRedirect("DriverHomeServlet");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format for fare or number of places.");
            request.getRequestDispatcher("createRide.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "Invalid date format.");
            request.getRequestDispatcher("createRide.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "An error occurred while creating the ride. Please try again.");
            request.getRequestDispatcher("createRide.jsp").forward(request, response);
        }
    }
}
