package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class GamePanel extends JPanel {

    private JFrame mainFrame;

    MazePanel MP;

    private int ROW = 4;
    private int COL = 4;

    private JPanel[][] maze;

    private JPanel gamePanel;
    private JPanel mazePanel;
    private JMenuBar mainMenuBar;

    GamePanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(2,2));

        maze = new JPanel[ROW][COL];
        MP = new MazePanel(gamePanel);

        gamePanel.add(MP);
        gamePanel.add(new JLabel("Trivia Question Display"));
        gamePanel.add(new JLabel("Current Room Display"));
        gamePanel.add(directionButtonPanel());

        mainMenuBar = new JMenuBar();
        setupMenuBar("Game Info");
        setupMenuBar("About Hodgepodge Team");
        setupMenuBar("Exit Game");

        mainFrame.add(gamePanel);
        mainFrame.setJMenuBar(mainMenuBar);

        gamePanel.setVisible(true);
    }

    // make own class?
    private JPanel mazePanel() {
        //mazePanel = new MazePanel(gamePanel);

        //mazePanel.setVisible(true);

        return mazePanel;
    }

    private JPanel directionButtonPanel() {
        JPanel directionButtonPanel = new JPanel();
        directionButtonPanel.setLayout(new GridLayout(3,3));

        BasicArrowButton northButton = new BasicArrowButton(BasicArrowButton.NORTH);
        BasicArrowButton southButton = new BasicArrowButton(BasicArrowButton.SOUTH);
        BasicArrowButton eastButton = new BasicArrowButton(BasicArrowButton.EAST);
        BasicArrowButton westButton = new BasicArrowButton(BasicArrowButton.WEST);
        //JLabel buttonPanelCenterLabel = new JLabel(scale(mazeCharacterIcon));

        addArrowActionListener(northButton, "North");
        addArrowActionListener(southButton, "South");
        addArrowActionListener(westButton, "West");
        addArrowActionListener(eastButton, "East");

        directionButtonPanel.add(new JLabel(""));
        directionButtonPanel.add(northButton);
        directionButtonPanel.add(new JLabel(""));
        directionButtonPanel.add(westButton);
        //directionButtonPanel.add(buttonPanelCenterLabel);
        directionButtonPanel.add(new JLabel(""));
        directionButtonPanel.add(eastButton);
        directionButtonPanel.add(new JLabel(""));
        directionButtonPanel.add(southButton);
        directionButtonPanel.add(new JLabel(""));

        directionButtonPanel.setVisible(true);

        return directionButtonPanel;
    }

    private void addArrowActionListener(BasicArrowButton arrowButton, String arrowDirection) {
        if (arrowDirection.equals("North")) {
            arrowButton.addActionListener(
                    e -> {
                        MP.move("North");
                    }
            );
        }
        else if (arrowDirection.equals("South")) {
            arrowButton.addActionListener(
                    e -> {
                        MP.move("South");
                    }
            );
        }
        else if (arrowDirection.equals("West")) {
            arrowButton.addActionListener(
                    e -> {
                        MP.move("West");
                    }
            );
        }
        else if (arrowDirection.equals("East")) {
            arrowButton.addActionListener(
                    e -> {
                        MP.move("East");
                    }
            );
        }
    }

    private JMenuBar setupMenuBar(String menuTitle) {
        final JMenu addMenu = new JMenu(menuTitle);
        final JMenuItem menuItem = new JMenuItem(menuTitle);

        addMenuActionListener(menuItem, menuTitle);

        addMenu.add(menuItem);

        mainMenuBar.add(addMenu);

        mainMenuBar.setVisible(true);

        return mainMenuBar;
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
                        new MainMenuPanel(mainFrame);
                    }
            );
        }
    }
}
