package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final JFrame myMainFrame;

    final MazePanel myMazePanel;
    DirectionButtonPanel myDirectionButtonPanel;

    private final int ROW = 4;
    private final int COL = 4;

    private final JPanel myGamePanel;
    private final JMenuBar myMainMenuBar;

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
        myDirectionButtonPanel = new DirectionButtonPanel(myGamePanel, myMazePanel, theTriviaMazeBrain);
        myQuestionPanel = new QuestionPanel(myGamePanel);
        myAnswerPanel = new AnswerPanel(myGamePanel, theTriviaMazeBrain, myQuestionPanel, myDirectionButtonPanel);

        // display menuBar on JFrame only when game is in progress
        myMainMenuBar = new JMenuBar();
        setupMenuBar("Admin Settings");
        setupMenuBar("About Hodgepodge Team");
        setupMenuBar("Game Info");
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

    public MazePanel getMyMazePanel() {
        return myMazePanel;
    }

    private void setupMenuBar(final String theMenuTitle) {
        final JMenu addMenu = new JMenu(theMenuTitle);

        if (theMenuTitle.equals("Admin Settings")) {
            final JCheckBoxMenuItem menuCheckBoxItem = new JCheckBoxMenuItem("Enable Debug Feature");
            addMenuActionListener(menuCheckBoxItem, theMenuTitle);
            addMenu.add(menuCheckBoxItem);
        }

        else {
            final JMenuItem menuItem = new JMenuItem(theMenuTitle);

            addMenuActionListener(menuItem, theMenuTitle);

            addMenu.add(menuItem);
        }

        myMainMenuBar.add(addMenu);

        myMainMenuBar.setVisible(true);
    }

    void resetDirectionButtonPanel() {
        myDirectionButtonPanel = new DirectionButtonPanel(myGamePanel, myMazePanel, myTriviaMazeBrain);
    }

    private void addMenuActionListener(final JMenuItem theMenuItem, final String theMenuName) {
        if (theMenuName.equals("Game Info")) {
            theMenuItem.addActionListener(
                    e -> {
                        myGamePanel.setVisible(false);
                        myMainMenuBar.setVisible(false);
                        new GameInfo(myMainFrame, myGamePanel, myMainMenuBar);
                    }
            );
        }
        else if (theMenuName.equals("About Hodgepodge Team")) {
            theMenuItem.addActionListener(
                    e -> {
                        myGamePanel.setVisible(false);
                        myMainMenuBar.setVisible(false);
                        new AboutTeam(myMainFrame, myGamePanel, myMainMenuBar);
                    }
            );
        }
        else if (theMenuName.equals("Admin Settings")) {
            theMenuItem.addActionListener(
                    e -> {
                        System.out.println("Admin settings changed");
                    }
            );
        }
        // edit to include save game option
        // exit game should check if game is saved
        // if not saved prompt for saving, else:
        else {
            theMenuItem.addActionListener(
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
}
