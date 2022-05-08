package Model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Maze {
    private Room[][] maze;
    private int characterRow;
    private int characterColumn;

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

    private void lockEdgeDoors() {
        for (int i = 0; i < maze.length; i++) {
            maze[i][0].getDoor(Direction.WEST).lockDoor();
            maze[i][maze[i].length - 1].getDoor(Direction.EAST).lockDoor();
            maze[0][i].getDoor(Direction.NORTH).lockDoor();
            maze[maze.length - 1][i].getDoor(Direction.SOUTH).lockDoor();
        }
    }

    private void openDatabaseConnection()  {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:questions.db");
        } catch ( Exception e ) {
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
