package View;

import javax.swing.*;
import java.awt.*;

/**
 * The AboutTeamPanel class represents the "About the Developers" tab on the menu
 * bar at the top of the trivia maze game.
 */
class AboutTeamPanel extends JPanel {

    private final static Color GOLD_COLOR = new Color(255,204,51).darker();
    private final static Color PURPLE_COLOR = new Color(102,0,153).darker();

    /**
     * Constructor for the AboutTeamPanel class.
     * Sets up the layout of the panel and adds it to the menu bar and the
     * overall game panel screen.
     * @param theGamePanel - the overall game panel screen
     * @param theMenuBar - the menu bar at the top of the game screen
     */
    AboutTeamPanel(final JPanel theGamePanel, final JMenuBar theMenuBar) {
        setLayout(new GridLayout(3,1));
        setBorder(BorderFactory.createLineBorder(GOLD_COLOR,5));
        setBackground(PURPLE_COLOR);

        add(setupTitlePanel());
        add(setupMembersPanel());
        add(setupSchoolInfoAndButtonPanel(theGamePanel, theMenuBar));

        setVisible(false);
    }

    /**
     * Sets up the about team title panel at the top of the game screen.
     * @return - the set-up about team title panel
     */
    private JPanel setupTitlePanel() {
        return GameRulesPanel.setupTitlePanel("<html><div style='text-align: center;'>"
                + "Harry Potter<br>Trivia Maze<br>Team" + "</div></html>");
    }

    /**
     * Sets up the panel where all the team members' info is displayed.
     * @return - the set-up team members' info panel
     */
    private JPanel setupMembersPanel() {
        final JPanel membersPanel = new JPanel();
        final ImageIcon CHLOE_ICON = new ImageIcon("src//View//Images//Chloe.png");
        final ImageIcon MARIA_ICON = new ImageIcon("src//View//Images//Maria.jpg");
        final ImageIcon EDWIN_ICON = new ImageIcon("src//View//Images//Edwin.png");


        final JLabel aboutMaria = setupTeamMemberJLabel("Maria Babko - The only actual Potterhead here ⚡");
        final JLabel aboutChloe = setupTeamMemberJLabel("Chloe Duncan - Addicted to Java: ☕ & \uD83D\uDCBB");
        final JLabel aboutEdwin = setupTeamMemberJLabel("Edwin Solis-Bruno - A connoisseur for everything \uD83C\uDF53 related");

        final GridLayout iconPanelLayout = new GridLayout(3,1);
        iconPanelLayout.setVgap(25);

        final JPanel iconPanel = setupPanelsOnMemberPanel(iconPanelLayout,
                new JLabel(GameRulesPanel.scaleImageIcon(MARIA_ICON, 40)),
                new JLabel(GameRulesPanel.scaleImageIcon(CHLOE_ICON, 40)),
                new JLabel(GameRulesPanel.scaleImageIcon(EDWIN_ICON, 40)));


        final GridLayout bioPanelLayout = new GridLayout(3,1);
        bioPanelLayout.setVgap(45);

        final JPanel bioPanel  = setupPanelsOnMemberPanel(bioPanelLayout, aboutMaria, aboutChloe, aboutEdwin);

        membersPanel.add(iconPanel);
        membersPanel.add(bioPanel);

        membersPanel.setBackground(PURPLE_COLOR);

        return membersPanel;
    }

    /**
     * Sets up styles and text for a team member JLabel.
     * @param theTeamMemberInfo - the team member about info
     * @return - the set-up team member label
     */
    private JLabel setupTeamMemberJLabel(final String theTeamMemberInfo) {
        final JLabel teamMemberLabel = new JLabel(theTeamMemberInfo);
        teamMemberLabel.setHorizontalAlignment(SwingConstants.LEFT);
        teamMemberLabel.setFont(new Font("SanSerif", Font.PLAIN, 15));
        teamMemberLabel.setForeground(GOLD_COLOR);

        return teamMemberLabel;
    }

    /**
     * Sets up the styles and labels on the specific component panels that
     * are on the member panel.
     * @param thePanelLayout - the layout of the specific panel
     * @param theLabel1 - the first label added to the panel
     * @param theLabel2 - the second label added to the panel
     * @param theLabel3 - the third label added to the panel
     * @return - the set-up component panel to add to member panel
     */
    private JPanel setupPanelsOnMemberPanel(final GridLayout thePanelLayout,
                                            final JLabel theLabel1, final JLabel theLabel2,
                                            final JLabel theLabel3) {
        final JPanel panelOnMemberPanel  = new JPanel();
        panelOnMemberPanel.setLayout(thePanelLayout);
        panelOnMemberPanel.setBackground(PURPLE_COLOR);
        panelOnMemberPanel.add(theLabel1);
        panelOnMemberPanel.add(theLabel2);
        panelOnMemberPanel.add(theLabel3);

        return panelOnMemberPanel;
    }

    /**
     * Sets up the style and info about the school and the class.
     * Also sets up the styles and info for the OK button.
     * @param theGamePanel - the overall game panel screen
     * @param theMenuBar - the menu tabs located at the top of the game screen
     * @return - the set-up panel with the school info and button set up
     */
    private JPanel setupSchoolInfoAndButtonPanel(final JPanel theGamePanel, final JMenuBar theMenuBar) {
        final JPanel teamInfoAndButtonPanel = new JPanel();
        teamInfoAndButtonPanel.setLayout(new BorderLayout());

        final JLabel aboutTeamLabel = new JLabel("<html><div style='text-align: center;'>"
                + "Maria Babko ~ Chloe Duncan ~ Edwin Solis-Bruno<br>" +
                "University of Washington<br>TCSS 360 A, Professor Tom Capaul<br>" +
                "Spring 2022" + "</div></html>");

        aboutTeamLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aboutTeamLabel.setVerticalAlignment(SwingConstants.CENTER);
        aboutTeamLabel.setFont(new Font("SanSerif", Font.PLAIN, 15));
        aboutTeamLabel.setForeground(GOLD_COLOR);

        final JButton okayButton = new JButton("OK");
        okayButton.addActionListener(
                e -> {
                    setVisible(false);
                    theGamePanel.setVisible(true);
                    theMenuBar.setVisible(true);
                }
        );

        okayButton.setVerticalAlignment(SwingConstants.CENTER);
        okayButton.setFont(new Font("SanSerif", Font.BOLD, 15));
        okayButton.setBackground(GOLD_COLOR);
        okayButton.setForeground(PURPLE_COLOR);

        teamInfoAndButtonPanel.setBackground(PURPLE_COLOR);

        teamInfoAndButtonPanel.add(aboutTeamLabel, BorderLayout.CENTER);
        teamInfoAndButtonPanel.add(okayButton, BorderLayout.SOUTH);

        return teamInfoAndButtonPanel;
    }
}
