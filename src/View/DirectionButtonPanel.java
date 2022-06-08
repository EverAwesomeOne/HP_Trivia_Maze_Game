package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

/**
 * The DirectionButtonPanel class represents the part of the game screen that displays
 * the direction buttons.
 */
public class DirectionButtonPanel extends JPanel {

    private final MazePanel myMazePanel;
    private final TriviaMazeBrain myTriviaMazeBrain;

    private static final String DIR_NORTH = "NORTH";
    private static final String DIR_SOUTH = "SOUTH";
    private static final String DIR_WEST = "WEST";
    private static final String DIR_EAST = "EAST";

    private final BasicArrowButton myNorthButton;
    private final BasicArrowButton mySouthButton;
    private final BasicArrowButton myWestButton;
    private final BasicArrowButton myEastButton;


    private final static Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 15);

    private final static Color GOLD_COLOR = new Color(255,204,51).darker();
    private final static Color PURPLE_COLOR = new Color(102,0,153).darker();
    private final static Color LIGHT_PURPLE_COLOR = new Color(230,230,255);

    /**
     * The constructor for the DirectionButtonPanel class.
     * Initializes some other references to GUI parts and the
     * overall controller as well as sets up the
     * direction button panel and adds it to the overall game panel screen.
     * @param theGamePanel - the overall game panel screen
     * @param theMazePanel - the panel that displays the maze visuals
     * @param theTriviaMazeBrain - the controller that connects the GUI with the logic
     */
    DirectionButtonPanel(final JPanel theGamePanel, final MazePanel theMazePanel, final TriviaMazeBrain theTriviaMazeBrain) {
        myMazePanel = theMazePanel;
        myTriviaMazeBrain = theTriviaMazeBrain;
        setLayout(new GridLayout(3,3));

        setBackground(PURPLE_COLOR);

        final TitledBorder border = new TitledBorder("Choose Door");
        border.setTitleFont(TITLE_FONT);
        border.setTitleColor(GOLD_COLOR);
        setBorder(border);
        myNorthButton = new BasicArrowButton(BasicArrowButton.NORTH);
        mySouthButton = new BasicArrowButton(BasicArrowButton.SOUTH);
        myEastButton = new BasicArrowButton(BasicArrowButton.EAST);
        myWestButton = new BasicArrowButton(BasicArrowButton.WEST);

        myNorthButton.setBackground(LIGHT_PURPLE_COLOR);
        mySouthButton.setBackground(LIGHT_PURPLE_COLOR);
        myEastButton.setBackground(LIGHT_PURPLE_COLOR);
        myWestButton.setBackground(LIGHT_PURPLE_COLOR);

        final JLabel centerLabel = new JLabel("<html>Current<br/>Room</html>", SwingConstants.CENTER);
        centerLabel.setFont(TITLE_FONT);
        centerLabel.setForeground(GOLD_COLOR);

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

    /**
     * Adds an action listener to the specified arrow button
     * @param theArrowButton - the GUI arrow button
     * @param theArrowDirection - the direction the arrow button is pointing
     */
    private void addArrowActionListener(final BasicArrowButton theArrowButton, final String theArrowDirection) {
        theArrowButton.addActionListener(
                e -> myTriviaMazeBrain.setupQuestion(theArrowDirection)
        );
    }

    /**
     * Updates the visibility of the direction buttons by enabling or
     * disabling them, depending on if it's a valid direction
     */
    public void setDirectionButtonsVisibility() {
        myNorthButton.setEnabled(myMazePanel.validDirection(DIR_NORTH));
        mySouthButton.setEnabled(myMazePanel.validDirection(DIR_SOUTH));
        myWestButton.setEnabled(myMazePanel.validDirection(DIR_WEST));
        myEastButton.setEnabled(myMazePanel.validDirection(DIR_EAST));
    }

    /**
     * Set all the direction buttons to disabled
     */
    void disableAllButtons() {
        myNorthButton.setEnabled(false);
        mySouthButton.setEnabled(false);
        myWestButton.setEnabled(false);
        myEastButton.setEnabled(false);
    }
}
