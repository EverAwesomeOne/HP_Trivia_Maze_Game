package Model.Tests;

import Model.Direction;
import Model.Door;
import Model.Room;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomTest {

    //Changed Room constructor visibility from package to public to test
    //Changed Door constructor visibility from package to public to test
    @Test
    public void testRoomConstructor(){
        Room room = new Room();
        //assertEquals(new Door(), );
    }

    //Changed Door constructor visibility from package to public to test
    //Changed myNorthDoor field visibility from private to public to test
    @Test
    public void testGetDoorForNorth() {
        Room room = new Room();
        assertEquals(room.myNorthDoor, room.getDoor(Direction.NORTH));
    }

    //Changed Door constructor visibility from package to public to test
    //Changed myEastDoor field visibility from private to public to test
    @Test
    public void testGetDoorForEast() {
        Room room = new Room();
        assertEquals(room.myEastDoor, room.getDoor(Direction.EAST));
    }

    //Changed Door constructor visibility from package to public to test
    //Changed mySouthDoor field visibility from private to public to test
    @Test
    public void testGetDoorForSouth() {
        Room room = new Room();
        assertEquals(room.mySouthDoor, room.getDoor(Direction.SOUTH));
    }

    //Changed Door constructor visibility from package to public to test
    //Changed myWestDoor field visibility from private to public to test
    @Test
    public void testGetDoorForWest() {
        Room room = new Room();
        assertEquals(room.myWestDoor, room.getDoor(Direction.WEST));
    }

    //Changed Room setSharedDoor visibility from package to public to test
    @Test
    public void testSetSharedDoor() {
        Room room = new Room();
    }
}
