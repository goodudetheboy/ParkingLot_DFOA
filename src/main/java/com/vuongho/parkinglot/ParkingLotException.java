package com.vuongho.parkinglot;

/**
 * An exception class for ParkingLot.
 */
public class ParkingLotException extends RuntimeException {
    /**
     * Construct a runtime exception from a message
     * 
     * @param message the message
     */
    public ParkingLotException(String message) {
        super(message);
    }
}
