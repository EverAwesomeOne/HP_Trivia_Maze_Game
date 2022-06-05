package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GameRulesPanel class represents the "Game Rules" tab located either as a button
 * on the main menu screen or at as a tab at the top of the game screen.
 */
public class GameRulesPanel extends JPanel {
    private JPanel myGamePanel;
    private JMenuBar myMenuBar;
    private JPanel myMainMenuPanel;

    final static Font ABOUT_FONT = new Font("SansSerif", Font.BOLD, 25);

    final static Color GOLD_COLOR = new Color(255,204,51).darker();
    final static Color PURPLE_COLOR = new Color(102,0,153).darker();

    /**
     * Constructor for the GameRulesPanel located as a tab in the game.
     * @param theGamePanel - the overall game panel screen
     * @param theMenuBar - the menu bar at the top of the game screen
     */
    GameRulesPanel(final JPanel theGamePanel, final JMenuBar theMenuBar) {
        myGamePanel = theGamePanel;
        myMenuBar = theMenuBar;
        setupGameRulesPanel("One");
    }

    /**
     * Constructor for the GameRulesPanel located as a button on the main menu screen.
     * @param theMainMenuPanel - the main menu displayed when the player
     *                          first launches the game
     */
    GameRulesPanel(final JPanel theMainMenuPanel) {
        myMainMenuPanel = theMainMenuPanel;
        setupGameRulesPanel("Two");
    }

