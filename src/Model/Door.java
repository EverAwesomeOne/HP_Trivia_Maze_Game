package Model;

public class Door {
    private boolean locked;
    private boolean firstTime;
    private Question question;

    Door() {
        firstTime = true;
        question = null;
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

    Question getQuestion() {return question;}
}
