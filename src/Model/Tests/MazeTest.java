package Model.Tests;

import Model.Direction;
import Model.Maze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    /*
    IMPORTANT notes about how the maze works so that the tests below make sense:
     -The true maze is a 4 by 4, however, we have it set as a 7 by 7. This lets
     us match up the maze with the GUI which needs a 7 by 7 to also display doors.
     -Thus, actual (valid) rooms are only in even rows and even columns. In other
     words, if either the row or column are odd, then it is not a room.
     -When there are too many similar tests, only a few will be explained
     to give you an idea of the way they work and the math behind it.
     -Test names that have R<Number>, C<Number>, or R<Number>C<Number> are used
     to signify row and column. For example R4C2 signifies we are looking at the
     room in row 4, column 2
     -Before certain batches of tests, there are comments to denote which visibility
     changes were necessary to change for that batch only so that the tests could run.
     */
/*
    // For the maze constructor test below, had to change the visibility
    // of myMaze from private to public
    @Test
    public void testMazeConstructor() {
        Maze maze = new Maze(7);
        assertEquals(0, maze.getCharacterRow());
        assertEquals(0, maze.getCharacterColumn());
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                assertNotNull(maze.myMaze[i][j]);
            }
        }
    }

    // For the update position tests below, had to change the visibility of myCharacterRow
    // and myCharacterColumn from private to public.
    // Let us start the maze at row 2 column 2 rather than the default row 0 column 0
    // for the update position tests below.
    @Test
    public void testUpdatePositionNorth() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        maze.myCharacterColumn = 2;
        maze.updatePosition(Direction.NORTH);
        // Moving north means subtracting 2 from the row number.
        assertEquals(0, maze.myCharacterRow);
        assertEquals(2, maze.myCharacterColumn);
    }

    @Test
    public void testUpdatePositionEast() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        maze.myCharacterColumn = 2;
        maze.updatePosition(Direction.EAST);
        // Moving east means adding 2 to the column number.
        assertEquals(2, maze.myCharacterRow);
        assertEquals(4, maze.myCharacterColumn);
    }

    @Test
    public void testUpdatePositionSouth() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        maze.myCharacterColumn = 2;
        maze.updatePosition(Direction.SOUTH);
        // Moving south means adding 2 to the row number.
        assertEquals(4, maze.myCharacterRow);
        assertEquals(2, maze.myCharacterColumn);
    }

    @Test
    public void testUpdatePositionWest() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        maze.myCharacterColumn = 2;
        maze.updatePosition(Direction.WEST);
        // Moving west means subtracting 2 from the column number.
        assertEquals(2, maze.myCharacterRow);
        assertEquals(0, maze.myCharacterColumn);
    }

    // For the has won tests below, had to change myCharacterRow
    // and myCharacterColumn visibility from private to public
    @Test
    public void testHasWonFalseR0C0() {
        Maze maze = new Maze(7);
        // At starting position so should not have won yet.
        assertFalse(maze.hasWon());
    }

    @Test
    public void testHasWonFalseR4C2() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 4;
        maze.myCharacterColumn = 2;
        assertFalse(maze.hasWon());
    }

    @Test
    public void testHasWonFalseLoop() {
        Maze maze = new Maze(7);
        // For every position except row 6 column 6 this should be false.
        // Also, we update row and column by two because we only care about actual rooms.
        for (int i = 0; i < 7; i += 2) {
            maze.myCharacterRow = i;
            for (int j = 0; j < 7; j += 2) {
                maze.myCharacterColumn = j;
                if (i == 6 && j == 6) continue;
                assertFalse(maze.hasWon());
            }
        }
    }

    @Test
    public void testHasWonTrue() {
        Maze maze = new Maze(7);
        // The only time a player wins is when they reach the exit room which
        // is at row 6 column 6.
        maze.myCharacterRow = 6;
        maze.myCharacterColumn = 6;
        assertTrue(maze.hasWon());
    }

    @Test
    public void testHasValidPathsTrueR0C0NoEdgesRemoved() {
        Maze maze = new Maze(7);
        // At starting position and no edges have been removed so the game can still be won.
        assertTrue(maze.hasValidPaths());
    }

    @Test
    public void testHasValidPathsTrueNoEdgesRemovedLoop() {
        Maze maze = new Maze(7);
        // Goes through each room without removing any edges to show the game can still be won.
        for (int i = 0; i < 7; i += 2) {
            for (int j = 0; j < 7; j += 2) {
                assertTrue(maze.hasValidPaths());
            }
        }
    }

    @Test
    public void testHasValidPathsTrueR0C0RemoveEdgeToR0C2() {
        Maze maze = new Maze(7);
        assertTrue(maze.hasValidPaths());
        maze.removeEdgeFromGraph(Direction.EAST);
        // After removing only the edge between the starting room
        // and the room to its right, the game can still be won.
        assertTrue(maze.hasValidPaths());
    }

    @Test
    public void testHasValidPathsFalseR0C0RemoveBothEdges() {
        Maze maze = new Maze(7);
        assertTrue(maze.hasValidPaths());
        maze.removeEdgeFromGraph(Direction.EAST);
        // After removing only the edge between the starting room
        // and the room to its right, the game can still be won.
        assertTrue(maze.hasValidPaths());
        maze.removeEdgeFromGraph(Direction.SOUTH);
        // Next, after removing the edge between the starting room and
        // the room below it, the game can no longer be won since.
        assertFalse(maze.hasValidPaths());
    }

    @Test
    public void testHasValidPathsFalseR6C4() {
        Maze maze = new Maze(7);
        assertTrue(maze.hasValidPaths());
        maze.myCharacterRow = 4;
        maze.myCharacterColumn = 6;
        // Setting the row to 4 and column to 6 positions us above the exit so
        // removing the south edge at this point closes off one of two ways to the exit
        maze.removeEdgeFromGraph(Direction.SOUTH);
        assertTrue(maze.hasValidPaths());

        // Setting the row to 6 and column to 4 positions us to the left of the exit
        // so removing the east edge at this point closes off the only remaining edge to the exit
        maze.myCharacterRow = 6;
        maze.myCharacterColumn = 4;
        assertTrue(maze.hasValidPaths());
        maze.removeEdgeFromGraph(Direction.EAST);
        assertFalse(maze.hasValidPaths());
    }

    // For the all the tests below which use myRoomConnections the math is as follows:
    // - If there is a room to the right of another, then its index/room number is 2 greater
    // because valid rooms are only on even rows (thus we skip odd rows which are not real rooms).
    // For example, the room to the right of room 2 is room 4.
    // -For the same reasons as described above, the room to the left of another has an index/room
    // number which is 2 less. For example, the room to the left of room 2 is room 0.
    // -If there is a room below another, then its index/room number is 14 greater
    // because valid rooms are only on even columns (thus we skip odd rows which are ont real rooms).
    // For example, the room below room 2 is room 16 (because you add the length of a full row twice
    // and 2 * 7 = 14).
    // - For the same reasons as described above, the room above another has an index/room number
    // which is 14 less. For example, the room above room 30 is room 16.

    // Also, the order in which edges are added if they exist is North (above the room), East (to
    // the right of the room), South (below the room), and finally West (to the left of the room).

    // For the remove edge from graph tests below, had to change
    // the visibility of myRoomConnections from private to public
    @Test
    public void testRemoveEdgeFromGraph0To2() {
        Maze maze = new Maze(7);
        // Initially the beginning room should have two edges and the room to its
        // right should have three edges in the undirected graph
        assertEquals(2, maze.myRoomConnections[0].size());
        assertEquals(3, maze.myRoomConnections[2].size());

        // Only East (0 + 2 = 2) and South (0 + 14 = 14) edges should exist.
        assertEquals(2, maze.myRoomConnections[0].get(0));
        assertEquals(14, maze.myRoomConnections[0].get(1));
        // East (2 + 2 = 4), South (2 + 14 = 16), and West (2 - 2 = 0) edges should exist.
        assertEquals(4, maze.myRoomConnections[2].get(0));
        assertEquals(16, maze.myRoomConnections[2].get(1));
        assertEquals(0, maze.myRoomConnections[2].get(2));

        maze.removeEdgeFromGraph(Direction.EAST);

        // After removing the edge connecting room 0 to room 2, the size of each
        // room's list should decrease by 1.
        assertEquals(1, maze.myRoomConnections[0].size());
        assertEquals(2, maze.myRoomConnections[2].size());

        // The edge between room 0 and room 2 should be gone from both lists.
        assertEquals(14, maze.myRoomConnections[0].get(0));
        assertEquals(4, maze.myRoomConnections[2].get(0));
        assertEquals(16, maze.myRoomConnections[2].get(1));
    }

    @Test
    public void testRemoveEdgeFromGraph0To14() {
        Maze maze = new Maze(7);
        assertEquals(2, maze.myRoomConnections[0].size());
        assertEquals(3, maze.myRoomConnections[14].size());

        assertEquals(2, maze.myRoomConnections[0].get(0));
        assertEquals(14, maze.myRoomConnections[0].get(1));
        assertEquals(0, maze.myRoomConnections[14].get(0));
        assertEquals(16, maze.myRoomConnections[14].get(1));
        assertEquals(28, maze.myRoomConnections[14].get(2));

        maze.removeEdgeFromGraph(Direction.SOUTH);

        assertEquals(1, maze.myRoomConnections[0].size());
        assertEquals(2, maze.myRoomConnections[14].size());

        assertEquals(2, maze.myRoomConnections[0].get(0));
        assertEquals(16, maze.myRoomConnections[14].get(0));
        assertEquals(28, maze.myRoomConnections[14].get(1));
    }

    @Test
    public void testRemoveEdgeFromGraph18To32() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        maze.myCharacterColumn = 4;

        assertEquals(4, maze.myRoomConnections[18].size());
        assertEquals(4, maze.myRoomConnections[32].size());

        assertEquals(4, maze.myRoomConnections[18].get(0));
        assertEquals(20, maze.myRoomConnections[18].get(1));
        assertEquals(32, maze.myRoomConnections[18].get(2));
        assertEquals(16, maze.myRoomConnections[18].get(3));
        assertEquals(18, maze.myRoomConnections[32].get(0));
        assertEquals(34, maze.myRoomConnections[32].get(1));
        assertEquals(46, maze.myRoomConnections[32].get(2));
        assertEquals(30, maze.myRoomConnections[32].get(3));

        maze.removeEdgeFromGraph(Direction.SOUTH);

        assertEquals(3, maze.myRoomConnections[18].size());
        assertEquals(3, maze.myRoomConnections[32].size());

        assertEquals(4, maze.myRoomConnections[18].get(0));
        assertEquals(20, maze.myRoomConnections[18].get(1));
        assertEquals(16, maze.myRoomConnections[18].get(2));
        assertEquals(34, maze.myRoomConnections[32].get(0));
        assertEquals(46, maze.myRoomConnections[32].get(1));
        assertEquals(30, maze.myRoomConnections[32].get(2));
    }

    // For the create initial graph tests below, had to change
    // the visibility of myRoomConnections from private to public
    @Test
    public void testCreateInitialGraphR0C0() {
        Maze maze = new Maze(7);
        // Only East (0 + 2 = 2) and South (0 + 14 = 14) edges should exist.
        assertEquals(2, maze.myRoomConnections[0].get(0));
        assertEquals(14, maze.myRoomConnections[0].get(1));
    }

    @Test
    public void testCreateInitialGraphR0C2() {
        Maze maze = new Maze(7);
        // East (2 + 2 = 4), South (2 + 14 = 16), and West (2 - 2 = 0) edges should exist.
        assertEquals(4, maze.myRoomConnections[2].get(0));
        assertEquals(16, maze.myRoomConnections[2].get(1));
        assertEquals(0, maze.myRoomConnections[2].get(2));
    }

    @Test
    public void testCreateInitialGraphR0C4() {
        Maze maze = new Maze(7);
        assertEquals(6, maze.myRoomConnections[4].get(0));
        assertEquals(18, maze.myRoomConnections[4].get(1));
        assertEquals(2, maze.myRoomConnections[4].get(2));
    }

    @Test
    public void testCreateInitialGraphR0C6() {
        Maze maze = new Maze(7);
        assertEquals(20, maze.myRoomConnections[6].get(0));
        assertEquals(4, maze.myRoomConnections[6].get(1));
    }

    @Test
    public void testCreateInitialGraphR2C0() {
        Maze maze = new Maze(7);
        assertEquals(0, maze.myRoomConnections[14].get(0));
        assertEquals(16, maze.myRoomConnections[14].get(1));
        assertEquals(28, maze.myRoomConnections[14].get(2));
    }

    @Test
    public void testCreateInitialGraphR2C2() {
        Maze maze = new Maze(7);
        // Should have all edges: North (16 - 14 = 2), East (16 + 2 = 18),
        // South (16 + 14 = 30), and West (16 - 2 = 14).
        assertEquals(2, maze.myRoomConnections[16].get(0));
        assertEquals(18, maze.myRoomConnections[16].get(1));
        assertEquals(30, maze.myRoomConnections[16].get(2));
        assertEquals(14, maze.myRoomConnections[16].get(3));
    }

    @Test
    public void testCreateInitialGraphR2C4() {
        Maze maze = new Maze(7);
        assertEquals(4, maze.myRoomConnections[18].get(0));
        assertEquals(20, maze.myRoomConnections[18].get(1));
        assertEquals(32, maze.myRoomConnections[18].get(2));
        assertEquals(16, maze.myRoomConnections[18].get(3));
    }

    @Test
    public void testCreateInitialGraphR2C6() {
        Maze maze = new Maze(7);
        assertEquals(6, maze.myRoomConnections[20].get(0));
        assertEquals(34, maze.myRoomConnections[20].get(1));
        assertEquals(18, maze.myRoomConnections[20].get(2));
    }

    @Test
    public void testCreateInitialGraphR4C0() {
        Maze maze = new Maze(7);
        assertEquals(14, maze.myRoomConnections[28].get(0));
        assertEquals(30, maze.myRoomConnections[28].get(1));
        assertEquals(42, maze.myRoomConnections[28].get(2));
    }

    @Test
    public void testCreateInitialGraphR4C2() {
        Maze maze = new Maze(7);
        assertEquals(16, maze.myRoomConnections[30].get(0));
        assertEquals(32, maze.myRoomConnections[30].get(1));
        assertEquals(44, maze.myRoomConnections[30].get(2));
        assertEquals(28, maze.myRoomConnections[30].get(3));
    }

    @Test
    public void testCreateInitialGraphR4C4() {
        Maze maze = new Maze(7);
        assertEquals(18, maze.myRoomConnections[32].get(0));
        assertEquals(34, maze.myRoomConnections[32].get(1));
        assertEquals(46, maze.myRoomConnections[32].get(2));
        assertEquals(30, maze.myRoomConnections[32].get(3));
    }

    @Test
    public void testCreateInitialGraphR4C6() {
        Maze maze = new Maze(7);
        assertEquals(20, maze.myRoomConnections[34].get(0));
        assertEquals(48, maze.myRoomConnections[34].get(1));
        assertEquals(32, maze.myRoomConnections[34].get(2));
    }

    @Test
    public void testCreateInitialGraphR6C0() {
        Maze maze = new Maze(7);
        assertEquals(28, maze.myRoomConnections[42].get(0));
        assertEquals(44, maze.myRoomConnections[42].get(1));
    }

    @Test
    public void testCreateInitialGraphR6C2() {
        Maze maze = new Maze(7);
        assertEquals(30, maze.myRoomConnections[44].get(0));
        assertEquals(46, maze.myRoomConnections[44].get(1));
        assertEquals(42, maze.myRoomConnections[44].get(2));
    }

    @Test
    public void testCreateInitialGraphR6C4() {
        Maze maze = new Maze(7);
        assertEquals(32, maze.myRoomConnections[46].get(0));
        assertEquals(48, maze.myRoomConnections[46].get(1));
        assertEquals(44, maze.myRoomConnections[46].get(2));
    }

    @Test
    public void testCreateInitialGraphR6C6() {
        Maze maze = new Maze(7);
        assertEquals(34, maze.myRoomConnections[48].get(0));
        assertEquals(46, maze.myRoomConnections[48].get(1));
    }

    // For the connect shared doors tests below, had to change
    // the visibility of myMaze from private to public
    @Test
    public void testConnectSharedDoorsR0C0() {
        Maze maze = new Maze(7);
        // R0C0's right door (East) should be the same as the left door (West) of R0C2.
        assertEquals(maze.myMaze[0][0].getDoor(Direction.EAST), maze.myMaze[0][2].getDoor(Direction.WEST));
        // R0C0's bottom door (South) should be the same as the top door (North) of R2C0.
        assertEquals(maze.myMaze[0][0].getDoor(Direction.SOUTH), maze.myMaze[2][0].getDoor(Direction.NORTH));
    }

    @Test
    public void testConnectSharedDoorsR0C2() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[0][2].getDoor(Direction.EAST), maze.myMaze[0][4].getDoor(Direction.WEST));
        assertEquals(maze.myMaze[0][2].getDoor(Direction.SOUTH), maze.myMaze[2][2].getDoor(Direction.NORTH));
        assertEquals(maze.myMaze[0][2].getDoor(Direction.WEST), maze.myMaze[0][0].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR0C4() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[0][4].getDoor(Direction.EAST), maze.myMaze[0][6].getDoor(Direction.WEST));
        assertEquals(maze.myMaze[0][4].getDoor(Direction.SOUTH), maze.myMaze[2][4].getDoor(Direction.NORTH));
        assertEquals(maze.myMaze[0][4].getDoor(Direction.WEST), maze.myMaze[0][2].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR0C6() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[0][6].getDoor(Direction.SOUTH), maze.myMaze[2][6].getDoor(Direction.NORTH));
        assertEquals(maze.myMaze[0][6].getDoor(Direction.WEST), maze.myMaze[0][4].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR2C0() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[2][0].getDoor(Direction.NORTH), maze.myMaze[0][0].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[2][0].getDoor(Direction.EAST), maze.myMaze[2][2].getDoor(Direction.WEST));
        assertEquals(maze.myMaze[2][0].getDoor(Direction.SOUTH), maze.myMaze[4][0].getDoor(Direction.NORTH));
    }

    @Test
    public void testConnectSharedDoorsR2C2() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[2][2].getDoor(Direction.NORTH), maze.myMaze[0][2].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[2][2].getDoor(Direction.EAST), maze.myMaze[2][4].getDoor(Direction.WEST));
        assertEquals(maze.myMaze[2][2].getDoor(Direction.SOUTH), maze.myMaze[4][2].getDoor(Direction.NORTH));
        assertEquals(maze.myMaze[2][2].getDoor(Direction.WEST), maze.myMaze[2][0].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR2C4() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[2][4].getDoor(Direction.NORTH), maze.myMaze[0][4].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[2][4].getDoor(Direction.EAST), maze.myMaze[2][6].getDoor(Direction.WEST));
        assertEquals(maze.myMaze[2][4].getDoor(Direction.SOUTH), maze.myMaze[4][4].getDoor(Direction.NORTH));
        assertEquals(maze.myMaze[2][4].getDoor(Direction.WEST), maze.myMaze[2][2].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR2C6() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[2][6].getDoor(Direction.NORTH), maze.myMaze[0][6].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[2][6].getDoor(Direction.SOUTH), maze.myMaze[4][6].getDoor(Direction.NORTH));
        assertEquals(maze.myMaze[2][6].getDoor(Direction.WEST), maze.myMaze[2][4].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR4C0() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[4][0].getDoor(Direction.NORTH), maze.myMaze[2][0].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[4][0].getDoor(Direction.EAST), maze.myMaze[4][2].getDoor(Direction.WEST));
        assertEquals(maze.myMaze[4][0].getDoor(Direction.SOUTH), maze.myMaze[6][0].getDoor(Direction.NORTH));
    }

    @Test
    public void testConnectSharedDoorsR4C2() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[4][2].getDoor(Direction.NORTH), maze.myMaze[2][2].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[4][2].getDoor(Direction.EAST), maze.myMaze[4][4].getDoor(Direction.WEST));
        assertEquals(maze.myMaze[4][2].getDoor(Direction.SOUTH), maze.myMaze[6][2].getDoor(Direction.NORTH));
        assertEquals(maze.myMaze[4][2].getDoor(Direction.WEST), maze.myMaze[4][0].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR4C4() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[4][4].getDoor(Direction.NORTH), maze.myMaze[2][4].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[4][4].getDoor(Direction.EAST), maze.myMaze[4][6].getDoor(Direction.WEST));
        assertEquals(maze.myMaze[4][4].getDoor(Direction.SOUTH), maze.myMaze[6][4].getDoor(Direction.NORTH));
        assertEquals(maze.myMaze[4][4].getDoor(Direction.WEST), maze.myMaze[4][2].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR4C6() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[4][6].getDoor(Direction.NORTH), maze.myMaze[2][6].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[4][6].getDoor(Direction.SOUTH), maze.myMaze[6][6].getDoor(Direction.NORTH));
        assertEquals(maze.myMaze[4][6].getDoor(Direction.WEST), maze.myMaze[4][4].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR6C0() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[6][0].getDoor(Direction.NORTH), maze.myMaze[4][0].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[6][0].getDoor(Direction.EAST), maze.myMaze[6][2].getDoor(Direction.WEST));
    }

    @Test
    public void testConnectSharedDoorsR6C2() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[6][2].getDoor(Direction.NORTH), maze.myMaze[4][2].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[6][2].getDoor(Direction.EAST), maze.myMaze[6][4].getDoor(Direction.WEST));
        assertEquals(maze.myMaze[6][2].getDoor(Direction.WEST), maze.myMaze[6][0].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR6C4() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[6][4].getDoor(Direction.NORTH), maze.myMaze[4][4].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[6][4].getDoor(Direction.EAST), maze.myMaze[6][6].getDoor(Direction.WEST));
        assertEquals(maze.myMaze[6][4].getDoor(Direction.WEST), maze.myMaze[6][2].getDoor(Direction.EAST));
    }

    @Test
    public void testConnectSharedDoorsR6C6() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[6][6].getDoor(Direction.NORTH), maze.myMaze[4][6].getDoor(Direction.SOUTH));
        assertEquals(maze.myMaze[6][6].getDoor(Direction.WEST), maze.myMaze[6][4].getDoor(Direction.EAST));
    }

    // Edge doors are doors that would lead outside the maze, thus, there is no room
    // in that direction and should be locked to avoid errors. Rooms in row 0 should have
    // their North door locked, rooms in column 6 should have their East door locked, rooms
    // in row 6 should have their South door locked, and rooms in column 0 should have their
    // West door locked.

    // For the lock edge doors tests below, had to change
    // the visibility of myMaze from private to public
    @Test
    public void testLockEdgeDoorsR0C0() {
        Maze maze = new Maze(7);
        assertTrue(maze.myMaze[0][0].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[0][0].getDoor(Direction.EAST).isLocked());
        assertTrue(maze.myMaze[0][0].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[0][0].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR0C2() {
        Maze maze = new Maze(7);
        assertTrue(maze.myMaze[0][2].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[0][2].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[0][2].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[0][2].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR0C4() {
        Maze maze = new Maze(7);
        assertTrue(maze.myMaze[0][4].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[0][4].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[0][4].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[0][4].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR0C6() {
        Maze maze = new Maze(7);
        assertTrue(maze.myMaze[0][6].getDoor(Direction.NORTH).isLocked());
        assertTrue(maze.myMaze[0][6].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[0][6].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[0][6].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR2C0() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[2][0].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[2][0].getDoor(Direction.EAST).isLocked());
        assertTrue(maze.myMaze[2][0].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[2][0].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR2C2() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[2][2].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[2][2].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[2][2].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[2][2].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR2C4() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[2][4].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[2][4].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[2][4].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[2][4].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR2C6() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[2][6].getDoor(Direction.NORTH).isLocked());
        assertTrue(maze.myMaze[2][6].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[2][6].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[2][6].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR4C0() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[4][0].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[4][0].getDoor(Direction.EAST).isLocked());
        assertTrue(maze.myMaze[4][0].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[4][0].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR4C2() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[4][2].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[4][2].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[4][2].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[4][2].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR4C4() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[4][4].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[4][4].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[4][4].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[4][4].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR4C6() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[4][6].getDoor(Direction.NORTH).isLocked());
        assertTrue(maze.myMaze[4][6].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[4][6].getDoor(Direction.WEST).isLocked());
        assertFalse(maze.myMaze[4][6].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR6C0() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[6][0].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[6][0].getDoor(Direction.EAST).isLocked());
        assertTrue(maze.myMaze[6][0].getDoor(Direction.WEST).isLocked());
        assertTrue(maze.myMaze[6][0].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR6C2() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[6][2].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[6][2].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[6][2].getDoor(Direction.WEST).isLocked());
        assertTrue(maze.myMaze[6][2].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR6C4() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[6][4].getDoor(Direction.NORTH).isLocked());
        assertFalse(maze.myMaze[6][4].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[6][4].getDoor(Direction.WEST).isLocked());
        assertTrue(maze.myMaze[6][4].getDoor(Direction.SOUTH).isLocked());
    }

    @Test
    public void testLockEdgeDoorsR6C6() {
        Maze maze = new Maze(7);
        assertFalse(maze.myMaze[6][6].getDoor(Direction.NORTH).isLocked());
        assertTrue(maze.myMaze[6][6].getDoor(Direction.EAST).isLocked());
        assertFalse(maze.myMaze[6][6].getDoor(Direction.WEST).isLocked());
        assertTrue(maze.myMaze[6][6].getDoor(Direction.SOUTH).isLocked());
    }

    // For the get current room tests below, had to change the visibility of
    // myMaze, myCharacterRow, and myCharacterColumn from private to public
    @Test
    public void testGetCurrentRoomR0C0() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[0][0], maze.getCurrentRoom());
    }

    @Test
    public void testGetCurrentRoomR2C6() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        maze.myCharacterColumn = 6;
        assertEquals(maze.myMaze[2][6], maze.getCurrentRoom());
    }

    @Test
    public void testGetCurrentRoomR4C4() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 4;
        maze.myCharacterColumn = 4;
        assertEquals(maze.myMaze[4][4], maze.getCurrentRoom());
    }

    @Test
    public void testGetCurrentRoomR6C2() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 6;
        maze.myCharacterColumn = 2;
        assertEquals(maze.myMaze[6][2], maze.getCurrentRoom());
    }

    @Test
    public void testGetCurrentRoomR6C6() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 6;
        maze.myCharacterColumn = 6;
        assertEquals(maze.myMaze[6][6], maze.getCurrentRoom());
    }

    // The following loop changes the row and column to match each possible
    // valid room and checks that the get current room works for all
    @Test
    public void testGetCurrentRoomLoop() {
        Maze maze = new Maze(7);
        for (int i = 0; i < 7; i += 2) {
            maze.myCharacterRow = i;
            for (int j = 0; j < 7; j += 2) {
                maze.myCharacterColumn = j;
                assertEquals(maze.myMaze[i][j], maze.getCurrentRoom());
            }
        }
    }

    // For the get specific room tests below, had to change
    // the visibility of myMaze from private to public
    @Test
    public void testGetSpecificRoomR2C6() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[2][6], maze.getSpecificRoom(2, 6));
    }

    @Test
    public void testGetSpecificRoomR4C2() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[4][2], maze.getSpecificRoom(4, 2));
    }

    @Test
    public void testGetSpecificRoomR6C0() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[6][0], maze.getSpecificRoom(6, 0));
    }

    // The following loop changes the row and column to match each possible
    // valid room and checks that the get specific room works
    @Test
    public void testGetSpecificRoomLoop() {
        Maze maze = new Maze(7);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals(maze.myMaze[i][j], maze.getSpecificRoom(i, j));
            }
        }
    }

    // For the get character row and get character column tests below, had to change the
    // visibility of myCharacterRow and myCharacterColumn from private to public
    @Test
    public void testGetCharacterRowR0() {
        Maze maze = new Maze(7);
        assertEquals(0, maze.getCharacterRow());
    }

    @Test
    public void testGetCharacterRowR2() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        assertEquals(2, maze.getCharacterRow());
    }

    @Test
    public void testGetCharacterRowR4() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 4;
        assertEquals(4, maze.getCharacterRow());
    }

    @Test
    public void testGetCharacterRowR6() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 6;
        assertEquals(6, maze.getCharacterRow());
    }

    @Test
    public void testGetCharacterColumnC0() {
        Maze maze = new Maze(7);
        assertEquals(0, maze.getCharacterColumn());
    }

    @Test
    public void testGetCharacterColumnC2() {
        Maze maze = new Maze(7);
        maze.myCharacterColumn = 2;
        assertEquals(2, maze.getCharacterColumn());
    }

    @Test
    public void testGetCharacterColumnC4() {
        Maze maze = new Maze(7);
        maze.myCharacterColumn = 4;
        assertEquals(4, maze.getCharacterColumn());
    }

    @Test
    public void testGetCharacterColumnC6() {
        Maze maze = new Maze(7);
        maze.myCharacterColumn = 6;
        assertEquals(6, maze.getCharacterColumn());
    }

    // The following loop changes the row and column to match each possible
    // valid room and checks that the get current row and column both work
    @Test
    public void testGetCharacterRowAndColumnLoop() {
        Maze maze = new Maze(7);
        for (int i = 0; i < 7; i += 2) {
            maze.myCharacterRow = i;
            for (int j = 0; j < 7; j += 2) {
                maze.myCharacterColumn = j;
                assertEquals(i, maze.getCharacterRow());
                assertEquals(j, maze.getCharacterColumn());
            }
        }
    }
*/
}
