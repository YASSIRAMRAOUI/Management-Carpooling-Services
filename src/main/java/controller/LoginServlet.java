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
        String remember = request.getParameter("remember");

        try {
            User user = userDAO.getUserByEmail(email);

            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user_id", user.getUserId());
                session.setAttribute("name", user.getName());
                session.setAttribute("role", user.getRole());

                if ("on".equals(remember)) {
                    Cookie emailCookie = new Cookie("rememberedEmail", email);
                    emailCookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(emailCookie);
                } else {
                    Cookie emailCookie = new Cookie("rememberedEmail", "");
                    emailCookie.setMaxAge(0);
                    response.addCookie(emailCookie);
                }

                if ("Driver".equals(user.getRole())) {
                    response.sendRedirect("DriverHomeServlet");
                } else {
                    response.sendRedirect("PassengerHomeServlet");
                }
            } else {
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
