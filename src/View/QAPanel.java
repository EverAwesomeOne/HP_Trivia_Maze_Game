package View;

import javax.swing.*;

public class QAPanel {

    private JPanel panelQA;

    // take in question
    // take in Array of answers
    // display qType depending on num of answers

    //


    QAPanel(int qType) {

        if (qType == 1) {
            panelQA = shortAnswerQ();
        }

        else if (qType == 2) {
            panelQA = yesNoQ();
        }

        else if (qType == 3) {
            panelQA = multiChoiceQ();
        }

        panelQA.setVisible(true);
    }

    private JPanel shortAnswerQ() {
        return null;
    }

    private JPanel yesNoQ() {
        return null;
    }

    private JPanel multiChoiceQ() {
        return null;
    }
}
