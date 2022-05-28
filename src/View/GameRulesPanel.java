package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRulesPanel extends JPanel {
    private JPanel myGamePanel;
    private JMenuBar myMenuBar;
    private JPanel myMainMenuPanel;

    final static Font ABOUT_FONT = new Font("SansSerif", Font.BOLD, 25);

    final static Color GOLD_COLOR = new Color(255,204,51).darker();
    final static Color PURPLE_COLOR = new Color(102,0,153).darker();

    GameRulesPanel(final JPanel theGamePanel, final JMenuBar theMenuBar) {
        myGamePanel = theGamePanel;
        myMenuBar = theMenuBar;
        setupGameRulesPanel("One");
    }

    GameRulesPanel(final JPanel theMainMenuPanel) {
        myMainMenuPanel = theMainMenuPanel;
        setupGameRulesPanel("Two");
    }

    private void setupGameRulesPanel(final String buttonActionType) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(GOLD_COLOR,5));
        setBackground(PURPLE_COLOR);

        add(titlePanel(), BorderLayout.NORTH);
        add(gameRules(), BorderLayout.CENTER);
        add(buttonPanel(buttonActionType), BorderLayout.SOUTH);

        setVisible(false);
    }

    private JPanel titlePanel() {
        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1, 3));

        final JLabel teamNameLabel = new JLabel("<html><div style='text-align: center;'>"
                + "Harry Potter<br>Trivia Maze<br>Game Rules" + "</div></html>");

        teamNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        teamNameLabel.setFont(ABOUT_FONT);
        teamNameLabel.setForeground(GOLD_COLOR);

        final ImageIcon HOGWARTS_ICON = new ImageIcon("src//View//Images//HogwartsIcon.jpg");
        titlePanel.add(new JLabel(scaleImageIcon(HOGWARTS_ICON, 90)));
        titlePanel.add(teamNameLabel);
        titlePanel.add(new JLabel(scaleImageIcon(HOGWARTS_ICON, 90)));

        titlePanel.setBackground(PURPLE_COLOR);
        titlePanel.setBorder(new EmptyBorder(45, 10, 5, 10));

        return titlePanel;
    }


    private JPanel gameRules () {
        JPanel gameRules = new JPanel();

        GridLayout gameRulesPanelLayout = new GridLayout(3,1);
        gameRulesPanelLayout.setVgap(4);

        gameRules.setLayout(gameRulesPanelLayout);
        gameRules.setBackground(PURPLE_COLOR);
        gameRules.add(howToPlayPanel());
        gameRules.add(featuresPanel());
        gameRules.add(answerLogisticsPanel());

        gameRules.setBackground(PURPLE_COLOR);
        gameRules.setBorder(new EmptyBorder(10, 10, 0, 10));

        return gameRules;

    }

    private JPanel howToPlayPanel () {
        final JLabel howToPlayTitle = new JLabel("How To Play:");
        final JLabel howToPlay = new JLabel("<html><div style='text-align: center;'>" +
                "Click the arrow buttons to choose a direction and answer trivia questions as " +
                "you go through different doors and get into other rooms. Make your way through" +
                " the rooms and to the exit (labeled as E) to win! If you get a question wrong: " +
                "the door will lock, an \"X\" will appear on that path, and you will have to try " +
                "to find another way to get to the exit. If you run out of open doors to reach the " +
                "exit, it's game over.</div></html>");

        return setUpPanelsOnGameRulesPanel(howToPlayTitle, howToPlay);
    }

    private JPanel featuresPanel () {
        final JLabel featuresTitle = new JLabel("Features:");
        final JLabel features = new JLabel("<html><div style='text-align: center;'>" +
                "Once you answer a question correctly that door unlocks " +
                "permanently and you will be able to move freely through all the " +
                "doors you answered correctly.</div></html>");

        return setUpPanelsOnGameRulesPanel(featuresTitle, features);
    }

    private JPanel answerLogisticsPanel () {
        final JLabel answerLogisticsTitle = new JLabel("Answer Logistics:");
        final JLabel answerLogistics = new JLabel("<html><div style='text-align: center;'>" +
                "All short answers will need to be capitalized for the answer to be correct." +
                " All name questions should be answered with full names." +
                " Questions are worded to prompt if the answer needs to be plural or not.</div></html>");

        return setUpPanelsOnGameRulesPanel(answerLogisticsTitle, answerLogistics);
    }

    private JPanel setUpPanelsOnGameRulesPanel(final JLabel theTitle, final JLabel theText) {
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

    private JPanel buttonPanel (final String buttonActionType) {
        final JPanel buttonPanel = new JPanel(new BorderLayout());
        final JButton okayButton = new JButton("OK");
        if (buttonActionType.equals("One")) {
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

    private ImageIcon scaleImageIcon(final ImageIcon icon, final int scale) {
        return new ImageIcon(icon.getImage().getScaledInstance(scale, scale, Image.SCALE_SMOOTH));
    }

    final ActionListener okayButtonOne = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            myGamePanel.setVisible(true);
            myMenuBar.setVisible(true);
        }
    };

    final ActionListener okayButtonTwo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            myMainMenuPanel.setVisible(true);
        }
    };
}