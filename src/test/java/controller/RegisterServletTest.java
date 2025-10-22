package controller;

import database.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class RegisterServletTest {

    private RegisterServlet registerServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private UserDAO userDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registerServlet = new RegisterServlet();
    }

    @Test
    public void testSuccessfulRegistration() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("email")).thenReturn("john@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getParameter("phone_number")).thenReturn("1234567890");
        when(request.getParameter("role")).thenReturn("driver");
    }

    @Test
    public void testRegistrationWithInvalidEmail() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("email")).thenReturn("invalidemail");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getParameter("phone_number")).thenReturn("1234567890");
        when(request.getParameter("role")).thenReturn("driver");
        when(request.getRequestDispatcher("register.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    public void testRegistrationWithExistingEmail() throws ServletException, IOException, SQLException {
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("email")).thenReturn("existing@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getParameter("phone_number")).thenReturn("1234567890");
        when(request.getParameter("role")).thenReturn("driver");
        when(request.getRequestDispatcher("register.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    public void testRegistrationWithEmptyFields() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("password")).thenReturn("");
        when(request.getParameter("phone_number")).thenReturn("");
        when(request.getParameter("role")).thenReturn("");
        when(request.getRequestDispatcher("register.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    public void testRegistrationAsPassenger() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("Jane Passenger");
        when(request.getParameter("email")).thenReturn("jane@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getParameter("phone_number")).thenReturn("0987654321");
        when(request.getParameter("role")).thenReturn("passenger");
    }

    @Test
    public void testRegistrationAsDriver() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("John Driver");
        when(request.getParameter("email")).thenReturn("john.driver@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getParameter("phone_number")).thenReturn("1234567890");
        when(request.getParameter("role")).thenReturn("driver");
    }
}
