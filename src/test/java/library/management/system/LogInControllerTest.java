package library.management.system;

import main.classes.Borrower;
import main.classes.Librarian;
import main.classes.LibraryManagementSystem;
import main.classes.PendingUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

    // Biến để theo dõi thông báo lỗi
    private boolean errorMessageShown;
    private String errorMessageContent;

    @BeforeEach
    void setUp() throws Exception {
        // Khởi tạo như bình thường
        libraryManagementSystem = TestDataInitializer.initializeLibraryData();

        logInJFrame = new JFrame();
        jpnView = new JPanel();
        jtfID = new JTextField(20);
        jtfPassword = new JTextField(20);
        jbtLogIn = new JButton("Log In");

        // Khởi tạo LogInController
        logInController = new LogInController(
                logInJFrame,
                jpnView,
                jtfID,
                jtfPassword,
                jbtLogIn,
                new PendingUser(),
                libraryManagementSystem
        );

        // Thêm các component
        jpnView.add(jtfID);
        jpnView.add(jtfPassword);
        jpnView.add(jbtLogIn);
        logInJFrame.add(jpnView);
        logInJFrame.pack();
        logInJFrame.setVisible(true);

        // Chuẩn bị để bắt thông báo lỗi
        errorMessageShown = false;
        errorMessageContent = null;

        // Thay thế phương thức showMessageDialog của JOptionPane
        replaceJOptionPaneShowMessageDialog();
    }

    // Phương thức để thay thế showMessageDialog
    private void replaceJOptionPaneShowMessageDialog() throws Exception {
        // Sử dụng reflection để thay đổi phương thức showMessageDialog
        Field showMessageDialogField = JOptionPane.class.getDeclaredField("showMessageDialog");
        showMessageDialogField.setAccessible(true);

        // Thay thế bằng một phương thức custom để bắt thông báo
        showMessageDialogField.set(null, (java.util.function.BiFunction<Component, Object, Void>) (parentComponent, message) -> {
            errorMessageShown = true;
            errorMessageContent = message.toString();
            return null;
        });
    }

    @AfterEach
    void tearDown() {
        logInJFrame.dispose();
    }

    @Test
    void testSuccessfulLoginAsBorrower() {
        // Đăng nhập với tài khoản borrower hợp lệ
        jtfID.setText("1");
        jtfPassword.setText("password");

        // Simulate button click
        jbtLogIn.doClick();

        // Kiểm tra đăng nhập thành công
        assertNotNull(logInController.borrower);
        assertNull(logInController.librarian);
        assertFalse(errorMessageShown);
    }

    @Test
    void testSuccessfulLoginAsLibrarian() {
        // Đăng nhập với tài khoản librarian hợp lệ
        jtfID.setText("2");
        jtfPassword.setText("password");

        // Simulate button click
        jbtLogIn.doClick();

        // Kiểm tra đăng nhập thành công
        assertNotNull(logInController.librarian);
        assertNull(logInController.borrower);
        assertFalse(errorMessageShown);
    }

    @Test
    void testLoginWithInvalidID() {
        // Đăng nhập với ID không tồn tại
        jtfID.setText("999");
        jtfPassword.setText("anyPassword");

        // Simulate button click
        jbtLogIn.doClick();

        // Kiểm tra hiển thị thông báo lỗi
        assertTrue(errorMessageShown);
        assertEquals("Invalid User ID", errorMessageContent);
        assertNull(logInController.borrower);
        assertNull(logInController.librarian);
    }

    @Test
    void testLoginWithEmptyFields() {
        // Để trống cả ID và password
        jtfID.setText("");
        jtfPassword.setText("");

        // Simulate button click
        jbtLogIn.doClick();

        // Kiểm tra hiển thị thông báo lỗi
        assertTrue(errorMessageShown);
        assertEquals("Please enter User ID and Password", errorMessageContent);
        assertNull(logInController.borrower);
        assertNull(logInController.librarian);
    }

    @Test
    void testLoginWithInvalidPassword() {
        // Đăng nhập với password sai
        jtfID.setText("1");
        jtfPassword.setText("wrongPassword");

        // Simulate button click
        jbtLogIn.doClick();

        // Kiểm tra hiển thị thông báo lỗi
        assertTrue(errorMessageShown);
        assertEquals("Invalid Password", errorMessageContent);
        assertNull(logInController.borrower);
        assertNull(logInController.librarian);
    }
}
