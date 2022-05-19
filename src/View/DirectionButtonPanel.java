package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class DirectionButtonPanel extends JPanel {

    private final MazePanel myMazePanel;
    private final TriviaMazeBrain myTriviaMazeBrain;

    private static final String DIR_NORTH = "NORTH";
    private static final String DIR_SOUTH = "SOUTH";
    private static final String DIR_WEST = "WEST";
    private static final String DIR_EAST = "EAST";

    private final BasicArrowButton myNorthButton;
    private final BasicArrowButton mySouthButton;
    private final BasicArrowButton myEastButton;
    private final BasicArrowButton myWestButton;

    DirectionButtonPanel(final JPanel theGamePanel, final MazePanel theMazePanel, final TriviaMazeBrain theTriviaMazeBrain) {
        myMazePanel = theMazePanel;
        myTriviaMazeBrain = theTriviaMazeBrain;
        JPanel myDirectionButtonPanel = new JPanel();
        myDirectionButtonPanel.setLayout(new GridLayout(3,3));
        myDirectionButtonPanel.setBorder(BorderFactory.createTitledBorder("Choose Door"));

        myNorthButton = new BasicArrowButton(BasicArrowButton.NORTH);
        mySouthButton = new BasicArrowButton(BasicArrowButton.SOUTH);
        myEastButton = new BasicArrowButton(BasicArrowButton.EAST);
        myWestButton = new BasicArrowButton(BasicArrowButton.WEST);

        // Replace with an icon?
        final JLabel centerLabel = new JLabel("<html>Current<br/>Room</html>", SwingConstants.CENTER);

        // initialize BasicArrowButtons based on the start position in maze
        disableButtons();

        addArrowActionListener(myNorthButton, DIR_NORTH);
        addArrowActionListener(mySouthButton, DIR_SOUTH);
        addArrowActionListener(myWestButton, DIR_WEST);
        addArrowActionListener(myEastButton, DIR_EAST);

        // empty JLabels act as placeholders in GridLayout
        // to distance BasicArrowButtons
        myDirectionButtonPanel.add(new JLabel(""));
        myDirectionButtonPanel.add(myNorthButton);
        myDirectionButtonPanel.add(new JLabel(""));
        myDirectionButtonPanel.add(myWestButton);
        myDirectionButtonPanel.add(centerLabel);
        myDirectionButtonPanel.add(myEastButton);
        myDirectionButtonPanel.add(new JLabel(""));
        myDirectionButtonPanel.add(mySouthButton);
        myDirectionButtonPanel.add(new JLabel(""));

        theGamePanel.add(myDirectionButtonPanel);

        myDirectionButtonPanel.setVisible(true);
    }

    private void addArrowActionListener(final BasicArrowButton theArrowButton, final String theArrowDirection) {
        theArrowButton.addActionListener(
                e -> myTriviaMazeBrain.move(theArrowDirection)
        );
    }

    void disableButtons() {
        myNorthButton.setEnabled(myMazePanel.validDirection(DIR_NORTH));
        mySouthButton.setEnabled(myMazePanel.validDirection(DIR_SOUTH));
        myWestButton.setEnabled(myMazePanel.validDirection(DIR_WEST));
        myEastButton.setEnabled(myMazePanel.validDirection(DIR_EAST));
    }

    void disableAllButtons() {
        myNorthButton.setEnabled(false);
        mySouthButton.setEnabled(false);
        myWestButton.setEnabled(false);
        myEastButton.setEnabled(false);
    }
}
