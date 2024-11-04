package library.management.system;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import main.classes.PendingUser;
import main.classes.LibraryManagementSystem;

public class LogInController {
    private JFrame logInJFrame;
    private JPanel jpnView;
    private JTextField jtfID;
    private JTextField jtfPassword;
    private JButton jbtLogIn;
    private PendingUser pendingUser;
    private LibraryManagementSystem libraryManagementSystem;

    public LogInController(JFrame logInJFrame, JPanel jpnView, JTextField jtfID, JTextField jtfPassword, JButton jbtLogIn, PendingUser pendingUser, LibraryManagementSystem libraryManagementSystem) {
        this.logInJFrame = logInJFrame;
        this.jpnView = jpnView;
        this.jtfID = jtfID;
        this.jtfPassword = jtfPassword;
        this.jbtLogIn = jbtLogIn;
        this.pendingUser = pendingUser;
        this.libraryManagementSystem = libraryManagementSystem;

        jbtLogIn.addActionListener(e -> handleLogIn());
        
    }

    private void handleLogIn() {
        String idText = jtfID.getText().trim();
        String passwordText = jtfPassword.getText().trim();

        if (idText.isEmpty() || passwordText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID and password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userID;
        try {
            userID = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        pendingUser.setPendingUserID(userID);
        pendingUser.setPendingUserPword(passwordText);

        pendingUser.checkIDandPword(libraryManagementSystem);

        if (pendingUser.getIsBorrower() || pendingUser.getIsLibrarian()) {
            JOptionPane.showMessageDialog(null, "Login successful!", "Notification", JOptionPane.INFORMATION_MESSAGE);
            logInJFrame.dispose();
            new StudentJFrame(pendingUser, libraryManagementSystem).setVisible(true);  
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect ID or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
