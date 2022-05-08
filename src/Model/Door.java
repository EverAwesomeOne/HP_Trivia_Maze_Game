package Model;

public class Door {
    private boolean locked;
    private boolean firstTime;
    private Question_Answer questionAnswer;

    Door() {
        firstTime = true;
        questionAnswer = new Question_Answer();
    }

    boolean isLocked() {
        return locked;
    }

    boolean wasVisited() {
        return firstTime;
    }

    void lockDoor() {
        locked = true;
    }

    Question_Answer getQuestion() {return questionAnswer;}
}
