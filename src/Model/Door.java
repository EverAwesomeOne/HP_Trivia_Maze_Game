package Model;

import java.io.Serial;
import java.io.Serializable;

public class Door implements Serializable {
    private boolean myLocked;
    private boolean myFirstTime;
    private final QuestionAnswer myQuestionAnswer;

    @Serial
    private static final long serialVersionUID = 109174852462682090L;

    Door() {
        myFirstTime = true;
        myQuestionAnswer = new QuestionAnswer();
    }

    public boolean isLocked() {
        return myLocked;
    }

    public boolean wasFirstTime() {
        return myFirstTime;
    }

    public void setFirstTime(final boolean theFirstTime) {
        myFirstTime = theFirstTime;
    }

    public void lockDoor() {
        myLocked = true;
    }

    public QuestionAnswer getQuestion() {
        return myQuestionAnswer;
    }
}
