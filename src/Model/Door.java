package Model;

public class Door {
    private boolean locked;
    private boolean firstTime;

    Door() {
        firstTime = true;
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
}
