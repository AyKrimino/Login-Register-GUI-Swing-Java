import custom.PasswordFieldCustom;
import custom.TextFieldCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener {
    private TextFieldCustom usernameField;
    private PasswordFieldCustom passwordField;

    public LoginGUI() {
        super("Login");
        setSize(CommonConstants.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        // Login image
        JLabel loginImage = CustomTools.loadImage(CommonConstants.LOGIN_IMAGE_PATH);
        loginImage.setBounds(
                (CommonConstants.FRAME_SIZE.width - loginImage.getPreferredSize().width) / 2,
                25,
                CommonConstants.LOGIN_IMAGE_SIZE.width,
                CommonConstants.LOGIN_IMAGE_SIZE.height
        );

        // Username Field
        usernameField = new TextFieldCustom("Enter Username", 30);
        usernameField.setBounds(
                50,
                loginImage.getY() + 315,
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

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(
                50,
                passwordField.getY() + 100,
                CommonConstants.BUTTON_SIZE.width,
                CommonConstants.BUTTON_SIZE.height
        );
        loginButton.addActionListener(this);

        // login register label
        JLabel registerLabel = new JLabel("Not registered ? Click Here!");
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setBounds(
                (CommonConstants.FRAME_SIZE.width - registerLabel.getPreferredSize().width) / 2,
                loginButton.getY() + 100,
                registerLabel.getPreferredSize().width,
                registerLabel.getPreferredSize().height
        );

        // add to frame
        getContentPane().add(loginImage);
        getContentPane().add(usernameField);
        getContentPane().add(passwordField);
        getContentPane().add(loginButton);
        getContentPane().add(registerLabel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equalsIgnoreCase("Login")) {
            // create a dialog box
            JDialog resultDialog = new JDialog();
            resultDialog.setPreferredSize(CommonConstants.RESULT_DIALOG_SIZE);
            resultDialog.pack();
            resultDialog.setLocationRelativeTo(this); // Center the dialog on the screen
            resultDialog.setModal(true); // This makes the dialog modal, meaning that while the dialog is open, the user cannot interact with the main LoginGUI window. The user must first close the dialog before returning to the main interface.

            // Create label (Display result)
            JLabel resultLabel = new JLabel();
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            resultDialog.add(resultLabel);

            // Retrieve Entered credentials
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Validate Credentials in UserDB
            if (UserDB.userDB.get(username) != null) {
                // checks password
                String validPass = UserDB.userDB.get(username);
                if (password.equals(validPass)) {
                    // Display a success dialog
                    resultLabel.setText("Login Successful!");
                } else {
                    // Display an incorrect password dialog
                    resultLabel.setText("Invalid Password!");
                }
            } else {
                // Display Incorrect username dialog
                resultLabel.setText("Username Not Found!");
            }
            resultDialog.setVisible(true);
        }
    }
}
