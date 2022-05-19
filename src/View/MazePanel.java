package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MazePanel {

    private static final ImageIcon iconEmptyRoom = new ImageIcon("src//View//Images//EmptyRoom.png");
    private static final ImageIcon iconCurrentRoom = new ImageIcon("src//View//Images//CurrentRoom.png");
    private static final ImageIcon iconStartRoom = new ImageIcon("src//View//Images//StartRoom.png");
    private static final ImageIcon iconEndRoom = new ImageIcon("src//View//Images//ExitRoom.png");
    private static final ImageIcon iconLockedRoom = new ImageIcon("src//View//Images//LockedRoom.png");

    private final int ROW = 4;
    private final int COL = 4;

    // create getter methods?
    private int currentRow;
    private int currentCol;

    private JLabel[][] roomImages;
    private JLabel startRoom;
    private JLabel endRoom;
    private JLabel initializeRoom;

    private TriviaMazeBrain triviaMazeBrain;

    public MazePanel(JPanel panel, TriviaMazeBrain triviaMazeBrain) {
        this.triviaMazeBrain = triviaMazeBrain;

        JPanel mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(ROW,COL));
        mazePanel.setBorder(BorderFactory.createTitledBorder("Maze"));

        initializeMaze();

        // add each JLabel stored in roomImages to mazePanel
        // make own method?
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                mazePanel.add(roomImages[i][j]);
            }
        }

        panel.add(mazePanel);

        mazePanel.setVisible(true);
    }

    private void initializeMaze() {
        roomImages = new JLabel[ROW][COL];

        // initialize all rooms to empty rooms
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                initializeRoom = new JLabel();
                initializeRoom.setIcon(iconEmptyRoom);
                roomImages[i][j] = initializeRoom;
            }
        }

        // initialize start room to show current position
        roomImages[0][0] = setStartRoom();

        // initialize end room to show target end room
        roomImages[3][3] = setEndRoom();

        // set current position in maze
        currentRow = 0;
        currentCol = 0;
    }

    public void updateCharacterPlacement(int row, int col) {
        setOldRoomIcon(currentRow, currentCol);
        setCurrentRoomIcon(row, col);
        currentRow = row;
        currentCol = col;
    }

    public boolean validDirection(String direction) {
        return triviaMazeBrain.checkIsLockedStatus(direction);
    }

    private void setLockedRoomIcon(int row, int col) {
        roomImages[row][col].setIcon(iconLockedRoom);
    }

    private void setCurrentRoomIcon(int row, int col) {
        roomImages[row][col].setIcon(iconCurrentRoom);
    }

    private void setOldRoomIcon(int row, int col) {
        if (row == 0 && col == 0) {
            roomImages[row][col].setIcon(iconStartRoom);
        }
        else if (row == 3 && col == 3) {
            roomImages[row][col].setIcon(iconEndRoom);
        }
        else {
            roomImages[row][col].setIcon(iconEmptyRoom);
        }
    }

    private JLabel setStartRoom() {
        startRoom = new JLabel();
        startRoom.setIcon(iconCurrentRoom);
        return startRoom;
    }

    private JLabel setEndRoom() {
        endRoom = new JLabel();
        endRoom.setIcon(iconEndRoom);
        return endRoom;
    }
}
