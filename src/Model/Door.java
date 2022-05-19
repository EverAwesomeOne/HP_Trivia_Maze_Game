package Model;

public class Door {
    private boolean locked;
    private boolean firstTime;
    private QuestionAnswer questionAnswer;

    Door() {
        firstTime = true;
        questionAnswer = new QuestionAnswer();
    }

    public boolean isLocked() {
        return locked;
    }

    boolean wasVisited() {
        return firstTime;
    }

    public void lockDoor() {
        locked = true;
    }

    public QuestionAnswer getQuestion() {
        return questionAnswer;
    }
}
