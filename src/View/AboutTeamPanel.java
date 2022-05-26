package View;

import javax.swing.*;
import java.awt.*;

class AboutTeamPanel extends JPanel {

    final static Font ABOUT_FONT = new Font("SansSerif", Font.PLAIN, 20);

    AboutTeamPanel(final JPanel theGamePanel, final JMenuBar theMenuBar) {
        setLayout(new BorderLayout());

        final JTextArea textField = new JTextArea("""
                Harry Potter Trivia Maze Team
                Maria Babko ~ Chloe Duncan ~ Edwin Solis-Bruno
                University of Washington
                TCSS 360 A, Professor Tom Capaul
                May 2022""");

        textField.setAlignmentX(CENTER_ALIGNMENT);
        textField.setEditable(false);

        final JButton okayButton = new JButton("OK");
        okayButton.addActionListener(
                e -> {
                    setVisible(false);
                    theGamePanel.setVisible(true);
                    theMenuBar.setVisible(true);
                }
        );

        textField.setFont(ABOUT_FONT);
        okayButton.setFont(ABOUT_FONT);

        add(textField, BorderLayout.CENTER);
        add(okayButton, BorderLayout.SOUTH);

        setVisible(false);
    }
}
