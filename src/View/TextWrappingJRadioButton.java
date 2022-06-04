package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextWrappingJRadioButton {
    private final JRadioButton myJRadioButton;
    private final JTextArea myJTextArea;

    TextWrappingJRadioButton(String answerText) {
        myJRadioButton = new JRadioButton();
        myJRadioButton.setBackground(new Color(230,230,255));

        myJTextArea = new JTextArea(answerText, 2, 18);
        myJTextArea.setLineWrap(true);
        myJTextArea.setWrapStyleWord(true);
        myJTextArea.setEditable(false);
        myJTextArea.setBackground(new Color(230,230,255));
        myJTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                myJRadioButton.setSelected(true);
            }
        });
    }

    JRadioButton getJRadioButton() {
        return myJRadioButton;
    }

    JTextArea getJTextArea() {
        return myJTextArea;
    }
}
