package Model;

public class Room {
    private Door northDoor;
    private Door eastDoor;
    private Door southDoor;
    private Door westDoor;

    Room() {

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
}
