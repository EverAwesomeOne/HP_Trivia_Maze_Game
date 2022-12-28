package Model;

import java.io.Serial;
import java.io.Serializable;

/**
 * The Door class represents each door in a room of the maze.
 */
public class Door implements Serializable {
    private boolean myLocked;
    private boolean myFirstTime;
    private final QuestionAnswer myQuestionAnswer;

    @Serial
    private static final long serialVersionUID = 109174852462682090L;

    /**
     * Constructor for the Door class.
     * Initializes instance fields myFirstTime and myQuestionAnswer.
     */
    Door() {
        myFirstTime = true;
        myQuestionAnswer = new QuestionAnswer();
    }

    /**
     * Checks to see if the door is locked or not.
     * @return - if the door is locked or not
     */
    public boolean isLocked() {
        return myLocked;
    }

    /**
     * Checks to see if the door was accessed already or not.
     * @return - if the door was accessed already or not
     */
    public boolean wasFirstTime() {
        return myFirstTime;
    }

    /**
     * Sets the door's state of if it is the first time being accessed or not.
     * @param theFirstTime - true or false if it was the door's first time being accessed
     */
    public void setFirstTime(final boolean theFirstTime) {
        myFirstTime = theFirstTime;
    }

    /**
     * Locks the door.
     */
    public void lockDoor() {
        myLocked = true;
    }

    /**
     * Gets the question and the answers that are attached to this door.
     * @return - the question and the answers that are attached to this door
     */
    public QuestionAnswer getQuestion() {
        return myQuestionAnswer;
    }
}
