package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class QuestionPanel extends JPanel {
    private final JPanel myQuestion = new JPanel();

    final static Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 15);

    QuestionPanel(final JPanel theGamePanel) {
        final TitledBorder border = new TitledBorder("Question");
        border.setTitleFont(TITLE_FONT);
        setBorder(border);

        theGamePanel.add(this);
        setVisible(true);
    }

    void createQuestion(final String[] theAnswerArray) {
        myQuestion.removeAll();

        final JTextArea questionLabel = new JTextArea(theAnswerArray[0], 8, 20);
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);
        questionLabel.setEditable(false);
        questionLabel.setOpaque(false);

        questionLabel.setFont(TITLE_FONT);

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
