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

                String message = (String) session.getAttribute("message");
                if (message != null) {
                    request.setAttribute("message", message);
                    session.removeAttribute("message");
                }

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

            if (userDAO.isEmailDuplicate(email, userId)) {
                request.setAttribute("errorMessage", "The email address is already associated with another account.");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            }

            User user = new User();
            user.setUserId(userId);
            user.setName(name);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);

            userDAO.updateUser(user);

            session.setAttribute("name", name);
            session.setAttribute("email", email);
            session.setAttribute("phone_number", phoneNumber);

            session.setAttribute("message", "Profile updated successfully.");
            response.sendRedirect("ProfileServlet");
        } catch (SQLException e) {
            throw new ServletException("Database error while updating user profile", e);
        }
    }
}
