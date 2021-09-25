package com.vuongho.parkinglot;

/**
 * A REPL implementation for managing a {@link ParkingLot}.
 */
public class ParkingLotMgr {
    private ParkingLot parkingLot = null;
    /**
     * Default constructor
     */
    public ParkingLotMgr() {
        // empty by default
    }

    /**
     * Constructor for a {@link ParkingLotMgr} with a {@link ParkingLot}
     * with a capacity of the input {@link capacity}.
     * 
     * @param capacity capacity of {@link ParkingLot} that this 
     *      {@link ParkingLotMgr} will manage.
     */
    public ParkingLotMgr(int capacity) {
        this.parkingLot = new ParkingLot(capacity);
    }

    /**
     * Handles the input command and returns appropriate message.
     * 
     * @param command input command
     * @return appropriate message from processing the command
     */
    public String giveCommand(String command) {
        return processCommand(command.split(" "));
    }

    private String processCommand(String[] args) {
        return "Created a parking lot with 6 slots";
    }

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(6);
        for (Car car : parkingLot.getParkedCars()) {
            System.out.println(car);
        }
    }
}
