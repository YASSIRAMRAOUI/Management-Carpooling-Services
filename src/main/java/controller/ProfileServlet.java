package controller;

import database.UserDAO;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user_id");

        if (userId == 0) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            User user = userDAO.getUserById(userId);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException("Database error while fetching user profile", e);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user_id");

        if (userId == 0) {
            response.sendRedirect("login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");

        try {
            User user = new User();
            user.setUserId(userId);
            user.setName(name);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);

            userDAO.updateUser(user);
            response.sendRedirect("ProfileServlet");
        } catch (SQLException e) {
            throw new ServletException("Database error while updating user profile", e);
        }
    }
}
