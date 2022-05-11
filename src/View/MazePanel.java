package View;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {

    private JPanel mazePanel;

    private ImageIcon iconEmptyRoom = new ImageIcon("src//View//Images//EmptyRoom.png");
    private ImageIcon iconCurrentRoom = new ImageIcon("src//View//Images//CurrentRoom.png");
    private ImageIcon iconStartRoom = new ImageIcon("src//View//Images//StartRoom.png");
    private ImageIcon iconEndRoom = new ImageIcon("src//View//Images//ExitRoom.png");
    private ImageIcon iconLockedRoom = new ImageIcon("src//View//Images//LockedRoom.png");

    private int ROW = 4;
    private int COL = 4;

    private int currentRow;
    private int currentCol;

    private JLabel[][] roomImages;
    private JLabel startRoom;
    private JLabel endRoom;
    private JLabel currentRoom;
    private JLabel otherRoom;

    MazePanel(JPanel panel) {
        mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(ROW,COL));

        initializeMaze();

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

        startRoom = new JLabel();
        endRoom = new JLabel();

        startRoom.setIcon(iconCurrentRoom);
        endRoom.setIcon(iconEndRoom);

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                //if ((ROW != 0 && COL != 0) && (ROW != 3 && COL != 3)) {
                    otherRoom = new JLabel();
                    otherRoom.setIcon(iconEmptyRoom);
                    roomImages[i][j] = otherRoom;
                //}
            }
        }

        roomImages[0][0] = startRoom;
        roomImages[3][3] = endRoom;

        currentRow = 0;
        currentCol = 0;
    }

    public void move(String direction) {
        int newRow = 0;
        int newCol = 0;

        if (direction.equals("North")) {
            newRow = currentRow - 1;
            //currentRow--;
            setCurrentRoom(newRow, currentCol);
            setOldRoom(currentRow, currentCol);
            currentRow = newRow;
        }

        else if (direction.equals("South")) {
            newRow = currentRow + 1;
            //currentRow++;
            setCurrentRoom(newRow, currentCol);
            setOldRoom(currentRow, currentCol);
            currentRow = newRow;
        }

        else if (direction.equals("East")) {
            newCol = currentCol + 1;
            //currentCol++;
            setCurrentRoom(currentRow, newCol);
            setOldRoom(currentRow, currentCol);
            currentCol = newCol;
        }

        else if (direction.equals("West")) {
            newCol = currentCol - 1;
            //currentCol--;
            setCurrentRoom(currentRow, newCol);
            setOldRoom(currentRow, currentCol);
            currentCol = newCol;
        }
    }

    private void setCurrentRoom(int row, int col) {
        roomImages[row][col].setIcon(iconCurrentRoom);
    }

    private void setOldRoom(int row, int col) {
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

    private void setStartRoom() {

    }

    private void setEndRoom() {

    }

    private void setRoomIcon() {

    }

    private void setLockedRoom(int row, int col) {
        roomImages[row][col].setIcon(iconLockedRoom);
    }
}
