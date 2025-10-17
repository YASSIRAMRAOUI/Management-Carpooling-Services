package models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Date;
import java.time.LocalDate;

public class RideTest {
    
    private Ride ride;
    
    @Before
    public void setUp() {
        ride = new Ride();
    }
    
    @Test
    public void testDefaultConstructor() {
        Ride defaultRide = new Ride();
        assertNotNull(defaultRide);
        assertEquals(0, defaultRide.getId());
        assertEquals(0, defaultRide.getDriverId());
        assertNull(defaultRide.getDate());
        assertNull(defaultRide.getDepart());
        assertNull(defaultRide.getDestination());
        assertNull(defaultRide.getStatus());
        assertEquals(0.0, defaultRide.getFare(), 0.001);
        assertNull(defaultRide.getPassengerName());
        assertEquals(0, defaultRide.getNumberOfPlaces());
        assertNull(defaultRide.getpassengerPhone());
        assertNull(defaultRide.getDriverName());
        assertEquals(0, defaultRide.getPlace());
    }
    
    @Test
    public void testParameterizedConstructor() {
        int id = 1;
        int driverId = 100;
        Date date = Date.valueOf(LocalDate.now());
        String depart = "City A";
        String destination = "City B";
        String status = "available";
        double fare = 25.50;
        String passengerName = "John Passenger";
        int numberOfPlaces = 4;
        String passengerPhone = "1234567890";
        String driverName = "Jane Driver";
        int place = 2;
        
        Ride paramRide = new Ride(id, driverId, date, depart, destination, status, 
                                 fare, passengerName, numberOfPlaces, passengerPhone, 
                                 driverName, place);
        
        assertEquals(id, paramRide.getId());
        assertEquals(driverId, paramRide.getDriverId());
        assertEquals(date, paramRide.getDate());
        assertEquals(depart, paramRide.getDepart());
        assertEquals(destination, paramRide.getDestination());
        assertEquals(status, paramRide.getStatus());
        assertEquals(fare, paramRide.getFare(), 0.001);
        assertEquals(passengerName, paramRide.getPassengerName());
        assertEquals(numberOfPlaces, paramRide.getNumberOfPlaces());
        assertEquals(passengerPhone, paramRide.getpassengerPhone());
        assertEquals(driverName, paramRide.getDriverName());
        assertEquals(place, paramRide.getPlace());
    }
    
    @Test
    public void testSettersAndGetters() {
        int id = 5;
        int driverId = 200;
        Date date = Date.valueOf("2024-12-25");
        String depart = "Paris";
        String destination = "Lyon";
        String status = "booked";
        double fare = 35.75;
        String passengerName = "Alice Smith";
        int numberOfPlaces = 3;
        String passengerPhone = "0987654321";
        String driverName = "Bob Driver";
        int place = 1;
        
        ride.setId(id);
        ride.setDriverId(driverId);
        ride.setDate(date);
        ride.setDepart(depart);
        ride.setDestination(destination);
        ride.setStatus(status);
        ride.setFare(fare);
        ride.setPassengerName(passengerName);
        ride.setNumberOfPlaces(numberOfPlaces);
        ride.setPassengerPhone(passengerPhone);
        ride.setDriverName(driverName);
        ride.setPlace(place);
        
        assertEquals(id, ride.getId());
        assertEquals(driverId, ride.getDriverId());
        assertEquals(date, ride.getDate());
        assertEquals(depart, ride.getDepart());
        assertEquals(destination, ride.getDestination());
        assertEquals(status, ride.getStatus());
        assertEquals(fare, ride.getFare(), 0.001);
        assertEquals(passengerName, ride.getPassengerName());
        assertEquals(numberOfPlaces, ride.getNumberOfPlaces());
        assertEquals(passengerPhone, ride.getpassengerPhone());
        assertEquals(driverName, ride.getDriverName());
        assertEquals(place, ride.getPlace());
    }
    
    @Test
    public void testNullValues() {
        ride.setDate(null);
        ride.setDepart(null);
        ride.setDestination(null);
        ride.setStatus(null);
        ride.setPassengerName(null);
        ride.setPassengerPhone(null);
        ride.setDriverName(null);
        
        assertNull(ride.getDate());
        assertNull(ride.getDepart());
        assertNull(ride.getDestination());
        assertNull(ride.getStatus());
        assertNull(ride.getPassengerName());
        assertNull(ride.getpassengerPhone());
        assertNull(ride.getDriverName());
    }
    
    @Test
    public void testFareBoundaryValues() {
        // Test zero fare
        ride.setFare(0.0);
        assertEquals(0.0, ride.getFare(), 0.001);
        
        // Test negative fare
        ride.setFare(-10.50);
        assertEquals(-10.50, ride.getFare(), 0.001);
        
        // Test large fare
        ride.setFare(999.99);
        assertEquals(999.99, ride.getFare(), 0.001);
    }
    
    @Test
    public void testPlacesBoundaryValues() {
        // Test zero places
        ride.setNumberOfPlaces(0);
        assertEquals(0, ride.getNumberOfPlaces());
        
        // Test negative places
        ride.setNumberOfPlaces(-1);
        assertEquals(-1, ride.getNumberOfPlaces());
        
        // Test large number of places
        ride.setNumberOfPlaces(10);
        assertEquals(10, ride.getNumberOfPlaces());
    }
    
    @Test
    public void testDateHandling() {
        Date today = Date.valueOf(LocalDate.now());
        Date future = Date.valueOf(LocalDate.now().plusDays(7));
        Date past = Date.valueOf(LocalDate.now().minusDays(7));
        
        ride.setDate(today);
        assertEquals(today, ride.getDate());
        
        ride.setDate(future);
        assertEquals(future, ride.getDate());
        
        ride.setDate(past);
        assertEquals(past, ride.getDate());
    }
}