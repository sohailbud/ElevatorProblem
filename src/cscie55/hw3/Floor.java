package cscie55.hw3;

import java.util.*;

/**
 * Created by Sohail on 3/16/14.
 */
public class Floor {

    public LinkedList <Passenger> resident;
    public LinkedList <Passenger> waitingUp;
    public LinkedList <Passenger> waitingDown;
    private static Building building;
    private int floorNumber;
    private int [] passengerWaiting;

    public Floor(Building building, int floorNumber) {
        passengerWaiting = new int[7];
        this.floorNumber=floorNumber;
        this.building=building;
        resident = new LinkedList<Passenger>();
        waitingUp = new LinkedList<Passenger>();
        waitingDown = new LinkedList<Passenger>();
    }

    //returns passenger waiting on given floor for elevator
    public int passengersWaiting() {
        return passengerWaiting[floorNumber-2];
    }

    //allows passengers to wait for the elevator
    public void waitForElevator(Passenger passenger, int floorNumber) {
        passenger.waitForElevator(floorNumber);
        //puts passenger's on floor's collection by checking there direction
        if (passenger.currentFloor() > passenger.destinationFloor()) {
            building.floor(passenger.currentFloor()).waitingDown.add(passenger);
        } else if (passenger.currentFloor() < passenger.destinationFloor()) {
            building.floor(passenger.currentFloor()).waitingUp.add(passenger);
        }
        //removes passenger from resident's collection
        building.floor(passenger.currentFloor()).resident.remove(passenger);
    }

    //add passengers to resident's collection
    public void enterGroundFloor(Passenger passenger) {
        resident.add(passenger);
    }

    //check if passenger is a resident
    public boolean isResident(Passenger passenger) {
        return building.floor(floorNumber).resident.contains(passenger);

    }

}
