package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JFrame mainFrame;

    private final String TITLE = "Hodgepodge Trivia Maze";

    private final ImageIcon mazeIcon = new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");

    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    private MainMenuPanel mainMenuPanel;

    public MainFrame() {
        mainFrame = new JFrame();
        mainFrame.setTitle(TITLE);
        mainFrame.setSize(500,500);
        mainFrame.setIconImage(mazeIcon.getImage());
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setFrameLocation(mainFrame);

        mainMenuPanel = new MainMenuPanel(mainFrame);

        mainFrame.setVisible(true);
    }

    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    private void setFrameLocation(JFrame frame) {
        Dimension dimension = KIT.getScreenSize();
        final int x = (int)((dimension.getWidth() - frame.getWidth()) / 2);
        final int y = (int)((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x,y);
    }
}
