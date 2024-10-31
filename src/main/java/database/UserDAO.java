package database;

import models.User;
import java.sql.*;

public class UserDAO {

    // Method to register a new user
    public boolean registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, phone_number, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword()); // Store hashed password
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getRole());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                }
                return true;
            }
            return false;
        }
    }

    // Method to retrieve a user by email (used for login)
    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setPhoneNumber(resultSet.getString("phone_number"));
                    user.setRole(resultSet.getString("role"));
                    return user;
                }
            }
        }
        return null;
    }

    // Method to check if an email is already registered
    public boolean isEmailRegistered(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Returns true if count > 0
                }
            }
        }
        return false;
    }
}
