package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel {

    private final JFrame myMainFrame;

    final MazePanel myMazePanel;
    DirectionButtonPanel myDirectionButtonPanel;

    private final JPanel myGamePanel;
    final JMenuBar myMainMenuBar;

    private final TriviaMazeBrain myTriviaMazeBrain;
    private final QuestionPanel myQuestionPanel;
    private final AnswerPanel myAnswerPanel;

    public GamePanel(final JFrame theMainFrame, final TriviaMazeBrain theTriviaMazeBrain) {
        myMainFrame = theMainFrame;
        myTriviaMazeBrain = theTriviaMazeBrain;

        myGamePanel = new JPanel();
        myGamePanel.setLayout(new GridLayout(2,2));

        // sets up these panels and adds to gamePanel
        myMazePanel = new MazePanel(myGamePanel, theTriviaMazeBrain);
        myDirectionButtonPanel = new DirectionButtonPanel(myGamePanel, myMazePanel,
                theTriviaMazeBrain);
        myQuestionPanel = new QuestionPanel(myGamePanel);
        myAnswerPanel = new AnswerPanel(myGamePanel, theTriviaMazeBrain, myQuestionPanel,
                myDirectionButtonPanel);

        // display menuBar on JFrame only when game is in progress
        myMainMenuBar = new JMenuBar();
        setupMenuBar("About Hodgepodge Team");
        setupMenuBar("Game Rules");
        setupMenuBar("Save Game");
        setupMenuBar("Exit Game");

        theMainFrame.add(myGamePanel);
        theMainFrame.setJMenuBar(myMainMenuBar);

        myGamePanel.setVisible(true);
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

    private void setupMenuBar(final String theMenuTitle) {
        final JMenu addMenu = new JMenu(theMenuTitle);
        final JMenuItem menuItem = new JMenuItem(theMenuTitle);

        final Font MENU_FONT = new Font("SansSerif", Font.BOLD, 16);
        addMenu.setFont(MENU_FONT);
        menuItem.setFont(MENU_FONT);

        addMenuActionListener(menuItem, theMenuTitle);

        addMenu.add(menuItem);

        myMainMenuBar.add(addMenu);

        myMainMenuBar.setVisible(true);
    }

    void resetDirectionButtonPanel() {
        myDirectionButtonPanel = new DirectionButtonPanel(myGamePanel, myMazePanel,
                myTriviaMazeBrain);
    }

    private void addMenuActionListener(final JMenuItem theMenuItem, final String theMenuName) {
        switch (theMenuName) {
            case "Game Rules" -> theMenuItem.addActionListener(
                    e -> {
                        myGamePanel.setVisible(false);
                        myMainMenuBar.setVisible(false);
                        new GameRules(myMainFrame, myGamePanel, myMainMenuBar);
                    }
            );
            case "About Hodgepodge Team" -> theMenuItem.addActionListener(
                    e -> {
                        myGamePanel.setVisible(false);
                        myMainMenuBar.setVisible(false);
                        new AboutTeam(myMainFrame, myGamePanel, myMainMenuBar);
                    }
            );
            case "Exit" -> theMenuItem.addActionListener(
                    e -> {
                        myGamePanel.setVisible(false);
                        myMainMenuBar.setVisible(false);
                        myTriviaMazeBrain.closeDatabaseConnection();
                        new MainMenuPanel(myMainFrame, myTriviaMazeBrain);
                        myTriviaMazeBrain.resetGameState();
                        resetDirectionButtonPanel();
                    }
            );

            // edit to include save game option
            default -> theMenuItem.addActionListener(
                    e -> {
                        myGamePanel.setVisible(false);
                        myMainMenuBar.setVisible(false);
                        new MainMenuPanel(myMainFrame, myTriviaMazeBrain);
                        myTriviaMazeBrain.resetGameState();
                        resetDirectionButtonPanel();
                    }
            );
        }
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

        final Timer timer = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGameMessage.setVisible(false);
                endGameMessage.dispose();
            }
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
                    myGamePanel.setVisible(false);
                    myMainMenuBar.setVisible(false);
                    endGameMessage.setVisible(false);

                    myTriviaMazeBrain.resetGameState();
                    resetDirectionButtonPanel();

                    new MainMenuPanel(myMainFrame, myTriviaMazeBrain);
                }
        );

        endGameMessage.add(exitGameButton);
        endGameMessage.setSize(300, 100);
        endGameMessage.setLocationRelativeTo(null);
        endGameMessage.setVisible(true);
    }
}
