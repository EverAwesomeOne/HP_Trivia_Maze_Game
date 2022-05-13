package View;

import javax.swing.*;
import java.awt.*;

public class AboutTeam extends JPanel {
    private JPanel aboutPanel;

    AboutTeam(JFrame mainFrame, JPanel gamePanel, JMenuBar mainMenuBar) {
        aboutPanel = new JPanel();
        aboutPanel.setLayout(new BorderLayout());

        JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
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
