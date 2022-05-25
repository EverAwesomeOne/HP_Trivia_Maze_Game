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
    private final static ImageIcon MAZE_ICON = new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");
    private final static Toolkit KIT = Toolkit.getDefaultToolkit();

    public MainFrame(final TriviaMazeBrain theTriviaMazeBrain) {
        setTitle(TITLE);
        setSize(600,600);
        // full screen
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(MAZE_ICON.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFrameLocation();


        myGamePanel = new GamePanel(this, theTriviaMazeBrain);
        final OptionsMenuBar myMenuBar = new OptionsMenuBar(this, theTriviaMazeBrain, myGamePanel);
        setJMenuBar(myMenuBar);
        myGameRulesPanelGP = new GameRulesPanel(myGamePanel, myMenuBar);
        myAboutTeamPanel = new AboutTeamPanel(this, myGamePanel, myMenuBar);
        myMainMenuPanel = new MainMenuPanel(this, theTriviaMazeBrain, myGamePanel);
        myGameRulesPanelMM = new GameRulesPanel(myMainMenuPanel);

        add(myMainMenuPanel);
        myMainMenuPanel.setVisible(true);

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
