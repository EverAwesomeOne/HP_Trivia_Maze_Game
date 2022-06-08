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

/**
 * The TriviaMazeBrain class represents the Controller that acts as the connector between
 * the View with the GUI and the Model with the logic. It helps to start the game and passes
 * information between the View and the Model.
 */
public class TriviaMazeBrain {
    private Connection myConnection;
    private Statement myStatement;
    private Maze myMaze;
    private GamePanel myGamePanel;
    private MainFrame myMainFrame;
    private MazePanel myMazePanel;

    private final static String TRIVIA_MAZE_FILE = "triviaMaze.txt";

    public final static int MAZE_LENGTH = 7;
    public final static String MOVE_FREELY = "Move freely";

    public static void main(final String[] theArgs) {
        new TriviaMazeBrain();
    }

    /**
     * Constructor for the TriviaMazeBrain class.
     * Starts running the game.
     */
    private TriviaMazeBrain() {
        runGame();
    }

    /**
     * Sets up the needed pieces of the Model, the database,
     * and the View to be able to run the game.
     */
    private void runGame() {
        myMaze = new Maze(MAZE_LENGTH);
        openDatabaseConnection();
        myMainFrame = new MainFrame(this);
    }

    /**
     * Generates and gets the question and answers for the GUI to set up
     * and have the player see.
     * If the player has already answered the question correctly at this door,
     * the player can just move freely through the door.
     * @param theDirectionType - the direction in which the door is located
     */
    public void setupQuestion(final String theDirectionType) {
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

    /**
     * Move the player by updating where they are in the maze from the Model side
     * as well as the View side.
     * If the player has already answered a question correctly at this door,
     * they can just move freely through the door.
     * @param thePlayerAnswer - the player answer
     * @param theDirectionType - the direction in which the door is located
     */
    public void moveCharacter(final String thePlayerAnswer, final String theDirectionType) {

        final Direction directionToMove = Direction.valueOf(theDirectionType);
        final Door chosenDoor = myMaze.getCurrentRoom().getDoor(directionToMove);
        final QuestionAnswer qa = chosenDoor.getQuestion();

        if (MOVE_FREELY.equals(thePlayerAnswer)) {
            myMaze.updatePosition(directionToMove);
        } else if (qa.selectedCorrectAnswer(thePlayerAnswer)) {
            answeredCorrectly(thePlayerAnswer, theDirectionType, directionToMove, qa);
            chosenDoor.setFirstTime(false);
        } else {
            answeredIncorrectly(thePlayerAnswer, theDirectionType, chosenDoor, qa);
        }

        myGamePanel.getDirectionButtonPanel().setDirectionButtonsVisibility();
        myMazePanel.updateCharacterPlacement(myMaze.getCharacterRow(),
                myMaze.getCharacterColumn());
    }

    /**
     * Updates the maze in Model and the GUI parts in View to reflect that the player
     * has gotten the answer correct.
     * @param thePlayerAnswer - the player answer
     * @param theDirectionType - the String version of the direction in which the door is located
     * @param theDirectionToMove - the Enum version of the direction in which the door is located
     * @param theQA - the question and answers attached to the chosen door
     */
    private void answeredCorrectly(final String thePlayerAnswer, final String theDirectionType,
                                   final Direction theDirectionToMove, final QuestionAnswer theQA) {
        myMazePanel.setDoorIcon(myMaze.getCharacterRow(), myMaze.getCharacterColumn(),
                theDirectionType, theQA.selectedCorrectAnswer(thePlayerAnswer));
        myMaze.updatePosition(theDirectionToMove);
        if (myMaze.hasWon()) {
            myGamePanel.displayWinningMessageBox();
        } else {
            myGamePanel.displayCorrectAnswerMessageBox();
        }
    }

    /**
     * Updates the maze in Model and the GUI parts in View to reflect that the player
     * has gotten the answer wrong.
     * If there are no paths left to get to the exit, the player will be displayed
     * a game over message.
     * @param thePlayerAnswer - the player answer
     * @param theDirectionType - the direction in which the door is located
     * @param theChosenDoor - the door that was picked to go through by the player
     * @param theQA - the question and answers attached to the chosen door
     */
    private void answeredIncorrectly(final String thePlayerAnswer, final String theDirectionType,
                                     final Door theChosenDoor, final QuestionAnswer theQA) {
        theChosenDoor.lockDoor();
        myMazePanel.setDoorIcon(myMaze.getCharacterRow(), myMaze.getCharacterColumn(),
                theDirectionType, theQA.selectedCorrectAnswer(thePlayerAnswer));
        myMaze.removeEdgeFromGraph(Direction.valueOf(theDirectionType));
        if (!myMaze.hasValidPaths()) {
            myGamePanel.displayLosingMessageBox();
        } else {
            myGamePanel.displayIncorrectAnswerMessageBox();
        }
    }

    /**
     * Checks to see if a specific door in the current room is locked or not.
     * @param theDirectionType - the direction in which the door is located
     * @return - if the door is locked or not
     */
    public boolean checkCurrentRoomIsLockedStatus(final String theDirectionType) {
        return !myMaze.getCurrentRoom().getDoor
                (Direction.valueOf(theDirectionType)).isLocked();
    }

    /**
     * Checks to see if the door is locked or not.
     * @param theDirectionType - the direction in which the door is located
     * @param theRow - the current row of the player
     * @param theCol - the current column of the player
     * @return - if the door is locked or not
     */
    public boolean checkSpecificRoomIsNotLocked(final String theDirectionType,
                                                final int theRow, final int theCol) {
        return myMaze.getSpecificRoom(theRow, theCol).getDoor(
                Direction.valueOf(theDirectionType)).isLocked();
    }

    /**
     * Checks to see if the player has been to this door or not.
     * @param theDirectionType - the direction in which the door is located
     * @param theRow - the current row of the player
     * @param theCol - the current column of the player
     * @return - if the player has been to this door or not
     */
    public boolean checkDoorIsNotFirstTime(final String theDirectionType,
                                           final int theRow, final int theCol) {
        return !myMaze.getSpecificRoom(theRow, theCol).getDoor(
                Direction.valueOf(theDirectionType)).wasFirstTime();
    }

    /**
     * Gets rid of all the current game related parts and
     * reruns the game.
     */
    public void resetGameState() {
        myMainFrame.dispose();
        runGame();
    }

    /**
     * Serializes the information and stores it in a file to
     * remember what state the game was in when the player
     * saved the game.
     */
    public void serialize() {

        try {
            FileOutputStream file = new FileOutputStream
                    (TRIVIA_MAZE_FILE);
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

    /**
     * Deserializes the information stored in a file to
     * remember what state the game was in when the player
     * saved the game.
     */
    public void deserialize() {
        try {
            FileInputStream file = new FileInputStream
                    (TRIVIA_MAZE_FILE);
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

    /**
     * Gets the row of where the character/player is located in the maze.
     * @return - the row of where the character/player is located in the maze
     */
    public int getRow() {
        return myMaze.getCharacterRow();
    }

    /**
     * Gets the column of where the character/player is located in the maze.
     * @return - the column of where the character/player is located in the maze
     */
    public int getColumn() {
        return myMaze.getCharacterColumn();
    }

    /**
     * Opens the connection to the "questions" database.
     */
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

    /**
     * Closes the connection to the "questions" database.
     */
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
