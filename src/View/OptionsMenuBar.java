package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

/**
 * The OptionsMenuBar class represents the menu tabs located at the top of the game screen.
 */
public class OptionsMenuBar extends JMenuBar {
    private final TriviaMazeBrain myTriviaMazeBrain;
    private final GamePanel myGamePanel;
    private final MainFrame myMainFrame;

    /**
     * The constructor for the OptionsMenuBar class.
     * Initializes some other references to GUI parts and the
     * overall controller as well as sets up the
     * menu bar and adds it to the overall game panel screen.
     * @param theMainFrame - the overall frame/window that all the other GUI
     *                      components are displayed on
     * @param theTriviaMazeBrain - the controller that connects the GUI with the logic
     * @param theGamePanel - the overall game panel screen
     */
    OptionsMenuBar(final MainFrame theMainFrame, final TriviaMazeBrain theTriviaMazeBrain,
                   final GamePanel theGamePanel) {
        myMainFrame = theMainFrame;
        myTriviaMazeBrain = theTriviaMazeBrain;
        myGamePanel = theGamePanel;
        setupMenuBar("About The Developers");
        setupMenuBar("Game Rules");
        setupMenuBar("Save Game");
        setupMenuBar("Exit Game");
    }

    /**
     * Sets up menu bar by adding the font and action listeners for the menu items
     * and adds them to the menu bar.
     * @param theMenuTitle - the title of the menu item
     */
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

    /**
     * Adds an action listener to the specified menu item.
     * @param theMenuItem - the menu tab
     * @param theMenuName - the name of the menu tab
     */
    private void addMenuActionListener(final JMenuItem theMenuItem, final String theMenuName) {
        switch (theMenuName) {
            case "Game Rules" -> theMenuItem.addActionListener(
                    e -> {
                        setVisible(false);
                        myGamePanel.setVisible(false);
                        final GameRulesPanel gameRulesPanel = myMainFrame.getGameRulesPanelGP();
                        myMainFrame.add(gameRulesPanel);
                        gameRulesPanel.setVisible(true);
                    }
            );
            case "About The Developers" -> theMenuItem.addActionListener(
                    e -> {
                        setVisible(false);
                        myGamePanel.setVisible(false);
                        final AboutTeamPanel aboutTeamPanel = myMainFrame.getAboutTeamPanel();
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
