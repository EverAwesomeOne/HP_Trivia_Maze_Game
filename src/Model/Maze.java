package Model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Maze {
    private Room[][] maze;
    private int characterRow;
    private int characterColumn;
    private LinkedList<Integer>[] roomConnections;

    private Connection conn;
    private Statement stmt;

    Maze(int chosenRows, int chosenColumns) {
        characterRow = 0;
        characterColumn = 0;

        for (int i = 0; i < chosenRows; i++) {
            for (int j = 0; j < chosenColumns; j++) {
                maze[i][j] = new Room();
            }
        }

        lockEdgeDoors();
        connectSharedDoors();
        createInitialGraph();
        openDatabaseConnection();
    }

    private void move(Direction directionToMove) {
        Door chosenDoor = maze[characterRow][characterColumn].getDoor(directionToMove);
        chosenDoor.getQuestion().getQuestionAnswerFromDatabase(stmt);
        if (!chosenDoor.isLocked()) {
            if (directionToMove == Direction.NORTH) {
                characterRow -= 1;
            } else if (directionToMove == Direction.EAST) {
                characterColumn += 1;
            } else if (directionToMove == Direction.SOUTH) {
                characterRow += 1;
            } else {
                characterColumn -= 1;
            }
        }
    }

    private boolean noValidPaths() {
        
        return false;
    }

    private void removeEdgeFromGraph(int firstNodeToRemoveFrom, int secondNodeToRemoveFrom) {
        roomConnections[firstNodeToRemoveFrom].remove((Integer) secondNodeToRemoveFrom);
        roomConnections[secondNodeToRemoveFrom].remove((Integer) firstNodeToRemoveFrom);
    }

    private void createInitialGraph() {
        roomConnections = new LinkedList[maze.length * maze[0].length];
        for (int i = 0; i < roomConnections.length; i++) {
            int rowIndex = i / maze.length;
            int columnIndex = i % maze[i].length;

            if (roomConnections[i] == null) {
                roomConnections[i] = new LinkedList<Integer>();
            }

            if (!maze[rowIndex][columnIndex].getDoor(Direction.NORTH).isLocked()) {
                roomConnections[i].add(i - maze.length);
            }
            if (!maze[rowIndex][columnIndex].getDoor(Direction.EAST).isLocked()) {
                roomConnections[i].add(i + 1);
            }
            if (!maze[rowIndex][columnIndex].getDoor(Direction.SOUTH).isLocked()) {
                roomConnections[i].add(i + maze.length);
            }
            if (!maze[rowIndex][columnIndex].getDoor(Direction.WEST).isLocked()) {
                roomConnections[i].add(i - 1);
            }
        }
    }

    private void connectSharedDoors() {
        // we want doors to adjacent rooms to be shared

        // east and west door of adjacent rooms
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length - 1; j++) {
                Door rightRoomWestDoor = maze[i][j + 1].getDoor(Direction.WEST);
                maze[i][j].setSharedDoor(Direction.EAST, rightRoomWestDoor);
            }
        }

        // north and south doors of adjacent rooms
        for (int i = 0; i < maze.length - 1; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                Door bottomRoomNorthDoor = maze[i][j].getDoor(Direction.NORTH);
                maze[i][j].setSharedDoor(Direction.SOUTH, bottomRoomNorthDoor);
            }
        }
    }

    private void lockEdgeDoors() {
        for (int i = 0; i < maze.length; i++) {
            // we want to lock the doors on the edge of the maze since there are no rooms beyond that
            maze[i][0].getDoor(Direction.WEST).lockDoor(); // left
            maze[i][maze[i].length - 1].getDoor(Direction.EAST).lockDoor(); // right
            maze[0][i].getDoor(Direction.NORTH).lockDoor(); // top
            maze[maze.length - 1][i].getDoor(Direction.SOUTH).lockDoor(); // bottom
        }
    }

    private void openDatabaseConnection() {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:questions.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void closeDatabaseConnection() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) { /* Ignored */}
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) { /* Ignored */}
        }
    }
}
