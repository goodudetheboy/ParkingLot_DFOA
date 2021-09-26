# **Parking Lot** #

This project simulates a parking lot.

## Features ##

There are many ways that you can interact with the parking lot, such as:

- Create a parking lot
- Park a car
- Check a car out
- Get status of the parking lot
- Get license number of cars in a certain color
- Get slot number of cars with a certain color
- Get slot number of a car with a certain license plate

## Usage ##

Do the following to create a new instance of a parking lot manager:

```java
ParkingLotMgr parkingLotMgr = new ParkingLotMgr();
```

Make sure to create a parking lot inside a parking lot manager before interacting with it:

```java
parkingLotMgr.createParkingLot(6);
```

To park a car into a parking lot:

```java
parkingLotMgr.park("LICENSE", "White");

```

To check a car out of a parking lot:

```java
parkingLotMgr.leave(4); // parameter is the number of the parking slot
```

To get the status of a parking lot:

```java
System.out.println(parkingLotMgr.status());
```

To get license number of cars in a certain color:

```java
System.out.println(parkingLotMgr.idsForCarsWithColor());
```

To get slot number of cars with a certain color:

```java
System.out.println(parkingLotMgr.slotsForCarsWithColor());
```

To get slot number of a car with a certain license plate

```java
System.out.println(parkingLotMgr.slotForId());
```

## Building ##

The project uses Gradle for building. Standard gradle tasks for the java plugin can be found [here](https://docs.gradle.org/current/userguide/java_plugin.html). They can be invoked on the command line by running `gradlew` or `gradlew.bat` with the name of the task, for example `gradlew jar` to create the jar archive.

This can also be build by running `bash ./bin/setup` in bash. It will build the project, export the .jar archive to the `./bin/` folder, and run the test suite.

## Running ##

Run `bash ./bin/parking_lot` to open the REPL and test the project out interactively, or you can put your commands in a `command.txt` folder and use it as argument by `bash ./bin/parking_lot command.txt`.
