package View;

import javax.swing.*;

public class QuestionPanel {

    private JPanel questionPanel;

    QuestionPanel(String[] answerArray, JPanel gamePanel) {

        questionPanel = new JPanel();
        questionPanel.setBorder(BorderFactory.createTitledBorder("Question"));
        JLabel questionLabel = new JLabel(answerArray[0]);
        questionPanel.add(questionLabel);

        gamePanel.add(questionPanel);

        questionPanel.setVisible(true);
    }

    // base question type panels based off of what is in QAPanel class
    // then delete QAPanel class
}
