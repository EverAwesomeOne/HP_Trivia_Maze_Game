package View;

import javax.swing.*;

public class QuestionPanel extends JPanel {
    private final JPanel myQuestion = new JPanel();

    QuestionPanel(final JPanel theGamePanel) {
        setBorder(BorderFactory.createTitledBorder("Question"));
        theGamePanel.add(this);
        setVisible(true);
    }

    void createQuestion(final String[] theAnswerArray) {
        myQuestion.removeAll();

        final JTextArea questionLabel = new JTextArea(theAnswerArray[0], 8, 22);
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);
        questionLabel.setEditable(false);

        myQuestion.add(questionLabel);
        add(myQuestion);
        revalidate();
        repaint();
    }

    void removeQuestion() {
        removeAll();
        revalidate();
        repaint();
    }

}
