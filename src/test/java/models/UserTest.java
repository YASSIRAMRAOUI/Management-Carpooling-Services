package models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    private User user;
    
    @Before
    public void setUp() {
        user = new User();
    }
    
    @Test
    public void testDefaultConstructor() {
        User defaultUser = new User();
        assertNotNull(defaultUser);
        assertEquals(0, defaultUser.getUserId());
        assertNull(defaultUser.getName());
        assertNull(defaultUser.getEmail());
        assertNull(defaultUser.getPassword());
        assertNull(defaultUser.getPhoneNumber());
        assertNull(defaultUser.getRole());
    }
    
    @Test
    public void testParameterizedConstructor() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";
        String phoneNumber = "1234567890";
        String role = "passenger";
        
        User paramUser = new User(name, email, password, phoneNumber, role);
        
        assertEquals(name, paramUser.getName());
        assertEquals(email, paramUser.getEmail());
        assertEquals(password, paramUser.getPassword());
        assertEquals(phoneNumber, paramUser.getPhoneNumber());
        assertEquals(role, paramUser.getRole());
        assertEquals(0, paramUser.getUserId()); // userId should be 0 by default
    }
    
    @Test
    public void testSettersAndGetters() {
        int userId = 1;
        String name = "Jane Smith";
        String email = "jane.smith@example.com";
        String password = "securePassword";
        String phoneNumber = "0987654321";
        String role = "driver";
        
        user.setUserId(userId);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        
        assertEquals(userId, user.getUserId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(role, user.getRole());
    }
    
    @Test
    public void testSetNullValues() {
        user.setName(null);
        user.setEmail(null);
        user.setPassword(null);
        user.setPhoneNumber(null);
        user.setRole(null);
        
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getPhoneNumber());
        assertNull(user.getRole());
    }
    
    @Test
    public void testSetEmptyValues() {
        user.setName("");
        user.setEmail("");
        user.setPassword("");
        user.setPhoneNumber("");
        user.setRole("");
        
        assertEquals("", user.getName());
        assertEquals("", user.getEmail());
        assertEquals("", user.getPassword());
        assertEquals("", user.getPhoneNumber());
        assertEquals("", user.getRole());
    }
    
    @Test
    public void testUserIdBoundary() {
        user.setUserId(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, user.getUserId());
        
        user.setUserId(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, user.getUserId());
        
        user.setUserId(0);
        assertEquals(0, user.getUserId());
    }
}