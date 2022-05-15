package View;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final JFrame mainFrame;

    MazePanel MP;
    DirectionButtonPanel DP;

    private final int ROW = 4;
    private final int COL = 4;

    private final JPanel gamePanel;
    private final JMenuBar mainMenuBar;

    GamePanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(2,2));

        // sets up these panels and adds to gamePanel
        MP = new MazePanel(gamePanel);
        DP = new DirectionButtonPanel(gamePanel, MP);

        //gamePanel.add(directionButtonPanel());
        gamePanel.add(new JLabel("Question"));
        gamePanel.add(new JLabel("Answer"));

        // display menuBar on JFrame only when game is in progress
        mainMenuBar = new JMenuBar();
        setupMenuBar("Game Info");
        setupMenuBar("About Hodgepodge Team");
        setupMenuBar("Save Game");
        setupMenuBar("Exit Game");

        mainFrame.add(gamePanel);
        mainFrame.setJMenuBar(mainMenuBar);

        gamePanel.setVisible(true);
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
                        gamePanel.setVisible(false);
                        mainMenuBar.setVisible(false);
                        new GameInfo(mainFrame, gamePanel, mainMenuBar);
                    }
            );
        }
        else if (menuName.equals("About Hodgepodge Team")) {
            menuItem.addActionListener(
                    e -> {
                        gamePanel.setVisible(false);
                        mainMenuBar.setVisible(false);
                        new AboutTeam(mainFrame, gamePanel, mainMenuBar);
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
