package Model.Tests;

import Model.Direction;
import Model.Door;
import Model.Room;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
/*
    //Changed Room constructor visibility from package to public to test
    @Test
    public void testRoomConstructor(){
        Room room = new Room();
        //Make sure doors are not null
        assertNotNull(room.getDoor(Direction.NORTH));
        assertNotNull(room.getDoor(Direction.EAST));
        assertNotNull(room.getDoor(Direction.SOUTH));
        assertNotNull(room.getDoor(Direction.WEST));

        //Make sure doors aren't equal to each other
        assertNotEquals(room.getDoor(Direction.NORTH), room.getDoor(Direction.EAST));
        assertNotEquals(room.getDoor(Direction.NORTH), room.getDoor(Direction.SOUTH));
        assertNotEquals(room.getDoor(Direction.NORTH), room.getDoor(Direction.WEST));
        assertNotEquals(room.getDoor(Direction.EAST), room.getDoor(Direction.SOUTH));
        assertNotEquals(room.getDoor(Direction.EAST), room.getDoor(Direction.WEST));
        assertNotEquals(room.getDoor(Direction.SOUTH), room.getDoor(Direction.WEST));
    }

    //Changed Room constructor visibility from package to public to test
    //Changed myNorthDoor field visibility from private to public to test
    @Test
    public void testGetDoorForNorth() {
        Room room = new Room();
        assertEquals(room.myNorthDoor, room.getDoor(Direction.NORTH));
    }

    //Changed Room constructor visibility from package to public to test
    //Changed myEastDoor field visibility from private to public to test
    @Test
    public void testGetDoorForEast() {
        Room room = new Room();
        assertEquals(room.myEastDoor, room.getDoor(Direction.EAST));
    }

    //Changed Room constructor visibility from package to public to test
    //Changed mySouthDoor field visibility from private to public to test
    @Test
    public void testGetDoorForSouth() {
        Room room = new Room();
        assertEquals(room.mySouthDoor, room.getDoor(Direction.SOUTH));
    }

    //Changed Room constructor visibility from package to public to test
    //Changed myWestDoor field visibility from private to public to test
    @Test
    public void testGetDoorForWest() {
        Room room = new Room();
        assertEquals(room.myWestDoor, room.getDoor(Direction.WEST));
    }

    //Changed Room constructor visibility from package to public to test
    //Changed Door constructor visibility from package to public to test
    //Changed Room setSharedDoor visibility from package to public to test
    @Test
    public void testSetSharedDoorForNorth() {
        Room room = new Room();
        Door door = new Door();
        room.setSharedDoor(Direction.NORTH, door);
        assertEquals(door, room.getDoor(Direction.NORTH));
    }

    //Changed Room constructor visibility from package to public to test
    //Changed Door constructor visibility from package to public to test
    //Changed Room setSharedDoor visibility from package to public to test
    @Test
    public void testSetSharedDoorForEast() {
        Room room = new Room();
        Door door = new Door();
        room.setSharedDoor(Direction.EAST, door);
        assertEquals(door, room.getDoor(Direction.EAST));
    }

    //Changed Room constructor visibility from package to public to test
    //Changed Door constructor visibility from package to public to test
    //Changed Room setSharedDoor visibility from package to public to test
    @Test
    public void testSetSharedDoorForSouth() {
        Room room = new Room();
        Door door = new Door();
        room.setSharedDoor(Direction.SOUTH, door);
        assertEquals(door, room.getDoor(Direction.SOUTH));
    }

    //Changed Room constructor visibility from package to public to test
    //Changed Door constructor visibility from package to public to test
    //Changed Room setSharedDoor visibility from package to public to test
    @Test
    public void testSetSharedDoorForWest() {
        Room room = new Room();
        Door door = new Door();
        room.setSharedDoor(Direction.WEST, door);
        assertEquals(door, room.getDoor(Direction.WEST));
    }*/
}
