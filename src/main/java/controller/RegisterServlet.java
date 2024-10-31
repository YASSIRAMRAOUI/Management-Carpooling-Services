package controller;

import database.UserDAO;
import models.User;
// import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    // Regular expression pattern for validating email format
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$");

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phone_number").trim();
        String role = request.getParameter("role");

        // Check if email is in a valid format
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            request.setAttribute("errorMessage", "Invalid email format. Please enter a valid email.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Check if email is already registered
        try {
            if (userDAO.isEmailRegistered(email)) {
                request.setAttribute("errorMessage", "Email is already registered. Please use a different email.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Hash the password
        // String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Create a User object
        User user = new User(name, email, password, phoneNumber, role);

        // Register the user in the database
        try {
            if (userDAO.registerUser(user)) {
                // Registration successful, redirect to login page
                response.sendRedirect("login.jsp");
            } else {
                // Registration failed, show an error message
                request.setAttribute("errorMessage", "Error registering user. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
