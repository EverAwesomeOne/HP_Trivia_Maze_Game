package View;

import javax.swing.*;
import java.awt.*;

public class AboutTeam extends JPanel {
    private JPanel aboutPanel;
    private JTextArea aboutTextArea;
    private JButton closeAboutButton;

    AboutTeam() {
        setupHelpPanel();
    }

    private JPanel setupHelpPanel() {
        aboutPanel = new JPanel();
        aboutPanel.setLayout(new GridLayout(2,1));

        aboutTextArea = new JTextArea("Temp Text");
        aboutTextArea.setEditable(false);
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setWrapStyleWord(true);

        closeAboutButton = new JButton("OK");
        closeAboutButton.addActionListener(
                e -> {
                    aboutPanel.setVisible(false);
                }
        );

        aboutPanel.add(aboutTextArea);
        aboutPanel.add(closeAboutButton);

        aboutPanel.setVisible(true);

        return aboutPanel;
    }
}
