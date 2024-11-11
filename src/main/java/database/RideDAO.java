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
                "WHERE r.driver_id = ? ORDER BY r.date DESC LIMIT 5";
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
        String sql = "SELECT r.id, r.driver_id, r.date, r.depart, r.destination, r.number_of_places, r.fare, r.status, u.name AS passenger_name "
                +
                "FROM rides r " +
                "JOIN ride_requests rr ON r.id = rr.ride_id " +
                "JOIN users u ON rr.passenger_id = u.user_id";
        List<Ride> allRides = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Ride ride = new Ride();
                ride.setId(resultSet.getInt("id"));
                ride.setDriverId(resultSet.getInt("driver_id"));
                ride.setDate(resultSet.getDate("date"));
                ride.setDepart(resultSet.getString("depart")); // Ensure this line is present
                ride.setDestination(resultSet.getString("destination"));
                ride.setNumberOfPlaces(resultSet.getInt("number_of_places"));
                ride.setFare(resultSet.getDouble("fare"));
                ride.setStatus(resultSet.getString("status"));
                ride.setPassengerName(resultSet.getString("passenger_name"));
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

    public List<Ride> getAvailableRidesForPassenger(int passengerId) throws SQLException {
        String sql = "SELECT r.id, r.driver_id, r.date, r.depart, r.destination, r.number_of_places, r.status, r.fare, u.name AS driver_name " +
                "FROM rides r " +
                "JOIN ride_requests rr ON r.id = rr.ride_id " +
                "JOIN users u ON r.driver_id = u.user_id " +
                "WHERE rr.passenger_id = ? AND r.status IN ('In Progress', 'Pending') AND r.number_of_places > 0";
        List<Ride> availableRides = new ArrayList<>();

        // Establish a connection to the database and execute the query
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, passengerId);  // Set the passengerId in the query

            try (ResultSet resultSet = statement.executeQuery()) {
                // Process the result set and populate the Ride objects
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
                    ride.setPassengerName(resultSet.getString("driver_name"));
                    availableRides.add(ride);  // Add the ride to the list
                }
            }
        }
        return availableRides;  // Return the list of available rides
    }
    public void acceptRide(int passengerId, int rideId) throws SQLException {
        // First, we need to ensure that the passenger has requested this ride
        String checkRequestSql = "SELECT * FROM ride_requests WHERE passenger_id = ? AND ride_id = ? AND status = 'Pending'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(checkRequestSql)) {

            statement.setInt(1, passengerId);  // Set the passengerId
            statement.setInt(2, rideId);  // Set the rideId

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // The ride request exists and is pending, so we can proceed with accepting the ride

                    // Update the ride status to 'Accepted'
                    String updateRideStatusSql = "UPDATE rides SET status = 'Accepted', number_of_places = number_of_places - 1 WHERE id = ? AND number_of_places > 0";

                    try (PreparedStatement updateStatement = connection.prepareStatement(updateRideStatusSql)) {
                        updateStatement.setInt(1, rideId);  // Set the rideId
                        int rowsUpdated = updateStatement.executeUpdate();

                        if (rowsUpdated == 0) {
                            throw new SQLException("No available seats or ride status could not be updated.");
                        }

                        // After accepting the ride, we also update the ride request status
                        String updateRequestStatusSql = "UPDATE ride_requests SET status = 'Accepted' WHERE ride_id = ? AND passenger_id = ?";
                        try (PreparedStatement updateRequestStatement = connection.prepareStatement(updateRequestStatusSql)) {
                            updateRequestStatement.setInt(1, rideId);  // Set the rideId
                            updateRequestStatement.setInt(2, passengerId);  // Set the passengerId
                            updateRequestStatement.executeUpdate();  // Update the request status to 'Accepted'
                        }
                    }
                } else {
                    throw new SQLException("This passenger has not requested this ride or the request is not pending.");
                }
            }
        }
    }

    // Method to decline a ride
    public void declineRide(int passengerId, int rideId) throws SQLException {
        // First, we need to ensure that the passenger has requested this ride
        String checkRequestSql = "SELECT * FROM ride_requests WHERE passenger_id = ? AND ride_id = ? AND status = 'Pending'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(checkRequestSql)) {

            statement.setInt(1, passengerId);  // Set the passengerId
            statement.setInt(2, rideId);  // Set the rideId

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // The ride request exists and is pending, so we can proceed with declining the ride

                    // Update the ride request status to 'Declined'
                    String updateRequestStatusSql = "UPDATE ride_requests SET status = 'Declined' WHERE ride_id = ? AND passenger_id = ?";

                    try (PreparedStatement updateRequestStatement = connection.prepareStatement(updateRequestStatusSql)) {
                        updateRequestStatement.setInt(1, rideId);  // Set the rideId
                        updateRequestStatement.setInt(2, passengerId);  // Set the passengerId
                        updateRequestStatement.executeUpdate();  // Update the request status to 'Declined'
                    }
                } else {
                    throw new SQLException("This passenger has not requested this ride or the request is not pending.");
                }
            }
        }
    }
}
