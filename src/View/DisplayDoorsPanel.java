package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class DisplayDoorsPanel extends JPanel{

    private JPanel doorsPanel;

    JButton door1;
    JButton door2;
    JButton door3;
    JButton door4;

    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";

    private ImageIcon iconCurrentRoom = new ImageIcon("src//View//Images//CurrentRoom.png");

    final static Font BOLD_FONT = new Font("Serif", Font.BOLD, 30);

    DisplayDoorsPanel(JPanel panel) {
        doorsPanel = new JPanel();
        doorsPanel.setLayout(new BoxLayout(doorsPanel, BoxLayout.X_AXIS));

        // for debugging
        doorsPanel.setBorder(BorderFactory.createTitledBorder("Doors"));

        addButtons();

        // HOW TO CENTER BUTTONS HORIZONTALLY!??

        //doorsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //doorsPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        panel.add(doorsPanel);

        doorsPanel.setVisible(true);
    }

    private void addButtons() {
        door1 = new JButton(ONE, iconCurrentRoom);
        door2 = new JButton(TWO, iconCurrentRoom);
        door3 = new JButton(THREE, iconCurrentRoom);
        door4 = new JButton(FOUR, iconCurrentRoom);

        door1.setFont(BOLD_FONT);
        door2.setFont(BOLD_FONT);
        door3.setFont(BOLD_FONT);
        door4.setFont(BOLD_FONT);

        door1.setBorder(BorderFactory.createEtchedBorder());
        door2.setBorder(BorderFactory.createEtchedBorder());
        door3.setBorder(BorderFactory.createEtchedBorder());
        door4.setBorder(BorderFactory.createEtchedBorder());

        door1.setContentAreaFilled(false);
        door2.setContentAreaFilled(false);
        door3.setContentAreaFilled(false);
        door4.setContentAreaFilled(false);

        addBtnActionListener(door1);
        addBtnActionListener(door2);

        doorsPanel.add(door1);
        doorsPanel.add(Box.createRigidArea(new Dimension(5,0)));
        doorsPanel.add(door2);
        doorsPanel.add(Box.createRigidArea(new Dimension(5,0)));
        doorsPanel.add(door3);
        doorsPanel.add(Box.createRigidArea(new Dimension(5,0)));
        doorsPanel.add(door4);
    }

    private void disableButtons() {

    }

    private void addBtnActionListener(JButton arrowButton) {
        arrowButton.addActionListener(
                e -> {
                    System.out.println("cool");
                }
        );
    }
}
