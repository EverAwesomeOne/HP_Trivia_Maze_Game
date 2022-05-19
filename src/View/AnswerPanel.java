package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class AnswerPanel {

    private final JPanel myAnswerPanel;
    private JPanel myQuestionTypePanel = new JPanel();

    private final TriviaMazeBrain myTriviaMazeBrain;

    private final QuestionPanel myQuestionPanel;

    private final DirectionButtonPanel myDirectionButtonPanel;

    private String[] myAnswerArray;

    private String myDirectionType;
    private String myUserAnswer;

    private int myNullCount;

    AnswerPanel(final JPanel theGamePanel, final TriviaMazeBrain theTriviaMazeBrain, final QuestionPanel theQuestionPanel, final DirectionButtonPanel theDirectionButtonPanel) {
        myTriviaMazeBrain = theTriviaMazeBrain;
        myQuestionPanel = theQuestionPanel;
        myDirectionButtonPanel = theDirectionButtonPanel;

        myAnswerPanel = new JPanel();
        myAnswerPanel.setBorder(BorderFactory.createTitledBorder("Answer"));

        theGamePanel.add(myAnswerPanel);

        myAnswerPanel.setVisible(true);
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

        myAnswerPanel.add(myQuestionTypePanel);
        myQuestionTypePanel.setVisible(true);
        myAnswerPanel.revalidate();
        myAnswerPanel.repaint();
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

        for (int i = 0; i < randomizeAnswerOrderList.length; i++) {
            final String answerText = myAnswerArray[randomizeAnswerOrderList[i]];
            final JRadioButton answerButton = new JRadioButton(answerText);

            organizeVerticalBox(verticalBox, radioButtonGroup, answerButton, answerText);
        }
        return getQuestionTypePanel(multiChoiceQPanel, questionLabel, verticalBox, radioButtonGroup);
    }

    private void organizeVerticalBox(final Box theVerticalBox, final ButtonGroup theRadioButtonGroup, final JRadioButton theAnswerButton, final String theTextForButton) {
        theVerticalBox.add(theAnswerButton);
        theRadioButtonGroup.add(theAnswerButton);
        theAnswerButton.setActionCommand(theTextForButton);
    }

    private JPanel getQuestionTypePanel(final JPanel theQuestionTypePanel, final JLabel theQuestionLabel, final Box theVerticalBox, final ButtonGroup theRadioButtonGroup) {
        theQuestionTypePanel.add(theVerticalBox);

        final JButton submitAButton = new JButton("SUBMIT");
        submitAButton.addActionListener(
                e -> {
                    myUserAnswer = theRadioButtonGroup.getSelection().getActionCommand();
                    updatePanels();
                }
        );

        theQuestionTypePanel.add(theQuestionLabel, BorderLayout.NORTH);
        theQuestionTypePanel.add(submitAButton, BorderLayout.SOUTH);

        return theQuestionTypePanel;
    }

    private void updatePanels() {
        myAnswerPanel.removeAll();
        myAnswerPanel.revalidate();
        myAnswerPanel.repaint();

        myQuestionPanel.removeQuestion();

        myTriviaMazeBrain.move2(myUserAnswer, myDirectionType);

        myDirectionButtonPanel.setDirectionButtonsVisibility();
    }
}
