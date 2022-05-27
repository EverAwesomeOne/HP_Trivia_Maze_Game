package View;

import javax.swing.*;
import java.awt.*;

class AboutTeamPanel extends JPanel {

    final static Font ABOUT_FONT = new Font("SansSerif", Font.BOLD, 25);

    final static Color GOLD_COLOR = new Color(255,204,51).darker();
    final static Color PURPLE_COLOR = new Color(102,0,153).darker();

    AboutTeamPanel(final JPanel theGamePanel, final JMenuBar theMenuBar) {
        setLayout(new GridLayout(3,1));
        setBorder(BorderFactory.createLineBorder(GOLD_COLOR,5));
        setBackground(PURPLE_COLOR);

        add(titlePanel());
        add(membersPanel());
        add(teamInfoAndButtonPanel(theGamePanel, theMenuBar));

        setVisible(false);
    }

    private JPanel titlePanel() {
        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1, 3));

        final JLabel teamNameLabel = new JLabel("<html><div style='text-align: center;'>" + "Harry Potter<br>Trivia Maze<br>Team" + "</div></html>");

        teamNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        teamNameLabel.setFont(ABOUT_FONT);
        teamNameLabel.setForeground(GOLD_COLOR);

        final ImageIcon HOGWARTS_ICON = new ImageIcon("src//View//Images//HogwartsIcon.jpg");
        titlePanel.add(new JLabel(scaleImageIcon(HOGWARTS_ICON, 90)));
        titlePanel.add(teamNameLabel);
        titlePanel.add(new JLabel(scaleImageIcon(HOGWARTS_ICON, 90)));

        titlePanel.setBackground(PURPLE_COLOR);

        return titlePanel;
    }

    private JPanel membersPanel() {
        final JPanel membersPanel = new JPanel();

        final ImageIcon HOGWARTS_ICON = new ImageIcon("src//View//Images//HogwartsIcon.jpg");

        final ImageIcon mariaIcon = HOGWARTS_ICON;
        final ImageIcon chloeIcon = HOGWARTS_ICON;
        final ImageIcon edwinIcon = HOGWARTS_ICON;

        final JLabel aboutMaria = new JLabel("Maria Biography Goes Here and this is the test");
        final JLabel aboutChloe = new JLabel("Chloe Biography Goes Here");
        final JLabel aboutEdwin = new JLabel("Edwin Bio");

        aboutMaria.setHorizontalAlignment(SwingConstants.LEFT);
        aboutChloe.setHorizontalAlignment(SwingConstants.LEFT);
        aboutEdwin.setHorizontalAlignment(SwingConstants.LEFT);

        aboutMaria.setFont(new Font("SanSerif", Font.PLAIN, 15));
        aboutChloe.setFont(new Font("SanSerif", Font.PLAIN, 15));
        aboutEdwin.setFont(new Font("SanSerif", Font.PLAIN, 15));

        aboutMaria.setForeground(GOLD_COLOR);
        aboutChloe.setForeground(GOLD_COLOR);
        aboutEdwin.setForeground(GOLD_COLOR);

        GridLayout iconPanelLayout = new GridLayout(3,1);
        iconPanelLayout.setVgap(25);

        final JPanel iconPanel = new JPanel();
        iconPanel.setLayout(iconPanelLayout);
        iconPanel.setBackground(PURPLE_COLOR);
        iconPanel.add(new JLabel(scaleImageIcon(mariaIcon, 40)));
        iconPanel.add(new JLabel(scaleImageIcon(chloeIcon, 40)));
        iconPanel.add(new JLabel(scaleImageIcon(edwinIcon, 40)));

        GridLayout bioPanelLayout = new GridLayout(3,1);
        bioPanelLayout.setVgap(45);

        final JPanel bioPanel  = new JPanel();
        bioPanel.setLayout(bioPanelLayout);
        bioPanel.setBackground(PURPLE_COLOR);
        bioPanel.add(aboutMaria);
        bioPanel.add(aboutChloe);
        bioPanel.add(aboutEdwin);

        membersPanel.add(iconPanel);
        membersPanel.add(bioPanel);

        membersPanel.setBackground(PURPLE_COLOR);

        return membersPanel;
    }

    private JPanel teamInfoAndButtonPanel(final JPanel theGamePanel, final JMenuBar theMenuBar) {
        final JPanel teamInfoAndButtonPanel = new JPanel();
        teamInfoAndButtonPanel.setLayout(new BorderLayout());

        final JLabel aboutTeamLabel = new JLabel("<html><div style='text-align: center;'>"
                + "Maria Babko ~ Chloe Duncan ~ Edwin Solis-Bruno<br>" +
                "University of Washington<br>TCSS 360 A, Professor Tom Capaul<br>" +
                "June 2022" + "</div></html>");

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

    private ImageIcon scaleImageIcon(final ImageIcon icon, final int scale) {
        return new ImageIcon(icon.getImage().getScaledInstance(scale, scale, Image.SCALE_SMOOTH));
    }
}
