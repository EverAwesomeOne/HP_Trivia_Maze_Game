package Model;

public class Door {
    private boolean myLocked;
    private boolean myFirstTime;
    private final QuestionAnswer myQuestionAnswer;

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
