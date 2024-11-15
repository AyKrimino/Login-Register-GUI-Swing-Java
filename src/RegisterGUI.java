import custom.ErrorLabel;
import custom.PasswordFieldCustom;
import custom.TextFieldCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterGUI extends JFrame implements FocusListener, ActionListener {
    private ErrorLabel usernameErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel, emailErrorLabel;
    private TextFieldCustom usernameField, emailField;
    private PasswordFieldCustom passwordField, confirmPasswordField;

    public RegisterGUI() {
        super("Register");
        setSize(CommonConstants.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        addGUIComponents();
    }

    private void addGUIComponents() {
        // Register Label
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(
                0,
                0,
                CommonConstants.REGISTER_LABEL_SIZE.width,
                CommonConstants.REGISTER_LABEL_SIZE.height
        );

        // Username Field
        usernameField = new TextFieldCustom("Enter Username", 30);
        usernameField.setBounds(
                50,
                registerLabel.getY() + 150,
                CommonConstants.TEXT_FIELD_SIZE.width,
                CommonConstants.TEXT_FIELD_SIZE.height
        );

        usernameField.addFocusListener(this);

        // username error label
        usernameErrorLabel = new ErrorLabel("*Invalid: can't be less than 6 characters");
        usernameErrorLabel.setBounds(
                50,
                usernameField.getY() + 50,
                CommonConstants.TEXT_FIELD_SIZE.width,
                CommonConstants.TEXT_FIELD_SIZE.height
        );

        // Password Field
        passwordField = new PasswordFieldCustom("Enter Password", 30);
        passwordField.setBounds(
                50,
                usernameField.getY() + 100,
                CommonConstants.TEXT_FIELD_SIZE.width,
                CommonConstants.TEXT_FIELD_SIZE.height
        );

        passwordField.addFocusListener(this);

        // Password error label
        passwordErrorLabel = new ErrorLabel("*Invalid: Size > 6, 1 Upper and Lower, 1 #, and one special character");
        passwordErrorLabel.setBounds(
                50,
                passwordField.getY() + 50,
                CommonConstants.TEXT_FIELD_SIZE.width,
                CommonConstants.TEXT_FIELD_SIZE.height
        );

        // Confirm Password Field
        confirmPasswordField = new PasswordFieldCustom("Confirm Password", 30);
        confirmPasswordField.setBounds(
                50,
                passwordField.getY() + 100,
                CommonConstants.TEXT_FIELD_SIZE.width,
                CommonConstants.TEXT_FIELD_SIZE.height
        );
        confirmPasswordField.addFocusListener(this);

        // confirm password error label
        confirmPasswordErrorLabel = new ErrorLabel("*Invalid: Password does not match");
        confirmPasswordErrorLabel.setBounds(
                50,
                confirmPasswordField.getY() + 50,
                CommonConstants.TEXT_FIELD_SIZE.width,
                CommonConstants.TEXT_FIELD_SIZE.height
        );

        // Email Field
        emailField = new TextFieldCustom("example@example.com", 50);
        emailField.setBounds(
                50,
                confirmPasswordField.getY() + 100,
                CommonConstants.TEXT_FIELD_SIZE.width,
                CommonConstants.TEXT_FIELD_SIZE.height
        );
        emailField.addFocusListener(this);

        // Email error label
        emailErrorLabel = new ErrorLabel("*Invalid: Not a valid email format");
        emailErrorLabel.setBounds(
                50,
                emailField.getY() + 50,
                CommonConstants.TEXT_FIELD_SIZE.width,
                CommonConstants.TEXT_FIELD_SIZE.height
        );

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBounds(
                50,
                emailField.getY() + 100,
                CommonConstants.BUTTON_SIZE.width,
                CommonConstants.BUTTON_SIZE.height
        );
        registerButton.addActionListener(this);

        // Register --> Login Label
        JLabel loginLabel = new JLabel("Already a user ? Login here.");
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setBounds(
                (CommonConstants.FRAME_SIZE.width - loginLabel.getPreferredSize().width) / 2,
                registerButton.getY() + 100,
                loginLabel.getPreferredSize().width,
                loginLabel.getPreferredSize().height
        );
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginGUI().setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
        });

        // Add to frame
        getContentPane().add(registerLabel);

        // username
        getContentPane().add(usernameField);
        getContentPane().add(usernameErrorLabel);

        // password
        getContentPane().add(passwordField);
        getContentPane().add(passwordErrorLabel);

        // confirm password
        getContentPane().add(confirmPasswordField);
        getContentPane().add(confirmPasswordErrorLabel);

        // email
        getContentPane().add(emailField);
        getContentPane().add(emailErrorLabel);

        getContentPane().add(registerButton);
        getContentPane().add(loginLabel);
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {

    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        Object fieldSource = focusEvent.getSource();
        if (fieldSource == usernameField) {
            // a valid username input should be >= 6
            if (usernameField.getText().length() < 6 || usernameField.isHasPlaceHolderText()) {
                usernameErrorLabel.setVisible(true);
            } else {
                usernameErrorLabel.setVisible(false);
            }
        } else if (fieldSource == passwordField) {
            // if password isn't 6 characters long, has 1 uppercase and 1 lowercase, and a special character -> it is invalid
            String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&-+=()<>~`])(?=\\S+$).{6,30}$";
            Pattern p = Pattern.compile(passwordRegex);
            Matcher m = p.matcher(passwordField.getText());
            if (!m.find()) {
                passwordErrorLabel.setVisible(true);
            } else {
                passwordErrorLabel.setVisible(false);
            }
        } else if (fieldSource == confirmPasswordField) {
            // if passwords does not match -> it is invalid
            if (!passwordField.getText().equals(confirmPasswordField.getText())) {
                confirmPasswordErrorLabel.setVisible(true);
            } else {
                confirmPasswordErrorLabel.setVisible(false);
            }
        } else if (fieldSource == emailField) {
            // checks email if it is in valid format
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern p = Pattern.compile(emailRegex);
            Matcher m = p.matcher(emailField.getText());
            if (!m.find()) {
                emailErrorLabel.setVisible(true);
            } else {
                emailErrorLabel.setVisible(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals("Register")) {
            // validate that there is no error label displayed and ain't no fields in placeholder state
            boolean isValid = !usernameErrorLabel.isVisible() && !passwordErrorLabel.isVisible() && !confirmPasswordErrorLabel.isVisible() && !emailErrorLabel.isVisible() && !usernameField.isHasPlaceHolderText() && !passwordField.isHasPlaceHolderText() && !confirmPasswordField.isHasPlaceHolderText() && !emailField.isHasPlaceHolderText();

            // result dialog
            JDialog resultDialog = new JDialog();
            resultDialog.setSize(CommonConstants.RESULT_DIALOG_SIZE);
            resultDialog.setLocationRelativeTo(this);
            resultDialog.setModal(true);

            // result label
            JLabel resultLabel = new JLabel();
            resultLabel.setPreferredSize(CommonConstants.RESULT_DIALOG_SIZE);
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            resultDialog.add(resultLabel);

            if (isValid) {
                // store the user info into userDB
                String username = usernameField.getText();
                String password = passwordField.getText();
                UserDB.addUser(username, password);

                // show a dialog that displays that the user has been added to the UserDB
                resultLabel.setText("Account Registered!");

                // redirect user back to the login gui (after closing the dialog)
                resultDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dispose();
                        new LoginGUI().setVisible(true);
                    }
                });
            } else {
                // show error label
                resultLabel.setText("Invalid credentials");
            }

            resultDialog.setVisible(true);
        }
    }
}
