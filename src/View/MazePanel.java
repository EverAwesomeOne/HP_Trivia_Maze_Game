package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {

    private static final ImageIcon ICON_EMPTY_ROOM = new ImageIcon("src//View//Images//EmptyRoom.png");
    private static final ImageIcon ICON_CURRENT_ROOM = new ImageIcon("src//View//Images//CurrentRoom.png");
    private static final ImageIcon ICON_START_ROOM = new ImageIcon("src//View//Images//StartRoom.png");
    private static final ImageIcon ICON_END_ROOM = new ImageIcon("src//View//Images//ExitRoom.png");
    private static final ImageIcon ICON_LOCKED_ROOM = new ImageIcon("src//View//Images//LockedRoom.png");

    private static final ImageIcon ICON_UNLOCKED_VERTICAL_DOOR = new ImageIcon("src//View//Images//UnlockedDoorVertical.png");
    private static final ImageIcon ICON_UNLOCKED_HORIZONTAL_DOOR = new ImageIcon("src//View//Images//UnlockedDoorHorizontal.png");
    private static final ImageIcon ICON_LOCKED_VERTICAL_DOOR = new ImageIcon("src//View//Images//LockedDoorVertical.png");
    private static final ImageIcon ICON_LOCKED_HORIZONTAL_DOOR = new ImageIcon("src//View//Images//LockedDoorHorizontal.png");

    private static final int ROW = TriviaMazeBrain.MAZE_LENGTH;
    private final int COL = ROW;

    // should we have a constant for the starting row and column of the character or YAGNI?

    private int myCurrentRow;
    private int myCurrentCol;

    private JLabel[][] myRoomImages;

    private final TriviaMazeBrain myTriviaMazeBrain;

    public MazePanel(final JPanel theGamePanel, final TriviaMazeBrain theTriviaMazeBrain) {
        myTriviaMazeBrain = theTriviaMazeBrain;
        
        setLayout(new GridLayout(ROW, COL));
        setBorder(BorderFactory.createTitledBorder("Maze"));

        initializeMaze();
        addImagesToMazePanel(this);
        theGamePanel.add(this);
        setVisible(true);
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

        for (int i = 0; i < ROW; i++) {
            createMazeRow(i);
        }

        for (int i = 1; i < ROW; i += 2) {
            setNonValidRooms(i);
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

    public void setDoorIcon(final int theRow, final int theCol, final String theDirectionType, final boolean correctAnswer) {
        int rowOffset = 0;
        int columnOffset = 0;
        if ("NORTH".equals(theDirectionType)) {
            rowOffset = -1;
        } else if ("SOUTH".equals(theDirectionType)) {
            rowOffset = 1;
        } else if ("EAST".equals(theDirectionType)) {
            columnOffset = 1;
        } else {
            columnOffset = -1;
        }

        if (rowOffset != 0) {
            if (correctAnswer) {
            myRoomImages[theRow + rowOffset][theCol + columnOffset].setIcon(ICON_UNLOCKED_VERTICAL_DOOR);
            } else {
                myRoomImages[theRow + rowOffset][theCol + columnOffset].setIcon(ICON_LOCKED_VERTICAL_DOOR);
            }
        } else {
            if (correctAnswer) {
                myRoomImages[theRow + rowOffset][theCol + columnOffset].setIcon(ICON_UNLOCKED_HORIZONTAL_DOOR);
            } else {
                myRoomImages[theRow + rowOffset][theCol + columnOffset].setIcon(ICON_LOCKED_HORIZONTAL_DOOR);
            }
        }
        myRoomImages[theRow + rowOffset][theCol + columnOffset].setHorizontalAlignment(SwingConstants.CENTER);
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

    private void setNonValidRooms(int theRow) {
        for (int i = 0; i < ROW; i++) {
            if ((i % 2) == 1) {
                myRoomImages[theRow][i] = new JLabel();
            }
        }
    }

    private void createMazeRow(int theRow) {
        if (theRow % 2 == 0) {
            for (int i = 0; i < ROW; i++) {
                if (i % 2 == 0) {
                    myRoomImages[theRow][i] = new JLabel(ICON_EMPTY_ROOM);
                } else {
                    //myRoomImages[theRow][i] = new JLabel(ICON_UNLOCKED_HORIZONTAL_DOOR);
                    myRoomImages[theRow][i] = new JLabel();
                }
            }
        } else {
            for (int i = 0; i < ROW; i++) {
                if (i % 2 == 0) {
                    myRoomImages[theRow][i] = new JLabel();
                    //myRoomImages[theRow][i] = new JLabel(ICON_UNLOCKED_VERTICAL_DOOR);
                } else {
                    myRoomImages[theRow][i] = new JLabel(ICON_EMPTY_ROOM);
                }
            }
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