package View;

import javax.swing.*;
import java.util.HashMap;

public class QAPanel {

    private JPanel gamePanel;

    private JPanel panelQA;

    private String[] answerArray;

    private int nullCount;




    QAPanel(String[] answerArray, JPanel gamePanel) {

        this.gamePanel = gamePanel;

        this.answerArray = answerArray;

        for (String s : answerArray) {
            if (s.equals("")) {
                nullCount++;
            }
        }

        panelQA = getQuestionType(nullCount);

        gamePanel.add(panelQA);

        panelQA.setVisible(true);
    }

    private JPanel getQuestionType(int nullCount) {
        JPanel questionTypePanel = null;

        if (nullCount == 0) {
            questionTypePanel = multiChoiceQ();
        }

        else if (nullCount == 2) {
            questionTypePanel = yesNoQ();
        }

        else if (nullCount == 3) {
            questionTypePanel = shortAnswerQ();
        }

        return questionTypePanel;
    }

    private JPanel shortAnswerQ() {
        JPanel shortAnswerQPanel = new JPanel();

        JLabel questionLabel = new JLabel("short answer question");

        JTextField answerTextArea = new JTextField(10);

        JButton submitAButton = new JButton("SUBMIT");
        submitAButton.addActionListener(
                e -> {
                    String answerString = answerTextArea.getText();
                    System.out.println(answerString);
                    shortAnswerQPanel.setVisible(false);
                    panelQA.setVisible(false);
                    //new DisplayDoorsPanel(gamePanel);
                }
        );

        shortAnswerQPanel.add(questionLabel);
        shortAnswerQPanel.add(answerTextArea);
        shortAnswerQPanel.add(submitAButton);

        shortAnswerQPanel.setVisible(true);

        return shortAnswerQPanel;
    }

    private JPanel yesNoQ() {
        JPanel yesNoQPanel = new JPanel();

        return yesNoQPanel;
    }

    private JPanel multiChoiceQ() {
        JPanel multiChoiceQPanel = new JPanel();

        return multiChoiceQPanel;
    }
}
