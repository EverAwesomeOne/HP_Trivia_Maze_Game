package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The GamePanel class represents the overall game screen that displays all the smaller
 * components within it.
 */
public class GamePanel extends JPanel {
    private final TriviaMazeBrain myTriviaMazeBrain;
    private final MazePanel myMazePanel;
    private final DirectionButtonPanel myDirectionButtonPanel;
    private final QuestionPanel myQuestionPanel;
    private final AnswerPanel myAnswerPanel;

    /**
     * The constructor for the GamePanel class.
     * Initializes overall controller and sets up the different
     * components located on the game panel.
     * @param theTriviaMazeBrain - the controller that connects the GUI with the logic
     */
    public GamePanel(final TriviaMazeBrain theTriviaMazeBrain) {
        myTriviaMazeBrain = theTriviaMazeBrain;
        
        setLayout(new GridLayout(2,2));

        // sets up these panels and adds to gamePanel
        myMazePanel = new MazePanel(this, theTriviaMazeBrain);
        myDirectionButtonPanel = new DirectionButtonPanel(this, myMazePanel,
                theTriviaMazeBrain);
        myQuestionPanel = new QuestionPanel(this);
        myAnswerPanel = new AnswerPanel(this, theTriviaMazeBrain, myQuestionPanel,
                myDirectionButtonPanel);
    }

    /**
     * Set up and display the question and possible answers to the player.
     * @param theQuestionList - the list of possible answers and the question
     * @param theDirectionType - the direction in which the door is located
     */
    public void askQuestion(final String[] theQuestionList, final String theDirectionType) {
        myDirectionButtonPanel.disableAllButtons();
        myQuestionPanel.createQuestion(theQuestionList);
        myAnswerPanel.createQuestionType(theQuestionList, theDirectionType);
    }

    /**
     * Get the maze panel component, the panel that displays the maze visuals.
     * @return - the panel that displays the maze visuals
     */
    public MazePanel getMazePanel() {
        return myMazePanel;
    }

    /**
     * Get direction button panel component, the panel with the direction buttons.
     * @return - the panel with the direction buttons
     */
    public DirectionButtonPanel getDirectionButtonPanel() {
        return myDirectionButtonPanel;
    }

    /**
     * Displays a good message pop-up if the player.
     * got the answer correct
     */
    public void displayCorrectAnswerMessageBox() {
        final String title = "Correct Answer! You're a wizard, Harry!";
        final Random randomNumber = new Random();
        ArrayList<String> winningMessages = createWinningAnswerMessages();
        displayPostAnswerMessageHelper(title, randomNumber, winningMessages);
    }

    /**
     * Creation of the possible good messages to display if
     * the player got the answer correct.
     * @return - the list of possible good messages
     */
    public ArrayList<String> createWinningAnswerMessages() {
        ArrayList<String> winningMessages = new ArrayList<>();
        winningMessages.add("You're doing awesome :)");
        winningMessages.add("Dementor? You just took my breath away!");
        winningMessages.add("Eek, one step closer!");
        winningMessages.add("You're on a roll~~");
        return winningMessages;
    }

    /**
     *  Displays a bad message pop-up if the player.
     *  got the answer incorrect
     */
    public void displayIncorrectAnswerMessageBox() {
        final String title = "Incorrect Answer";
        final Random randomNumber = new Random();
        ArrayList<String> losingMessages = createLosingAnswerMessages();
        displayPostAnswerMessageHelper(title, randomNumber, losingMessages);
    }

    /**
     * Creation of the possible bad messages to display if
     * the player got the answer incorrect.
     * @return - the list of possible bad messages
     */
    public ArrayList<String> createLosingAnswerMessages() {
        ArrayList<String> losingMessages = new ArrayList<>();
        losingMessages.add("Dang, not quite");
        losingMessages.add("You were so close");
        losingMessages.add("Try again. You got this!");
        losingMessages.add("Muggle? You're hitting platform 9 and 3/4.");

        return losingMessages;
    }

    /**
     * Helper method to set up and display the good/bad pop-up
     * message for a small duration.
     * @param title - the title of the pop-up message
     * @param randomNumber - a random number to pick from the list of
     *                     good/bad messages
     * @param postAnswerMessages - the list of good/bad messages
     */
    private void displayPostAnswerMessageHelper(String title, Random randomNumber, ArrayList<String> postAnswerMessages) {
        int selectRandomMessage = randomNumber.nextInt(postAnswerMessages.size());

        final JDialog endGameMessage = new JDialog();
        endGameMessage.setTitle(title);

        final JLabel endGameLabel = new JLabel(postAnswerMessages.get(selectRandomMessage));
        endGameLabel.setHorizontalAlignment(JLabel.CENTER);
        endGameMessage.add(endGameLabel);

        final Timer timer = new Timer(2500, e -> {
            endGameMessage.setVisible(false);
            endGameMessage.dispose();
        });
        timer.setRepeats(false);
        timer.start();

        endGameMessage.setSize(300, 100);
        endGameMessage.setLocationRelativeTo(null);
        endGameMessage.setVisible(true);
    }

    /**
     * Displays the winning game pop-up message.
     */
    public void displayWinningMessageBox() {
        final String title = "You Win! :))";
        final String winningMessage = "Take the (U)W!";
        setUpDialogBox(title, winningMessage);
    }

    /**
     * Displays the losing game pop-up message.
     */
    public void displayLosingMessageBox() {
        final String title = "Game Over...Play Again!";
        final String losingMessage = "Guess you're not a trivia wizard";
        setUpDialogBox(title, losingMessage);
    }

    /**
     * Helper method to set up the winning/losing
     * pop-up messages.
     * @param theTitle - the title of pop-up message
     * @param theMessage - the message to display in the pop-up
     */
    private void setUpDialogBox(final String theTitle, final String theMessage) {
        final JDialog endGameMessage = new JDialog();
        endGameMessage.setTitle(theTitle);

        final JButton exitGameButton = new JButton(theMessage);
        exitGameButton.addActionListener(
                e -> {
                    myTriviaMazeBrain.closeDatabaseConnection();
                    setVisible(false);
                    endGameMessage.setVisible(false);
                    myTriviaMazeBrain.resetGameState();
                }
        );

        endGameMessage.add(exitGameButton);
        endGameMessage.setSize(300, 100);
        endGameMessage.setLocationRelativeTo(null);
        endGameMessage.setVisible(true);
    }
}
