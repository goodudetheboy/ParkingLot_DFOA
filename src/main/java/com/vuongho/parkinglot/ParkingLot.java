package com.vuongho.parkinglot;

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
     * reached. Returns the number of lot that the {@link Car} was parked in.
     * If there is no space, throws a {@link ParkingLotException}.
     * 
     * @param car the {@link Car} to be parked.
     * @return the number of lot that the {@link Car} was parked in.
     * @throws ParkingLotException if there is no space.
     */
    public int park(Car car) throws ParkingLotException {
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
     * Creates then parks a {@link Car} in the {@link ParkingLot} with the
     * input license number and color, if capacity haven't been reached. 
     * Returns the number of lot that the {@link Car} was parked in.
     * If there is no space, throws a {@link ParkingLotException}.
     * 
     * @param car the {@link Car} to be parked.
     * @return the number of lot that the {@link Car} was parked in.
     * @throws ParkingLotException if there is no space.
     */
    public int park(String licenseNumber, String color) throws ParkingLotException {
        Car parkedCar = new Car(licenseNumber, color);
        return park(parkedCar);
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
     * Checks if a specific parking lot is occupied.
     * 
     * @param lot the index of the parking lot.
     * @return true if the specified parking lot is occupied, false otherwise.
     * @throws ParkingLotException if the index is out of range.
     */
    public boolean isEmptyLot(int lot) throws ParkingLotException {
        if (lot < 0 || lot >= capacity) {
            throw new ParkingLotException("Invalid lot number");
        }
        return parkedCars[lot] == null;
    }

    /**
     * Checks a car out of the {@link ParkingLot} based on the lot number, and
     * returns the {@link Car} that was parked in the specified lot.
     * 
     * @param lot
     * @return the {@link Car} that was parked in the specified lot, null if none found.
     * @throws ParkingLotException if the lot number is invalid
     */
    public Car leave(int lot) throws ParkingLotException {
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

    /**
     * Gets a formatted string of a current status of the parking lot, in this form:
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
     * lot status: if the lot is empty, it will be marked with "Empty".
     * Otherwise, the returning string only contains lot that are not empty
     * 
     * @param fullInfo true if want to get the full status (including empty lot),
     *      false otherwise.
     * @return a formatted string of the current status of the parking lot.
     */
    public String status(boolean fullInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Slot No.\tID\t\tColor\n");
        for (int lot=0; lot < capacity; lot++) {
            boolean isOccupied = (parkedCars[lot] != null);
            sb.append(lot+1).append("\t\t");
            if (!isOccupied && fullInfo) {
                sb.append("(empty)\n");
            } else {
                Car parkedCar = parkedCars[lot];
                sb.append(parkedCar.getLicensePlate()).append("\t\t");
                sb.append(parkedCar.getColor()).append("\n");
            }
        }
        return sb.toString();
    }
}
