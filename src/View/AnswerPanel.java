package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Random;

class AnswerPanel extends JPanel {
    private final TriviaMazeBrain myTriviaMazeBrain;

    private JPanel myQuestionTypePanel = new JPanel();
    private final QuestionPanel myQuestionPanel;
    private final DirectionButtonPanel myDirectionButtonPanel;

    private String[] myAnswerArray;
    private String myDirectionType;
    private String myUserAnswer;

    private int myNullCount;

    final static Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 15);
    final static Font ANSWER_FONT = new Font("SansSerif", Font.PLAIN, 15);

    AnswerPanel(final JPanel theGamePanel, final TriviaMazeBrain theTriviaMazeBrain,
                final QuestionPanel theQuestionPanel,
                final DirectionButtonPanel theDirectionButtonPanel) {

        myTriviaMazeBrain = theTriviaMazeBrain;
        myQuestionPanel = theQuestionPanel;
        myDirectionButtonPanel = theDirectionButtonPanel;

        final TitledBorder border = new TitledBorder("Answer");
        border.setTitleFont(TITLE_FONT);
        setBorder(border);
        theGamePanel.add(this);
        setVisible(true);
    }

    void createQuestionType(final String[] theAnswerArray, final String theDirectionType) {
        myDirectionType = theDirectionType;
        myAnswerArray = theAnswerArray;

        myQuestionTypePanel.removeAll();
        myUserAnswer = null;

        for (String s : theAnswerArray) {
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
        //add an else to catch possible errors

        add(myQuestionTypePanel);
        myQuestionTypePanel.setVisible(true);
        revalidate();
        repaint();
        myNullCount = 0;
    }

    private JPanel shortAnswerQ() {
        final JPanel shortAnswerQPanel = new JPanel(new BorderLayout());
        final JLabel questionLabel = new JLabel("Short Answer:");
        final JTextField answerTextArea = new JTextField(10);

        final JButton submitAButton = new JButton("SUBMIT");
        submitAButton.addActionListener(
                e -> {
                    myUserAnswer = answerTextArea.getText();
                    updatePanels();
                }
        );

        questionLabel.setFont(ANSWER_FONT);
        answerTextArea.setFont(ANSWER_FONT);
        submitAButton.setFont(TITLE_FONT);

        final JPanel labelAndTextAreaPanel = new JPanel();
        shortAnswerQPanel.add(labelAndTextAreaPanel, BorderLayout.NORTH);
        labelAndTextAreaPanel.add(questionLabel, BorderLayout.NORTH);
        labelAndTextAreaPanel.add(answerTextArea, BorderLayout.SOUTH);
        shortAnswerQPanel.add(submitAButton, BorderLayout.SOUTH);

        return shortAnswerQPanel;
    }

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

        return getQuestionTypePanel(trueFalseQPanel, questionLabel, verticalBox, radioButtonGroup);
    }

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

        for (int index : randomizeAnswerOrderList) {
            final String answerText = myAnswerArray[index];
            final TextWrappingJRadioButton textWrappingJRadioButton = new TextWrappingJRadioButton(answerText);

            textWrappingJRadioButton.getJTextArea().setFont(ANSWER_FONT);

            final JPanel customJRadioButtonPanel = new JPanel();
            FlowLayout layout = (FlowLayout) customJRadioButtonPanel.getLayout();
            layout.setVgap(0);
            customJRadioButtonPanel.add(textWrappingJRadioButton.getJRadioButton());
            customJRadioButtonPanel.add(textWrappingJRadioButton.getJTextArea());

            verticalBox.add(customJRadioButtonPanel);
            radioButtonGroup.add(textWrappingJRadioButton.getJRadioButton());
            textWrappingJRadioButton.getJRadioButton().setActionCommand(answerText);
        }

        return getQuestionTypePanel(multiChoiceQPanel, questionLabel,
                verticalBox, radioButtonGroup);
    }

    private void organizeVerticalBox(final Box theVerticalBox,
                                     final ButtonGroup theRadioButtonGroup,
                                     final JRadioButton theAnswerButton,
                                     final String theTextForButton) {
        theVerticalBox.add(theAnswerButton);
        theRadioButtonGroup.add(theAnswerButton);
        theAnswerButton.setActionCommand(theTextForButton);
    }

    private JPanel getQuestionTypePanel(final JPanel theQuestionTypePanel,
                                        final JLabel theQuestionLabel,
                                        final Box theVerticalBox,
                                        final ButtonGroup theRadioButtonGroup) {
        theQuestionTypePanel.add(theVerticalBox);

        final JButton submitAButton = new JButton("SUBMIT");
        submitAButton.addActionListener(
                e -> {
                    myUserAnswer = theRadioButtonGroup.getSelection().getActionCommand();
                    updatePanels();
                }
        );

        theQuestionLabel.setFont(ANSWER_FONT);
        submitAButton.setFont(TITLE_FONT);

        theQuestionTypePanel.add(theQuestionLabel, BorderLayout.NORTH);
        theQuestionTypePanel.add(submitAButton, BorderLayout.SOUTH);

        return theQuestionTypePanel;
    }

    private void updatePanels() {
        removeAll();
        revalidate();
        repaint();

        myQuestionPanel.removeQuestion();
        myTriviaMazeBrain.moveCharacter(myUserAnswer, myDirectionType);
        myDirectionButtonPanel.setDirectionButtonsVisibility();
    }
}
