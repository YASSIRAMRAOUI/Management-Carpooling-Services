package controller;

import database.UserDAO;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password");

        try {
            User user = userDAO.getUserByEmail(email);

            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                // Password is correct, create session
                HttpSession session = request.getSession();
                session.setAttribute("user_id", user.getUserId());
                session.setAttribute("name", user.getName());
                session.setAttribute("role", user.getRole());

                // Redirect based on role
                if ("Driver".equals(user.getRole())) {
                    response.sendRedirect("driverHome.jsp");
                } else {
                    response.sendRedirect("passengerHome.jsp");
                }
            } else {
                // Invalid email or password
                request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
