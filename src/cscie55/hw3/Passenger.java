package cscie55.hw3;

/**
 * Created by Sohail on 3/16/14.
 */
public class Passenger {

    private int currentFloor;
    private int destinationFloor;
    private final int UNDEFINED_FLOOR = -1;

    public Passenger(int id) {
        currentFloor = 1;
        destinationFloor = -1;
    }

    //return passenger's current floor
    public int currentFloor() {
        return currentFloor;
    }

    //return passenger's destination floor
    public int destinationFloor() {
        return destinationFloor;
    }

    //Sets the Passenger's destination floor to newDestinationFloor
    public void waitForElevator(int newDestinationFloor) {
        destinationFloor = newDestinationFloor;
    }

    //Sets passenger current floor to be undefined
    public void boardElevator() {
        currentFloor = UNDEFINED_FLOOR;
    }

    //passenger arrives at destination floor
    public void arrive() {
        currentFloor = destinationFloor;
        destinationFloor = UNDEFINED_FLOOR;
    }

    public void setDestinationFloor(int df) {
        destinationFloor=df;
    }


}
