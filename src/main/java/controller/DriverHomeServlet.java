package controller;

import database.RideDAO;
import models.Ride;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/DriverHomeServlet")
public class DriverHomeServlet extends HttpServlet {
    private RideDAO rideDAO;

    public void init() {
        rideDAO = new RideDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer driverId = (Integer) session.getAttribute("user_id");

        // Fetch summary statistics and recent rides for the driver
        try {
            int totalRides = rideDAO.getTotalRidesByDriver(driverId);
            int pendingRequests = rideDAO.getPendingRequestsByDriver(driverId);
            double earnings = rideDAO.getEarningsByDriver(driverId);
            List<Ride> recentRides = rideDAO.getRecentRidesByDriver(driverId);

            // Set attributes for JSP
            request.setAttribute("totalRides", totalRides);
            request.setAttribute("pendingRequests", pendingRequests);
            request.setAttribute("earnings", earnings);
            request.setAttribute("recentRides", recentRides);

            // Forward to driverHome.jsp
            request.getRequestDispatcher("driverHome.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        }
    }
}