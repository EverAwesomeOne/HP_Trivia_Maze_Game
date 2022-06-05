package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * The MazePanel class represents the panel that displays the maze visuals.
 */
public class MazePanel extends JPanel{

    private static final ImageIcon EMPTY_ROOM_ICON =
            new ImageIcon("src//View//Images//EmptyRoom.png");
    private static final ImageIcon CURRENT_ROOM_ICON =
            new ImageIcon("src//View//Images//CurrentRoom.png");
    private static final ImageIcon START_ROOM_ICON =
            new ImageIcon("src//View//Images//StartRoom.png");
    private static final ImageIcon END_ROOM_ICON =
            new ImageIcon("src//View//Images//ExitRoom.png");

    private static final ImageIcon UNLOCKED_VERTICAL_DOOR_ICON =
            new ImageIcon("src//View//Images//UnlockedDoorVertical.png");
    private static final ImageIcon UNLOCKED_HORIZONTAL_DOOR_ICON =
            new ImageIcon("src//View//Images//UnlockedDoorHorizontal.png");
    private static final ImageIcon LOCKED_VERTICAL_DOOR_ICON =
            new ImageIcon("src//View//Images//LockedDoorVertical.png");
    private static final ImageIcon LOCKED_HORIZONTAL_DOOR_ICON =
            new ImageIcon("src//View//Images//LockedDoorHorizontal.png");

    private static final int ROW = TriviaMazeBrain.MAZE_LENGTH;
    private final int COL = ROW;

    private int myCurrentRow;
    private int myCurrentCol;

    private JLabel[][] myRoomImages;

    private final TriviaMazeBrain myTriviaMazeBrain;

