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

    private static final ImageIcon ICON_UNLOCKED_VERTICAL_ROOM = new ImageIcon("src//View//Images//UnlockedDoorVertical.png");
    private static final ImageIcon ICON_UNLOCKED_HORIZONTAL_ROOM = new ImageIcon("src//View//Images//UnlockedDoorHorizontal.png");
    private static final ImageIcon ICON_LOCKED_VERTICAL_ROOM = new ImageIcon("src//View//Images//LockedDoorVertical.png");
    private static final ImageIcon ICON_LOCKED_HORIZONTAL_ROOM = new ImageIcon("src//View//Images//LockedDoorHorizontal.png");

    private static final int ROW = TriviaMazeBrain.MAZE_LENGTH;
    //private static final int ROW = (TriviaMazeBrain.MAZE_LENGTH * 2) - 1;
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
        /*myRoomImages = new JLabel[ROW][COL];

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
        myCurrentCol = 0;*/

        myRoomImages = new JLabel[ROW][COL];

        createMazeRowWithHorizontalDoors(0);
        createMazeRowWithVerticalDoors(1);
        setNonValidRooms(1);
        createMazeRowWithHorizontalDoors(2);
        createMazeRowWithVerticalDoors(3);
        setNonValidRooms(3);
        createMazeRowWithHorizontalDoors(4);
        createMazeRowWithVerticalDoors(5);
        setNonValidRooms(5);
        createMazeRowWithHorizontalDoors(6);

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

    private void setNonValidRooms(int theRow) {
        for (int i = 0; i < ROW; i++) {
            if ((i % 2) == 1) {
                myRoomImages[theRow][i] = new JLabel();
            }
        }
    }

    private void createMazeRowWithHorizontalDoors(int theRow) {
        for (int i = 0; i < ROW; i++) {
            if (i % 2 == 0) {
                myRoomImages[theRow][i] = new JLabel(ICON_EMPTY_ROOM);
            } else {
                myRoomImages[theRow][i] = new JLabel(ICON_UNLOCKED_HORIZONTAL_ROOM);
            }
        }
    }

    private void createMazeRowWithVerticalDoors(int theRow) {
        for (int i = 0; i < ROW; i++) {
            if (i % 2 == 0) {
                myRoomImages[theRow][i] = new JLabel(ICON_UNLOCKED_VERTICAL_ROOM);
            } else {
                myRoomImages[theRow][i] = new JLabel(ICON_EMPTY_ROOM);
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

/*
package View;

        import Controller.TriviaMazeBrain;

        import javax.swing.*;
        import java.awt.*;

public class MazePanel {

    private static final ImageIcon ICON_EMPTY_ROOM =
            new ImageIcon("src//View//Images//EmptyRoom.png");
    private static final ImageIcon ICON_CURRENT_ROOM =
            new ImageIcon("src//View//Images//CurrentRoom.png");
    private static final ImageIcon ICON_START_ROOM =
            new ImageIcon("src//View//Images//StartRoom.png");
    private static final ImageIcon ICON_END_ROOM =
            new ImageIcon("src//View//Images//ExitRoom.png");
    private static final ImageIcon ICON_LOCKED_ROOM =
            new ImageIcon("src//View//Images//LockedRoom.png");

    private static final ImageIcon ICON_UNLOCKED_VERTICAL_ROOM =
            new ImageIcon("src//View//Images//UnlockedDoorVertical.png");
    private static final ImageIcon ICON_UNLOCKED_HORIZONTAL_ROOM =
            new ImageIcon("src//View//Images//UnlockedDoorHorizontal.png");
    private static final ImageIcon ICON_LOCKED_VERTICAL_ROOM =
            new ImageIcon("src//View//Images//LockedDoorVertical.png");
    private static final ImageIcon ICON_LOCKED_HORIZONTAL_ROOM =
            new ImageIcon("src//View//Images//LockedDoorHorizontal.png");


    private static final int ROW = (TriviaMazeBrain.MAZE_LENGTH * 2) - 1;
    private final int COL = ROW;
    // should we have a constant for the starting row and column of the character or YAGNI?

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

        createMazeRowWithHorizontalDoors(0);
        createMazeRowWithVerticalDoors(1);
        setNonValidRooms(1);
        createMazeRowWithHorizontalDoors(2);
        createMazeRowWithVerticalDoors(3);
        setNonValidRooms(3);
        createMazeRowWithHorizontalDoors(4);
        createMazeRowWithVerticalDoors(5);
        setNonValidRooms(5);
        createMazeRowWithHorizontalDoors(6);

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
        if (theRow == 0) {
            if (theCol == 0) setCurrentRoomIcon(theRow,0);
            if (theCol == 1) setCurrentRoomIcon(theRow, 2);
            if (theCol == 2) setCurrentRoomIcon(theRow, 4);
            if (theCol == 3) setCurrentRoomIcon(theRow, 6);
        }

        else if (theRow == 1) {
            if (theCol == 0) setCurrentRoomIcon(theRow + 1,0);
            if (theCol == 1) setCurrentRoomIcon(theRow + 1, 2);
            if (theCol == 2) setCurrentRoomIcon(theRow + 1, 4);
            if (theCol == 3) setCurrentRoomIcon(theRow + 1, 6);
        }

        else if (theRow == 2) {
            if (theCol == 0) setCurrentRoomIcon(theRow * 2,0);
            if (theCol == 1) setCurrentRoomIcon(theRow * 2, 2);
            if (theCol == 2) setCurrentRoomIcon(theRow * 2, 4);
            if (theCol == 3) setCurrentRoomIcon(theRow * 2, 6);
        }

        else if (theRow == 3) {
            if (theCol == 0) setCurrentRoomIcon(theRow * 2,0);
            if (theCol == 1) setCurrentRoomIcon(theRow * 2, 2);
            if (theCol == 2) setCurrentRoomIcon(theRow * 2, 4);
            if (theCol == 3) setCurrentRoomIcon(theRow * 2, 6);
        }


        //setCurrentRoomIcon(theRow, theCol);
        myCurrentRow = theRow;
        myCurrentCol = theCol;
    }

    public boolean validDirection(final String theDirection) {
        return myTriviaMazeBrain.checkIsLockedStatus(theDirection);
    }

    private void setLockedVerticalDoor(final int theRow, final int theCol) {
        myRoomImages[theRow][theCol].setIcon(ICON_LOCKED_VERTICAL_ROOM);
    }

    private void setLockedHorizontalDoor(final int theRow, final int theCol) {
        myRoomImages[theRow][theCol].setIcon(ICON_LOCKED_HORIZONTAL_ROOM);
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

    private void createMazeRowWithHorizontalDoors(int theRow) {
        for (int i = 0; i < ROW; i++) {
            if (i % 2 == 0) {
                myRoomImages[theRow][i] = new JLabel(ICON_EMPTY_ROOM);
            } else {
                myRoomImages[theRow][i] = new JLabel(ICON_UNLOCKED_HORIZONTAL_ROOM);
            }
        }
    }

    private void createMazeRowWithVerticalDoors(int theRow) {
        for (int i = 0; i < ROW; i++) {
            if (i % 2 == 0) {
                myRoomImages[theRow][i] = new JLabel(ICON_UNLOCKED_VERTICAL_ROOM);
            } else {
                myRoomImages[theRow][i] = new JLabel(ICON_EMPTY_ROOM);
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
}*/

