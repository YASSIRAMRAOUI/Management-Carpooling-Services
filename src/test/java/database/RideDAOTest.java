package database;

import models.Ride;
import models.User;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class RideDAOTest {
    private RideDAO rideDAO;
    private UserDAO userDAO;
    private int testDriverId;
    private int testPassengerId;

    @Before
    public void setUp() throws SQLException {
        rideDAO = new RideDAO();
        userDAO = new UserDAO();
        createTestTables();
        createTestUsers();
    }

    @After
    public void tearDown() throws SQLException {
        dropTestTables();
    }

    private void createTestTables() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "phone_number VARCHAR(20), " +
                    "role VARCHAR(50) NOT NULL)");
            
            stmt.execute("CREATE TABLE IF NOT EXISTS rides (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "driver_id INT NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "depart VARCHAR(255) NOT NULL, " +
                    "destination VARCHAR(255) NOT NULL, " +
                    "fare DOUBLE NOT NULL, " +
                    "number_of_places INT NOT NULL, " +
                    "status VARCHAR(50) NOT NULL, " +
                    "FOREIGN KEY (driver_id) REFERENCES users(user_id))");
            
            stmt.execute("CREATE TABLE IF NOT EXISTS ride_requests (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "ride_id INT NOT NULL, " +
                    "passenger_id INT NOT NULL, " +
                    "status VARCHAR(50) NOT NULL, " +
                    "place INT DEFAULT 1, " +
                    "FOREIGN KEY (ride_id) REFERENCES rides(id), " +
                    "FOREIGN KEY (passenger_id) REFERENCES users(user_id))");
        }
    }

    private void createTestUsers() throws SQLException {
        User driver = new User("Test Driver", "driver@test.com", "password", "1234567890", "driver");
        User passenger = new User("Test Passenger", "passenger@test.com", "password", "0987654321", "passenger");
        
        userDAO.registerUser(driver);
        userDAO.registerUser(passenger);
        
        testDriverId = driver.getUserId();
        testPassengerId = passenger.getUserId();
    }

    private void dropTestTables() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS ride_requests");
            stmt.execute("DROP TABLE IF EXISTS rides");
            stmt.execute("DROP TABLE IF EXISTS users");
        }
    }

    @Test
    public void testCreateRide() throws SQLException {
        Ride ride = new Ride();
        ride.setDriverId(testDriverId);
        ride.setDate(Date.valueOf(LocalDate.now().plusDays(1)));
        ride.setDepart("City A");
        ride.setDestination("City B");
        ride.setFare(25.50);
        ride.setNumberOfPlaces(4);
        ride.setStatus("In Progress");
        
        rideDAO.createRide(ride);
        
        List<Ride> rides = rideDAO.getRidesByDriver(testDriverId);
        assertFalse("Rides list should not be empty", rides.isEmpty());
        assertEquals("Should have one ride", 1, rides.size());
    }

    @Test
    public void testGetTotalRidesByDriver() throws SQLException {
        createTestRide(testDriverId, LocalDate.now().plusDays(1), "In Progress");
        createTestRide(testDriverId, LocalDate.now().plusDays(2), "Completed");
        
        int totalRides = rideDAO.getTotalRidesByDriver(testDriverId);
        assertEquals("Should have 2 rides", 2, totalRides);
    }

    @Test
    public void testGetEarningsByDriver() throws SQLException {
        createTestRide(testDriverId, LocalDate.now(), "Completed", 50.0);
        createTestRide(testDriverId, LocalDate.now().plusDays(1), "Completed", 30.0);
        createTestRide(testDriverId, LocalDate.now().plusDays(2), "In Progress", 20.0);
        
        double earnings = rideDAO.getEarningsByDriver(testDriverId);
        assertEquals("Earnings should be 80.0", 80.0, earnings, 0.01);
    }

    @Test
    public void testGetRidesByDriver() throws SQLException {
        createTestRide(testDriverId, LocalDate.now().plusDays(1), "In Progress");
        createTestRide(testDriverId, LocalDate.now().plusDays(2), "In Progress");
        
        List<Ride> rides = rideDAO.getRidesByDriver(testDriverId);
        assertEquals("Should have 2 rides", 2, rides.size());
    }

    @Test
    public void testUpdateRideStatus() throws SQLException {
        int rideId = createTestRide(testDriverId, LocalDate.now().plusDays(1), "In Progress");
        
        rideDAO.updateRideStatus(rideId, "Completed");
        
        List<Ride> rides = rideDAO.getRidesByDriver(testDriverId);
        assertEquals("Status should be updated", "Completed", rides.get(0).getStatus());
    }

    @Test
    public void testDeleteRide() throws SQLException {
        int rideId = createTestRide(testDriverId, LocalDate.now().plusDays(1), "In Progress");
        
        rideDAO.deleteRide(rideId);
        
        List<Ride> rides = rideDAO.getRidesByDriver(testDriverId);
        assertTrue("Rides list should be empty", rides.isEmpty());
    }

    @Test
    public void testGetPendingRequestsByDriver() throws SQLException {
        createTestRide(testDriverId, LocalDate.now().plusDays(1), "In Progress");
        createTestRide(testDriverId, LocalDate.now().plusDays(2), "In Progress");
        createTestRide(testDriverId, LocalDate.now().plusDays(3), "Completed");
        
        int pendingRequests = rideDAO.getPendingRequestsByDriver(testDriverId);
        assertEquals("Should have 2 pending requests", 2, pendingRequests);
    }

    @Test
    public void testGetAvailableRides() throws SQLException {
        createTestRide(testDriverId, LocalDate.now().plusDays(1), "In Progress");
        createTestRide(testDriverId, LocalDate.now().plusDays(2), "Completed");
        
        List<Ride> availableRides = rideDAO.getAvailableRides();
        assertEquals("Should have 1 available ride", 1, availableRides.size());
        assertEquals("Status should be In Progress", "In Progress", availableRides.get(0).getStatus());
    }

    @Test
    public void testAcceptRide() throws SQLException {
        int rideId = createTestRide(testDriverId, LocalDate.now().plusDays(1), "In Progress", 25.0, 4);
        
        rideDAO.acceptRide(testPassengerId, rideId, 2);
        
        List<Ride> rides = rideDAO.getRidesByDriver(testDriverId);
        assertEquals("Number of places should be reduced by 2", 2, rides.get(0).getNumberOfPlaces());
    }

    @Test
    public void testAcceptRideFullCapacity() throws SQLException {
        int rideId = createTestRide(testDriverId, LocalDate.now().plusDays(1), "In Progress", 25.0, 2);
        
        rideDAO.acceptRide(testPassengerId, rideId, 2);
        
        List<Ride> rides = rideDAO.getRidesByDriver(testDriverId);
        assertEquals("Status should be Completed when full", "Completed", rides.get(0).getStatus());
        assertEquals("Number of places should be 0", 0, rides.get(0).getNumberOfPlaces());
    }

    @Test
    public void testDeclineRide() throws SQLException {
        int rideId = createTestRide(testDriverId, LocalDate.now().plusDays(1), "In Progress");
        
        rideDAO.declineRide(testPassengerId, rideId);
        
        // Verify ride request was created with Declined status
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT status FROM ride_requests WHERE ride_id = ? AND passenger_id = ?")) {
            stmt.setInt(1, rideId);
            stmt.setInt(2, testPassengerId);
            ResultSet rs = stmt.executeQuery();
            assertTrue("Request should exist", rs.next());
            assertEquals("Status should be Declined", "Declined", rs.getString("status"));
        }
    }

    @Test
    public void testGetRideHistoryByPassenger() throws SQLException {
        int rideId = createTestRide(testDriverId, LocalDate.now(), "Completed");
        createRideRequest(rideId, testPassengerId, "Accepted");
        
        List<Ride> history = rideDAO.getRideHistoryByPassenger(testPassengerId);
        assertFalse("History should not be empty", history.isEmpty());
        assertEquals("Should have one ride in history", 1, history.size());
    }

    @Test
    public void testUpdateExpiredRides() throws SQLException {
        // Create a ride with past date
        createTestRide(testDriverId, LocalDate.now().minusDays(1), "In Progress");
        
        rideDAO.updateExpiredRides();
        
        List<Ride> rides = rideDAO.getRidesByDriver(testDriverId);
        assertEquals("Expired ride should be marked as Completed", "Completed", rides.get(0).getStatus());
    }

    // Helper methods
    private int createTestRide(int driverId, LocalDate date, String status) throws SQLException {
        return createTestRide(driverId, date, status, 25.0, 4);
    }

    private int createTestRide(int driverId, LocalDate date, String status, double fare) throws SQLException {
        return createTestRide(driverId, date, status, fare, 4);
    }

    private int createTestRide(int driverId, LocalDate date, String status, double fare, int places) throws SQLException {
        Ride ride = new Ride();
        ride.setDriverId(driverId);
        ride.setDate(Date.valueOf(date));
        ride.setDepart("City A");
        ride.setDestination("City B");
        ride.setFare(fare);
        ride.setNumberOfPlaces(places);
        ride.setStatus(status);
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO rides (driver_id, date, depart, destination, fare, number_of_places, status) VALUES (?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, ride.getDriverId());
            stmt.setDate(2, ride.getDate());
            stmt.setString(3, ride.getDepart());
            stmt.setString(4, ride.getDestination());
            stmt.setDouble(5, ride.getFare());
            stmt.setInt(6, ride.getNumberOfPlaces());
            stmt.setString(7, ride.getStatus());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    private void createRideRequest(int rideId, int passengerId, String status) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO ride_requests (ride_id, passenger_id, status) VALUES (?, ?, ?)")) {
            stmt.setInt(1, rideId);
            stmt.setInt(2, passengerId);
            stmt.setString(3, status);
            stmt.executeUpdate();
        }
    }
}