    private final static Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 15);

    private final static Color GOLD_COLOR = new Color(255,204,51).darker();
    private final static Color PURPLE_COLOR = new Color(102,0,153).darker();

    /**
     * The constructor for the MazePanel class.
     * Initializes some other references to GUI parts and the
     * overall controller as well as sets up the
     * maze panel and adds it to the overall game panel screen.
     * @param theGamePanel - the overall game panel screen
     * @param theTriviaMazeBrain - the controller that connects the GUI with the logic
     */
    public MazePanel(final JPanel theGamePanel, final TriviaMazeBrain theTriviaMazeBrain) {
        myTriviaMazeBrain = theTriviaMazeBrain;
        
        setLayout(new GridLayout(ROW, COL));

        setBackground(PURPLE_COLOR);

        final TitledBorder border = new TitledBorder("Maze");
        border.setTitleFont(TITLE_FONT);
        border.setTitleColor(GOLD_COLOR);
        setBorder(border);

        initializeMaze();
        addImagesToMazePanel(this);
        theGamePanel.add(this);
        setVisible(true);
    }

    /**
     * Adds the maze visuals to the maze panel.
     * @param theMazePanel - the panel that displays the maze visuals
     */
    private void addImagesToMazePanel(final JPanel theMazePanel) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                theMazePanel.add(myRoomImages[i][j]);
            }
        }
    }

    /**
     * Sets up the initial look of the maze visuals when the player.
     * first starts a new game.
     */
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

    /**
     * Updates the maze visuals of where the player icon is.
     * @param theRow - the row the player is located
     * @param theCol - the column the player is located
     */
    public void updateCharacterPlacement(final int theRow, final int theCol) {
        setOldRoomIcon(myCurrentRow, myCurrentCol);
        setCurrentRoomIcon(theRow, theCol);
        myCurrentRow = theRow;
        myCurrentCol = theCol;
    }

    /**
     * Checks if the specified direction is a valid direction
     * (i.e. if the door is already locked because the player got it wrong
     * or a door the edge of the maze).
     * @param theDirection - the direction in which the door is located
     * @return - if the door is locked or not
     */
    public boolean validDirection(final String theDirection) {
        return myTriviaMazeBrain.checkCurrentRoomIsLockedStatus(theDirection);
    }

    /**
     * Updates the icons for a specific door depending on the specific room the
     * player is located in, the direction the player wants to go, and
     * if the player got the question right or not.
     * @param theRow - the row the player is located
     * @param theCol - the column the player is located
     * @param theDirectionType - the direction in which the door is located
     * @param correctAnswer - if the player got the answer correct or not
     */
    public void setDoorIcon(final int theRow, final int theCol,
                            final String theDirectionType, final boolean correctAnswer) {
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
            myRoomImages[theRow + rowOffset][theCol + columnOffset]
                    .setIcon(UNLOCKED_VERTICAL_DOOR_ICON);
            } else {
                myRoomImages[theRow + rowOffset][theCol + columnOffset]
                        .setIcon(LOCKED_VERTICAL_DOOR_ICON);
            }
        } else {
            if (correctAnswer) {
                myRoomImages[theRow + rowOffset][theCol + columnOffset]
                        .setIcon(UNLOCKED_HORIZONTAL_DOOR_ICON);
            } else {
                myRoomImages[theRow + rowOffset][theCol + columnOffset]
                        .setIcon(LOCKED_HORIZONTAL_DOOR_ICON);
            }
        }
        myRoomImages[theRow + rowOffset][theCol + columnOffset]
                .setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Manual deserialization for the maze visuals.
     * Updates the maze visuals depending on the loaded game data
     * from the players save.
     */
    public void loadDoorIcons() {

        for (int i = 0; i < 7; i+=2) {
            for (int j = 0; j < 7; j+=2) {
                int rowOffset = 0;
                int columnOffset = 0;
                //North
                if (myTriviaMazeBrain
                        .checkSpecificRoomIsNotLocked("NORTH", i, j) && i!=0) {
                    rowOffset = -1;
                    myRoomImages[i + rowOffset][j].setIcon(LOCKED_VERTICAL_DOOR_ICON);
                } else if (myTriviaMazeBrain.checkDoorIsNotFirstTime("NORTH", i, j) && i!=0) {
                    rowOffset = -1;
                    myRoomImages[i + rowOffset][j].setIcon(UNLOCKED_VERTICAL_DOOR_ICON);
                }
                myRoomImages[i + rowOffset][j].setHorizontalAlignment(SwingConstants.CENTER);

                //East
                if (myTriviaMazeBrain.checkSpecificRoomIsNotLocked("EAST", i, j) && j!=6) {
                    columnOffset = 1;
                    myRoomImages[i][j + columnOffset].setIcon(LOCKED_HORIZONTAL_DOOR_ICON);
                } else if (myTriviaMazeBrain.checkDoorIsNotFirstTime("EAST", i, j) && j!=6) {
                    columnOffset = 1;
                    myRoomImages[i][j + columnOffset].setIcon(UNLOCKED_HORIZONTAL_DOOR_ICON);
                }
                myRoomImages[i][j + columnOffset].setHorizontalAlignment(SwingConstants.CENTER);

                //South
                if (myTriviaMazeBrain.checkSpecificRoomIsNotLocked("SOUTH", i, j) && i!=6) {
                    rowOffset = 1;
                    myRoomImages[i + rowOffset][j].setIcon(LOCKED_VERTICAL_DOOR_ICON);
                } else if (myTriviaMazeBrain.checkDoorIsNotFirstTime("SOUTH", i, j) && i!=6) {
                    rowOffset = 1;
                    myRoomImages[i + rowOffset][j].setIcon(UNLOCKED_VERTICAL_DOOR_ICON);
                }
                myRoomImages[i + rowOffset][j].setHorizontalAlignment(SwingConstants.CENTER);

                //West
                if (myTriviaMazeBrain.checkSpecificRoomIsNotLocked("WEST", i, j) && j!=0) {
                    columnOffset = -1;
                    myRoomImages[i][j + columnOffset].setIcon(LOCKED_HORIZONTAL_DOOR_ICON);
                } else if (myTriviaMazeBrain.checkDoorIsNotFirstTime("WEST", i, j) && j!=0) {
                    columnOffset = -1;
                    myRoomImages[i][j + columnOffset].setIcon(UNLOCKED_HORIZONTAL_DOOR_ICON);
                }
                myRoomImages[i][j + columnOffset].setHorizontalAlignment(SwingConstants.CENTER);

            }
        }
    }

    /**
     * Sets the player icon in the specified coordinates for the room.
     * @param theRow - the row the player is located
     * @param theCol - the column the player is located
     */
    private void setCurrentRoomIcon(final int theRow, final int theCol) {
        myRoomImages[theRow][theCol].setIcon(CURRENT_ROOM_ICON);
    }

    private void setOldRoomIcon(final int theRow, final int theCol) {
        if (theRow == 0 && theCol == 0) {
            myRoomImages[theRow][theCol].setIcon(START_ROOM_ICON);
        }
        else if (theRow == ROW - 1 && theCol == COL - 1) {
            myRoomImages[theRow][theCol].setIcon(END_ROOM_ICON);
        }
        else {
            myRoomImages[theRow][theCol].setIcon(EMPTY_ROOM_ICON);
        }
    }

    /**
     * Sets up the places in the overall maze where a room is not located
     * @param theRow - the row the player is located
     */
    private void setNonValidRooms(final int theRow) {
        for (int i = 0; i < ROW; i++) {
            if ((i % 2) == 1) {
                myRoomImages[theRow][i] = new JLabel();
            }
        }
    }

    /**
     * Sets up the maze visuals of a specific row, switching between an
     * empty room icon to show a room and no visuals to show the doors.
     * @param theRow - the row the player is located
     */
    private void createMazeRow(final int theRow) {
        if (theRow % 2 == 0) {
            for (int i = 0; i < ROW; i++) {
                if (i % 2 == 0) {
                    myRoomImages[theRow][i] = new JLabel(EMPTY_ROOM_ICON);
                } else {
                    myRoomImages[theRow][i] = new JLabel();
                }
            }
        } else {
            for (int i = 0; i < ROW; i++) {
                if (i % 2 == 0) {
                    myRoomImages[theRow][i] = new JLabel();
                } else {
                    myRoomImages[theRow][i] = new JLabel(EMPTY_ROOM_ICON);
                }
            }
        }
    }

    /**
     * Sets up the starting room for the player.
     * @return - the starting room
     */
    private JLabel setStartRoom() {
        final JLabel startRoom = new JLabel();
        startRoom.setIcon(CURRENT_ROOM_ICON);
        startRoom.setHorizontalAlignment(SwingConstants.CENTER);
        return startRoom;
    }

    /**
     * Sets up the ending room for the player.
     * @return - the ending room
     */
    private JLabel setEndRoom() {
        final JLabel endRoom = new JLabel();
        endRoom.setIcon(END_ROOM_ICON);
        endRoom.setHorizontalAlignment(SwingConstants.CENTER);
        return endRoom;
    }
}