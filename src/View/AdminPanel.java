package View;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    private JPanel adminPanel;

    AdminPanel(JFrame mainFrame) {
        adminPanel = new JPanel();
        adminPanel.setLayout(new BorderLayout());

        JLabel temp = new JLabel("ADD ADMIN FEATURES");
        temp.setHorizontalAlignment(JLabel.CENTER);

        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(
                e -> {
                    adminPanel.setVisible(false);
                    new MainMenuPanel(mainFrame);
                }
        );

        adminPanel.add(temp, BorderLayout.CENTER);
        adminPanel.add(okBtn, BorderLayout.SOUTH);

        mainFrame.add(adminPanel);

        adminPanel.setVisible(true);
    }
}
