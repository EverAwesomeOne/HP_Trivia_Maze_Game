package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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

    final static Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 15);

    DirectionButtonPanel(final JPanel theGamePanel, final MazePanel theMazePanel,
                         final TriviaMazeBrain theTriviaMazeBrain) {

        myMazePanel = theMazePanel;
        myTriviaMazeBrain = theTriviaMazeBrain;
        setLayout(new GridLayout(3,3));

        final TitledBorder border = new TitledBorder("Choose Door");
        border.setTitleFont(TITLE_FONT);
        setBorder(border);
        myNorthButton = new BasicArrowButton(BasicArrowButton.NORTH);
        mySouthButton = new BasicArrowButton(BasicArrowButton.SOUTH);
        myEastButton = new BasicArrowButton(BasicArrowButton.EAST);
        myWestButton = new BasicArrowButton(BasicArrowButton.WEST);

        // Replace with an icon?
        final JLabel centerLabel = new JLabel("<html>Current<br/>Room</html>", SwingConstants.CENTER);
        centerLabel.setFont(TITLE_FONT);

        // initialize BasicArrowButtons based on the start position in maze
        setDirectionButtonsVisibility();

        addArrowActionListener(myNorthButton, DIR_NORTH);
        addArrowActionListener(mySouthButton, DIR_SOUTH);
        addArrowActionListener(myWestButton, DIR_WEST);
        addArrowActionListener(myEastButton, DIR_EAST);

        // empty JLabels act as placeholders in GridLayout
        // to distance BasicArrowButtons
        add(new JLabel(""));
        add(myNorthButton);
        add(new JLabel(""));
        add(myWestButton);
        add(centerLabel);
        add(myEastButton);
        add(new JLabel(""));
        add(mySouthButton);
        add(new JLabel(""));

        theGamePanel.add(this);

        setVisible(true);
    }

    private void addArrowActionListener(final BasicArrowButton theArrowButton,
                                        final String theArrowDirection) {
        theArrowButton.addActionListener(
                e -> myTriviaMazeBrain.setUpQuestion(theArrowDirection)
        );
    }

    public void setDirectionButtonsVisibility() {
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
