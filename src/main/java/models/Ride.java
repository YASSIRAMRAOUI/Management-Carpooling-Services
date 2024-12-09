package models;

import java.sql.Date;

public class Ride {
    private int id;
    private int driverId;
    private Date date;
    private String depart;
    private String destination;
    private String status;
    private double fare;
    private String passengerName;
    private int numberOfPlaces;
    private String passengerPhone;
    private String driverName;
    private int place;

    // Constructors
    public Ride() {
    }

    public Ride(int id, int driverId, Date date, String depart, String destination, String status, double fare,
            String passengerName, int numberOfPlaces, String passengerPhone, String driverName, int place) {
        this.id = id;
        this.driverId = driverId;
        this.date = date;
        this.depart = depart;
        this.destination = destination;
        this.status = status;
        this.fare = fare;
        this.passengerName = passengerName;
        this.numberOfPlaces = numberOfPlaces;
        this.passengerPhone = passengerPhone;
        this.driverName = driverName;
        this.place = place;
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

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
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

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public String getpassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}