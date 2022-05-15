package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class DirectionButtonPanel extends JPanel {

    private static JPanel directionButtonPanel;

    private final MazePanel MP;

    private static final String DIR_NORTH = "North";
    private static final String DIR_SOUTH = "South";
    private static final String DIR_WEST = "West";
    private static final String DIR_EAST = "East";

    BasicArrowButton northButton;
    BasicArrowButton southButton;
    BasicArrowButton eastButton;
    BasicArrowButton westButton;

    DirectionButtonPanel(JPanel gamePanel, MazePanel MP) {
        this.MP = MP;

        directionButtonPanel = new JPanel();
        directionButtonPanel.setLayout(new GridLayout(3,3));
        directionButtonPanel.setBorder(BorderFactory.createTitledBorder("Choose Door"));

        northButton = new BasicArrowButton(BasicArrowButton.NORTH);
        southButton = new BasicArrowButton(BasicArrowButton.SOUTH);
        eastButton = new BasicArrowButton(BasicArrowButton.EAST);
        westButton = new BasicArrowButton(BasicArrowButton.WEST);

        // Replace with an icon?
        JLabel centerLabel = new JLabel("<html>Current<br/>Room</html>", SwingConstants.CENTER);

        // initialize BasicArrowButtons based on the start position in maze
        disableButtons();

        addArrowActionListener(northButton, DIR_NORTH);
        addArrowActionListener(southButton, DIR_SOUTH);
        addArrowActionListener(westButton, DIR_WEST);
        addArrowActionListener(eastButton, DIR_EAST);

        // empty JLabels act as placeholders in GridLayout
        // to distance BasicArrowButtons
        directionButtonPanel.add(new JLabel(""));
        directionButtonPanel.add(northButton);
        directionButtonPanel.add(new JLabel(""));
        directionButtonPanel.add(westButton);
        directionButtonPanel.add(centerLabel);
        directionButtonPanel.add(eastButton);
        directionButtonPanel.add(new JLabel(""));
        directionButtonPanel.add(southButton);
        directionButtonPanel.add(new JLabel(""));

        gamePanel.add(directionButtonPanel);

        directionButtonPanel.setVisible(true);
    }

    private void addArrowActionListener(BasicArrowButton arrowButton, String arrowDirection) {
        if (arrowDirection.equals(DIR_NORTH)) {
            arrowButton.addActionListener(
                    e -> {

                        // send direction to controller,
                        // receive QA.
                        // hide DisplayDoorsPanel so user can't click,
                        // display QAPanel, user return A.
                        // send A to controller,
                        // receive boolean if correct A.
                        // update buttons and room icon
                        // false -> disable BasicArrowButton, NorthDoor in current room, set attempted room icon to locked
                        // true -> move in that direction
                        // show DisplayDoorsPanel for next move

                        //

                        MP.move(DIR_NORTH);
                        disableButtons();
                    }
            );
        }
        else if (arrowDirection.equals(DIR_SOUTH)) {
            arrowButton.addActionListener(
                    e -> {
                        MP.move(DIR_SOUTH);
                        disableButtons();
                    }
            );
        }
        else if (arrowDirection.equals(DIR_WEST)) {
            arrowButton.addActionListener(
                    e -> {
                        MP.move(DIR_WEST);
                        disableButtons();
                    }
            );
        }
        else if (arrowDirection.equals(DIR_EAST)) {
            arrowButton.addActionListener(
                    e -> {
                        MP.move(DIR_EAST);
                        disableButtons();
                    }
            );
        }
    }

    private void disableButtons() {
        northButton.setEnabled(MP.validDirection(DIR_NORTH));
        southButton.setEnabled(MP.validDirection(DIR_SOUTH));
        westButton.setEnabled(MP.validDirection(DIR_WEST));
        eastButton.setEnabled(MP.validDirection(DIR_EAST));
    }
}
