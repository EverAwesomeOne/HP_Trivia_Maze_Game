package View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextWrappingJRadioButton {
    private final JRadioButton myJRadioButton;
    private final JTextArea myJTextArea;

    TextWrappingJRadioButton(String answerText) {
        myJRadioButton = new JRadioButton();
        myJTextArea = new JTextArea(answerText, 2, 18);
        myJTextArea.setLineWrap(true);
        myJTextArea.setWrapStyleWord(true);
        myJTextArea.setOpaque(false);
        myJTextArea.setEditable(false);
        myJTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                myJRadioButton.setSelected(true);
            }
        });
    }

    public JRadioButton getJRadioButton() {
        return myJRadioButton;
    }

    public JTextArea getJTextArea() {
        return myJTextArea;
    }
}
