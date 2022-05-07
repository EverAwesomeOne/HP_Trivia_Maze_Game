package View;

import javax.swing.*;
import java.awt.*;

public class TriviaMazeGUI extends JFrame /*implements ActionListener*/ {

    private static final String TITLE = "Hodgepodge Trivia Maze";

    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    private static final String NORTH_COMMAND = "North";

    private static final String SOUTH_COMMAND = "South";

    private static final String WEST_COMMAND = "West";

    private static final String EAST_COMMAND = "East";

    private final static Font TITLE_FONT = new Font("Serif", Font.PLAIN, 20);

    final static Font DEFAULT_FONT = new Font("Serif", Font.BOLD, 17);

    private JFrame mainFrame;
    private JPanel mainPanel;
    final JMenuBar mainBar = new JMenuBar();

    private final ImageIcon mazeIcon = new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");

    public TriviaMazeGUI() {
        initializeGUI();

    }

    private void initializeGUI() {
        mainFrame = new JFrame();
        mainFrame.setTitle(TITLE);
        mainFrame.setSize(500,500);
        mainFrame.setIconImage(mazeIcon.getImage());

        // center frame on screen
        setFrameLocation();

        // frame exit on close
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setupMainPanel();
        setupStartPanel();

        mainFrame.setVisible(true);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // add to North border
        setupMenuBar("Game Info");
        setupMenuBar("About Hodgepodge Team");

        mainFrame.add(mainPanel);
        mainPanel.setVisible(true);
    }

    private Component setupMenuBar(String menuTitle) {
        final JMenu addMenu = new JMenu(menuTitle);

        /*final JMenu gameInfo = new JMenu("Game Info");
        final JMenu aboutAuthor = new JMenu("About Hodgepodge Team");*/

        /*mainBar.add(gameInfo);
        mainBar.add(aboutAuthor);*/

        mainBar.add(addMenu);

        mainPanel.add(mainBar, BorderLayout.NORTH);

        mainBar.setVisible(true);
        return mainBar;
    }

    private void setupStartPanel() {
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());

        JLabel gameTitleLabel = new JLabel(TITLE);
        gameTitleLabel.setFont(TITLE_FONT);
        gameTitleLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel iconLabel = new JLabel(mazeIcon);

        JButton startButton = new JButton("ENTER MAZE");
        startButton.setFont(DEFAULT_FONT);
        startButton.addActionListener(
                e -> {
                    startPanel.setVisible(false);
                    setupGamePanel();
                }
        );

        JButton newGame = new JButton("New Game");
        JButton loadGame = new JButton("Load Game");

        startPanel.add(gameTitleLabel, BorderLayout.NORTH);
        startPanel.add(iconLabel, BorderLayout.CENTER);
        startPanel.add(startButton, BorderLayout.SOUTH);

        mainPanel.add(startPanel, BorderLayout.CENTER);

        startPanel.setVisible(true);
    }

    private void setupGamePanel() {
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(2,2));

        JLabel tempMazeLabel = new JLabel(mazeIcon);
        JLabel tempPlaceHolder = new JLabel("Place Holder");
        JLabel tempTriviaQA = new JLabel("QA Place Holder");

        gamePanel.add(tempMazeLabel);
        gamePanel.add(setupButtonPanel());
        gamePanel.add(tempPlaceHolder);
        gamePanel.add(tempTriviaQA);

        setupMenuBar("Save Game");
        setupMenuBar("Exit Game");

        mainPanel.add(gamePanel, BorderLayout.CENTER);

        gamePanel.setVisible(true);
    }

    private JPanel setupButtonPanel() {
        JPanel directionButtonPanel = new JPanel();
        directionButtonPanel.setLayout(new BorderLayout());

        JButton northButton = new JButton(NORTH_COMMAND);
        JButton southButton = new JButton(SOUTH_COMMAND);
        JButton eastButton = new JButton(EAST_COMMAND);
        JButton westButton = new JButton(WEST_COMMAND);

        directionButtonPanel.add(northButton, BorderLayout.NORTH);
        directionButtonPanel.add(southButton, BorderLayout.SOUTH);
        directionButtonPanel.add(eastButton, BorderLayout.EAST);
        directionButtonPanel.add(westButton, BorderLayout.WEST);

        directionButtonPanel.setVisible(true);

        return directionButtonPanel;
    }

    private ImageIcon scale(ImageIcon icon) {
        return new ImageIcon(icon.getImage().getScaledInstance(500, 440, Image.SCALE_SMOOTH));
    }

    private void setFrameLocation() {
        Dimension dimension = KIT.getScreenSize();
        final int x = (int)((dimension.getWidth() - mainFrame.getWidth()) / 2);
        final int y = (int)((dimension.getHeight() - mainFrame.getHeight()) / 2);
        mainFrame.setLocation(x,y);
    }

   /* @Override
    public void actionPerformed(ActionEvent e) {

    }*/
}
