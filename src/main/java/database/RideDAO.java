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

    public List<Ride> getAvailableRides(int passengerId) throws SQLException {
        String sql = "SELECT r.id, r.driver_id, r.date, r.depart, r.destination, r.number_of_places, r.status, r.fare, u.name AS driver_name " +
                "FROM rides r " +
                "JOIN users u ON r.driver_id = u.user_id " +
                "LEFT JOIN ride_requests rr ON r.id = rr.ride_id AND rr.passenger_id = ? AND rr.status = 'Accepted' " +
                "WHERE r.status = 'In Progress' AND r.number_of_places > 0 AND rr.ride_id IS NULL";

        List<Ride> availableRides = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, passengerId);

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
                    ride.setDriverName(resultSet.getString("driver_name"));

                    availableRides.add(ride);
                }
            }
        }
        return availableRides;
    }

    public void acceptRide(Integer passengerId, int rideId, int places) throws SQLException {
        String decreaseSeatsSQL = "UPDATE rides SET number_of_places = number_of_places - ? WHERE id = ?";
        String checkFullSQL = "UPDATE rides SET status = 'Completed' WHERE id = ? AND number_of_places <= 0";
        String insertRequestSQL = "INSERT INTO ride_requests (ride_id, passenger_id, status, places) VALUES (?, ?, 'Accepted', ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            try (PreparedStatement decreaseSeatsStmt = connection.prepareStatement(decreaseSeatsSQL);
                 PreparedStatement checkFullStmt = connection.prepareStatement(checkFullSQL);
                 PreparedStatement insertRequestStmt = connection.prepareStatement(insertRequestSQL)) {

                connection.setAutoCommit(false); // Start transaction

                insertRequestStmt.setInt(1, rideId);
                insertRequestStmt.setInt(2, passengerId);
                insertRequestStmt.setInt(3, places);
                insertRequestStmt.executeUpdate();

                decreaseSeatsStmt.setInt(1, places);
                decreaseSeatsStmt.setInt(2, rideId);
                decreaseSeatsStmt.executeUpdate();

                checkFullStmt.setInt(1, rideId);
                checkFullStmt.executeUpdate();

                connection.commit(); // Commit transaction
            } catch (SQLException e) {
                connection.rollback(); // Rollback on failure
                throw e;
            } finally {
                connection.setAutoCommit(true); // Ensure auto-commit is re-enabled
            }
        }
    }
    public void declineRide(Integer passengerId, int rideId) throws SQLException {
        String sql = "INSERT INTO ride_requests (ride_id, passenger_id, status) VALUES (?, ?, 'Declined')";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rideId);
            statement.setInt(2, passengerId);
            statement.executeUpdate();
        }
    }

}
