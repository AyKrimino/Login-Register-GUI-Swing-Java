import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // ensures that the GUI is created and managed on the Event Dispatch Thread, which is essential for thread safety in Swing.
            @Override
            public void run() {
                //new LoginGUI().setVisible(true);
                new RegisterGUI().setVisible(true);
            }
        });
    }
}
