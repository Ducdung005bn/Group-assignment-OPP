package library.management.system;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import main.classes.Borrower;
import main.classes.Librarian;
import main.classes.PendingUser;
import main.classes.LibraryManagementSystem;

/**
 * Controller class for handling the login process in the library management system.
 * It manages the login JFrame, user input fields, and performs user authentication.
 */
public class LogInController {
    private JFrame logInJFrame;
    private JPanel jpnView;
    private JTextField jtfID;
    private JTextField jtfPassword;
    private JButton jbtLogIn;
    private PendingUser pendingUser;
    private LibraryManagementSystem libraryManagementSystem;

    /**
     * Constructs the LogInController with the provided components and sets up the login action.
     *
     * @param logInJFrame            the JFrame for the login interface
     * @param jpnView                the JPanel to display components
     * @param jtfID                  the JTextField for user ID input
     * @param jtfPassword            the JTextField for user password input
     * @param jbtLogIn               the JButton to handle login
     * @param pendingUser            the PendingUser object for temporary credential storage
     * @param libraryManagementSystem the LibraryManagementSystem instance for user validation
     */
    public LogInController(JFrame logInJFrame, JPanel jpnView, JTextField jtfID, JTextField jtfPassword, JButton jbtLogIn, PendingUser pendingUser, LibraryManagementSystem libraryManagementSystem) {
        this.logInJFrame = logInJFrame;
        this.jpnView = jpnView;
        this.jtfID = jtfID;
        this.jtfPassword = jtfPassword;
        this.jbtLogIn = jbtLogIn;
        this.pendingUser = pendingUser;
        this.libraryManagementSystem = libraryManagementSystem;

        // Add action listener for the login button.
        jbtLogIn.addActionListener(e -> handleLogIn());
    }

    /**
     * Handles the login process when the login button is clicked.
     * Validates input, authenticates the user, and opens the appropriate interface.
     */
    private void handleLogIn() {
        // Retrieve user input from text fields.
        String idText = jtfID.getText().trim();
        String passwordText = jtfPassword.getText().trim();

        // Validate that both fields are not empty.
        if (idText.isEmpty() || passwordText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID and password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userID;
        try {
            // Parse the user ID to ensure it's a valid integer.
            userID = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Set the pending user's credentials for verification.
        pendingUser.setPendingUserID(userID);
        pendingUser.setPendingUserPword(passwordText);

        // Check the credentials against the library management system.
        pendingUser.checkIDandPword(libraryManagementSystem);

        // Determine the result of the login attempt.
        if (pendingUser.getIsBorrower()) {
            // Successful login as a borrower.
            JOptionPane.showMessageDialog(null, "Login successful!", "Notification", JOptionPane.INFORMATION_MESSAGE);
            logInJFrame.dispose(); // Close the login window.
            Borrower borrower = (Borrower) libraryManagementSystem.findUser(pendingUser.getPendingUserID());
            new StudentJFrame(borrower, libraryManagementSystem).setVisible(true); // Open the borrower interface.
        } else if (pendingUser.getIsLibrarian()) {
            // Successful login as a librarian.
            JOptionPane.showMessageDialog(null, "Login successful!", "Notification", JOptionPane.INFORMATION_MESSAGE);
            logInJFrame.dispose(); // Close the login window.
            Librarian librarian = (Librarian) libraryManagementSystem.findUser(pendingUser.getPendingUserID());
            new LibrarianJFrame(librarian, libraryManagementSystem).setVisible(true); // Open the librarian interface.
        } else {
            // Failed login due to incorrect ID or password.
            JOptionPane.showMessageDialog(null, "Incorrect ID or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
