import custom.PasswordFieldCustom;
import custom.TextFieldCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JFrame implements ActionListener {
    private TextFieldCustom usernameField;
    private PasswordFieldCustom passwordField;
    private Font customFont;

    public LoginGUI() {
        super("Login");
        setSize(CommonConstants.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);

        // create custom font
        customFont = CustomTools.createFont(CommonConstants.FONT_PATH);

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
        usernameField.setFont(customFont.deriveFont(32f));
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(Color.WHITE);
        usernameField.setBounds(
                50,
                loginImage.getY() + 315,
                CommonConstants.TEXT_FIELD_SIZE.width,
                CommonConstants.TEXT_FIELD_SIZE.height

        );

        // Password Field
        passwordField = new PasswordFieldCustom("Enter Password", 30);
        passwordField.setFont(customFont.deriveFont(32f));
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(Color.WHITE);
        passwordField.setBounds(
                50,
                usernameField.getY() + 100,
                CommonConstants.TEXT_FIELD_SIZE.width,
                CommonConstants.TEXT_FIELD_SIZE.height
        );

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(customFont.deriveFont(32f));
        loginButton.setBackground(CommonConstants.BUTTON_COLOR);
        loginButton.setForeground(Color.WHITE);
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
        registerLabel.setFont(customFont.deriveFont(32f));
        registerLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setBounds(
                (CommonConstants.FRAME_SIZE.width - registerLabel.getPreferredSize().width) / 2,
                loginButton.getY() + 100,
                registerLabel.getPreferredSize().width,
                registerLabel.getPreferredSize().height
        );
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new RegisterGUI().setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                registerLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerLabel.setForeground(CommonConstants.SECONDARY_COLOR);
            }
        });

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
            resultDialog.getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);

            // Create label (Display result)
            JLabel resultLabel = new JLabel();
            resultLabel.setFont(customFont.deriveFont(26f));
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            resultLabel.setForeground(CommonConstants.SECONDARY_COLOR);
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
