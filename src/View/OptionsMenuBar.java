package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class OptionsMenuBar extends JMenuBar {
    private final TriviaMazeBrain myTriviaMazeBrain;
    private final GamePanel myGamePanel;
    private final MainFrame myMainFrame;

    OptionsMenuBar(final MainFrame theMainFrame, final TriviaMazeBrain theTriviaMazeBrain, final GamePanel theGamePanel) {
        myMainFrame = theMainFrame;
        myTriviaMazeBrain = theTriviaMazeBrain;
        myGamePanel = theGamePanel;
        setupMenuBar("About The Developers");
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
        setVisible(false);
    }

    private void addMenuActionListener(final JMenuItem theMenuItem, final String theMenuName) {
        switch (theMenuName) {
            case "Game Rules" -> theMenuItem.addActionListener(
                    e -> {
                        setVisible(false);
                        myGamePanel.setVisible(false);
                        GameRulesPanel gameRulesPanel = myMainFrame.getGameRulesPanelGP();
                        myMainFrame.add(gameRulesPanel);
                        gameRulesPanel.setVisible(true);
                    }
            );
            case "About The Developers" -> theMenuItem.addActionListener(
                    e -> {
                        setVisible(false);
                        myGamePanel.setVisible(false);
                        AboutTeamPanel aboutTeamPanel = myMainFrame.getAboutTeamPanel();
                        myMainFrame.add(aboutTeamPanel);
                        aboutTeamPanel.setVisible(true);
                    }
            );
            case "Exit Game" -> theMenuItem.addActionListener(
                    e -> {
                        myTriviaMazeBrain.closeDatabaseConnection();
                        setVisible(false);
                        myTriviaMazeBrain.resetGameState();
                    }
            );
            //Save game
            default -> theMenuItem.addActionListener(
                    e -> {
                        myTriviaMazeBrain.serialize();
                        myTriviaMazeBrain.closeDatabaseConnection();
                        setVisible(false);
                        myTriviaMazeBrain.resetGameState();
                    }
            );
        }
    }
}
