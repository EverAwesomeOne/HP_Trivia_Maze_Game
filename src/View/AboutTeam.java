package View;

import javax.swing.*;
import java.awt.*;

class AboutTeam extends JPanel {

    AboutTeam(final JFrame theMainFrame, JPanel theGamePanel, final JMenuBar theMainMenuBar) {
        setLayout(new BorderLayout());

        final JTextArea textField = new JTextArea("""
                Hodgepodge Trivia Maze Team
                Maria Babko ~ Chloe Duncan ~ Edwin Solis-Bruno
                University of Washington
                TCSS 360 A, Professor Tom Capaul
                May 2022""");
        textField.setEditable(false);

        final JButton okayButton = new JButton("OK");
        okayButton.addActionListener(
                e -> {
                    setVisible(false);
                    theGamePanel.setVisible(true);
                    theMainMenuBar.setVisible(true);
                }
        );

        add(textField, BorderLayout.CENTER);
        add(okayButton, BorderLayout.SOUTH);

        theMainFrame.add(this);

        setVisible(true);
    }
}
