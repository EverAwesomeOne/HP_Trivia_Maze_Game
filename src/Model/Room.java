package Model;

public class Room {
    private Door northDoor;
    private Door eastDoor;
    private Door southDoor;
    private Door westDoor;

    Room() {
        northDoor = new Door();
        eastDoor = new Door();
        southDoor = new Door();
        westDoor = new Door();
    }

    Door getDoor(Direction direction) {
        if (direction == Direction.NORTH) {
            return northDoor;
        } else if (direction == Direction.EAST) {
            return eastDoor;
        } else if (direction == Direction.SOUTH) {
            return southDoor;
        } else {
            return westDoor;
        }
    }

    void setSharedDoor(Direction direction, Door doorToShare) {
        if (direction == Direction.NORTH) {
            northDoor = doorToShare;
        } else if (direction == Direction.EAST) {
            eastDoor = doorToShare;
        } else if (direction == Direction.SOUTH) {
            southDoor = doorToShare;
        } else {
            westDoor = doorToShare;
        }
    }
}
