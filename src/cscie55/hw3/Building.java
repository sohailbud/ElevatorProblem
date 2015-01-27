package cscie55.hw3;

import java.util.ArrayList;
/**
 * Created by Sohail on 3/16/14.
 */
public class Building {

    public final static int FLOORS = 7;
    private static ArrayList<Floor> floors = new ArrayList<Floor>();
    private Elevator elevator;
    private int floorNo;

    //constructor creates elevator and floor objects
    public Building() {
        elevator = new Elevator(Elevator.building);

        int count = 1;
        while (count != FLOORS+1) {
            floors.add(new Floor(Elevator.building, count));
            count++;
        }
    }

    //returns elevator object
    public Elevator elevator() {
        return elevator;
    }

    //returns proper floor object by accessing the array
    public Floor floor(int floorNumber) {
        floorNo = floorNumber;
        return floors.get(floorNumber-1);
    }

    public int getFloorNo() {
        return floorNo;
    }

    //adds passenger to ground object
    public void enter(Passenger passenger) {
        floor(1).enterGroundFloor(passenger);
    }
}
