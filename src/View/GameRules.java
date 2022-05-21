package View;

import javax.swing.*;
import java.awt.*;

public class GameRules extends JPanel {

    GameRules(final JFrame theMainFrame, final JPanel theGamePanel,
              final JMenuBar theMainMenuBar) {
        setLayout(new BorderLayout());

        JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        JButton okayButton = new JButton("OK");
        okayButton.addActionListener(
                e -> {
                    setVisible(false);
                    theGamePanel.setVisible(true);
                    theMainMenuBar.setVisible(true);
                }
        );

        add(textField, BorderLayout.CENTER);
        add(okayButton, BorderLayout.SOUTH);

        theMainFrame.add(this);
        setVisible(true);
    }

    GameRules(final JFrame theMainFrame, final JPanel theMainMenuPanel) {
        setLayout(new BorderLayout());

        JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        JButton okayButton = new JButton("OK");
        okayButton.addActionListener(
                e -> {
                    setVisible(false);
                    theMainMenuPanel.setVisible(true);
                }
        );

        add(textField, BorderLayout.CENTER);
        add(okayButton, BorderLayout.SOUTH);

        theMainFrame.add(this);
        setVisible(true);
    }

    // Pull out common code!!

    /*private void setupGameRulesPanel(String buttonActionType) {
        this = new JPanel();
        setLayout(new BorderLayout());

        JTextField textField = new JTextField("Add Text");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        JButton okayButton = new JButton("OK");
        if (buttonActionType.equals("One")) {
            okayButton.addActionListener(okayButtonOne);
        } else {
            okayButton.addActionListener(okayButtonTwo);
        }

        add(textField, BorderLayout.CENTER);
        add(okayButton, BorderLayout.SOUTH);

        //theMainFrame.add(this);

        setVisible(true);
    }

    ActionListener okayButtonOne = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            theGamePanel.setVisible(true);
            theMainMenuBar.setVisible(true);
        }
    };

    ActionListener okayButtonTwo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            theMainMenuPanel.setVisible(true);
        }
    };*/

}
