package database;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    @Test
    public void testGetConnection() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            assertNotNull("Connection should not be null", connection);
            assertFalse("Connection should be open", connection.isClosed());
        } catch (SQLException e) {
            fail("Should be able to get a database connection: " + e.getMessage());
        }
    }

    @Test
    public void testMultipleConnections() {
        try (Connection conn1 = DatabaseConnection.getConnection();
             Connection conn2 = DatabaseConnection.getConnection()) {
            
            assertNotNull("First connection should not be null", conn1);
            assertNotNull("Second connection should not be null", conn2);
            assertFalse("First connection should be open", conn1.isClosed());
            assertFalse("Second connection should be open", conn2.isClosed());
            
            // Verify they are different connection objects
            assertNotSame("Connections should be different objects", conn1, conn2);
        } catch (SQLException e) {
            fail("Should be able to get multiple database connections: " + e.getMessage());
        }
    }

    @Test
    public void testConnectionIsValid() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            assertTrue("Connection should be valid", connection.isValid(5));
        } catch (SQLException e) {
            fail("Connection validation failed: " + e.getMessage());
        }
    }

    @Test
    public void testConnectionAutoCommit() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // By default, connections should have auto-commit enabled
            assertTrue("Auto-commit should be enabled by default", connection.getAutoCommit());
        } catch (SQLException e) {
            fail("Failed to check auto-commit: " + e.getMessage());
        }
    }
}
