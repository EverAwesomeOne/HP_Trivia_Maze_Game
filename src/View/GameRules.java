package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRules {

    private JPanel myGameInfoPanel;

    private final JFrame myMainFrame;
    private JPanel myGamePanel;
    private JMenuBar myMainMenuBar;

    private JPanel myMainMenuPanel;

    GameRules(final JFrame theMainFrame, final JPanel theGamePanel, final JMenuBar theMainMenuBar) {
        myMainFrame = theMainFrame;
        myGamePanel = theGamePanel;
        myMainMenuBar = theMainMenuBar;
        setupGameRulesPanel("One");
    }

    GameRules(final JFrame theMainFrame, final JPanel theMainMenuPanel) {
        myMainFrame = theMainFrame;
        myMainMenuPanel = theMainMenuPanel;
        setupGameRulesPanel("Two");
    }

    private void setupGameRulesPanel(String buttonActionType) {
        myGameInfoPanel = new JPanel();
        myGameInfoPanel.setLayout(new BorderLayout());

        final JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        final JButton okayButton = new JButton("OK");
        if (buttonActionType.equals("One")) {
            okayButton.addActionListener(okayButtonOne);
        } else {
            okayButton.addActionListener(okayButtonTwo);
        }

        myGameInfoPanel.add(textField, BorderLayout.CENTER);
        myGameInfoPanel.add(okayButton, BorderLayout.SOUTH);

        myMainFrame.add(myGameInfoPanel);

        myGameInfoPanel.setVisible(true);
    }

    ActionListener okayButtonOne = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            myGameInfoPanel.setVisible(false);
            myGamePanel.setVisible(true);
            myMainMenuBar.setVisible(true);
        }
    };

    ActionListener okayButtonTwo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            myGameInfoPanel.setVisible(false);
            myMainMenuPanel.setVisible(true);
        }
    };
}
