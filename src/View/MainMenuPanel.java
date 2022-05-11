package View;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    private final String TITLE = "Hodgepodge Trivia Maze";

    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    private final ImageIcon mazeIcon = new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");

    private JFrame mainFrame;

    private JPanel mainPanel;

    private JLabel gameTitle;

    MainMenuPanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        gameTitle = new JLabel(TITLE);
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(gameTitle, BorderLayout.NORTH);

        mainPanel.add(setupButtonPanel(), BorderLayout.CENTER);

        mainPanel.add(new JLabel(mazeIcon), BorderLayout.SOUTH);

        mainFrame.add(mainPanel);

        mainPanel.setVisible(true);
    }

    private JPanel setupButtonPanel() {
        JPanel mainMenuBtnPanel = new JPanel();
        mainMenuBtnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton btnNewGame = new JButton("New Game");
        mainMenuBtnPanel.add(btnNewGame);

        JButton btnLoadGame = new JButton("Load Game");
        mainMenuBtnPanel.add(btnLoadGame);

        JButton btnAddQuestion = new JButton("Admin Settings");
        mainMenuBtnPanel.add(btnAddQuestion);

        addActionListener(btnNewGame, "New Game");
        addActionListener(btnLoadGame, "Load Game");
        addActionListener(btnAddQuestion, "Admin Settings");

        return mainMenuBtnPanel;
    }

    private void addActionListener(JButton button, String buttonName) {
        if (buttonName.equals("New Game")) {
            button.addActionListener(
                    e -> {
                        mainPanel.setVisible(false);
                        new GamePanel(mainFrame);
                    }
            );
        }
        else if (buttonName.equals("Load Game")) {
            button.addActionListener(
                    e -> {
                        mainPanel.setVisible(false);
                        new GamePanel(mainFrame);
                    }
            );
        }
        else {
            button.addActionListener(
                    e -> {
                        mainPanel.setVisible(false);
                        mainPanel.setVisible(false);
                        new AddQuestionPanel(mainFrame);
                    }
            );
        }
    }

    private void setFrameLocation(JFrame frame) {
        Dimension dimension = KIT.getScreenSize();
        final int x = (int)((dimension.getWidth() - frame.getWidth()) / 2);
        final int y = (int)((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x,y);
    }
}