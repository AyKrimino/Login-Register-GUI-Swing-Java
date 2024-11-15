package custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextFieldCustom extends JTextField {
    private String placeholderText;
    private boolean hasPlaceHolderText;

    public boolean isHasPlaceHolderText() {
        return hasPlaceHolderText;
    }

    public TextFieldCustom(String placeholderText, int charLimit) {
        super();
        this.placeholderText = placeholderText;

        // placeholder text status
        hasPlaceHolderText = true;

        // limit char input field
        setDocument(new LimitText(charLimit));
        setText(this.placeholderText);

        // add margin to text (which is equivalent to TextField padding)
        setMargin(new Insets(0, 10, 0, 0));

        addListeners();
    }

    private void addListeners() {
        // check for mouse clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (hasPlaceHolderText) {
                    hasPlaceHolderText = false;
                    setText("");
                }
            }
        });

        // check for key presses
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (hasPlaceHolderText) {
                    hasPlaceHolderText = false;
                    setText("");
                }
            }
        });

        // check to see if user has remove focus for this field
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                // check if it is empty
                if (getText().trim().isEmpty()) {
                    hasPlaceHolderText = true;
                    setText(placeholderText);
                }
            }
        });
    }
}
