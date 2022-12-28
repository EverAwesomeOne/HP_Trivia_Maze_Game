package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Random;

/**
 * The AnswerPanel class represents the part of the game screen that displays
 * the answers.
 */
class AnswerPanel extends JPanel {
    private final TriviaMazeBrain myTriviaMazeBrain;

    private JPanel myQuestionTypePanel = new JPanel();
    private final QuestionPanel myQuestionPanel;
    private final DirectionButtonPanel myDirectionButtonPanel;

    private String[] myQuestionAndAnswerArray;
    private String myDirectionType;
    private String myPlayerAnswer;

    private int myNullCount;

    private final static Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 15);
    private final static Font ANSWER_FONT = new Font("SansSerif", Font.PLAIN, 15);

    private final static Color GOLD_COLOR = new Color(255,204,51).darker();
    private final static Color PURPLE_COLOR = new Color(102,0,153).darker();
    private final static Color LIGHT_PURPLE_COLOR = new Color(230,230,255);

    /**
     * The constructor for the AnswerPanel class.
     * Initializes some other references to GUI parts and the
     * overall controller as well as sets up the
     * answer panel and adds it to the overall game panel screen.
     * @param theGamePanel - the overall game panel screen
     * @param theTriviaMazeBrain - the controller that connects the GUI with the logic
     * @param theQuestionPanel - the panel with the question
     * @param theDirectionButtonPanel - the panel with the direction buttons
     */
    AnswerPanel(final JPanel theGamePanel, final TriviaMazeBrain theTriviaMazeBrain,
                final QuestionPanel theQuestionPanel, final DirectionButtonPanel theDirectionButtonPanel) {

        myTriviaMazeBrain = theTriviaMazeBrain;
        myQuestionPanel = theQuestionPanel;
        myDirectionButtonPanel = theDirectionButtonPanel;

        setBackground(PURPLE_COLOR);

        final TitledBorder border = new TitledBorder("Answer");
        border.setTitleFont(TITLE_FONT);
        border.setTitleColor(GOLD_COLOR);
        setBorder(border);
        theGamePanel.add(this);
        setVisible(true);
    }

    /**
     * Creates the answer panel depending on the question type: short answer, multiple choice,
     * or true false.
     * @param theQuestionAndAnswerArray - the array containing the possible answers and
     *                       question to list on the panel
     * @param theDirectionType - the direction in which the door is located
     */
    void createQuestionType(final String[] theQuestionAndAnswerArray, final String theDirectionType) {
        myDirectionType = theDirectionType;
        myQuestionAndAnswerArray = theQuestionAndAnswerArray;

        myQuestionTypePanel.removeAll();
        myPlayerAnswer = null;

        for (String s : theQuestionAndAnswerArray) {
            if (s == null) {
                myNullCount++;
            }
        }

        if (myNullCount == 0) {
            myQuestionTypePanel = multiChoiceQ();
        }

        else if (myNullCount == 2) {
            myQuestionTypePanel = trueFalseQ();
        }

        else if (myNullCount == 3) {
            myQuestionTypePanel = shortAnswerQ();
        }

        add(myQuestionTypePanel);
        myQuestionTypePanel.setVisible(true);
        revalidate();
        repaint();
        myNullCount = 0;
    }

    /**
     * Sets up the answer panel with the layout for a short answer
     * question.
     * @return - the set-up short answer question panel to add to the answer panel
     */
    private JPanel shortAnswerQ() {
        final JPanel shortAnswerQPanel = new JPanel(new BorderLayout());
        final JLabel questionLabel = new JLabel("Short Answer:");
        final JTextField answerTextArea = new JTextField(10);

        final JButton submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(
                e -> {
                    myPlayerAnswer = answerTextArea.getText();
                    updatePanels();
                }
        );

        questionLabel.setFont(ANSWER_FONT);
        answerTextArea.setFont(ANSWER_FONT);

        answerTextArea.setBackground(LIGHT_PURPLE_COLOR);

        submitButton.setFont(TITLE_FONT);
        submitButton.setBackground(GOLD_COLOR);
        submitButton.setForeground(PURPLE_COLOR);

        final JPanel labelAndTextAreaPanel = new JPanel();
        labelAndTextAreaPanel.setBackground(LIGHT_PURPLE_COLOR);
        shortAnswerQPanel.add(labelAndTextAreaPanel, BorderLayout.NORTH);
        labelAndTextAreaPanel.add(questionLabel, BorderLayout.NORTH);
        labelAndTextAreaPanel.add(answerTextArea, BorderLayout.SOUTH);
        shortAnswerQPanel.add(submitButton, BorderLayout.SOUTH);

        return shortAnswerQPanel;
    }

    /**
     * Sets up the answer panel with the layout for a true or false
     * question.
     * @return - the set-up true or false question panel to add to the answer panel
     */
    private JPanel trueFalseQ() {
        final JPanel trueFalseQPanel = new JPanel(new BorderLayout());
        final JLabel questionLabel = new JLabel("True or False:");

        final Box verticalBox = Box.createVerticalBox();
        final ButtonGroup radioButtonGroup = new ButtonGroup();
        final String trueButtonText = "True";
        final String falseButtonText = "False";
        final JRadioButton trueButton = new JRadioButton(trueButtonText);
        final JRadioButton falseButton = new JRadioButton(falseButtonText);

        organizeVerticalBox(verticalBox, radioButtonGroup, trueButton, trueButtonText);
        organizeVerticalBox(verticalBox, radioButtonGroup, falseButton, falseButtonText);

        trueButton.setFont(ANSWER_FONT);
        falseButton.setFont(ANSWER_FONT);

        trueButton.setBackground(LIGHT_PURPLE_COLOR);
        falseButton.setBackground(LIGHT_PURPLE_COLOR);

        return getQuestionTypePanel(trueFalseQPanel, questionLabel, verticalBox,
                radioButtonGroup, null);
    }

    /**
     * Sets up the answer panel with the layout for a multiple choice
     * question.
     * @return - the set-up multiple choice question panel to add to the answer panel
     */
    private JPanel multiChoiceQ() {
        final JPanel multiChoiceQPanel = new JPanel(new BorderLayout());
        final JLabel questionLabel = new JLabel("Multiple Choice:");

        //Make possible answers displayed randomly
        final int[] randomizeAnswerOrderList = { 1, 2, 3, 4};

        final Random rand = new Random();

        for (int i = 0; i < randomizeAnswerOrderList.length; i++) {
            final int randomIndexToSwap = rand.nextInt(randomizeAnswerOrderList.length);
            final int temp = randomizeAnswerOrderList[randomIndexToSwap];
            randomizeAnswerOrderList[randomIndexToSwap] = randomizeAnswerOrderList[i];
            randomizeAnswerOrderList[i] = temp;
        }

        final Box verticalBox = Box.createVerticalBox();
        final ButtonGroup radioButtonGroup = new ButtonGroup();

        final TextWrappingJRadioButton[] textWrappingJRadioButtons = new TextWrappingJRadioButton[4];
        for (int index : randomizeAnswerOrderList) {
            final String answerText = myQuestionAndAnswerArray[index];
            final TextWrappingJRadioButton textWrappingJRadioButton = new TextWrappingJRadioButton(answerText);
            textWrappingJRadioButtons[index-1] = textWrappingJRadioButton;

            textWrappingJRadioButton.getJTextArea().setFont(ANSWER_FONT);

            final JPanel customJRadioButtonPanel = new JPanel();
            final FlowLayout layout = (FlowLayout) customJRadioButtonPanel.getLayout();
            layout.setVgap(0);
            customJRadioButtonPanel.add(textWrappingJRadioButton.getJRadioButton());
            customJRadioButtonPanel.add(textWrappingJRadioButton.getJTextArea());
            customJRadioButtonPanel.setBackground(LIGHT_PURPLE_COLOR);

            verticalBox.add(customJRadioButtonPanel);
            radioButtonGroup.add(textWrappingJRadioButton.getJRadioButton());
            textWrappingJRadioButton.getJRadioButton().setActionCommand(answerText);
        }

        return getQuestionTypePanel(multiChoiceQPanel, questionLabel,
                verticalBox, radioButtonGroup, textWrappingJRadioButtons);
    }

    /**
     * Sets up the display group and the action commands of the radio buttons for
     * the answer.
     * @param theVerticalBox - the organizer that lets the radio buttons be vertically
     *                       displayed
     * @param theRadioButtonGroup - the organizer that groups the radio buttons
     * @param theAnswerButton - the radio button with a possible answer
     * @param theTextForButton - the text of the answer on the button
     */
    private void organizeVerticalBox(final Box theVerticalBox, final ButtonGroup theRadioButtonGroup,
                                     final JRadioButton theAnswerButton, final String theTextForButton) {

        theVerticalBox.add(theAnswerButton);
        theRadioButtonGroup.add(theAnswerButton);
        theAnswerButton.setActionCommand(theTextForButton);
    }

    /**
     * Fully sets-up the answer panel with the needed buttons, labels, and action
     * listeners.
     * @param theQuestionTypePanel - the answer panel with the specified question type
     * @param theQuestionLabel - the label for the specific question type
     * @param theVerticalBox - the organizer that lets the radio buttons be vertically
     *                       displayed
     * @param theRadioButtonGroup - the organizer that groups the radio buttons
     * @param theCustomButtons - the array of custom radio buttons
     * @return - the set-up answer panel
     */
    private JPanel getQuestionTypePanel(final JPanel theQuestionTypePanel, final JLabel theQuestionLabel,
                                        final Box theVerticalBox, final ButtonGroup theRadioButtonGroup,
                                        final TextWrappingJRadioButton[] theCustomButtons) {

        theQuestionTypePanel.add(theVerticalBox);
        theQuestionTypePanel.setBackground(LIGHT_PURPLE_COLOR);

        final JButton submitButton = new JButton("SUBMIT");
        submitButton.setEnabled(false);

        if (theCustomButtons != null) {
            for (TextWrappingJRadioButton theCustomButton: theCustomButtons) {
                theCustomButton.getJTextArea().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        submitButton.setEnabled(true);
                    }
                });
            }
        }

        final Enumeration<AbstractButton> radioButtons = theRadioButtonGroup.getElements();

        while (radioButtons.hasMoreElements()) {
            radioButtons.nextElement().addActionListener(e->submitButton.setEnabled(true));
        }

        submitButton.addActionListener(
                e -> {
                    myPlayerAnswer = theRadioButtonGroup.getSelection().getActionCommand();
                    updatePanels();
                }
        );

        theQuestionLabel.setFont(ANSWER_FONT);

        submitButton.setFont(TITLE_FONT);
        submitButton.setBackground(GOLD_COLOR);
        submitButton.setForeground(PURPLE_COLOR);

        theQuestionTypePanel.add(theQuestionLabel, BorderLayout.NORTH);
        theQuestionTypePanel.add(submitButton, BorderLayout.SOUTH);

        return theQuestionTypePanel;
    }

    /**
     * Updates the GUI after the player has answered.
     */
    private void updatePanels() {
        removeAll();
        revalidate();
        repaint();

        myQuestionPanel.removeQuestion();
        myTriviaMazeBrain.moveCharacter(myPlayerAnswer, myDirectionType);
        myDirectionButtonPanel.setDirectionButtonsVisibility();
    }
}
