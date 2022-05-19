package Controller;

import Model.Direction;
import Model.Door;
import Model.Maze;
import Model.Question_Answer;
import View.*;
import org.sqlite.SQLiteDataSource;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TriviaMazeBrain {
    private Connection conn;
    private Statement stmt;
    private Maze maze;
    private GamePanel gamePanel;
    private MainFrame mainFrame;
    private MazePanel mazePanel;

    public static void main(String[] args) {
        TriviaMazeBrain triviaMazeBrain = new TriviaMazeBrain();
    }

    public TriviaMazeBrain() {
        runGame();
    }

    private void runGame() {
        maze = new Maze(4, 4);
        openDatabaseConnection();
        mainFrame = new MainFrame(this);

    }

    public void move(String directionType) {
        gamePanel = mainFrame.getTheMainMenuPanel().getGamePanel();
        mazePanel = gamePanel.getMyMazePanel();
        Direction directionToMove = Direction.valueOf(directionType);
        Door chosenDoor = maze.getCurrentRoom().getDoor(directionToMove);
        // check first time of door to move freely
        Question_Answer qa = chosenDoor.getQuestion();
        qa.getQuestionAnswerFromDatabase(stmt);
        gamePanel.askQuestion(qa.getQuestionList(), directionType);
    }

    public void move2 (String userAnswer, String directionType) {

        Direction directionToMove = Direction.valueOf(directionType);
        Door chosenDoor = maze.getCurrentRoom().getDoor(directionToMove);
        Question_Answer qa = chosenDoor.getQuestion();
        //qa.getQuestionAnswerFromDatabase(stmt);

        if (!qa.selectedCorrectAnswer(userAnswer)) {
            chosenDoor.lockDoor();
            maze.removeEdgeFromGraph(Direction.valueOf(directionType));
        }

        if (!maze.hasValidPaths()) {
            JDialog losingMessaging = new JDialog();
            losingMessaging.setTitle("Game Over :((");
            JButton exitGameButton = new JButton("Take the L");
            exitGameButton.addActionListener(
                    e -> {
                        gamePanel.setVisible(false);
                        //mainMenuBar.setVisible(false);
                        new MainMenuPanel(mainFrame, this);
                    }
            );

            losingMessaging.add(exitGameButton);
            losingMessaging.setSize(300, 100);
            losingMessaging.setLocationRelativeTo(null);
            losingMessaging.setVisible(true);
        }

        if(!chosenDoor.isLocked()) {
            maze.updatePosition(directionToMove);
        }
        mazePanel.updateCharacterPlacement(maze.getCharacterRow(), maze.getCharacterColumn());
    }

    public boolean checkIsLockedStatus(String directionType) {
        return !maze.getCurrentRoom().getDoor(Direction.valueOf(directionType)).isLocked();
    }

    public void resetGameState() {
        maze = new Maze(4, 4);
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
