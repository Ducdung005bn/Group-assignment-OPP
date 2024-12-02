import library.management.system.*;
import main.classes.other.opponents.*;
import main.classes.main.opponents.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogInControllerTest {
    private Borrower borrower;
    private Librarian librarian;
    private PendingUser pendingUser;
    private JFrame logInJFrame;
    private JPanel jpnView;
    private JTextField jtfID;
    private JTextField jtfPassword;
    private JButton jbtLogIn;
    private LibraryManagementSystem libraryManagementSystem;
    private LogInController logInController;

    @BeforeEach
    void setUp() {
        libraryManagementSystem = TestDataInitializer.initializeLibraryData();

        // Create GUI components for testing
        logInJFrame = new JFrame();
        jpnView = new JPanel();
        jtfID = new JTextField(20);
        jtfPassword = new JTextField(20);
        jbtLogIn = new JButton("Log In");

        // Initialize the LogInController with the created components
        logInController = new LogInController(logInJFrame, jpnView, jtfID, jtfPassword, jbtLogIn, new PendingUser(), libraryManagementSystem);

        // Add components to the frame
        jpnView.add(jtfID);
        jpnView.add(jtfPassword);
        jpnView.add(jbtLogIn);
        logInJFrame.add(jpnView);
        logInJFrame.pack();
        logInJFrame.setVisible(true);
    }


    @AfterEach
    void tearDown() {
        logInJFrame.dispose(); // Dispose of the frame after each test
    }

    @Test
    void testSuccessfulLoginAsBorrower() {
        // Assuming you have a borrower with ID 1 and password "password"
        jtfID.setText("1");
        jtfPassword.setText("password");

        // Simulate button click
        jbtLogIn.doClick();

        // Check if the borrower interface is opened (you may need to implement a way to verify this)
        // This can include verifying that the correct JFrame is displayed or that the state has changed appropriately
        assertNotNull(logInController.borrower); // Ensure the borrower is set
    }

    @Test
    void testSuccessfulLoginAsLibrarian() {
        // Assuming you have a librarian with ID 2 and password "password"
        jtfID.setText("2");
        jtfPassword.setText("password");

        // Simulate button click
        jbtLogIn.doClick();

        // Check if the librarian interface is opened
        assertNotNull(logInController.librarian); // Ensure the librarian is set
    }

    @Test
    void testLoginWithInvalidID() {
        jtfID.setText("999"); // Assuming this ID does not exist
        jtfPassword.setText("anyPassword");

        // Simulate button click
        jbtLogIn.doClick();

        // You could verify that an error message is shown (you may need to mock JOptionPane)
        // This might require additional setup to intercept the JOptionPane calls
    }

    @Test
    void testLoginWithEmptyFields() {
//        jtfID.setText("");
//        jtfPassword.setText("");

        // Simulate button click
        jbtLogIn.doClick();

        // Verify that an error message is shown
        // Again, this might require intercepting JOptionPane calls
    }

    @Test
    void testLoginWithInvalidPassword() {
        jtfID.setText("1"); // Assuming this ID exists
        jtfPassword.setText("wrongPassword");

        // Simulate button click
        jbtLogIn.doClick();

        // Verify that an error message is shown
    }
}
