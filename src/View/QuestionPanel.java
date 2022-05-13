package View;

import javax.swing.*;

public class QuestionPanel {

    private JPanel questionPanel;

    QuestionPanel(JPanel gamePanel) {
        // make it equal to chosen question type panel instead
        questionPanel = new JPanel();

        // set up layout
        // determine question type given Answer array
        // display determined question type panel


        gamePanel.add(questionPanel);

        questionPanel.setVisible(true);
    }

    // base question type panels based off of what is in QAPanel class
    // then delete QAPanel class
}
