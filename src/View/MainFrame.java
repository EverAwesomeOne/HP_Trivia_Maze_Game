package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

/**
 * The MainFrame class represents the overall frame/window that all the other GUI
 * components are displayed on.
 */
public class MainFrame extends JFrame {
    private final GamePanel myGamePanel;
    private final GameRulesPanel myGameRulesPanelMM;
    private final GameRulesPanel myGameRulesPanelGP;
    private final AboutTeamPanel myAboutTeamPanel;

    private final static String TITLE = "Harry Potter Trivia Maze Game";
    private final static ImageIcon MAZE_ICON =
            new ImageIcon("src//View//Images//HogwartsIcon.jpg");
    private final static Toolkit KIT = Toolkit.getDefaultToolkit();

    /**
     * The constructor for the MainFrame class.
     * Initializes overall controller and GUI parts.
     * @param theTriviaMazeBrain - the controller that connects the GUI with the logic
     */
    public MainFrame(final TriviaMazeBrain theTriviaMazeBrain) {
        setTitle(TITLE);
        setSize(600,600);
        setIconImage(MAZE_ICON.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFrameLocation();


        myGamePanel = new GamePanel(theTriviaMazeBrain);
        final OptionsMenuBar myMenuBar =
                new OptionsMenuBar(this, theTriviaMazeBrain, myGamePanel);
        setJMenuBar(myMenuBar);
        myGameRulesPanelGP = new GameRulesPanel(myGamePanel, myMenuBar);
        myAboutTeamPanel = new AboutTeamPanel(myGamePanel, myMenuBar);
        final MainMenuPanel myMainMenuPanel =
                new MainMenuPanel(this, theTriviaMazeBrain, myGamePanel);
        myGameRulesPanelMM = new GameRulesPanel(myMainMenuPanel);

        add(myMainMenuPanel);
        myMainMenuPanel.setVisible(true);

        setResizable(false);
        setVisible(true);
    }

    /**
     * Get the overall game panel that displays all the game components.
     * @return - the overall game panel
     */
    public GamePanel getGamePanel() {
        return myGamePanel;
    }

    /**
     * Gets the game rules panel that is located on the main menu.
     * @return - the game rules panel that is located on the main menu
     */
    public GameRulesPanel getGameRulesPanelMM() {
        return myGameRulesPanelMM;
    }

    /**
     * Gets the game rules panel that is located on the game panel
     * when the player is inside the game already.
     * @return - the game rules panel that is located on the game panel inside the game
     */
    public GameRulesPanel getGameRulesPanelGP() {
        return myGameRulesPanelGP;
    }

    /**
     * Gets the panel that displays information about the developers.
     * @return - the panel that displays information about the developers
     */
    public AboutTeamPanel getAboutTeamPanel() {
        return myAboutTeamPanel;
    }

    /**
     * Sets up the game window to be centered on the players screen .
     */
    private void setFrameLocation() {
        final Dimension dimension = KIT.getScreenSize();
        final int x = (int)((dimension.getWidth() - getWidth()) / 2);
        final int y = (int)((dimension.getHeight() - getHeight()) / 2);
        setLocation(x,y);
    }
}
