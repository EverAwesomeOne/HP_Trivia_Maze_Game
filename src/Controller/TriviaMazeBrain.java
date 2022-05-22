package Controller;

import Model.Direction;
import Model.Door;
import Model.Maze;
import Model.QuestionAnswer;
import View.*;
import org.sqlite.SQLiteDataSource;

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

    public final static int MAZE_LENGTH = 4;
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
            //QuestionAnswer qa = QuestionAnswer.getQuestionAnswerfromdatabase()
            qa.getQuestionAnswerFromDatabase(myStatement);
            myGamePanel.askQuestion(qa.getQuestionList(), theDirectionType);
            chosenDoor.setFirstTime(false);
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
        }

        else {

            if (!qa.selectedCorrectAnswer(theUserAnswer)) {
                chosenDoor.lockDoor();
                myMaze.removeEdgeFromGraph(Direction.valueOf(theDirectionType));
            }

            if(!chosenDoor.isLocked()) {
                myMaze.updatePosition(directionToMove);
            }
        }

        myGamePanel.getDirectionButtonPanel().setDirectionButtonsVisibility();
        myMazePanel.updateCharacterPlacement(myMaze.getCharacterRow(),
                myMaze.getCharacterColumn());

        // every time answer is correct (except for if you win), display correct answer message
        if (qa.selectedCorrectAnswer(theUserAnswer)) {
            if (myMaze.hasWon()) {
                myGamePanel.displayWinningMessageBox();
            } else {
                myGamePanel.displayCorrectAnswerMessageBox();
            }
        }

        // every time answer is incorrect (except for if you lose), display incorrect answer message
        else if (!qa.selectedCorrectAnswer(theUserAnswer) && !MOVE_FREELY.equals(theUserAnswer)) {
            if (!myMaze.hasValidPaths()) {
                myGamePanel.displayLosingMessageBox();
            } else {
                myGamePanel.displayIncorrectAnswerMessageBox();
            }
        }
    }

    public boolean checkIsLockedStatus(final String theDirectionType) {
        return !myMaze.getCurrentRoom().getDoor
                (Direction.valueOf(theDirectionType)).isLocked();
    }

    public void resetGameState() {
        runGame();
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
