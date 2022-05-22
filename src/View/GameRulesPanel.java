package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRulesPanel extends JPanel {
    private JPanel myGamePanel;
    private JMenuBar myMenuBar;
    private JPanel myMainMenuPanel;

    GameRulesPanel(final JPanel theGamePanel, final JMenuBar theMenuBar) {
        myGamePanel = theGamePanel;
        myMenuBar = theMenuBar;
        setupGameRulesPanel("One");
    }

    GameRulesPanel(final JPanel theMainMenuPanel) {
        myMainMenuPanel = theMainMenuPanel;
        setupGameRulesPanel("Two");
    }

    private void setupGameRulesPanel(String buttonActionType) {
        setLayout(new BorderLayout());

        final JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        final JButton okayButton = new JButton("OK");
        if (buttonActionType.equals("One")) {
            okayButton.addActionListener(okayButtonOne);
        } else {
            okayButton.addActionListener(okayButtonTwo);
        }

        add(textField, BorderLayout.CENTER);
        add(okayButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    final ActionListener okayButtonOne = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            myGamePanel.setVisible(true);
            myMenuBar.setVisible(true);
        }
    };

    final ActionListener okayButtonTwo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            myMainMenuPanel.setVisible(true);
        }
    };
}