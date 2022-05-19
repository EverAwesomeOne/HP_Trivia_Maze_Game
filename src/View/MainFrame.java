package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JFrame theMainFrame;

    private final String theTitle = "Hodgepodge Trivia Maze";

    private final ImageIcon theMazeIcon = new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");

    private static final Toolkit theKit = Toolkit.getDefaultToolkit();

    private final MainMenuPanel theMainMenuPanel;

    //private TriviaMazeBrain triviaMazeBrain;

    public MainFrame(final TriviaMazeBrain theTriviaMazeBrain) {
        theMainFrame = new JFrame();
        theMainFrame.setTitle(theTitle);
        theMainFrame.setSize(500,500);
        theMainFrame.setIconImage(theMazeIcon.getImage());
        theMainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //this.triviaMazeBrain = triviaMazeBrain;
        setFrameLocation(theMainFrame);

        theMainMenuPanel = new MainMenuPanel(theMainFrame, theTriviaMazeBrain);

        theMainFrame.setResizable(false);
        theMainFrame.setVisible(true);
    }

    public MainMenuPanel getTheMainMenuPanel() {
        return theMainMenuPanel;
    }

    private void setFrameLocation(final JFrame theFrame) {
        final Dimension dimension = theKit.getScreenSize();
        final int x = (int)((dimension.getWidth() - theFrame.getWidth()) / 2);
        final int y = (int)((dimension.getHeight() - theFrame.getHeight()) / 2);
        theFrame.setLocation(x,y);
    }
}
