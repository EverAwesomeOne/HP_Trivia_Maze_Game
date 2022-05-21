package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel {

    private static final String TITLE = "Hodgepodge Trivia Maze";

    private static final ImageIcon MAZE_ICON =
            new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");

    private static final ImageIcon HOGWARTS_ICON =
            new ImageIcon("src//View//Images//HogwartsIcon.jpg");

    private static final ImageIcon HARRY_POTTER_ICON =
            new ImageIcon("src//View//Images//HPTrivia.png");

    private final JFrame myMainFrame;

    private final JPanel myMainPanel;

    private GamePanel myGamePanel;

    private final TriviaMazeBrain myTriviaMazeBrain;

    final static Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 25);
    final static Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 18);

    final static Color GOLD_COLOR = new Color(255,204,51).darker();
    final static Color PURPLE_COLOR = new Color(102,0,153).darker();

    public MainMenuPanel(final JFrame theMainFrame, final TriviaMazeBrain theTriviaMazeBrain) {
        myMainFrame = theMainFrame;
        myTriviaMazeBrain = theTriviaMazeBrain;

        myMainPanel = new JPanel();
        myMainPanel.setLayout(new GridLayout(1,3));
        myMainPanel.setBorder(BorderFactory.createLineBorder(GOLD_COLOR,5));
        myMainPanel.setBackground(PURPLE_COLOR);

        myMainPanel.add(setupIconPanel());
        myMainPanel.add(setupGameTitlePanel());
        myMainPanel.add(setupIconPanel());

        theMainFrame.add(myMainPanel);

        myMainPanel.setVisible(true);
    }

    private JPanel setupGameTitlePanel() {
        final JPanel gameTitlePanel = new JPanel();
        gameTitlePanel.setLayout(new GridLayout(2,1));
        gameTitlePanel.setBackground(PURPLE_COLOR);

        final JLabel myGameTitle = new JLabel("<html><div style='text-align: center;'>" + "Harry Potter<br>Trivia Maze<br>Game" + "</div></html>");
        myGameTitle.setForeground(GOLD_COLOR);
        myGameTitle.setHorizontalAlignment(JLabel.CENTER);
        myGameTitle.setFont(TITLE_FONT);

        gameTitlePanel.add(myGameTitle);
        gameTitlePanel.add(setupButtonPanel());

        return gameTitlePanel;
    }

    private JPanel setupIconPanel() {
        final JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new GridLayout(3,1));
        iconPanel.setBackground(PURPLE_COLOR);

        iconPanel.add(new JLabel(scaleImageIcon(HARRY_POTTER_ICON)));
        iconPanel.add(new JLabel(scaleImageIcon(MAZE_ICON)));
        iconPanel.add(new JLabel(scaleImageIcon(HOGWARTS_ICON)));

        return iconPanel;
    }

    private JPanel setupButtonPanel() {
        final JPanel mainMenuBtnPanel = new JPanel();
        mainMenuBtnPanel.setBackground(PURPLE_COLOR);

        // newGameButton refactor
        final JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(BUTTON_FONT);
        newGameButton.setBackground(GOLD_COLOR);
        newGameButton.setForeground(Color.black);
        mainMenuBtnPanel.add(newGameButton);

        // loadGameButton refactor
        final JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setFont(BUTTON_FONT);
        loadGameButton.setBackground(GOLD_COLOR);
        loadGameButton.setForeground(Color.black);
        mainMenuBtnPanel.add(loadGameButton);

        final JButton gameRulesButton = new JButton("Game Rules");
        gameRulesButton.setFont(BUTTON_FONT);
        gameRulesButton.setBackground(GOLD_COLOR);
        gameRulesButton.setForeground(Color.black);
        mainMenuBtnPanel.add(gameRulesButton);

        addActionListener(newGameButton, "New Game");
        addActionListener(loadGameButton, "Load Game");
        addActionListener(gameRulesButton, "Game Rules");

        return mainMenuBtnPanel;
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
        else if (buttonName.equals("Game Rules")) {
            button.addActionListener(
                    e -> {
                        myMainPanel.setVisible(false);
                        new GameRules(myMainFrame, myMainPanel);
                    }
            );
        }
    }

    private ImageIcon scaleImageIcon(ImageIcon icon) {
        return new ImageIcon(icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH));
    }

    public GamePanel getGamePanel() {
        return myGamePanel;
    }
}