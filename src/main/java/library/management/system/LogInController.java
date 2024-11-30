package library.management.system;

import javax.swing.*;

import main.classes.*;

import java.awt.*;

/**
 * Controller class for handling the login process in the library management system.
 * It manages the login JFrame, user input fields, and performs user authentication.
 */
public class LogInController {
    public static Borrower borrower;
    public static Librarian librarian;
    private PendingUser pendingUser;
    private JFrame logInJFrame;
    private JPanel jpnView;
    private JTextField jtfID;
    private JTextField jtfPassword;
    private JButton jbtLogIn;
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

        //Forget Password handle
        forgetPasswordHandle();

        // Add action listener for the login button.
        jbtLogIn.addActionListener(e -> handleLogIn());
    }

    private void forgetPasswordHandle() {
        // Create a JCheckBox for the "Forget Password" option.
        JCheckBox chkForgetPassword = new JCheckBox("Forget Password");
        chkForgetPassword.setBounds(20, 190, 125, 20);
        chkForgetPassword.setBackground(Color.GREEN);
        jpnView.add(chkForgetPassword);

        // Create a JTextField for entering the email address (hidden by default).
        JTextField jtfEmail = new JTextField();
        jtfEmail.setBounds(170, 190, 200, 25);
        jtfEmail.setText("Enter your email address here");
        jtfEmail.setVisible(false); // Initially hidden.
        jpnView.add(jtfEmail);

        // Create a JButton to send the password (hidden by default).
        JButton jbtSendPassword = new JButton("Send");
        jbtSendPassword.setBounds(400, 190, 75, 25);
        jbtSendPassword.setBackground(Color.GREEN);
        jbtSendPassword.setVisible(false); // Initially hidden.
        jpnView.add(jbtSendPassword);

        // Add an action listener to the checkbox to toggle visibility of email and button.
        chkForgetPassword.addActionListener(e -> {
            boolean isSelected = chkForgetPassword.isSelected();
            jtfEmail.setVisible(isSelected);
            jbtSendPassword.setVisible(isSelected);

            jpnView.revalidate();
            jpnView.repaint();
        });

        // Add an action listener to the "Send Password" button.
        jbtSendPassword.addActionListener(e -> {
            String email = jtfEmail.getText().trim(); // Get the entered email address.

            // Validate that the email field is not empty.
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your email.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String userPassword = libraryManagementSystem.findUserPasswordByEmail(email);
            int userID = libraryManagementSystem.findUserIDByEmail(email);

            if (userPassword == null && userID == 0) {
                JOptionPane.showMessageDialog(null, "No email address found in the system.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Send email for password recovery
            EmailSender emailSender = new EmailSender();
            String recipientEmail = email;
            String subject = "Password Recovery";
            String messageText = createMessageText(recipientEmail, userID, userPassword);
            emailSender.sendEmail(recipientEmail, subject, messageText);
        });

        //Load the image to the background
        ImageIcon imageIcon = new ImageIcon("C:\\Test\\background.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, 600, 345);
        jpnView.add(imageLabel);

        jpnView.revalidate();
        jpnView.repaint();
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
            borrower = (Borrower) libraryManagementSystem.findUserByID(pendingUser.getPendingUserID());
            new StudentJFrame(borrower, libraryManagementSystem).setVisible(true); // Open the borrower interface.
        } else if (pendingUser.getIsLibrarian()) {
            // Successful login as a librarian.
            JOptionPane.showMessageDialog(null, "Login successful!", "Notification", JOptionPane.INFORMATION_MESSAGE);
            logInJFrame.dispose(); // Close the login window.
            librarian = (Librarian) libraryManagementSystem.findUserByID(pendingUser.getPendingUserID());
            new LibrarianJFrame(librarian, libraryManagementSystem).setVisible(true); // Open the librarian interface.
        } else {
            // Failed login due to incorrect ID or password.
            JOptionPane.showMessageDialog(null, "Incorrect ID or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String createMessageText(String recipientName, int userID, String userPassword) {
        String message = "Dear " + recipientName + ",\n\n"
                + "We are reaching out to you regarding your recent request.\n\n"
                + "Your ID is: " + userID + "\n\n"
                + "Your password is: " + userPassword + "\n\n"
                + "Thank you for your attention.\n"
                + "Best regards,\n"
                + "Library Management System";
        return message;
    }
}
