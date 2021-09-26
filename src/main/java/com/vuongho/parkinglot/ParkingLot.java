package com.vuongho.parkinglot;

import java.util.ArrayList;
import java.util.List;

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
     * @return true if the {@link ParkingLot} is full, false otherwise.
     */
    public boolean isFull() {
        return currentSize == capacity;
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
     * reached. Returns the number of slot that the {@link Car} was parked in.
     * If there is no space, throws a {@link ParkingLotException}.
     * 
     * @param car the {@link Car} to be parked.
     * @return the number of slot that the {@link Car} was parked in.
     * @throws ParkingLotException if there is no space.
     */
    public int park(Car car) throws ParkingLotException {
        if (currentSize < capacity) {
            int emptyLot = getEmptyLot();
            parkedCars[emptyLot] = car;
            currentSize++;
            return emptyLot;
        } else {
            throw new ParkingLotException("Sorry, parking slot is full");
        }
    }

    /**
     * Creates then parks a {@link Car} in the {@link ParkingLot} with the
     * input license number and color, if capacity haven't been reached. 
     * Returns the number of slot that the {@link Car} was parked in.
     * If there is no space, throws a {@link ParkingLotException}.
     * 
     * @param car the {@link Car} to be parked.
     * @return the number of slot that the {@link Car} was parked in.
     * @throws ParkingLotException if there is no space.
     */
    public int park(String licenseNumber, String color) throws ParkingLotException {
        Car parkedCar = new Car(licenseNumber, color);
        return park(parkedCar);
    }

    /**
     * Checks if there is an empty slot in the {@link ParkingLot}, and return its
     * index. If there is no empty slot, returns -1.
     * 
     * @return the index of the empty slot, or -1 if there is no empty slot.
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
     * Checks if a specific parking slot is occupied.
     * 
     * @param slot the index of the parking slot.
     * @return true if the specified parking slot is occupied, false otherwise.
     * @throws ParkingLotException if the index is out of range.
     */
    public boolean isEmptyLot(int slot) throws ParkingLotException {
        if (slot < 0 || slot >= capacity) {
            throw new ParkingLotException("Invalid slot number");
        }
        return parkedCars[slot] == null;
    }

    /**
     * Checks a car out of the {@link ParkingLot} based on the slot number, and
     * returns the {@link Car} that was parked in the specified slot.
     * 
     * @param slot 
     * @return the {@link Car} that was parked in the specified slot, null if none found.
     * @throws ParkingLotException if the slot number is invalid
     */
    public Car leave(int slot) throws ParkingLotException {
        if (slot < 0 || slot >= capacity) {
            throw new ParkingLotException("Invalid slot number");
        }
        if (parkedCars[slot] == null) {
            return null;
        }
        Car carToLeave = parkedCars[slot];
        parkedCars[slot] = null;
        currentSize--;
        return carToLeave;
    }

    /**
     * Gets a formatted string of a current status of the parking slot, in this form:
     * <pre>
     * Slot No. ID      Color
     * 1        EUS687  White
     * 2        510IBD  White
     * 3        Empty
     * 5        IYTE32  Blue
     * 6        MNG728  Black
     * """
     * </pre>
     * If {@code fullInfo} is true, the status string returned will denotes all
     * slot status: if the slot is empty, it will be marked with "Empty".
     * Otherwise, the returning string only contains slot that are not empty
     * 
     * @param fullInfo true if want to get the full status (including empty slot),
     *      false otherwise.
     * @return a formatted string of the current status of the parking slot.
     */
    public String status(boolean fullInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Slot No.\tID\t\tColor\n");
        for (int slot=0; slot < capacity; slot++) {
            boolean isOccupied = (parkedCars[slot] != null);
            if (!isOccupied) {
                if (fullInfo) {
                    sb.append(slot+1).append("\t\t(empty)\n");
                }
                continue;
            } else {
                sb.append(slot+1).append("\t\t");
                Car parkedCar = parkedCars[slot];
                sb.append(parkedCar.getLicensePlate()).append("\t\t");
                sb.append(parkedCar.getColor()).append("\n");
            }
        }
        return sb.substring(0, sb.length()-1);
    }

    /**
     * Returns the list of {@link Car} in this {@link ParkingLot} that has the
     * same color as the input color.
     * 
     * @param color the color of the {@link Car} to be searched.
     * @return a list of {@link Car} that has the same color as the input color.
     */
    public List<Car> getCarsWithColor(String color) {
        List<Car> cars = new ArrayList<>();
        for (int slot=0; slot < capacity; slot++) {
            Car parkedCar = parkedCars[slot];
            if (parkedCar != null && parkedCar.getColor().equals(color)) {
                cars.add(parkedCar);
            }
        }
        return cars;
    }

    /**
     * Returns the list of {@link Car} in this {@link ParkingLot} that has the
     * same color as the input color.
     * 
     * @param color the color of the {@link Car} to be searched.
     * @return a list of {@link Car} that has the same color as the input color.
     */
    public List<Integer> getSlotsNumberForCarsWithColor(String color) {
        List<Integer> slots = new ArrayList<>();
        for (int slot=0; slot < capacity; slot++) {
            Car parkedCar = parkedCars[slot];
            if (parkedCar != null && parkedCar.getColor().equals(color)) {
                slots.add(slot);
            }
        }
        return slots;
    }

    /**
     * Gets the slot number of the {@link Car} that has the same license plate
     * as the input license plate.
     * 
     * @param id the license plate of the {@link Car} to be searched.
     * @return the slot number of the {@link Car} that has the same license plate,
     *    or -1 if not found.
     */
    public int getSlotNumberForId(String id) {
        for (int slot=0; slot < capacity; slot++) {
            Car parkedCar = parkedCars[slot];
            if (parkedCar != null && parkedCar.getLicensePlate().equals(id)) {
                return slot;
            }
        }
        return -1;
    }
}
