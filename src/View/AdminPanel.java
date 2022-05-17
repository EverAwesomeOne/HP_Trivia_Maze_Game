package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    private JPanel adminPanel;

    private TriviaMazeBrain triviaMazeBrain;

    AdminPanel(JFrame mainFrame, TriviaMazeBrain triviaMazeBrain) {
        adminPanel = new JPanel();
        adminPanel.setLayout(new BorderLayout());
        this.triviaMazeBrain =triviaMazeBrain;

        JLabel temp = new JLabel("ADD ADMIN FEATURES");
        temp.setHorizontalAlignment(JLabel.CENTER);

        JCheckBox selectDebugFeatureBox = new JCheckBox("Turn on debug feature");


        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(
                e -> {
                    adminPanel.setVisible(false);
                    new MainMenuPanel(mainFrame, triviaMazeBrain);
                }
        );

        adminPanel.add(temp, BorderLayout.NORTH);
        adminPanel.add(selectDebugFeatureBox, BorderLayout.CENTER);
        adminPanel.add(okBtn, BorderLayout.SOUTH);

        mainFrame.add(adminPanel);

        adminPanel.setVisible(true);
    }
}
