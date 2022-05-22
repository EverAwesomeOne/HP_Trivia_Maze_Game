package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private final TriviaMazeBrain myTriviaMazeBrain;
    private final MazePanel myMazePanel;
    private DirectionButtonPanel myDirectionButtonPanel;
    private final QuestionPanel myQuestionPanel;
    private final AnswerPanel myAnswerPanel;

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

        setVisible(true);
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

    void resetDirectionButtonPanel() {
        myDirectionButtonPanel = new DirectionButtonPanel(this, myMazePanel,
                myTriviaMazeBrain);
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
        winningMessages.add("Keep it up!");
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
        losingMessages.add("Oopsie, that wasn't right!");
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
                    //myMainMenuBar.setVisible(false);
                    endGameMessage.setVisible(false);

                    myTriviaMazeBrain.resetGameState();
                    resetDirectionButtonPanel();

                    //new MainMenuPanel(myMainFrame, myTriviaMazeBrain);
                }
        );

        endGameMessage.add(exitGameButton);
        endGameMessage.setSize(300, 100);
        endGameMessage.setLocationRelativeTo(null);
        endGameMessage.setVisible(true);
    }
}
