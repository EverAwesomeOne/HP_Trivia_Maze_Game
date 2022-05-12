package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class GamePanel extends JPanel {

    private final JFrame mainFrame;

    MazePanel MP;
    DisplayDoorsPanel DP;

    private final int ROW = 4;
    private final int COL = 4;

    private JPanel[][] maze;

    private final JPanel gamePanel;
    private final JMenuBar mainMenuBar;

    private static final String DIR_NORTH = "North";
    private static final String DIR_SOUTH = "South";
    private static final String DIR_WEST = "West";
    private static final String DIR_EAST = "East";

    BasicArrowButton northButton;
    BasicArrowButton southButton;
    BasicArrowButton eastButton;
    BasicArrowButton westButton;

    GamePanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(2,2));

        maze = new JPanel[ROW][COL];
        MP = new MazePanel(gamePanel);
        DP = new DisplayDoorsPanel(gamePanel);

        JPanel temp = new JPanel();
        JLabel tempL = new JLabel("");
        temp.add(tempL);

        //gamePanel.add(MP);
        //gamePanel.add(new JLabel("Trivia Question Display"));
        //gamePanel.add(DP);
        //gamePanel.add(new JLabel(""));
        gamePanel.add(temp);
        gamePanel.add(directionButtonPanel());

        mainMenuBar = new JMenuBar();
        setupMenuBar("Game Info");
        setupMenuBar("About Hodgepodge Team");
        setupMenuBar("Exit Game");

        mainFrame.add(gamePanel);
        mainFrame.setJMenuBar(mainMenuBar);

        gamePanel.setVisible(true);
    }

    private JPanel directionButtonPanel() {
        JPanel directionButtonPanel = new JPanel();
        directionButtonPanel.setLayout(new GridLayout(3,3));

        // for debugging
        directionButtonPanel.setBorder(BorderFactory.createTitledBorder("Current Room"));

        northButton = new BasicArrowButton(BasicArrowButton.NORTH);
        southButton = new BasicArrowButton(BasicArrowButton.SOUTH);
        eastButton = new BasicArrowButton(BasicArrowButton.EAST);
        westButton = new BasicArrowButton(BasicArrowButton.WEST);

        JLabel centerLabel = new JLabel("<html>Current<br/>Room</html>", SwingConstants.CENTER);

        disableButtons();

        addArrowActionListener(northButton, DIR_NORTH);
        addArrowActionListener(southButton, DIR_SOUTH);
        addArrowActionListener(westButton, DIR_WEST);
        addArrowActionListener(eastButton, DIR_EAST);

        directionButtonPanel.add(new JLabel(""));
        directionButtonPanel.add(northButton);
        directionButtonPanel.add(new JLabel(""));
        directionButtonPanel.add(westButton);
        directionButtonPanel.add(centerLabel);
        directionButtonPanel.add(eastButton);
        directionButtonPanel.add(new JLabel(""));
        directionButtonPanel.add(southButton);
        directionButtonPanel.add(new JLabel(""));

        directionButtonPanel.setVisible(true);

        return directionButtonPanel;
    }

    private void addArrowActionListener(BasicArrowButton arrowButton, String arrowDirection) {
        if (arrowDirection.equals(DIR_NORTH)) {
            arrowButton.addActionListener(
                    e -> {
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

    private void setupMenuBar(String menuTitle) {
        final JMenu addMenu = new JMenu(menuTitle);
        final JMenuItem menuItem = new JMenuItem(menuTitle);

        addMenuActionListener(menuItem, menuTitle);

        addMenu.add(menuItem);

        mainMenuBar.add(addMenu);

        mainMenuBar.setVisible(true);
    }

    private void addMenuActionListener(JMenuItem menuItem, String menuName) {
        if (menuName.equals("Game Info")) {
            menuItem.addActionListener(
                    e -> {
                        new GameInfo();
                    }
            );
        }
        else if (menuName.equals("About Hodgepodge Team")) {
            menuItem.addActionListener(
                    e -> {
                        new AboutTeam();
                    }
            );
        }
        else {
            menuItem.addActionListener(
                    e -> {
                        gamePanel.setVisible(false);
                        mainMenuBar.setVisible(false);
                        new MainMenuPanel(mainFrame);
                    }
            );
        }
    }
}
