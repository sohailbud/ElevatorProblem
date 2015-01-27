package cscie55.hw3;

import com.sun.swing.internal.plaf.synth.resources.synth_sv;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.*;
import java.util.TreeSet;

/**
 * Created by Sohail on 3/16/14.
 */
public class Elevator{

    private static LinkedHashMap <Integer, Set<Passenger>> destinationFloors = new LinkedHashMap<Integer, Set<Passenger>>();

    private int currentFloorNo;
    private boolean directionUp; //true = up, false = down
    public static final int CAPACITY = 10;
    public static Building building = new Building();

    public Elevator(Building building) {
        currentFloorNo = 1;
        directionUp = true;

        //constructor creates each floor's collection and adds to a linkedHashMap collection
        HashSet <Passenger> floor1 = new HashSet<Passenger>();
        HashSet <Passenger> floor2 = new HashSet<Passenger>();
        HashSet <Passenger> floor3 = new HashSet<Passenger>();
        HashSet <Passenger> floor4 = new HashSet<Passenger>();
        HashSet <Passenger> floor5 = new HashSet<Passenger>();
        HashSet <Passenger> floor6 = new HashSet<Passenger>();
        HashSet <Passenger> floor7 = new HashSet<Passenger>();

        destinationFloors.put(1, floor1);
        destinationFloors.put(2, floor2);
        destinationFloors.put(3, floor3);
        destinationFloors.put(4, floor4);
        destinationFloors.put(5, floor5);
        destinationFloors.put(6, floor6);
        destinationFloors.put(7, floor7);
    }

    //moves the elevator by 1 floor in appropriate direction
    public void move() {

        //check direction and move next floor
        if (goingUp()) {
            currentFloorNo += 1;
        } else if (goingDown()) {
            currentFloorNo -= 1;
        }

        //if elevator reached top or bottom floor, change direction
        if (currentFloorNo == Building.FLOORS) {directionUp = false;}
        else if (currentFloorNo == 1) {directionUp = true;}


        //unload passengers
            // unload from destinationFloors and add to floor's resident collection
            //remove from destinationFloors
            // change passenger's status
        if (destinationFloors.get(currentFloor()).isEmpty()) {

        } else {
            for (Passenger psg : destinationFloors.get(currentFloorNo)) {
                building.floor(currentFloorNo).resident.add(psg);
                psg.arrive();
            }
            destinationFloors.get(currentFloorNo).clear();
        }

        //board passengers
            //check direction and if anyone is waiting on next floor
            //access proper collection and move to destinationFloor
            //remove from floor's waitingUp collection
            //change passenger's status
        if (goingUp()) {
            if (building.floor(currentFloorNo).waitingUp.isEmpty()) {

            } else {
                HashSet <Passenger> temp = new HashSet<Passenger>();

                for (Passenger psg : building.floor(currentFloorNo).waitingUp) {
                    try {
                        building.elevator().boardPassenger(psg);
                        temp.add(psg); //saves passengers in temp collection to be removed later
                    } catch (ElevatorFullException me) {
                        break;

                    }
                }

                //removes passenger from floor's collections using temp storage
                for (Passenger psgTemp : temp) {
                    building.floor(currentFloorNo).waitingUp.remove(psgTemp);
                }
            }

        }

        if (goingDown()) {
            if (building.floor(currentFloorNo).waitingDown.isEmpty()) {

            } else {
                HashSet <Passenger> temp = new HashSet<Passenger>();

                for (Passenger psg : building.floor(currentFloorNo).waitingDown) {
                    try {
                        building.elevator().boardPassenger(psg);
                        temp.add(psg);
                    } catch (ElevatorFullException me) {
                        break;
                    }
                }
                for (Passenger psgTemp : temp) {
                    building.floor(currentFloorNo).waitingDown.remove(psgTemp);
                }
            }
        }
    }

    //board passengers on elevator
    public void boardPassenger(Passenger passenger) throws ElevatorFullException {
        if(passengers().size() == CAPACITY){
            throw new ElevatorFullException("Elevator is full.");
        } else {
            destinationFloors.get(passenger.destinationFloor()).add(passenger);
            passenger.boardElevator();
        }
    }

    //returns current floor number
    public int currentFloor() {
        return currentFloorNo;
    }

    //returns all the passengers currently in the elevator in a Set form
    public Set<Passenger> passengers() {
        Set <Passenger> passengersElevator = new HashSet<Passenger>();
        //loops over linkedHashMap to access values for each floor
        for (Map.Entry<Integer, Set<Passenger>> entry : destinationFloors.entrySet()){
            passengersElevator.addAll(entry.getValue());
        }
        return passengersElevator;
    }

    //checks to see if elevator is going up
    public boolean goingUp() {
        if (directionUp) {
            return true;
        } else {
            return false;
        }
    }

    //checks to see if elevator is going down
    public boolean goingDown() {
        if (directionUp) {
            return false;
        } else {
            return true;
        }
    }

}
