package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRules {

    private final JPanel myGameInfoPanel;

    GameRules(final JFrame theMainFrame, final JPanel theGamePanel,
              final JMenuBar theMainMenuBar) {
        myGameInfoPanel = new JPanel();
        myGameInfoPanel.setLayout(new BorderLayout());

        JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        JButton okayButton = new JButton("OK");
        okayButton.addActionListener(
                e -> {
                    myGameInfoPanel.setVisible(false);
                    theGamePanel.setVisible(true);
                    theMainMenuBar.setVisible(true);
                }
        );

        myGameInfoPanel.add(textField, BorderLayout.CENTER);
        myGameInfoPanel.add(okayButton, BorderLayout.SOUTH);

        theMainFrame.add(myGameInfoPanel);

        myGameInfoPanel.setVisible(true);
    }

    GameRules(final JFrame theMainFrame, final JPanel theMainMenuPanel) {
        myGameInfoPanel = new JPanel();
        myGameInfoPanel.setLayout(new BorderLayout());

        JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        JButton okayButton = new JButton("OK");
        okayButton.addActionListener(
                e -> {
                    myGameInfoPanel.setVisible(false);
                    theMainMenuPanel.setVisible(true);
                }
        );

        myGameInfoPanel.add(textField, BorderLayout.CENTER);
        myGameInfoPanel.add(okayButton, BorderLayout.SOUTH);

        theMainFrame.add(myGameInfoPanel);

        myGameInfoPanel.setVisible(true);
    }

    // Pull out common code!!

    /*private void setupGameRulesPanel(String buttonActionType) {
        myGameInfoPanel = new JPanel();
        myGameInfoPanel.setLayout(new BorderLayout());

        JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        JButton okayButton = new JButton("OK");
        if (buttonActionType.equals("One")) {
            okayButton.addActionListener(okayButtonOne);
        } else {
            okayButton.addActionListener(okayButtonTwo);
        }

        myGameInfoPanel.add(textField, BorderLayout.CENTER);
        myGameInfoPanel.add(okayButton, BorderLayout.SOUTH);

        //theMainFrame.add(myGameInfoPanel);

        myGameInfoPanel.setVisible(true);
    }

    ActionListener okayButtonOne = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            myGameInfoPanel.setVisible(false);
            theGamePanel.setVisible(true);
            theMainMenuBar.setVisible(true);
        }
    };

    ActionListener okayButtonTwo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            myGameInfoPanel.setVisible(false);
            theMainMenuPanel.setVisible(true);
        }
    };*/

}
