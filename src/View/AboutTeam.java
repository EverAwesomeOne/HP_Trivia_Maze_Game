package View;

import javax.swing.*;
import java.awt.*;

public class AboutTeam extends JPanel {
    private JPanel aboutPanel;

    AboutTeam(JFrame mainFrame, JPanel gamePanel, JMenuBar mainMenuBar) {
        aboutPanel = new JPanel();
        aboutPanel.setLayout(new BorderLayout());

        JTextArea textField = new JTextArea("Hodgepodge Trivia Maze Team\n" +
                "Maria Babko ~ Chloe Duncan ~ Edwin Solis-Bruno\nUniversity of Washington\n" +
                "TCSS 360 A, Professor Tom Capaul\nMay 2022");
        textField.setEditable(false);

        JButton OK = new JButton("OK");
        OK.addActionListener(
                e -> {
                    aboutPanel.setVisible(false);
                    gamePanel.setVisible(true);
                    mainMenuBar.setVisible(true);
                }
        );

        aboutPanel.add(textField, BorderLayout.CENTER);
        aboutPanel.add(OK, BorderLayout.SOUTH);

        mainFrame.add(aboutPanel);

        aboutPanel.setVisible(true);
    }
}
