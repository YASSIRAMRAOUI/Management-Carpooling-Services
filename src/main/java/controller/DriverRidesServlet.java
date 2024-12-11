package controller;

import database.RideDAO;
import models.Ride;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/DriverRidesServlet")
public class DriverRidesServlet extends HttpServlet {
    private RideDAO rideDAO;

    public void init() {
        rideDAO = new RideDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer driverId = (Integer) session.getAttribute("user_id");

        if (driverId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Ride> driverRides = rideDAO.getRidesByDriver(driverId);
            request.setAttribute("driverRides", driverRides);
            response.sendRedirect("RideRequestServlet");

        } catch (SQLException e) {
            throw new ServletException("Database error while fetching rides", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int rideId = Integer.parseInt(request.getParameter("rideId"));

        try {
            if ("updateStatus".equals(action)) {
                String status = request.getParameter("status");
                rideDAO.updateRideStatus(rideId, status);
            } else if ("delete".equals(action)) {
                rideDAO.deleteRide(rideId);
            }
            response.sendRedirect("DriverRidesServlet");
        } catch (SQLException e) {
            throw new ServletException("Error processing ride action", e);
        }
    }
}
