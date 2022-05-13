package View;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {

    private JPanel mazePanel;

    private final ImageIcon iconEmptyRoom = new ImageIcon("src//View//Images//EmptyRoom.png");
    private final ImageIcon iconCurrentRoom = new ImageIcon("src//View//Images//CurrentRoom.png");
    private final ImageIcon iconStartRoom = new ImageIcon("src//View//Images//StartRoom.png");
    private final ImageIcon iconEndRoom = new ImageIcon("src//View//Images//ExitRoom.png");
    private final ImageIcon iconLockedRoom = new ImageIcon("src//View//Images//LockedRoom.png");

    private final int ROW = 4;
    private final int COL = 4;

    // create getter methods?
    private int currentRow;
    private int currentCol;

    private JLabel[][] roomImages;
    private JLabel startRoom;
    private JLabel endRoom;
    private JLabel initializeRoom;

    boolean[] lockedDirection = new boolean[4];

    MazePanel(JPanel panel) {
        mazePanel = new JPanel();
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

    public void move(String direction) {
        int newRow = 0;
        int newCol = 0;

        if (direction.equals("North")) {
            newRow = currentRow - 1;
            setCurrentRoomIcon(newRow, currentCol);
            setOldRoomIcon(currentRow, currentCol);
            currentRow = newRow;
        }

        else if (direction.equals("South")) {
            newRow = currentRow + 1;
            setCurrentRoomIcon(newRow, currentCol);
            setOldRoomIcon(currentRow, currentCol);
            currentRow = newRow;
        }

        else if (direction.equals("East")) {
            newCol = currentCol + 1;
            setCurrentRoomIcon(currentRow, newCol);
            setOldRoomIcon(currentRow, currentCol);
            currentCol = newCol;
        }

        else if (direction.equals("West")) {
            newCol = currentCol - 1;
            setCurrentRoomIcon(currentRow, newCol);
            setOldRoomIcon(currentRow, currentCol);
            currentCol = newCol;
        }
    }

    public boolean validDirection(String direction) {
        boolean validDirection = true;

        if (direction.equals("North")) {
            if (currentRow - 1 < 0) validDirection = false;
        }

        else if (direction.equals("South")) {
            if (currentRow + 1 > 3) validDirection = false;
        }

        else if (direction.equals("West")) {
            if (currentCol - 1 < 0) validDirection = false;
        }

        else if (direction.equals("East")) {
            if (currentCol + 1 > 3) validDirection = false;
        }

        return validDirection;
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
