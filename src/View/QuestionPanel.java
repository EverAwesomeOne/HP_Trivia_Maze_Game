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
        question.removeAll();
        JTextArea questionLabel = new JTextArea(answerArray[0], 8, 25);
        questionLabel.setLineWrap(true);
        questionLabel.setEditable(false);
        question.add(questionLabel);
        questionPanel.add(question);
        questionPanel.revalidate();
        questionPanel.repaint();
    }

    void removeQuestion() {
        questionPanel.removeAll();
        questionPanel.revalidate();
        questionPanel.repaint();
    }

    // base question type panels based off of what is in QAPanel class
    // then delete QAPanel class
}
