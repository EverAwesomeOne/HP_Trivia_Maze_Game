package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class QuestionPanel extends JPanel {
    private final JPanel myQuestionPanel = new JPanel();

    private final static Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 15);
    private final static Font QUESTION_FONT = new Font("SansSerif", Font.PLAIN, 15);

    final static Color GOLD_COLOR = new Color(255,204,51).darker();
    final static Color PURPLE_COLOR = new Color(102,0,153).darker();
    final static Color LIGHT_PURPLE_COLOR = new Color(230,230,255);

    QuestionPanel(final JPanel theGamePanel) {
        final TitledBorder border = new TitledBorder("Question");
        border.setTitleFont(TITLE_FONT);
        border.setTitleColor(GOLD_COLOR);
        setBorder(border);

        setBackground(PURPLE_COLOR);

        theGamePanel.add(this);
        setVisible(true);
    }

    void createQuestion(final String[] theAnswerArray) {
        myQuestionPanel.removeAll();

        final JTextArea questionTextArea = new JTextArea(theAnswerArray[0], 8, 20);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        questionTextArea.setBackground(LIGHT_PURPLE_COLOR);
        questionTextArea.setFont(QUESTION_FONT);

        myQuestionPanel.add(questionTextArea);
        myQuestionPanel.setBackground(LIGHT_PURPLE_COLOR);

        add(myQuestionPanel);
        revalidate();
        repaint();
    }

    void removeQuestion() {
        removeAll();
        revalidate();
        repaint();
    }

}
