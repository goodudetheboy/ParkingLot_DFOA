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
     * This function is exposed to the REPL.
     * 
     * @param command input command
     * @return appropriate message from processing the command
     */
    public String giveCommand(String command) {
        return processCommand(command.split(" "));
    }

    /**
     * Handles the input command and returns appropriate message.
     * 
     * @param args array of arguments
     * @return appropriate message from processing the command
     */
    private String processCommand(String[] args) {
        String command = args[0];
        switch (command) {
            case "create_parking_lot":
                return createParkingLot(args);
            case "park":
                return park(args);
            case "leave":
                return leave(args);
            default:
                return "Invalid command";
        }
    }

    private String createParkingLot(String[] args) {
        if (args.length != 2) {
            return "Invalid command";
        }
        int capacity = Integer.parseInt(args[1]);
        parkingLot = new ParkingLot(capacity);
        return "Created a parking lot with " + capacity + " slots";
    }

    private String park(String[] args) {
        if (args.length != 3) {
            return "Invalid command";
        }
        if (parkingLot.isFull()) {
            return "Sorry, parking lot is full";
        }
        String registrationNumber = args[1];
        String color = args[2];
        Car car = new Car(registrationNumber, color);
        int slotNumber = parkingLot.park(car);
        return "Allocated slot number: " + (slotNumber + 1);
    }

    private String leave(String[] args) {
        if (args.length != 2) {
            return "Invalid command";
        }
        int slotNumber = Integer.parseInt(args[1]);
        if (parkingLot.isEmptyLot(slotNumber)) {
            return "Invalid slot number";
        }
        parkingLot.leave(slotNumber);
        return "Slot number " + slotNumber + " is free";
    }

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(6);
        for (Car car : parkingLot.getParkedCars()) {
            System.out.println(car);
        }
    }
}
