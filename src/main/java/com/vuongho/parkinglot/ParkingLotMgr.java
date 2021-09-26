package com.vuongho.parkinglot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

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
    String processCommand(String[] args) {
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
            case "ids_for_cars_with_color":
                return idsForCarsWithColor(args);
            case "slot_numbers_for_cars_with_color":
                return slotsForCarsWithColor(args);
            case "slot_number_for_id":
                return slotForId(args);
            default:
                return "Invalid command";
        }
    }

    /**
     * Creates a {@link ParkingLot} with a capacity of the input {@link capacity}.
     *
     * @param args command array
     * @return appropriate message from processing the command
     */
    String createParkingLot(String[] args) {
        if (args.length != 2) {
            return "Invalid command";
        }
        int capacity = Integer.parseInt(args[1]);
        return createParkingLot(capacity);
    }

    /**
     * Creates a {@link ParkingLot} with a capacity of the input {@link capacity}.
     *
     * 
     * @param capacity capacity of {@link ParkingLot}
     * @return appropriate message from creating the parking lot
     */
    public String createParkingLot(int capacity) {
        parkingLot = new ParkingLot(capacity);
        return "Created a parking lot with " + capacity + " slots";
    }

    /**
     * Parks a {@link Car} taken from the input args. {@link Car}'s detail must
     * contain a registration number and its color.
     * 
     * @param args command array
     * @return appropriate message from processing the command
     */
    String park(String[] args) {
        if (args.length != 3) {
            return "Invalid command";
        }
        String licensePlate = args[1];
        String color = args[2];
        return park(licensePlate, color);
    }

    public String park(String licensePlate, String color) {
        if (parkingLot == null) {
            return "Please create a parking lot first";
        }
        if (parkingLot.isFull()) {
            return "Sorry, parking lot is full";
        }
        Car car = new Car(licensePlate, color);
        int slot;
        try {
            slot = parkingLot.park(car);
        } catch (ParkingLotException e) {
            return e.getMessage();
        }
        return "Allocated slot number: " + (slot + 1);
    }

    /**
     * Checks a {@link Car}'s out of the specified lot number.
     * 
     * @param args command array
     * @return appropriate message from processing the command
     */
    String leave(String[] args) {
        if (args.length != 2) {
            return "Invalid command";
        }
        int slot = Integer.parseInt(args[1]);
        return leave(slot);
    }

    /**
     * Checks a {@link Car}'s out of the specified lot number.
     * 
     * @param slot slot number
     * @return appropriate message from processing the command
     */
    public String leave(int slot) {
        if (parkingLot == null) {
            return "Please create a parking lot first";
        }
        try {
            parkingLot.leave(slot-1);
        } catch (ParkingLotException e) {
            return e.getMessage();
        }
        return "Slot number " + slot + " is free";
    }

    /**
     * Checks the status of the current {@link ParkingLot} stored in this
     * {@link ParkingLotMgr}.
     * 
     * @param args command array
     * @return the string status of the current {@link ParkingLot}
     */
    String status(String[] args) {
        if (args.length != 1) {
            return "Invalid command";
        }
        return status();
    }

    /**
     * Checks the status of the current {@link ParkingLot} stored in this
     * {@link ParkingLotMgr}.
     * 
     * @param args command array
     * @return the string status of the current {@link ParkingLot}
     */
    public String status() {
        if (parkingLot == null) {
            return "Please create a parking lot first";
        }
        return parkingLot.status(false);
    }

    /**
     * Gets the license number (id) of the {@link Car} with the specified color,
     * formatted by the following:
     * <pre>
     * <id1>, <id2>, <id3>,...
     * </pre>
     * 
     * @param args command array
     * @return the license number of the {@link Car}s with the specified color
     */
    String idsForCarsWithColor(String[] args) {
        if (args.length != 2) {
            return "Invalid command";
        }
        String color = args[1];
        return idsForCarsWithColor(color);
    }

    /**
     * Gets the license number (id) of the {@link Car} with the specified color,
     * formatted by the following:
     * <pre>
     * <id1>, <id2>, <id3>,...
     * </pre>
     * 
     * @param args command array
     * @return the license number of the {@link Car}s with the specified color
     */
    public String idsForCarsWithColor(String color) {
        if (parkingLot == null) {
            return "Please create a parking lot first";
        }
        List<Car> cars = parkingLot.getCarsWithColor(color);
        // checks in case there is no car with the specified color
        if (cars.size() == 0) {
            return "None found";
        }
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            sb.append(car.getLicensePlate()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    /**
     * Gets the slot number of the {@link Car} with the specified color,
     * formatted by the following:
     * <pre>
     * <slot1>, <slot2>, <slot3>,...
     * </pre>
     * 
     * @param args command array
     * @return the license number of the {@link Car}s with the specified color
     */
    String slotsForCarsWithColor(String[] args) {
        if (args.length != 2) {
            return "Invalid command";
        }
        String color = args[1];
        return slotsForCarsWithColor(color);
    }

    /**
     * Gets the slot number of the {@link Car} with the specified color,
     * formatted by the following:
     * <pre>
     * <slot1>, <slot2>, <slot3>,...
     * </pre>
     * 
     * @param color color of the {@link Car}
     * @return the license number of the {@link Car}s with the specified color
     */
    public String slotsForCarsWithColor(String color) {
        if (parkingLot == null) {
            return "Please create a parking lot first";
        }
        List<Integer> slots = parkingLot.getSlotsNumberForCarsWithColor(color);
        StringBuilder sb = new StringBuilder();
        // checks in case there is no car with the specified color
        if (slots.size() == 0) {
            return "None found";
        }
        for (Integer slot : slots) {
            sb.append(slot + 1).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    /**
     * Gets the slot number of the {@link Car} with the specified license plate.
     * 
     * @param args command array
     * @return the slot number of the {@link Car} with the specified license plate
     */
    String slotForId(String[] args) {
        if (args.length != 2) {
            return "Invalid command";
        }
        String id = args[1];
        return slotForId(id);
    }

    /**
     * Gets the slot number of the {@link Car} with the specified license plate.
     * 
     * @param id license plate of the {@link Car}
     * @return the slot number of the {@link Car} with the specified license plate
     */
    public String slotForId(String id) {
        if (parkingLot == null) {
            return "Please create a parking lot first";
        }
        int slot = parkingLot.getSlotNumberForId(id);
        if (slot == -1) {
            return "Not found";
        }
        return (slot + 1) + "";
    }

    public static void main(String[] args) throws ParkingLotException, IOException {
        ParkingLotMgr pMgr = new ParkingLotMgr();

        if (args.length == 1) {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
            String command;
            while ((command = inputReader.readLine()) != null) {
                System.out.println(pMgr.giveCommand(command));
            }
            inputReader.close();
            return;
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("exit")) {
                break;
            }
            String message = pMgr.giveCommand(command);
            System.out.println(message);
        }
        sc.close();
    }
}
