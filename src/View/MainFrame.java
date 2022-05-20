package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainFrame {

    private final static String TITLE = "Harry Potter Trivia Maze Game";

    private final static ImageIcon MAZE_ICON =
            new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");

    private final static Toolkit KIT = Toolkit.getDefaultToolkit();

    private final MainMenuPanel myMainMenuPanel;


    public MainFrame(final TriviaMazeBrain theTriviaMazeBrain) {
        final JFrame myMainFrame = new JFrame();
        myMainFrame.setTitle(TITLE);
        myMainFrame.setSize(500,500);
        myMainFrame.setIconImage(MAZE_ICON.getImage());
        myMainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setFrameLocation(myMainFrame);

        myMainMenuPanel = new MainMenuPanel(myMainFrame, theTriviaMazeBrain);

        myMainFrame.setResizable(false);
        myMainFrame.setVisible(true);
    }

    public MainMenuPanel getMainMenuPanel() {
        return myMainMenuPanel;
    }

    private void setFrameLocation(final JFrame theFrame) {
        final Dimension dimension = KIT.getScreenSize();
        final int x = (int)((dimension.getWidth() - theFrame.getWidth()) / 2);
        final int y = (int)((dimension.getHeight() - theFrame.getHeight()) / 2);
        theFrame.setLocation(x,y);
    }
}
