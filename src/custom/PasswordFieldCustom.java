package custom;

import javax.swing.*;
import java.awt.event.*;

// Custom version of JPasswordField to have placeholder text
public class PasswordFieldCustom extends JPasswordField {
    private String placeholderText;
    private boolean hasPlaceHolderText;

    public boolean isHasPlaceHolderText() {
        return hasPlaceHolderText;
    }

    public PasswordFieldCustom(String placeholderText, int charLimit) {
        super();
        this.placeholderText = placeholderText;

        // placeholder text status
        hasPlaceHolderText = true;

        // limit char input field
        setDocument(new LimitText(charLimit));
        setText(this.placeholderText);

        // changes styling to text
        setEchoChar((char) 0);
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
                    setEchoChar('*');
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
                    setEchoChar('*');
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
                    setEchoChar((char) 0);
                }
            }
        });
    }
}
