package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final MainMenuPanel myMainMenuPanel;
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

        myMainMenuPanel = new MainMenuPanel(this, theTriviaMazeBrain);

        setResizable(false);
        setVisible(true);
    }

    public MainMenuPanel getMainMenuPanel() {
        return myMainMenuPanel;
    }

    private void setFrameLocation() {
        final Dimension dimension = KIT.getScreenSize();
        final int x = (int)((dimension.getWidth() - getWidth()) / 2);
        final int y = (int)((dimension.getHeight() - getHeight()) / 2);
        setLocation(x,y);
    }
}
