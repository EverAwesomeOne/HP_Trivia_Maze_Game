package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel {

    private static final String TITLE = "Hodgepodge Trivia Maze";

    private static final ImageIcon mazeIcon = new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");

    private final JFrame myMainFrame;

    private final JPanel myMainPanel;

    private final JLabel myGameTitle;

    private GamePanel myGamePanel;

    private TriviaMazeBrain myTriviaMazeBrain;

    public MainMenuPanel(final JFrame theMainFrame, final TriviaMazeBrain theTriviaMazeBrain) {
        myMainFrame = theMainFrame;
        myTriviaMazeBrain = theTriviaMazeBrain;

        myMainPanel = new JPanel();
        myMainPanel.setLayout(new BorderLayout());

        myGameTitle = new JLabel(TITLE);
        myGameTitle.setHorizontalAlignment(JLabel.CENTER);
        myMainPanel.add(myGameTitle, BorderLayout.NORTH);

        myMainPanel.add(setupButtonPanel(), BorderLayout.CENTER);

        myMainPanel.add(new JLabel(mazeIcon), BorderLayout.SOUTH);

        theMainFrame.add(myMainPanel);

        myMainPanel.setVisible(true);
    }

    private JPanel setupButtonPanel() {
        JPanel mainMenuBtnPanel = new JPanel();

        JButton btnNewGame = new JButton("New Game");
        mainMenuBtnPanel.add(btnNewGame);

        JButton btnLoadGame = new JButton("Load Game");
        mainMenuBtnPanel.add(btnLoadGame);

        addActionListener(btnNewGame, "New Game");
        addActionListener(btnLoadGame, "Load Game");

        return mainMenuBtnPanel;
    }

    public GamePanel getGamePanel() {
        return myGamePanel;
    }

    private void addActionListener(JButton button, String buttonName) {
        if (buttonName.equals("New Game")) {
            button.addActionListener(
                    e -> {
                        myMainPanel.setVisible(false);
                        myTriviaMazeBrain.openDatabaseConnection();
                        myGamePanel = new GamePanel(myMainFrame, myTriviaMazeBrain);
                    }
            );
        }
        else if (buttonName.equals("Load Game")) {
            button.addActionListener(
                    e -> {
                        // call deserialize method
                        myMainPanel.setVisible(false);
                        myGamePanel = new GamePanel(myMainFrame, myTriviaMazeBrain);
                    }
            );
        }
        // else
    }
}