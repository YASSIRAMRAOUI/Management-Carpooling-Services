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

        String dateStr = request.getParameter("date");
        String depart = request.getParameter("depart");
        String destination = request.getParameter("destination");
        Double fare = Double.parseDouble(request.getParameter("fare"));
        int numberOfPlaces = Integer.parseInt(request.getParameter("numberOfPlaces"));
        String status = "In Progress";

        try {
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
        } catch (SQLException e) {
            throw new ServletException("Error creating ride", e);
        }
    }
}
