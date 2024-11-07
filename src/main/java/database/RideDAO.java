package database;

import models.Ride;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // Method to get pending requests by driver
    public int getPendingRequestsByDriver(int driverId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM rides WHERE driver_id = ? AND status = 'Pending'";
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
        String sql = "SELECT * FROM rides WHERE driver_id = ? ORDER BY date DESC LIMIT 5";
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
                    ride.setDestination(resultSet.getString("destination"));
                    ride.setStatus(resultSet.getString("status"));
                    ride.setFare(resultSet.getDouble("fare"));
                    rides.add(ride);
                }
            }
        }
        return rides;
    }

    // Method to create a new ride
    public void createRide(Ride ride) throws SQLException {
        String sql = "INSERT INTO rides (driver_id, date, destination, status, fare) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ride.getDriverId());
            statement.setDate(2, ride.getDate());
            statement.setString(3, ride.getDestination());
            statement.setString(4, ride.getStatus());
            statement.setDouble(5, ride.getFare());
            statement.executeUpdate();
        }
    }

    // Method to get pending ride requests for a driver
    public List<Ride> getRideRequestsByDriver(int driverId) throws SQLException {
        String sql = "SELECT r.*, u.name AS passenger_name FROM rides r " +
                "JOIN users u ON r.passenger_id = u.user_id " +
                "WHERE r.driver_id = ?";
        List<Ride> rideRequests = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, driverId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ride ride = new Ride();
                    ride.setId(resultSet.getInt("id"));
                    ride.setDriverId(resultSet.getInt("driver_id"));
                    ride.setDate(resultSet.getDate("date"));
                    ride.setDestination(resultSet.getString("destination"));
                    ride.setStatus(resultSet.getString("status"));
                    ride.setPassengerName(resultSet.getString("passenger_name"));
                    rideRequests.add(ride);
                }
            }

        }
        return rideRequests;
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
}
