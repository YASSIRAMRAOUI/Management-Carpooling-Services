package controller;

import database.RideDAO;
import models.Ride;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/MyRideServlet")
public class MyRideServlet extends HttpServlet {
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
            List<Ride> rideHistory = rideDAO.getRideHistoryByPassenger(PassengerId);

            request.setAttribute("rideHistory", rideHistory);

            request.getRequestDispatcher("myRides.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();

            throw new ServletException("Database access error", e);
        }
    }

}
