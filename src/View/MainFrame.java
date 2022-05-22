package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final MainMenuPanel myMainMenuPanel;
    private final GamePanel myGamePanel;
    private final GameRulesPanel myGameRulesPanelMM;
    private final GameRulesPanel myGameRulesPanelGP;
    private final AboutTeamPanel myAboutTeamPanel;

    private final static String TITLE = "Harry Potter Trivia Maze Game";
    private final static ImageIcon MAZE_ICON =
            new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");
    private final static Toolkit KIT = Toolkit.getDefaultToolkit();

    public MainFrame(final TriviaMazeBrain theTriviaMazeBrain) {
        setTitle(TITLE);
        setSize(500,500);
        setIconImage(MAZE_ICON.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFrameLocation();

        myMainMenuPanel = new MainMenuPanel(theTriviaMazeBrain);
        add(myMainMenuPanel);
        myGamePanel = new GamePanel(theTriviaMazeBrain);
        add(myGamePanel);
        final OptionsMenuBar myMenuBar = new OptionsMenuBar(theTriviaMazeBrain, myGamePanel);
        setJMenuBar(myMenuBar);
        myGameRulesPanelMM = new GameRulesPanel(myMainMenuPanel);
        add(myGameRulesPanelMM);
        myGameRulesPanelGP = new GameRulesPanel(myGamePanel, myMenuBar);
        myAboutTeamPanel = new AboutTeamPanel(myGamePanel, myMenuBar);


        setResizable(false);
        setVisible(true);
    }

    public MainMenuPanel getMainMenuPanel() {
        return myMainMenuPanel;
    }

    public GamePanel getGamePanel() {
        return myGamePanel;
    }

    public GameRulesPanel getGameRulesPanelMM() {
        return myGameRulesPanelMM;
    }

    public GameRulesPanel getGameRulesPanelGP() {
        return myGameRulesPanelGP;
    }

    public AboutTeamPanel getAboutTeamPanel() {
        return myAboutTeamPanel;
    }

    private void setFrameLocation() {
        final Dimension dimension = KIT.getScreenSize();
        final int x = (int)((dimension.getWidth() - getWidth()) / 2);
        final int y = (int)((dimension.getHeight() - getHeight()) / 2);
        setLocation(x,y);
    }
}
