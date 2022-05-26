package Model;

public class Room {
    public Door myNorthDoor;
    public Door myEastDoor;
    public Door mySouthDoor;
    public Door myWestDoor;

    public Room() {
        myNorthDoor = new Door();
        myEastDoor = new Door();
        mySouthDoor = new Door();
        myWestDoor = new Door();
    }

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
