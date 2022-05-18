package View;

import javax.swing.*;

public class QuestionPanel {

    private JPanel questionPanel;
    private JPanel question = new JPanel();

    QuestionPanel(JPanel gamePanel) {

        questionPanel = new JPanel();
        questionPanel.setBorder(BorderFactory.createTitledBorder("Question"));


        gamePanel.add(questionPanel);

        questionPanel.setVisible(true);
    }

    void createQuestion(String[] answerArray) {
        JLabel questionLabel = new JLabel(answerArray[0]);
        questionLabel.setSize(2, 20);
        question.add(questionLabel);
        questionPanel.add(question);
    }

    void removeQuestion() {
        questionPanel.remove(question);
        question = new JPanel();
    }
    // base question type panels based off of what is in QAPanel class
    // then delete QAPanel class
}
