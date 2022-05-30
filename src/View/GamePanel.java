package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private final MainFrame myMainFrame;
    private final TriviaMazeBrain myTriviaMazeBrain;
    private final MazePanel myMazePanel;
    private final DirectionButtonPanel myDirectionButtonPanel;
    private final QuestionPanel myQuestionPanel;
    private final AnswerPanel myAnswerPanel;

    public GamePanel(final MainFrame theMainFrame, final TriviaMazeBrain theTriviaMazeBrain) {
        myMainFrame = theMainFrame;
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

    public void askQuestion(final String[] theQuestionList, final String theDirectionType) {
        myDirectionButtonPanel.disableAllButtons();
        myQuestionPanel.createQuestion(theQuestionList);
        myAnswerPanel.createQuestionType(theQuestionList, theDirectionType);
    }

    public MazePanel getMazePanel() {
        return myMazePanel;
    }

    public DirectionButtonPanel getDirectionButtonPanel() {
        return myDirectionButtonPanel;
    }

    public void displayCorrectAnswerMessageBox() {
        final String title = "Correct Answer! You're a wizard, Harry!";
        final Random randomNumber = new Random();
        ArrayList<String> winningMessages = createWinningAnswerMessages();
        displayPostAnswerMessageHelper(title, randomNumber, winningMessages);
    }

    public ArrayList<String> createWinningAnswerMessages() {
        ArrayList<String> winningMessages = new ArrayList<>();
        winningMessages.add("You're doing awesome :)");
        winningMessages.add("Dementor? You just took my breath away!");
        winningMessages.add("Eek, one step closer!");
        winningMessages.add("You're on a roll~~");
        return winningMessages;
    }

    public void displayIncorrectAnswerMessageBox() {
        final String title = "Incorrect Answer";
        final Random randomNumber = new Random();
        ArrayList<String> losingMessages = createLosingAnswerMessages();
        displayPostAnswerMessageHelper(title, randomNumber, losingMessages);
    }

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

    public ArrayList<String> createLosingAnswerMessages() {
        ArrayList<String> losingMessages = new ArrayList<>();
        losingMessages.add("Dang, not quite");
        losingMessages.add("You were so close");
        losingMessages.add("Try again. You got this!");
        losingMessages.add("Muggle? You're hitting platform 9 and 3/4.");

        return losingMessages;
    }

    public void displayWinningMessageBox() {
        final String title = "You Win! :))";
        final String winningMessage = "Take the (U)W!";
        setUpDialogBox(title, winningMessage);
    }

    public void displayLosingMessageBox() {
        final String title = "Game Over...Play Again!";
        final String losingMessage = "Guess you're not a trivia wizard";
        setUpDialogBox(title, losingMessage);
    }

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