    /**
     * Sets up the styles and adds components to the game rules panel.
     * @param theButtonActionType - the indicator if the game rules panel is being
     *                            accessed from the main menu or in the game
     */
    private void setupGameRulesPanel(final String theButtonActionType) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(GOLD_COLOR,5));
        setBackground(PURPLE_COLOR);

        add(setUpTitlePanel(), BorderLayout.NORTH);
        add(setUpRulesPanel(), BorderLayout.CENTER);
        add(setUpButtonPanel(theButtonActionType), BorderLayout.SOUTH);

        setVisible(false);
    }

    /**
     * Sets up the game rules title panel at the top of the game screen.
     * @return - the set-up game rules title panel
     */
    private JPanel setUpTitlePanel() {
        return setUpTitlePanel("<html><div style='text-align: center;'>"
                + "Harry Potter<br>Trivia Maze<br>Game Rules" + "</div></html>");
    }

    /**
     * Sets up the styles and adds the components to the rules panel.
     * @return - the set-up rules panel
     */
    private JPanel setUpRulesPanel() {
        JPanel rules = new JPanel();

        GridLayout gameRulesPanelLayout = new GridLayout(3,1);
        gameRulesPanelLayout.setVgap(4);

        rules.setLayout(gameRulesPanelLayout);
        rules.setBackground(PURPLE_COLOR);
        rules.add(setUpHowToPlayPanel());
        rules.add(setUpFeaturesPanel());
        rules.add(setUpAnswerLogisticsPanel());

        rules.setBackground(PURPLE_COLOR);
        rules.setBorder(new EmptyBorder(10, 10, 0, 10));

        return rules;

    }

    /**
     * Sets up the info on how to play the game.
     * @return - the set-up panel with info on how to play
     */
    private JPanel setUpHowToPlayPanel() {
        final JLabel howToPlayTitle = new JLabel("How To Play:");
        final JLabel howToPlay = new JLabel("<html><div style='text-align: center;'>" +
                "Click the arrow buttons to choose a direction and answer trivia questions as " +
                "you go through different doors and get into other rooms. Make your way through" +
                " the rooms and to the exit (labeled as E) to win! If you get a question wrong: " +
                "the door will lock, an \"X\" will appear on that path, and you will have to try " +
                "to find another way to get to the exit. If you run out of open doors to reach the " +
                "exit, it's game over.</div></html>");

        return setUpPanelsOnRulesPanel(howToPlayTitle, howToPlay);
    }

    /**
     * Sets up the info on the features of the game.
     * @return - the set-up panel with info on features
     */
    private JPanel setUpFeaturesPanel() {
        final JLabel featuresTitle = new JLabel("Features:");
        final JLabel features = new JLabel("<html><div style='text-align: center;'>" +
                "Once you answer a question correctly that door unlocks " +
                "permanently and you will be able to move freely through all the " +
                "doors you answered correctly.</div></html>");

        return setUpPanelsOnRulesPanel(featuresTitle, features);
    }

    /**
     * Sets up the info on the answer logistics of the game.
     * @return - the set-up panel with info on answer logistics
     */
    private JPanel setUpAnswerLogisticsPanel() {
        final JLabel answerLogisticsTitle = new JLabel("Answer Logistics:");
        final JLabel answerLogistics = new JLabel("<html><div style='text-align: center;'>" +
                "All short answers will need to be capitalized for the answer to be correct." +
                " All name questions should be answered with full names." +
                " Questions are worded to prompt if the answer needs to be plural or not.</div></html>");

        return setUpPanelsOnRulesPanel(answerLogisticsTitle, answerLogistics);
    }

    /**
     * Sets up the styles and info of a specific component panel on the rules panel.
     * @param theTitle - the title to display for the specific panel
     * @param theText - the text to display for the specific panel
     * @return - the set-up component panel for the rules panel
     */
    private JPanel setUpPanelsOnRulesPanel(final JLabel theTitle, final JLabel theText) {
        JPanel setUpPanel = new JPanel();
        setUpPanel.setLayout(new BoxLayout(setUpPanel, BoxLayout.Y_AXIS));

        theTitle.setForeground(GOLD_COLOR);
        theText.setForeground(GOLD_COLOR);

        theTitle.setAlignmentX(CENTER_ALIGNMENT);
        theText.setAlignmentX(CENTER_ALIGNMENT);

        theTitle.setFont(new Font("SanSerif", Font.BOLD, 14));
        theText.setFont(new Font("SanSerif", Font.PLAIN, 12));

        setUpPanel.add(theTitle);
        setUpPanel.add(theText);

        setUpPanel.setBackground(PURPLE_COLOR);

        return setUpPanel;
    }

    /**
     * Sets up the styles, info and action listener of the button panel
     * depending on the indicator.
     * @param theButtonActionType - the indicator if the game rules panel is being
     *                            accessed from the main menu or in the game
     * @return - the set-up button panel
     */
    private JPanel setUpButtonPanel(final String theButtonActionType) {
        final JPanel buttonPanel = new JPanel(new BorderLayout());
        final JButton okayButton = new JButton("OK");
        if (theButtonActionType.equals("One")) {
            okayButton.addActionListener(okayButtonOne);
        } else {
            okayButton.addActionListener(okayButtonTwo);
        }
        okayButton.setVerticalAlignment(SwingConstants.CENTER);
        okayButton.setFont(new Font("SanSerif", Font.BOLD, 15));
        okayButton.setBackground(GOLD_COLOR);
        okayButton.setForeground(PURPLE_COLOR);

        buttonPanel.add(okayButton, BorderLayout.SOUTH);
        buttonPanel.setBackground(PURPLE_COLOR);
        return buttonPanel;
    }

    /////Helper methods used by both GameRulesPanel and AboutTeamPanel/////
    /**
     * Scales the images to be the same width and height.
     * @param theIcon - the image to scale
     * @param theScale - the scale
     * @return - the scaled image
     */
    static ImageIcon scaleImageIcon(final ImageIcon theIcon, final int theScale) {
        return new ImageIcon(theIcon.getImage()
                .getScaledInstance(theScale, theScale, Image.SCALE_SMOOTH));
    }

    /**
     * Sets up the styles and info of the title panel.
     * @param theTeamNameLabel - the label to add
     * @return - the set-up title panel
     */
    static JPanel setUpTitlePanel(final String theTeamNameLabel) {
        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1, 3));

        final JLabel teamNameLabel = new JLabel(theTeamNameLabel);

        teamNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        teamNameLabel.setFont(ABOUT_FONT);
        teamNameLabel.setForeground(GOLD_COLOR);

        final ImageIcon HOGWARTS_ICON = new ImageIcon("src//View//Images//HogwartsIcon.jpg");
        titlePanel.add(new JLabel(scaleImageIcon(HOGWARTS_ICON, 90)));
        titlePanel.add(teamNameLabel);
        titlePanel.add(new JLabel(scaleImageIcon(HOGWARTS_ICON, 90)));

        titlePanel.setBackground(PURPLE_COLOR);
        titlePanel.setBorder(new EmptyBorder(20, 10, 5, 10));

        return titlePanel;
    }


    /**
     * Set up the action listener for the OK button on the GameRulesPanel
     * located as a tab in the game.
     */
    final ActionListener okayButtonOne = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            myGamePanel.setVisible(true);
            myMenuBar.setVisible(true);
        }
    };

    /**
     * Set up the action listener for the OK button on the GameRulesPanel
     * located as a button on the main menu screen.
     */
    final ActionListener okayButtonTwo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            myMainMenuPanel.setVisible(true);
        }
    };
}