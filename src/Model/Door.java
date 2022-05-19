package Model;

public class Door {
    private boolean myLocked;
    private final boolean myFirstTime;
    private final QuestionAnswer myQuestionAnswer;

    Door() {
        myFirstTime = true;
        myQuestionAnswer = new QuestionAnswer();
    }

    public boolean isLocked() {
        return myLocked;
    }

    boolean wasVisited() {
        return myFirstTime;
    }

    public void lockDoor() {
        myLocked = true;
    }

    public QuestionAnswer getQuestion() {
        return myQuestionAnswer;
    }
}
