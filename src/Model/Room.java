package Model;

public class Room {
    private Door myNorthDoor;
    private Door myEastDoor;
    private Door mySouthDoor;
    private Door myWestDoor;

    Room() {
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
