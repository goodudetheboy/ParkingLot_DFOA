package com.vuongho.parkinglot;

import java.rmi.UnexpectedException;

/**
 * Implementation of a parking lot.
 * 
 * @author Vuong Ho
 */
public class ParkingLot {
    /**
     * The number of parking spaces of the {@link ParkingLot}.
     */
    private int capacity;

    /**
     * The list of cars that are parked in the {@link ParkingLot}.
     */
    private Car[] parkedCars;

    /**
     * The current number of cars that are parked in the {@link ParkingLot}.
     */
    private int currentSize = 0;

    /**
     * Default constructor, with capacity set to 6.
     */
    public ParkingLot() {
        this(6);
    }

    /**
     * Constructor for a {@link ParkingLot} with a capacity.
     * 
     * @param capacity
     */
    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedCars = new Car[capacity];
    }

    /**
     * @return the capacity of the {@link ParkingLot}.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @return the list of cars that are parked in the {@link ParkingLot}.
     */
    public Car[] getParkedCars() {
        return parkedCars;
    }

    /**
     * @return the current number of cars that are parked in the {@link ParkingLot}.
     */
    public int getCurrentSize() {
        return currentSize;
    }

    /**
     * Sets the capacity of the {@link ParkingLot}.
     * 
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Parks a {@link Car} in the {@link ParkingLot}, if capacity haven't been
     * reached. Returns the number of lot that the {@link Car} was parked in.
     * If there is no space, throws a {@link ParkingLotException}.
     * 
     * @param car the {@link Car} to be parked.
     * @return the number of lot that the {@link Car} was parked in.
     * @throws ParkingLotException if there is no space.
     */
    public int park(Car car) {
        if (currentSize < capacity) {
            int emptyLot = getEmptyLot();
            parkedCars[emptyLot] = car;
            currentSize++;
            return emptyLot;
        } else {
            throw new ParkingLotException("Sorry, parking lot is full");
        }
    }

    /**
     * Checks if there is an empty lot in the {@link ParkingLot}, and return its
     * index. If there is no empty lot, returns -1.
     * 
     * @return the index of the empty lot, or -1 if there is no empty lot.
     */
    private int getEmptyLot() {
        for (int i=0; i < capacity; i++) {
            if (parkedCars[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks a car out of the {@link ParkingLot} based on , and returns the {@link}
     * 
     * @param lot
     * @return
     */
    public Car leave(int lot) {
        if (lot < 0 || lot >= capacity) {
            throw new ParkingLotException("Invalid lot number");
        }
        if (parkedCars[lot] == null) {
            return null;
        }
        Car carToLeave = parkedCars[lot];
        parkedCars[lot] = null;
        currentSize--;
        return carToLeave;
    }
}
