package View;

import javax.swing.*;
import java.awt.*;

public class GameInfo extends JPanel {

    private JPanel gameInfoPanel;

    GameInfo(JFrame mainFrame, JPanel gamePanel, JMenuBar mainMenuBar) {
        gameInfoPanel = new JPanel();
        gameInfoPanel.setLayout(new BorderLayout());

        JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        JButton OK = new JButton("OK");
        OK.addActionListener(
                e -> {
                    gameInfoPanel.setVisible(false);
                    gamePanel.setVisible(true);
                    mainMenuBar.setVisible(true);
                }
        );

        gameInfoPanel.add(textField, BorderLayout.CENTER);
        gameInfoPanel.add(OK, BorderLayout.SOUTH);

        mainFrame.add(gameInfoPanel);

        gameInfoPanel.setVisible(true);
    }
}
