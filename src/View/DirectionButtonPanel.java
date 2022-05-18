package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class DirectionButtonPanel extends JPanel {

    private static JPanel directionButtonPanel;

    private final MazePanel MP;
    private final TriviaMazeBrain triviaMazeBrain;

    private static final String DIR_NORTH = "NORTH";
    private static final String DIR_SOUTH = "SOUTH";
    private static final String DIR_WEST = "WEST";
    private static final String DIR_EAST = "EAST";

    BasicArrowButton northButton;
    BasicArrowButton southButton;
    BasicArrowButton eastButton;
    BasicArrowButton westButton;

    DirectionButtonPanel(JPanel gamePanel, MazePanel MP, TriviaMazeBrain triviaMazeBrain) {
        this.MP = MP;
        this.triviaMazeBrain = triviaMazeBrain;
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
        arrowButton.addActionListener(
                e -> {
                    triviaMazeBrain.move(arrowDirection);
                }
        );
    }

    void disableButtons() {
        northButton.setEnabled(MP.validDirection(DIR_NORTH));
        southButton.setEnabled(MP.validDirection(DIR_SOUTH));
        westButton.setEnabled(MP.validDirection(DIR_WEST));
        eastButton.setEnabled(MP.validDirection(DIR_EAST));
    }

    void disableAllButtons() {
        northButton.setEnabled(false);
        southButton.setEnabled(false);
        westButton.setEnabled(false);
        eastButton.setEnabled(false);
    }
}
