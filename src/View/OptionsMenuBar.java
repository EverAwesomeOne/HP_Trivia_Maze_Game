package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class OptionsMenuBar extends JMenuBar {
    private final TriviaMazeBrain myTriviaMazeBrain;
    private final GamePanel myGamePanel;

    OptionsMenuBar(final TriviaMazeBrain theTriviaMazeBrain, final GamePanel theGamePanel) {
        myTriviaMazeBrain = theTriviaMazeBrain;
        myGamePanel = theGamePanel;
        setupMenuBar("About Hodgepodge Team");
        setupMenuBar("Game Rules");
        setupMenuBar("Save Game");
        setupMenuBar("Exit Game");
    }

    private void setupMenuBar(final String theMenuTitle) {
        final JMenu addMenu = new JMenu(theMenuTitle);
        final JMenuItem menuItem = new JMenuItem(theMenuTitle);

        final Font MENU_FONT = new Font("SansSerif", Font.BOLD, 15);
        addMenu.setFont(MENU_FONT);
        menuItem.setFont(MENU_FONT);

        addMenuActionListener(menuItem, theMenuTitle);
        addMenu.add(menuItem);

        add(addMenu);
        setVisible(true);
    }

    private void addMenuActionListener(final JMenuItem theMenuItem, final String theMenuName) {
        switch (theMenuName) {
            case "Game Rules" -> theMenuItem.addActionListener(
                    e -> {
                        setVisible(false);
                        setVisible(false);
                        new GameRulesPanel(myGamePanel, this);
                    }
            );
            case "About Hodgepodge Team" -> theMenuItem.addActionListener(
                    e -> {
                        setVisible(false);
                        setVisible(false);
                        new AboutTeamPanel(myGamePanel, this);
                    }
            );
            case "Exit" -> theMenuItem.addActionListener(
                    e -> {
                        setVisible(false);
                        setVisible(false);
                        myTriviaMazeBrain.closeDatabaseConnection();
                        new MainMenuPanel(myTriviaMazeBrain);
                        myTriviaMazeBrain.resetGameState();
                        myGamePanel.resetDirectionButtonPanel();
                    }
            );

            // edit to include save game option
            default -> theMenuItem.addActionListener(
                    e -> {
                        setVisible(false);
                        setVisible(false);
                        new MainMenuPanel(myTriviaMazeBrain);
                        myTriviaMazeBrain.resetGameState();
                        myGamePanel.resetDirectionButtonPanel();
                    }
            );
        }
    }
}
