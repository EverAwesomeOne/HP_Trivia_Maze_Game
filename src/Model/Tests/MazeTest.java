package Model.Tests;

import Model.Direction;
import Model.Maze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

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
    // and myCharacterColumn from private to public
    @Test
    public void testUpdatePositionNorth() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        maze.myCharacterColumn = 2;
        maze.updatePosition(Direction.NORTH);
        assertEquals(0, maze.myCharacterRow);
        assertEquals(2, maze.myCharacterColumn);
    }

    @Test
    public void testUpdatePositionEast() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        maze.myCharacterColumn = 2;
        maze.updatePosition(Direction.EAST);
        assertEquals(2, maze.myCharacterRow);
        assertEquals(4, maze.myCharacterColumn);
    }

    @Test
    public void testUpdatePositionSouth() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        maze.myCharacterColumn = 2;
        maze.updatePosition(Direction.SOUTH);
        assertEquals(4, maze.myCharacterRow);
        assertEquals(2, maze.myCharacterColumn);
    }

    @Test
    public void testUpdatePositionWest() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 2;
        maze.myCharacterColumn = 2;
        maze.updatePosition(Direction.WEST);
        assertEquals(2, maze.myCharacterRow);
        assertEquals(0, maze.myCharacterColumn);
    }

    @Test
    public void testHasWonNotTrueR0C0() {
        Maze maze = new Maze(7);
        assertFalse(maze.hasWon());
    }

    @Test
    public void testHasWonNotTrueR4C2() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 4;
        maze.myCharacterColumn = 2;
        assertFalse(maze.hasWon());
    }

    @Test
    public void testHasWonNotTrueLoop() {
        Maze maze = new Maze(7);
        for (int i = 0; i < 6; i++) {
            maze.myCharacterRow = i;
            for (int j = 0; j < 6; j++) {
                maze.myCharacterColumn = j;
                assertFalse(maze.hasWon());
            }
        }
    }

    @Test
    public void testHasWonTrue() {
        Maze maze = new Maze(7);
        maze.myCharacterRow = 6;
        maze.myCharacterColumn = 6;
        assertTrue(maze.hasWon());
    }

    @Test
    public void testCreateInitialGraph() {
        Maze maze = new Maze(7);
    }

    @Test
    public void testRemoveEdgeFromGraph() {
        Maze maze = new Maze(7);
    }

    @Test
    public void testHasValidPathsNotTrue() {
        Maze maze = new Maze(7);
    }

    @Test
    public void testHasValidPathsTrue() {
        Maze maze = new Maze(7);
    }

    @Test
    public void testConnectSharedDoorsR0C0() {
        Maze maze = new Maze(7);
        assertEquals(maze.myMaze[0][0].getDoor(Direction.EAST), maze.myMaze[0][2].getDoor(Direction.WEST));
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
}
