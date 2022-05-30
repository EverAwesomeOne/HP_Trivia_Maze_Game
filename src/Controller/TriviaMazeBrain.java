package Controller;

import Model.Direction;
import Model.Door;
import Model.Maze;
import Model.QuestionAnswer;
import View.*;
import org.sqlite.SQLiteDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TriviaMazeBrain {
    private Connection myConnection;
    private Statement myStatement;
    private Maze myMaze;
    private GamePanel myGamePanel;
    private MainFrame myMainFrame;
    private MazePanel myMazePanel;

    private String triviaMazeFile = "triviaMaze.txt";

    public final static int MAZE_LENGTH = 7;
    public final static String MOVE_FREELY = "Move freely";

    public static void main(final String[] theArgs) {
        new TriviaMazeBrain();
    }

    public TriviaMazeBrain() {
        runGame();
    }

    private void runGame() {
        myMaze = new Maze(MAZE_LENGTH);
        openDatabaseConnection();
        myMainFrame = new MainFrame(this);
    }

    public void setUpQuestion(final String theDirectionType) {
        myGamePanel = myMainFrame.getGamePanel();
        myMazePanel = myGamePanel.getMazePanel();
        final Direction directionToMove = Direction.valueOf(theDirectionType);
        final Door chosenDoor = myMaze.getCurrentRoom().getDoor(directionToMove);
        // check first time of door to move freely
        if (chosenDoor.wasFirstTime()) {
            final QuestionAnswer qa = chosenDoor.getQuestion();
            qa.getQuestionAnswerFromDatabase(myStatement);
            myGamePanel.askQuestion(qa.getQuestionList(), theDirectionType);
        } else {
            moveCharacter(MOVE_FREELY, theDirectionType);
        }
    }

    public void moveCharacter(final String theUserAnswer, final String theDirectionType) {

        final Direction directionToMove = Direction.valueOf(theDirectionType);
        final Door chosenDoor = myMaze.getCurrentRoom().getDoor(directionToMove);
        final QuestionAnswer qa = chosenDoor.getQuestion();

        if (MOVE_FREELY.equals(theUserAnswer)) {
            myMaze.updatePosition(directionToMove);
        } else if (qa.selectedCorrectAnswer(theUserAnswer)) {
            answeredCorrectly(theUserAnswer, theDirectionType, directionToMove, qa);
            chosenDoor.setFirstTime(false);
        } else {
            answeredIncorrectly(theUserAnswer, theDirectionType, chosenDoor, qa);
        }

        myGamePanel.getDirectionButtonPanel().setDirectionButtonsVisibility();
        myMazePanel.updateCharacterPlacement(myMaze.getCharacterRow(),
                myMaze.getCharacterColumn());
    }

    private void answeredCorrectly(final String theUserAnswer, final String theDirectionType,
                                   final Direction theDirectionToMove, final QuestionAnswer theQA) {
        myMazePanel.setDoorIcon(myMaze.getCharacterRow(), myMaze.getCharacterColumn(),
                theDirectionType, theQA.selectedCorrectAnswer(theUserAnswer));
        myMaze.updatePosition(theDirectionToMove);
        if (myMaze.hasWon()) {
            myGamePanel.displayWinningMessageBox();
        } else {
            myGamePanel.displayCorrectAnswerMessageBox();
        }
    }

    private void answeredIncorrectly(final String theUserAnswer, final String theDirectionType,
                                     final Door theChosenDoor, final QuestionAnswer theQA) {
        theChosenDoor.lockDoor();
        myMazePanel.setDoorIcon(myMaze.getCharacterRow(), myMaze.getCharacterColumn(),
                theDirectionType, theQA.selectedCorrectAnswer(theUserAnswer));
        myMaze.removeEdgeFromGraph(Direction.valueOf(theDirectionType));
        if (!myMaze.hasValidPaths()) {
            myGamePanel.displayLosingMessageBox();
        } else {
            myGamePanel.displayIncorrectAnswerMessageBox();
        }
    }

    public boolean checkCurrentRoomIsLockedStatus(final String theDirectionType) {
        return !myMaze.getCurrentRoom().getDoor
                (Direction.valueOf(theDirectionType)).isLocked();
    }

    public boolean checkSpecificRoomIsNotLocked(final String theDirectionType,
                                                final int theRow, final int theCol) {
        return myMaze.getSpecificRoom(theRow, theCol).getDoor(
                Direction.valueOf(theDirectionType)).isLocked();
    }

    public boolean checkDoorIsNotFirstTime(final String theDirectionType,
                                           final int theRow, final int theCol) {
        return !myMaze.getSpecificRoom(theRow, theCol).getDoor(
                Direction.valueOf(theDirectionType)).wasFirstTime();
    }

    public void resetGameState() {
        myMainFrame.dispose();
        runGame();
    }

    public void serialize() {

        try {
            FileOutputStream file = new FileOutputStream
                    (triviaMazeFile);
            ObjectOutputStream out = new ObjectOutputStream
                    (file);

            out.writeObject(myMaze);

            out.close();
            file.close();
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    public void deserialize() {
        try {
            FileInputStream file = new FileInputStream
                    (triviaMazeFile);
            ObjectInputStream in = new ObjectInputStream
                    (file);

            myMaze = (Maze) in.readObject();
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
        }
    }

    public int getRow() {
        return myMaze.getCharacterRow();
    }

    public int getColumn() {
        return myMaze.getCharacterColumn();
    }


    public void openDatabaseConnection() {
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
            myConnection = ds.getConnection();
            myStatement = myConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void closeDatabaseConnection() {
        if (myStatement != null) {
            try {
                myStatement.close();
            } catch (SQLException e) { /* Ignored */}
        }
        if (myConnection != null) {
            try {
                myConnection.close();
            } catch (SQLException e) { /* Ignored */}
        }
    }
}
