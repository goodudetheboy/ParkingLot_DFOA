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
            case "status":
                return status(args);
            default:
                return "Invalid command";
        }
    }

    /**
     * Creates a {@link ParkingLot} with a capacity of the input {@link capacity}.
     *
     * @param args
     * @return
     */
    private String createParkingLot(String[] args) {
        if (args.length != 2) {
            return "Invalid command";
        }
        int capacity = Integer.parseInt(args[1]);
        parkingLot = new ParkingLot(capacity);
        return "Created a parking lot with " + capacity + " slots";
    }

    /**
     * Parks a {@link Car} taken from the input args. {@link Car}'s detail must
     * contain a registration number and its color.
     * 
     * @param args command array
     * @return appropriate message from processing the command
     * @throws ParkingLotException
     */
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
        int slotNumber;
        try {
            slotNumber = parkingLot.park(car);
        } catch (ParkingLotException e) {
            return e.getMessage();
        }
        return "Allocated slot number: " + (slotNumber + 1);
    }

    /**
     * Checks a {@link Car}'s out of the specified lot number.
     * 
     * @param args command array
     * @return appropriate message from processing the command
     */
    private String leave(String[] args) {
        if (args.length != 2) {
            return "Invalid command";
        }
        int slotNumber = Integer.parseInt(args[1]);
        try {
            parkingLot.leave(slotNumber-1);
        } catch (ParkingLotException e) {
            return e.getMessage();
        }
        return "Slot number " + slotNumber + " is free";
    }

    /**
     * Checks the status of the current {@link ParkingLot} stored in this
     * {@link ParkingLotMgr}.
     * 
     * @param args command array
     * @return the string status of the current {@link ParkingLot}
     */
    private String status(String[] args) {
        if (args.length != 1) {
            return "Invalid command";
        }
        return parkingLot.status(true);
    }

    public static void main(String[] args) throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(6);
        parkingLot.park("your12", "Dark Brown");
        System.out.println(parkingLot.status(true));
    }
}
