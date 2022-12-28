package Model;

import java.io.Serial;
import java.io.Serializable;

/**
 * The Room class represents a specific room in the maze.
 */
public class Room implements Serializable {
    private Door myNorthDoor;
    private Door myEastDoor;
    private Door mySouthDoor;
    private Door myWestDoor;

    @Serial
    private static final long serialVersionUID = 109174852462682090L;

    /**
     * Constructor for the Room class.
     * Initializes all four doors that are in the room.
     */
    Room() {
        myNorthDoor = new Door();
        myEastDoor = new Door();
        mySouthDoor = new Door();
        myWestDoor = new Door();
    }

    /**
     * Gets the door specified from the direction being given.
     * @param theDirection - the direction of the door needed
     * @return - the door located in that specific direction
     */
    public Door getDoor(final Direction theDirection) {
        if (theDirection == Direction.NORTH) {
            return myNorthDoor;
        } else if (theDirection == Direction.EAST) {
            return myEastDoor;
        } else if (theDirection == Direction.SOUTH) {
            return mySouthDoor;
        } else {
            return myWestDoor;
        }
    }

    /**
     * Sets a door to have the same reference as another door depending on the
     * direction to make it easy to update when a player goes through a door
     * and into another room.
     * @param theDirection - the direction in which the other door is located
     * @param theDoorToShare - the door that will share the reference
     */
    void setSharedDoor(final Direction theDirection, final Door theDoorToShare) {
        if (theDirection == Direction.NORTH) {
            myNorthDoor = theDoorToShare;
        } else if (theDirection == Direction.EAST) {
            myEastDoor = theDoorToShare;
        } else if (theDirection == Direction.SOUTH) {
            mySouthDoor = theDoorToShare;
        } else {
            myWestDoor = theDoorToShare;
        }
    }
}
