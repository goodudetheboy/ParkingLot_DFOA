package com.vuongho.parkinglot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test class for {@link ParkingLotMgr}
 */
public class UnitTest {
    public ParkingLotMgr parkingLotMgrInit() {
        ParkingLotMgr parkingLotMgr = new ParkingLotMgr();
        parkingLotMgr.createParkingLot(6);
        parkingLotMgr.park("EUS687", "White");
        parkingLotMgr.park("510IBD", "White");
        parkingLotMgr.park("6TRJ24", "Black");
        parkingLotMgr.park("EK3333", "Red");
        parkingLotMgr.park("IYTE32", "Blue");
        parkingLotMgr.park("MNG728", "Black");
        return parkingLotMgr;
    }

    @Test
    public void createParkingLotTest() {
        assertEquals("Created a parking lot with 6 slots", (new ParkingLotMgr()).createParkingLot(6));
    }

    @Test
    public void parkTest() {
        ParkingLotMgr parkingLotMgr = new ParkingLotMgr();
        parkingLotMgr.createParkingLot(6);
        assertEquals("Allocated slot number: 1", parkingLotMgr.park("EUS687", "White"));
        assertEquals("Allocated slot number: 2", parkingLotMgr.park("510IBD", "White"));
        assertEquals("Allocated slot number: 3", parkingLotMgr.park("6TRJ24", "Black"));
        assertEquals("Allocated slot number: 4", parkingLotMgr.park("EK3333", "Red"));
        assertEquals("Allocated slot number: 5", parkingLotMgr.park("IYTE32", "Blue"));
        assertEquals("Allocated slot number: 6", parkingLotMgr.park("MNG728", "Black"));
    }

    @Test
    public void leaveTest() {
        ParkingLotMgr parkingLotMgr = parkingLotMgrInit();
        assertEquals("Slot number 4 is free", parkingLotMgr.leave(4));
        assertEquals("Slot number 4 is free", parkingLotMgr.leave(4));
    }

    @Test
    public void statusTest() {
        ParkingLotMgr parkingLotMgr = parkingLotMgrInit();
        String expected =
        "Slot No.\tID\t\tColor\n" +
        "1\t\tEUS687\t\tWhite\n" +
        "2\t\t510IBD\t\tWhite\n" +
        "3\t\t6TRJ24\t\tBlack\n" +
        "4\t\tEK3333\t\tRed\n" +
        "5\t\tIYTE32\t\tBlue\n" +
        "6\t\tMNG728\t\tBlack";
        assertEquals(expected, parkingLotMgr.status());
    }

    @Test
    public void idsForCarsWithColorTest() {
        ParkingLotMgr parkingLotMgr = parkingLotMgrInit();
        parkingLotMgr.leave(4);
        assertEquals("EUS687, 510IBD", parkingLotMgr.idsForCarsWithColor("White"));
        assertEquals("6TRJ24, MNG728", parkingLotMgr.idsForCarsWithColor("Black"));
        assertEquals("None found", parkingLotMgr.idsForCarsWithColor("Red"));
        assertEquals("IYTE32", parkingLotMgr.idsForCarsWithColor("Blue"));
    }

    @Test
    public void slotsForCarsWithColorTest() {
        ParkingLotMgr parkingLotMgr = parkingLotMgrInit();
        parkingLotMgr.leave(4);
        assertEquals("1, 2", parkingLotMgr.slotsForCarsWithColor("White"));
        assertEquals("3, 6", parkingLotMgr.slotsForCarsWithColor("Black"));
        assertEquals("None found", parkingLotMgr.slotsForCarsWithColor("Red"));
        assertEquals("5", parkingLotMgr.slotsForCarsWithColor("Blue"));
    }

    @Test
    public void getSlotNumberForId() {
        ParkingLotMgr parkingLotMgr = parkingLotMgrInit();
        assertEquals("6", parkingLotMgr.slotForId("MNG728"));
        assertEquals("Not found", parkingLotMgr.slotForId("045BKR"));
    }

    @Test
    public void invalidCommandTest() {
        ParkingLotMgr parkingLotMgr = parkingLotMgrInit();
        assertEquals("Invalid command", parkingLotMgr.giveCommand("invalid"));

        // The following tests when parking lot has not been created
        parkingLotMgr = new ParkingLotMgr();
        assertEquals("Please create a parking lot first", parkingLotMgr.park("SMTH", "White"));
        assertEquals("Please create a parking lot first", parkingLotMgr.leave(1));
        assertEquals("Please create a parking lot first", parkingLotMgr.status());
        assertEquals("Please create a parking lot first", parkingLotMgr.idsForCarsWithColor("White"));
        assertEquals("Please create a parking lot first", parkingLotMgr.slotsForCarsWithColor("White"));
        assertEquals("Please create a parking lot first", parkingLotMgr.slotForId("SMTH"));
    }
}
