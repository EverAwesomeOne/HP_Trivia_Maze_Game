package View;

import javax.swing.*;

public class QuestionPanel {

    private final JPanel myQuestionPanel;
    private final JPanel myQuestion = new JPanel();

    QuestionPanel(final JPanel theGamePanel) {

        myQuestionPanel = new JPanel();
        myQuestionPanel.setBorder(BorderFactory.createTitledBorder("Question"));


        theGamePanel.add(myQuestionPanel);

        myQuestionPanel.setVisible(true);
    }

    void createQuestion(final String[] theAnswerArray) {
        myQuestion.removeAll();

        final JTextArea questionLabel = new JTextArea(theAnswerArray[0], 8, 25);
        questionLabel.setLineWrap(true);
        questionLabel.setEditable(false);

        myQuestion.add(questionLabel);
        myQuestionPanel.add(myQuestion);
        myQuestionPanel.revalidate();
        myQuestionPanel.repaint();
    }

    void removeQuestion() {
        myQuestionPanel.removeAll();
        myQuestionPanel.revalidate();
        myQuestionPanel.repaint();
    }

    // base question type panels based off of what is in QAPanel class
    // then delete QAPanel class
}
