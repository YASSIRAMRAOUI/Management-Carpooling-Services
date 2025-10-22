package models;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Date;

public class RideTest {

    @Test
    public void testDefaultConstructor() {
        Ride ride = new Ride();
        assertNotNull("Ride object should not be null", ride);
        assertEquals("Id should be 0 by default", 0, ride.getId());
        assertEquals("DriverId should be 0 by default", 0, ride.getDriverId());
        assertNull("Date should be null by default", ride.getDate());
        assertNull("Depart should be null by default", ride.getDepart());
        assertNull("Destination should be null by default", ride.getDestination());
        assertNull("Status should be null by default", ride.getStatus());
        assertEquals("Fare should be 0.0 by default", 0.0, ride.getFare(), 0.001);
    }

    @Test
    public void testParameterizedConstructor() {
        Date date = Date.valueOf("2025-10-23");
        Ride ride = new Ride(1, 100, date, "City A", "City B", "In Progress", 25.50, 
                           "John Passenger", 4, "1234567890", "Jane Driver", 2);
        
        assertEquals("Id should match", 1, ride.getId());
        assertEquals("DriverId should match", 100, ride.getDriverId());
        assertEquals("Date should match", date, ride.getDate());
        assertEquals("Depart should match", "City A", ride.getDepart());
        assertEquals("Destination should match", "City B", ride.getDestination());
        assertEquals("Status should match", "In Progress", ride.getStatus());
        assertEquals("Fare should match", 25.50, ride.getFare(), 0.001);
        assertEquals("PassengerName should match", "John Passenger", ride.getPassengerName());
        assertEquals("NumberOfPlaces should match", 4, ride.getNumberOfPlaces());
        assertEquals("PassengerPhone should match", "1234567890", ride.getPassengerPhone());
        assertEquals("DriverName should match", "Jane Driver", ride.getDriverName());
        assertEquals("Place should match", 2, ride.getPlace());
    }

    @Test
    public void testSettersAndGetters() {
        Ride ride = new Ride();
        
        ride.setId(5);
        assertEquals("Id should be set correctly", 5, ride.getId());
        
        ride.setDriverId(200);
        assertEquals("DriverId should be set correctly", 200, ride.getDriverId());
        
        Date date = Date.valueOf("2025-11-01");
        ride.setDate(date);
        assertEquals("Date should be set correctly", date, ride.getDate());
        
        ride.setDepart("Downtown");
        assertEquals("Depart should be set correctly", "Downtown", ride.getDepart());
        
        ride.setDestination("Airport");
        assertEquals("Destination should be set correctly", "Airport", ride.getDestination());
        
        ride.setStatus("Completed");
        assertEquals("Status should be set correctly", "Completed", ride.getStatus());
        
        ride.setFare(30.75);
        assertEquals("Fare should be set correctly", 30.75, ride.getFare(), 0.001);
        
        ride.setPassengerName("Test Passenger");
        assertEquals("PassengerName should be set correctly", "Test Passenger", ride.getPassengerName());
        
        ride.setNumberOfPlaces(3);
        assertEquals("NumberOfPlaces should be set correctly", 3, ride.getNumberOfPlaces());
        
        ride.setPassengerPhone("9999999999");
        assertEquals("PassengerPhone should be set correctly", "9999999999", ride.getPassengerPhone());
        
        ride.setDriverName("Test Driver");
        assertEquals("DriverName should be set correctly", "Test Driver", ride.getDriverName());
        
        ride.setPlace(1);
        assertEquals("Place should be set correctly", 1, ride.getPlace());
    }

    @Test
    public void testRideStatuses() {
        Ride ride = new Ride();
        
        ride.setStatus("In Progress");
        assertEquals("Status should be 'In Progress'", "In Progress", ride.getStatus());
        
        ride.setStatus("Completed");
        assertEquals("Status should be 'Completed'", "Completed", ride.getStatus());
        
        ride.setStatus("Cancelled");
        assertEquals("Status should be 'Cancelled'", "Cancelled", ride.getStatus());
    }

    @Test
    public void testFareCalculations() {
        Ride ride = new Ride();
        
        ride.setFare(10.00);
        assertEquals("Fare should be 10.00", 10.00, ride.getFare(), 0.001);
        
        ride.setFare(0.0);
        assertEquals("Fare can be 0.0", 0.0, ride.getFare(), 0.001);
        
        ride.setFare(999.99);
        assertEquals("Fare should handle large values", 999.99, ride.getFare(), 0.001);
    }

    @Test
    public void testNumberOfPlaces() {
        Ride ride = new Ride();
        
        ride.setNumberOfPlaces(4);
        assertEquals("NumberOfPlaces should be 4", 4, ride.getNumberOfPlaces());
        
        ride.setNumberOfPlaces(1);
        assertEquals("NumberOfPlaces should be 1", 1, ride.getNumberOfPlaces());
        
        ride.setNumberOfPlaces(0);
        assertEquals("NumberOfPlaces can be 0", 0, ride.getNumberOfPlaces());
    }

    @Test
    public void testDateHandling() {
        Ride ride = new Ride();
        
        Date today = Date.valueOf("2025-10-22");
        ride.setDate(today);
        assertEquals("Date should be set to today", today, ride.getDate());
        
        Date future = Date.valueOf("2025-12-31");
        ride.setDate(future);
        assertEquals("Date should be set to future date", future, ride.getDate());
    }
}
