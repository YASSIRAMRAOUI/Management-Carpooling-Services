package database;

import models.Ride;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class RideDAO {

    // Method to get total rides by driver
    public int getTotalRidesByDriver(int driverId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM rides WHERE driver_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, driverId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        return 0;
    }

    // Method to get total earnings by driver
    public double getEarningsByDriver(int driverId) throws SQLException {
        String sql = "SELECT SUM(fare) FROM rides WHERE driver_id = ? AND status = 'Completed'";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, driverId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        }
        return 0.0;
    }

    // Method to get recent rides by driver
    public List<Ride> getRecentRidesByDriver(int driverId) throws SQLException {
        String sql = "SELECT r.*, u.name AS passenger_name, u.phone_number AS passenger_phone FROM rides r " +
                "JOIN ride_requests rr ON r.id = rr.ride_id " +
                "JOIN users u ON rr.passenger_id = u.user_id " +
                "WHERE r.driver_id = ? ORDER BY r.date DESC LIMIT 10";
        List<Ride> rides = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, driverId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ride ride = new Ride();
                    ride.setId(resultSet.getInt("id"));
                    ride.setDriverId(resultSet.getInt("driver_id"));
                    ride.setDate(resultSet.getDate("date"));
                    ride.setDepart(resultSet.getString("depart"));
                    ride.setDestination(resultSet.getString("destination"));
                    ride.setStatus(resultSet.getString("status"));
                    ride.setFare(resultSet.getDouble("fare"));
                    ride.setPassengerName(resultSet.getString("passenger_name"));
                    ride.setPassengerPhone(resultSet.getString("passenger_phone"));
                    rides.add(ride);
                }
            }
        }
        return rides;
    }

    // Method to create a new ride
    public void createRide(Ride ride) throws SQLException {
        String sql = "INSERT INTO rides (driver_id, date, depart, destination, fare, number_of_places, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ride.getDriverId());
            statement.setDate(2, ride.getDate());
            statement.setString(3, ride.getDepart());
            statement.setString(4, ride.getDestination());
            statement.setDouble(5, ride.getFare());
            statement.setInt(6, ride.getNumberOfPlaces());
            statement.setString(7, ride.getStatus());
            statement.executeUpdate();
        }

    }

    // Method to get all ride requests for a driver
    public List<Ride> getAllRides() throws SQLException {
        String sql = "SELECT id, driver_id, date, depart, destination, number_of_places, fare, status FROM rides";
        List<Ride> allRides = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Ride ride = new Ride();
                ride.setId(resultSet.getInt("id"));
                ride.setDriverId(resultSet.getInt("driver_id"));
                ride.setDate(resultSet.getDate("date"));
                ride.setDepart(resultSet.getString("depart"));
                ride.setDestination(resultSet.getString("destination"));
                ride.setNumberOfPlaces(resultSet.getInt("number_of_places"));
                ride.setFare(resultSet.getDouble("fare"));
                ride.setStatus(resultSet.getString("status"));
                allRides.add(ride);
            }
        }

        return allRides;
    }

    // Method to update the status of a ride request
    public void updateRideRequestStatus(int requestId, String status) throws SQLException {
        String sql = "UPDATE rides SET status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, requestId);
            statement.executeUpdate();
        }
    }

    // Method to get pending ride requests for a driver
    public int getPendingRequestsByDriver(int driverId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM rides WHERE driver_id = ? AND status = 'In Progress'";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, driverId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        return 0;
    }

    // Get rides created by a driver
    public List<Ride> getRidesByDriver(int driverId) throws SQLException {
        String sql = "SELECT * FROM rides WHERE driver_id = ?";
        List<Ride> rides = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, driverId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ride ride = new Ride();
                    ride.setId(resultSet.getInt("id"));
                    ride.setDriverId(resultSet.getInt("driver_id"));
                    ride.setDate(resultSet.getDate("date"));
                    ride.setDepart(resultSet.getString("depart"));
                    ride.setDestination(resultSet.getString("destination"));
                    ride.setFare(resultSet.getDouble("fare"));
                    ride.setNumberOfPlaces(resultSet.getInt("number_of_places"));
                    ride.setStatus(resultSet.getString("status"));
                    rides.add(ride);
                }
            }
        }
        return rides;
    }

    // Update ride status
    public void updateRideStatus(int rideId, String status) throws SQLException {
        String sql = "UPDATE rides SET status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, rideId);
            statement.executeUpdate();
        }
    }

    // Delete a ride
    public void deleteRide(int rideId) throws SQLException {
        String sql = "DELETE FROM rides WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rideId);
            statement.executeUpdate();
        }
    }

    // Update expired rides
    public void updateExpiredRides() throws SQLException {
        String sql = "UPDATE rides r " +
                "SET r.status = 'Completed' " +
                "WHERE r.status = 'In Progress' " +
                "AND (r.date < ? OR " +
                "(SELECT COUNT(*) FROM ride_requests rr WHERE rr.ride_id = r.id AND rr.status = 'Accepted') >= r.number_of_places)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, Date.valueOf(LocalDate.now()));
            int affectedRows = statement.executeUpdate();

            System.out.println("Updated " + affectedRows + " expired rides to 'Completed' status.");
        }
    }
}
