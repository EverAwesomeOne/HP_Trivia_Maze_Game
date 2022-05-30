package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private final MainFrame myMainFrame;
    private final TriviaMazeBrain myTriviaMazeBrain;
    private final GamePanel myGamePanel;

    private static final ImageIcon HOGWARTS_ICON = new ImageIcon("src//View//Images//HogwartsIcon.jpg");
    private static final ImageIcon HARRY_POTTER_ICON = new ImageIcon("src//View//Images//HPTrivia.png");

    final static Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 25);
    final static Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 18);

    final static Color GOLD_COLOR = new Color(255,204,51).darker();
    final static Color PURPLE_COLOR = new Color(102,0,153).darker();

    public MainMenuPanel(final MainFrame theMainFrame, final TriviaMazeBrain theTriviaMazeBrain, final GamePanel theGamePanel) {
        myMainFrame = theMainFrame;
        myTriviaMazeBrain = theTriviaMazeBrain;
        myGamePanel = theGamePanel;

        setLayout(new GridLayout(1,3));
        setBorder(BorderFactory.createLineBorder(GOLD_COLOR,5));
        setBackground(PURPLE_COLOR);

        add(setupIconPanel());
        add(setupGameTitlePanel());
        add(setupIconPanel());
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
        iconPanel.setLayout(new GridLayout(2,1));
        iconPanel.setBackground(PURPLE_COLOR);

        iconPanel.add(new JLabel(scaleImageIcon(HOGWARTS_ICON)));
        iconPanel.add(new JLabel(scaleImageIcon(HARRY_POTTER_ICON)));

        return iconPanel;
    }

    // factor out the common code call it addStyledButton()
    private JPanel setupButtonPanel() {
        final JPanel mainMenuBtnPanel = new JPanel();
        mainMenuBtnPanel.setBackground(PURPLE_COLOR);

        addStyledButtonToMainMenu(" New Game ", mainMenuBtnPanel);
        addStyledButtonToMainMenu("Load  Game", mainMenuBtnPanel);
        addStyledButtonToMainMenu("Game Rules", mainMenuBtnPanel);

        return mainMenuBtnPanel;
    }

    private void addStyledButtonToMainMenu(final String theTextForBtn, final JPanel theMainMenuBtnPanel) {
        final JButton mainMenuButton = new JButton(theTextForBtn);
        mainMenuButton.setFont(BUTTON_FONT);
        mainMenuButton.setBackground(GOLD_COLOR);
        mainMenuButton.setForeground(PURPLE_COLOR);
        theMainMenuBtnPanel.add(mainMenuButton);
        addActionListener(mainMenuButton, theTextForBtn);
    }

    private void addActionListener(JButton button, String buttonName) {
        if (buttonName.equals(" New Game ")) {
            button.addActionListener(
                    e -> {
                        setVisible(false);
                        myTriviaMazeBrain.openDatabaseConnection();
                        myMainFrame.getJMenuBar().setVisible(true);
                        myMainFrame.add(myGamePanel);
                        myGamePanel.setVisible(true);
                    }
            );
        }
        else if (buttonName.equals("Load  Game")) {
            button.addActionListener(
                    e -> {
                        setVisible(false);
                        myTriviaMazeBrain.deserialize();
                        myTriviaMazeBrain.openDatabaseConnection();
                        myMainFrame.getJMenuBar().setVisible(true);
                        //set up proper gui stuff
                        myGamePanel.getDirectionButtonPanel().setDirectionButtonsVisibility();
                        myGamePanel.getMazePanel().updateCharacterPlacement(
                                myTriviaMazeBrain.getRow(), myTriviaMazeBrain.getColumn());
                        myGamePanel.getMazePanel().loadDoorIcons();
                        myMainFrame.add(myGamePanel);
                        myGamePanel.setVisible(true);

                    }
            );
        }
        else if (buttonName.equals("Game Rules")) {
            button.addActionListener(
                    e -> {
                        setVisible(false);
                        GameRulesPanel gameRulesPanel = myMainFrame.getGameRulesPanelMM();
                        myMainFrame.add(gameRulesPanel);
                        gameRulesPanel.setVisible(true);
                    }
            );
        }
    }

    private ImageIcon scaleImageIcon(ImageIcon icon) {
        return new ImageIcon(icon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
    }
}