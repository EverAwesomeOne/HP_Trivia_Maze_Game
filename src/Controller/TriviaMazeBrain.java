package Controller;

import Model.Direction;
import Model.Door;
import Model.Maze;
import Model.Question_Answer;
import View.QuestionPanel;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TriviaMazeBrain {
    private Connection conn;
    private Statement stmt;
    private Maze maze;

    private void runGame() {
        maze = new Maze(4, 4);
        openDatabaseConnection();
    }

    public void move(String directionType) {
        Direction directionToMove = Direction.valueOf(directionType);
        Door chosenDoor = maze.getCurrentRoom().getDoor(directionToMove);
        Question_Answer qa = chosenDoor.getQuestion();
        qa.getQuestionAnswerFromDatabase(stmt);
        String userAnswer = QuestionPanel.askQuestion(qa.getQuestionList());

        if (!qa.selectedCorrectAnswer(userAnswer)) {
            chosenDoor.lockDoor();
        }

        if(!chosenDoor.isLocked()) {
            maze.updatePosition(directionToMove);
        }
        updateCharacterPlacement(maze.getCharacterRow(), maze.getCharacterColumn());
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
