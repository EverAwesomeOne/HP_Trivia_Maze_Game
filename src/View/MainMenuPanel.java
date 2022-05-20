package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel {

    private static final String TITLE = "Hodgepodge Trivia Maze";

    private static final ImageIcon MAZE_ICON =
            new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");

    private final JFrame myMainFrame;

    private final JPanel myMainPanel;

    private GamePanel myGamePanel;

    private final TriviaMazeBrain myTriviaMazeBrain;

    final static Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 22);
    final static Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 18);

    public MainMenuPanel(final JFrame theMainFrame, final TriviaMazeBrain theTriviaMazeBrain) {
        myMainFrame = theMainFrame;
        myTriviaMazeBrain = theTriviaMazeBrain;

        myMainPanel = new JPanel();
        myMainPanel.setLayout(new GridLayout(1,3));

        final JPanel gameTitlePanel = new JPanel();
        gameTitlePanel.setLayout(new GridLayout(2,1));

        final JLabel myGameTitle = new JLabel("<html><div style='text-align: center;'>" + "Hodgepodge<br>Trivia Maze<br>Game" + "</div></html>");
        myGameTitle.setHorizontalAlignment(JLabel.CENTER);
        myGameTitle.setFont(TITLE_FONT);

        gameTitlePanel.add(myGameTitle);
        gameTitlePanel.add(setupButtonPanel());

        myMainPanel.add(new JLabel(MAZE_ICON));
        myMainPanel.add(gameTitlePanel);
        myMainPanel.add(new JLabel(MAZE_ICON));

        //myMainPanel.add(new JLabel());
        //myMainPanel.add(setupButtonPanel());


        /*myMainPanel.setLayout(new BorderLayout());

        final JLabel myGameTitle = new JLabel(TITLE);
        myGameTitle.setHorizontalAlignment(JLabel.CENTER);
        myMainPanel.add(myGameTitle, BorderLayout.NORTH);

        myMainPanel.add(setupButtonPanel(), BorderLayout.CENTER);

        myMainPanel.add(new JLabel(mazeIcon), BorderLayout.SOUTH);*/

        theMainFrame.add(myMainPanel);

        myMainPanel.setVisible(true);
    }

    private JPanel setupButtonPanel() {
        JPanel mainMenuBtnPanel = new JPanel();

        JButton btnNewGame = new JButton("New Game");
        btnNewGame.setFont(BUTTON_FONT);
        mainMenuBtnPanel.add(btnNewGame);

        JButton btnLoadGame = new JButton("Load Game");
        btnLoadGame.setFont(BUTTON_FONT);
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