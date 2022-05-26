package View;

import javax.swing.*;
import java.awt.*;

class AboutTeamPanel extends JPanel {

    final static Font ABOUT_FONT = new Font("SansSerif", Font.PLAIN, 20);

    final static Color GOLD_COLOR = new Color(255,204,51).darker();
    final static Color PURPLE_COLOR = new Color(102,0,153).darker();

    AboutTeamPanel(final JPanel theGamePanel, final JMenuBar theMenuBar) {
        setLayout(new GridLayout(4,1));
        setBorder(BorderFactory.createLineBorder(GOLD_COLOR,5));
        setBackground(PURPLE_COLOR);

        final JLabel aboutTeamLabel = new JLabel("<html><div style='text-align: center;'>"
                + "Maria Babko ~ Chloe Duncan ~ Edwin Solis-Bruno<br>" +
                "University of Washington<br>TCSS 360 A, Professor Tom Capaul<br>" +
                "June 2022" + "</div></html>");


        aboutTeamLabel.setHorizontalAlignment(SwingConstants.CENTER);

        aboutTeamLabel.setFont(ABOUT_FONT);

        aboutTeamLabel.setForeground(GOLD_COLOR);

        add(titlePanel());
        add(membersPanel());
        add(aboutTeamLabel);
        add(okayButtonPanel(theGamePanel, theMenuBar));

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

        titlePanel.add(new JLabel(scaleImageIcon(HOGWARTS_ICON, 100)));
        titlePanel.add(teamNameLabel);
        titlePanel.add(new JLabel(scaleImageIcon(HOGWARTS_ICON, 100)));

        titlePanel.setBackground(PURPLE_COLOR);

        return titlePanel;
    }

    private JPanel membersPanel() {
        final JPanel membersPanel = new JPanel();
        membersPanel.setLayout(new GridLayout(3,2));
        membersPanel.setBorder(BorderFactory.createLineBorder(GOLD_COLOR, 1));

        final ImageIcon HOGWARTS_ICON = new ImageIcon("src//View//Images//HogwartsIcon.jpg");

        final ImageIcon mariaIcon = HOGWARTS_ICON;
        final ImageIcon chloeIcon = HOGWARTS_ICON;
        final ImageIcon edwinIcon = HOGWARTS_ICON;

        final JLabel aboutMaria = new JLabel("Maria Bio");
        final JLabel aboutChloe = new JLabel("Chloe Bio");
        final JLabel aboutEdwin = new JLabel("Edwin Bio");

        aboutMaria.setFont(ABOUT_FONT);
        aboutChloe.setFont(ABOUT_FONT);
        aboutEdwin.setFont(ABOUT_FONT);

        aboutMaria.setForeground(GOLD_COLOR);
        aboutChloe.setForeground(GOLD_COLOR);
        aboutEdwin.setForeground(GOLD_COLOR);

        membersPanel.add(new JLabel(scaleImageIcon(mariaIcon, 30)));
        membersPanel.add(aboutMaria);
        membersPanel.add(new JLabel(scaleImageIcon(chloeIcon, 30)));
        membersPanel.add(aboutChloe);
        membersPanel.add(new JLabel(scaleImageIcon(edwinIcon, 30)));
        membersPanel.add(aboutEdwin);

        membersPanel.setBackground(PURPLE_COLOR);

        return membersPanel;
    }

    private JPanel okayButtonPanel(final JPanel theGamePanel, final JMenuBar theMenuBar) {
        final JPanel okayButtonPanel = new JPanel();

        final JButton okayButton = new JButton("OK");
        okayButton.addActionListener(
                e -> {
                    setVisible(false);
                    theGamePanel.setVisible(true);
                    theMenuBar.setVisible(true);
                }
        );

        okayButton.setVerticalAlignment(SwingConstants.CENTER);

        okayButtonPanel.setBackground(PURPLE_COLOR);

        okayButton.setFont(ABOUT_FONT);
        okayButton.setBackground(GOLD_COLOR);
        okayButton.setForeground(PURPLE_COLOR);

        okayButtonPanel.add(okayButton);

        return okayButtonPanel;
    }

    private ImageIcon scaleImageIcon(final ImageIcon icon, final int scale) {
        return new ImageIcon(icon.getImage().getScaledInstance(scale, scale, Image.SCALE_SMOOTH));
    }
}
