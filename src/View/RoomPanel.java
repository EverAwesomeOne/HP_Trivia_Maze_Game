package View;

import javax.swing.*;
import java.awt.*;

public class RoomPanel extends JPanel {

    private JPanel roomPanel;

    private final ImageIcon iconUnlockedDoorHoriz = new ImageIcon("src//View//Images//UnlockedDoorHorizontal.png");
    private final ImageIcon iconUnlockedDoorVert = new ImageIcon("src//View//Images//UnlockedDoorVertical.png");
    private final ImageIcon iconLockedDoorHoriz = new ImageIcon("src//View//Images//LockedDoorHorizontal.png");
    private final ImageIcon iconLockedDoorVert = new ImageIcon("src//View//Images//LockedDoorVertical.png");


    RoomPanel(JPanel panel) {
        roomPanel = new JPanel();

        // for debugging
        roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        panel.add(roomPanel);

        roomPanel.setVisible(true);
    }
}
