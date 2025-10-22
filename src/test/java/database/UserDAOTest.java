package database;

import models.User;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAOTest {
    private UserDAO userDAO;

    @Before
    public void setUp() throws SQLException {
        userDAO = new UserDAO();
        createTestTables();
    }

    @After
    public void tearDown() throws SQLException {
        dropTestTables();
    }

    private void createTestTables() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Create users table
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "phone_number VARCHAR(20), " +
                    "role VARCHAR(50) NOT NULL)");
            
            // Create password_reset_tokens table
            stmt.execute("CREATE TABLE IF NOT EXISTS password_reset_tokens (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT NOT NULL, " +
                    "token VARCHAR(255) NOT NULL, " +
                    "created_at TIMESTAMP NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(user_id))");
        }
    }

    private void dropTestTables() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS password_reset_tokens");
            stmt.execute("DROP TABLE IF EXISTS users");
        }
    }

    @Test
    public void testRegisterUser() throws SQLException {
        User user = new User("Test User", "test@example.com", "hashedPassword", "1234567890", "driver");
        boolean result = userDAO.registerUser(user);
        
        assertTrue("User should be registered successfully", result);
        assertTrue("User ID should be set after registration", user.getUserId() > 0);
    }

    @Test
    public void testIsEmailRegistered() throws SQLException {
        User user = new User("Test User", "test@example.com", "hashedPassword", "1234567890", "driver");
        userDAO.registerUser(user);
        
        assertTrue("Email should be registered", userDAO.isEmailRegistered("test@example.com"));
        assertFalse("Email should not be registered", userDAO.isEmailRegistered("nonexistent@example.com"));
    }

    @Test
    public void testGetUserByEmail() throws SQLException {
        String hashedPassword = BCrypt.hashpw("password123", BCrypt.gensalt());
        User user = new User("John Doe", "john@example.com", hashedPassword, "9876543210", "passenger");
        userDAO.registerUser(user);
        
        User retrievedUser = userDAO.getUserByEmail("john@example.com");
        
        assertNotNull("User should be found", retrievedUser);
        assertEquals("Name should match", "John Doe", retrievedUser.getName());
        assertEquals("Email should match", "john@example.com", retrievedUser.getEmail());
        assertEquals("Phone number should match", "9876543210", retrievedUser.getPhoneNumber());
        assertEquals("Role should match", "passenger", retrievedUser.getRole());
    }

    @Test
    public void testGetUserByEmailNotFound() throws SQLException {
        User retrievedUser = userDAO.getUserByEmail("nonexistent@example.com");
        assertNull("User should not be found", retrievedUser);
    }

    @Test
    public void testGetUserById() throws SQLException {
        User user = new User("Jane Smith", "jane@example.com", "hashedPassword", "5555555555", "driver");
        userDAO.registerUser(user);
        int userId = user.getUserId();
        
        User retrievedUser = userDAO.getUserById(userId);
        
        assertNotNull("User should be found", retrievedUser);
        assertEquals("User ID should match", userId, retrievedUser.getUserId());
        assertEquals("Name should match", "Jane Smith", retrievedUser.getName());
        assertEquals("Email should match", "jane@example.com", retrievedUser.getEmail());
        assertEquals("Phone number should match", "5555555555", retrievedUser.getPhoneNumber());
        assertEquals("Role should match", "driver", retrievedUser.getRole());
    }

    @Test
    public void testGetUserByIdNotFound() throws SQLException {
        User retrievedUser = userDAO.getUserById(99999);
        assertNull("User should not be found", retrievedUser);
    }

    @Test
    public void testUpdateUser() throws SQLException {
        User user = new User("Original Name", "original@example.com", "password", "1111111111", "driver");
        userDAO.registerUser(user);
        
        user.setName("Updated Name");
        user.setEmail("updated@example.com");
        user.setPhoneNumber("2222222222");
        
        userDAO.updateUser(user);
        
        User updatedUser = userDAO.getUserById(user.getUserId());
        assertEquals("Name should be updated", "Updated Name", updatedUser.getName());
        assertEquals("Email should be updated", "updated@example.com", updatedUser.getEmail());
        assertEquals("Phone number should be updated", "2222222222", updatedUser.getPhoneNumber());
    }

    @Test
    public void testIsEmailDuplicate() throws SQLException {
        User user1 = new User("User 1", "user1@example.com", "password", "1111111111", "driver");
        User user2 = new User("User 2", "user2@example.com", "password", "2222222222", "passenger");
        
        userDAO.registerUser(user1);
        userDAO.registerUser(user2);
        
        // user1 wants to change to user2's email - should be duplicate
        assertTrue("Email should be duplicate for different user", 
                   userDAO.isEmailDuplicate("user2@example.com", user1.getUserId()));
        
        // user1 keeps their own email - should not be duplicate
        assertFalse("Email should not be duplicate for same user", 
                    userDAO.isEmailDuplicate("user1@example.com", user1.getUserId()));
        
        // New email that doesn't exist - should not be duplicate
        assertFalse("Non-existent email should not be duplicate", 
                    userDAO.isEmailDuplicate("new@example.com", user1.getUserId()));
    }

    @Test
    public void testSavePasswordResetToken() throws SQLException {
        User user = new User("Test User", "test@example.com", "password", "1234567890", "driver");
        userDAO.registerUser(user);
        
        String token = "test-reset-token-12345";
        userDAO.savePasswordResetToken(user.getUserId(), token);
        
        // Verify token was saved by trying to verify it
        assertTrue("Token should be valid", userDAO.verifyPasswordResetToken(token));
    }

    @Test
    public void testVerifyPasswordResetTokenInvalid() throws SQLException {
        assertFalse("Invalid token should not be verified", 
                    userDAO.verifyPasswordResetToken("non-existent-token"));
    }

    @Test
    public void testUpdatePassword() throws SQLException {
        User user = new User("Test User", "test@example.com", "oldPassword", "1234567890", "driver");
        userDAO.registerUser(user);
        
        String token = "password-reset-token";
        userDAO.savePasswordResetToken(user.getUserId(), token);
        
        String newPassword = "newPassword123";
        userDAO.updatePassword(token, newPassword);
        
        // Retrieve user and verify password was updated
        User updatedUser = userDAO.getUserByEmail("test@example.com");
        assertTrue("New password should match", BCrypt.checkpw(newPassword, updatedUser.getPassword()));
        
        // Verify token was deleted after use
        assertFalse("Token should be deleted after use", userDAO.verifyPasswordResetToken(token));
    }

    @Test(expected = SQLException.class)
    public void testUpdatePasswordWithInvalidToken() throws SQLException {
        userDAO.updatePassword("invalid-token", "newPassword");
    }

    @Test
    public void testRegisterMultipleUsersWithDifferentRoles() throws SQLException {
        User driver = new User("Driver Name", "driver@example.com", "password", "1111111111", "driver");
        User passenger = new User("Passenger Name", "passenger@example.com", "password", "2222222222", "passenger");
        
        assertTrue("Driver should be registered", userDAO.registerUser(driver));
        assertTrue("Passenger should be registered", userDAO.registerUser(passenger));
        
        User retrievedDriver = userDAO.getUserByEmail("driver@example.com");
        User retrievedPassenger = userDAO.getUserByEmail("passenger@example.com");
        
        assertEquals("Driver role should match", "driver", retrievedDriver.getRole());
        assertEquals("Passenger role should match", "passenger", retrievedPassenger.getRole());
    }
}
