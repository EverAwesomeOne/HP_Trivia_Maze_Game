package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MazePanel {

    private static final ImageIcon ICON_EMPTY_ROOM = new ImageIcon("src//View//Images//EmptyRoom.png");
    private static final ImageIcon ICON_CURRENT_ROOM = new ImageIcon("src//View//Images//CurrentRoom.png");
    private static final ImageIcon ICON_START_ROOM = new ImageIcon("src//View//Images//StartRoom.png");
    private static final ImageIcon ICON_END_ROOM = new ImageIcon("src//View//Images//ExitRoom.png");
    private static final ImageIcon ICON_LOCKED_ROOM = new ImageIcon("src//View//Images//LockedRoom.png");

    private static final int ROW = TriviaMazeBrain.MAZE_LENGTH;
    private final int COL = ROW;
    // should we have a constant for the starting row and column of the character?

    private int myCurrentRow;
    private int myCurrentCol;

    private JLabel[][] myRoomImages;

    private final TriviaMazeBrain myTriviaMazeBrain;

    public MazePanel(final JPanel theGamePanel, final TriviaMazeBrain theTriviaMazeBrain) {
        myTriviaMazeBrain = theTriviaMazeBrain;

        final JPanel mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(ROW, COL));
        mazePanel.setBorder(BorderFactory.createTitledBorder("Maze"));

        initializeMaze();
        addImagesToMazePanel(mazePanel);

        theGamePanel.add(mazePanel);

        mazePanel.setVisible(true);
    }

    private void addImagesToMazePanel(JPanel theMazePanel) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                theMazePanel.add(myRoomImages[i][j]);
            }
        }
    }

    private void initializeMaze() {
        myRoomImages = new JLabel[ROW][COL];

        // initialize all rooms to empty rooms
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                final JLabel initializeRoom = new JLabel();
                initializeRoom.setIcon(ICON_EMPTY_ROOM);
                myRoomImages[i][j] = initializeRoom;
            }
        }

        // initialize start room to show current position
        myRoomImages[0][0] = setStartRoom();

        // initialize end room to show target end room
        myRoomImages[ROW - 1][COL - 1] = setEndRoom();

        // set current position in maze
        myCurrentRow = 0;
        myCurrentCol = 0;
    }

    public void updateCharacterPlacement(final int theRow, final int theCol) {
        setOldRoomIcon(myCurrentRow, myCurrentCol);
        setCurrentRoomIcon(theRow, theCol);
        myCurrentRow = theRow;
        myCurrentCol = theCol;
    }

    public boolean validDirection(final String theDirection) {
        return myTriviaMazeBrain.checkIsLockedStatus(theDirection);
    }

    private void setLockedRoomIcon(final int theRow, final int theCol) {
        myRoomImages[theRow][theCol].setIcon(ICON_LOCKED_ROOM);
    }

    private void setCurrentRoomIcon(final int theRow, final int theCol) {
        myRoomImages[theRow][theCol].setIcon(ICON_CURRENT_ROOM);
    }

    private void setOldRoomIcon(final int theRow, final int theCol) {
        if (theRow == 0 && theCol == 0) {
            myRoomImages[theRow][theCol].setIcon(ICON_START_ROOM);
        }
        else if (theRow == ROW - 1 && theCol == COL - 1) {
            myRoomImages[theRow][theCol].setIcon(ICON_END_ROOM);
        }
        else {
            myRoomImages[theRow][theCol].setIcon(ICON_EMPTY_ROOM);
        }
    }

    private JLabel setStartRoom() {
        final JLabel startRoom = new JLabel();
        startRoom.setIcon(ICON_CURRENT_ROOM);
        return startRoom;
    }

    private JLabel setEndRoom() {
        final JLabel endRoom = new JLabel();
        endRoom.setIcon(ICON_END_ROOM);
        return endRoom;
    }
}
