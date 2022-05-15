package Model;

public class Door {
    private boolean locked;
    private boolean firstTime;
    private Question_Answer questionAnswer;

    Door() {
        firstTime = true;
        questionAnswer = new Question_Answer();
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

    public Question_Answer getQuestion() {
        return questionAnswer;
    }
}
