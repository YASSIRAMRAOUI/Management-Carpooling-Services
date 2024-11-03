// File: controller/RideRequestServlet.java
package controller;

import database.RideDAO;
import models.Ride;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/RideRequestServlet")
public class RideRequestServlet extends HttpServlet {
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
            // Get pending ride requests for the driver
            List<Ride> rideRequests = rideDAO.getRideRequestsByDriver(driverId);
            request.setAttribute("rideRequests", rideRequests);
            request.getRequestDispatcher("rideRequests.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error while fetching ride requests", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");

        try {
            if ("accept".equals(action)) {
                rideDAO.updateRideRequestStatus(requestId, "Accepted");
            } else if ("reject".equals(action)) {
                rideDAO.updateRideRequestStatus(requestId, "Rejected");
            }
            response.sendRedirect("RideRequestServlet"); // Refresh the ride requests page
        } catch (SQLException e) {
            throw new ServletException("Database error while updating ride request status", e);
        }
    }
}
