package View;

import Controller.TriviaMazeBrain;
import Model.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AnswerPanel {

    private JPanel gamePanel;

    private JPanel answerPanel;

    private String[] answerArray;

    private String userAnswer;

    private int nullCount;

    private TriviaMazeBrain triviaMazeBrain;

    private String directionType;


    AnswerPanel(String[] answerArray, JPanel gamePanel, TriviaMazeBrain triviaMazeBrain, String directionType) {

        this.gamePanel = gamePanel;
        this.triviaMazeBrain = triviaMazeBrain;
        this.directionType = directionType;
        this.answerArray = answerArray;

        userAnswer = null;

        for (String s : answerArray) {
            if (s == null) {
                nullCount++;
            }
        }

        answerPanel = getQuestionType(nullCount);
        answerPanel.setBorder(BorderFactory.createTitledBorder("Answer"));
        answerPanel.setVisible(true);

        gamePanel.add(answerPanel);

        answerPanel.setVisible(true);
    }

    private JPanel getQuestionType(int nullCount) {
        JPanel questionTypePanel = null;

        if (nullCount == 0) {
            questionTypePanel = multiChoiceQ();
        }

        else if (nullCount == 2) {
            questionTypePanel = trueFalseQ();
        }

        else if (nullCount == 3) {
            questionTypePanel = shortAnswerQ();
        }
        //add an else to catch possible errors

        return questionTypePanel;
    }

    private JPanel shortAnswerQ() {
        JPanel shortAnswerQPanel = new JPanel(new BorderLayout());
        JLabel questionLabel = new JLabel("Short Answer:");
        JTextField answerTextArea = new JTextField(10);

        JButton submitAButton = new JButton("SUBMIT");
        submitAButton.addActionListener(
                e -> {
                    userAnswer = answerTextArea.getText();
                    triviaMazeBrain.move2(userAnswer, directionType);
                    shortAnswerQPanel.setVisible(false);
                    answerPanel.setVisible(false);
                    //new DisplayDoorsPanel(gamePanel);
                }
        );

        JPanel labelAndTextAreaPanel = new JPanel();
        shortAnswerQPanel.add(labelAndTextAreaPanel, BorderLayout.NORTH);
        labelAndTextAreaPanel.add(questionLabel, BorderLayout.NORTH);
        labelAndTextAreaPanel.add(answerTextArea, BorderLayout.SOUTH);
        shortAnswerQPanel.add(submitAButton, BorderLayout.SOUTH);

        return shortAnswerQPanel;
    }

    private JPanel trueFalseQ() {
        JPanel trueFalseQPanel = new JPanel(new BorderLayout());

        JLabel questionLabel = new JLabel("True or False:");

        Box verticalBox = Box.createVerticalBox();
        ButtonGroup radioButtonGroup = new ButtonGroup();
        JRadioButton trueButton = new JRadioButton("True");
        JRadioButton falseButton = new JRadioButton("False");
        verticalBox.add(trueButton);
        radioButtonGroup.add(trueButton);
        verticalBox.add(falseButton);
        radioButtonGroup.add(falseButton);
        trueFalseQPanel.add(verticalBox);

        JTextField answerTextArea = new JTextField(10);
        JButton submitAButton = new JButton("SUBMIT");
        submitAButton.addActionListener(
                e -> {
                    userAnswer = answerTextArea.getText();
                    triviaMazeBrain.move2(userAnswer, directionType);
                    trueFalseQPanel.setVisible(false);
                    answerPanel.setVisible(false);
                    //new DisplayDoorsPanel(gamePanel);
                }
        );

        trueFalseQPanel.add(questionLabel, BorderLayout.NORTH);
        trueFalseQPanel.add(submitAButton, BorderLayout.SOUTH);
        return trueFalseQPanel;
    }

    String getUserAnswer() {
        return userAnswer;
    }

    private JPanel multiChoiceQ() {
        JPanel multiChoiceQPanel = new JPanel(new BorderLayout());

        JLabel questionLabel = new JLabel("Multiple Choice:");

        //Make possible answers displayed randomly
        int[] questionListRandomIndex = { 1, 2, 3, 4};

        Random rand = new Random();

        for (int i = 0; i < questionListRandomIndex.length; i++) {
            int randomIndexToSwap = rand.nextInt(questionListRandomIndex.length);
            int temp = questionListRandomIndex[randomIndexToSwap];
            questionListRandomIndex[randomIndexToSwap] = questionListRandomIndex[i];
            questionListRandomIndex[i] = temp;
        }

        Box verticalBox = Box.createVerticalBox();
        ButtonGroup radioButtonGroup = new ButtonGroup();

        for (int i = 0; i < questionListRandomIndex.length; i++) {
            String index = answerArray[questionListRandomIndex[i]];
            JRadioButton answer = new JRadioButton(index);
            answer.setActionCommand(index);
            verticalBox.add(answer);
            radioButtonGroup.add(answer);
        }

        multiChoiceQPanel.add(verticalBox);


        JButton submitAButton = new JButton("SUBMIT");
        submitAButton.addActionListener(
                e -> {
                    userAnswer = radioButtonGroup.getSelection().getActionCommand();
                    triviaMazeBrain.move2(userAnswer, directionType);
                    multiChoiceQPanel.setVisible(false);
                    answerPanel.setVisible(false);
                    //new DisplayDoorsPanel(gamePanel);
                }
        );

        multiChoiceQPanel.add(questionLabel, BorderLayout.NORTH);
        multiChoiceQPanel.add(submitAButton, BorderLayout.SOUTH);

        return multiChoiceQPanel;
    }
}
