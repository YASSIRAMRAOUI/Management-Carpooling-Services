package models;

import java.sql.Date;

public class Ride {
    private int id;
    private int driverId;
    private Date date;
    private String destination;
    private String status;
    private double fare;
    private String passengerName;

    // Constructors
    public Ride() {
    }

    public Ride(int id, int driverId, Date date, String destination, String status, double fare, String passengerName) {
        this.id = id;
        this.driverId = driverId;
        this.date = date;
        this.destination = destination;
        this.status = status;
        this.fare = fare;
        this.passengerName = passengerName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String string) {
        this.passengerName = string;
    }
}
