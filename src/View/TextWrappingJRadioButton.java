package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The TextWrappingJRadioButton class represents a custom JRadioButton used for
 * the multiple choice questions to format better.
 */
public class TextWrappingJRadioButton {
    private final JRadioButton myJRadioButton;
    private final JTextArea myJTextArea;

    /**
     * Constructor for the TextWrappingJRadioButton class.
     * Sets up the styles and mouse listener for the button.
     * @param answerText - the text for the custom JRadio button that displays
     *                   a possible answer
     */
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

    /**
     * Getter for the custom JRadioButton.
     * @return - the custom JRadioButton
     */
    JRadioButton getJRadioButton() {
        return myJRadioButton;
    }

    /**
     * Getter for the text area that displays the text attached to the
     * specific custom JRadio button
     * @return - the text area of the custom JRadio button
     */
    JTextArea getJTextArea() {
        return myJTextArea;
    }
}
