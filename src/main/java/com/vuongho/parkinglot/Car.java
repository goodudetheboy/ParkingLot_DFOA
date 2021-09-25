package com.vuongho.parkinglot;

/**
 * Object implementation of a car in a {@link ParkingLot}.
 */
public class Car {
    private String licensePlate; // or ID of the car
    private String color;

    /**
     * Constructor of a {@link Car} with a license plate and a color.
     * 
     * @param licensePlate
     * @param color
     */
    public Car(String licensePlate, String color) {
        this.licensePlate = licensePlate;
        this.color = color;
    }

    /**
     * @return the license plate of the car
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @return the color of the car
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the license plate of the car.
     * 
     * @param licensePlate the license plate of the car
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * Sets the color of the car.
     * 
     * @param color the color of the car
     */
    public void setColor(String color) {
        this.color = color;
    }
}
