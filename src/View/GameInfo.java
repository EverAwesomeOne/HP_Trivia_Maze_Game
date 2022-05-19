package View;

import javax.swing.*;
import java.awt.*;

public class GameInfo extends JPanel {

    private final JPanel myGameInfoPanel;

    GameInfo(final JFrame theMainFrame, final JPanel theGamePanel, final JMenuBar theMainMenuBar) {
        myGameInfoPanel = new JPanel();
        myGameInfoPanel.setLayout(new BorderLayout());

        JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        JButton OK = new JButton("OK");
        OK.addActionListener(
                e -> {
                    myGameInfoPanel.setVisible(false);
                    theGamePanel.setVisible(true);
                    theMainMenuBar.setVisible(true);
                }
        );

        myGameInfoPanel.add(textField, BorderLayout.CENTER);
        myGameInfoPanel.add(OK, BorderLayout.SOUTH);

        theMainFrame.add(myGameInfoPanel);

        myGameInfoPanel.setVisible(true);
    }
}
