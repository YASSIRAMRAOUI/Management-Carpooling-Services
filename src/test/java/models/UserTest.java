package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testDefaultConstructor() {
        User user = new User();
        assertNotNull("User object should not be null", user);
        assertEquals("UserId should be 0 by default", 0, user.getUserId());
        assertNull("Name should be null by default", user.getName());
        assertNull("Email should be null by default", user.getEmail());
        assertNull("Password should be null by default", user.getPassword());
        assertNull("PhoneNumber should be null by default", user.getPhoneNumber());
        assertNull("Role should be null by default", user.getRole());
    }

    @Test
    public void testParameterizedConstructor() {
        User user = new User("John Doe", "john@example.com", "password123", "1234567890", "driver");
        
        assertNotNull("User object should not be null", user);
        assertEquals("Name should match", "John Doe", user.getName());
        assertEquals("Email should match", "john@example.com", user.getEmail());
        assertEquals("Password should match", "password123", user.getPassword());
        assertEquals("PhoneNumber should match", "1234567890", user.getPhoneNumber());
        assertEquals("Role should match", "driver", user.getRole());
    }

    @Test
    public void testSettersAndGetters() {
        User user = new User();
        
        user.setUserId(1);
        assertEquals("UserId should be set correctly", 1, user.getUserId());
        
        user.setName("Jane Smith");
        assertEquals("Name should be set correctly", "Jane Smith", user.getName());
        
        user.setEmail("jane@example.com");
        assertEquals("Email should be set correctly", "jane@example.com", user.getEmail());
        
        user.setPassword("securePass");
        assertEquals("Password should be set correctly", "securePass", user.getPassword());
        
        user.setPhoneNumber("9876543210");
        assertEquals("PhoneNumber should be set correctly", "9876543210", user.getPhoneNumber());
        
        user.setRole("passenger");
        assertEquals("Role should be set correctly", "passenger", user.getRole());
    }

    @Test
    public void testDriverRole() {
        User driver = new User("Driver Name", "driver@example.com", "pass", "1111111111", "driver");
        assertEquals("Role should be driver", "driver", driver.getRole());
    }

    @Test
    public void testPassengerRole() {
        User passenger = new User("Passenger Name", "passenger@example.com", "pass", "2222222222", "passenger");
        assertEquals("Role should be passenger", "passenger", passenger.getRole());
    }

    @Test
    public void testUserWithEmptyValues() {
        User user = new User("", "", "", "", "");
        assertEquals("Empty name should be allowed", "", user.getName());
        assertEquals("Empty email should be allowed", "", user.getEmail());
        assertEquals("Empty password should be allowed", "", user.getPassword());
        assertEquals("Empty phoneNumber should be allowed", "", user.getPhoneNumber());
        assertEquals("Empty role should be allowed", "", user.getRole());
    }

    @Test
    public void testUserIdUpdate() {
        User user = new User();
        user.setUserId(100);
        assertEquals("UserId should be updatable", 100, user.getUserId());
        
        user.setUserId(200);
        assertEquals("UserId should be updatable multiple times", 200, user.getUserId());
    }
}
