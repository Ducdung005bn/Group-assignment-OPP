import library.management.system.*;
import main.classes.other.opponents.*;
import main.classes.main.opponents.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static org.junit.jupiter.api.Assertions.*;

class BorrowOrRemoveDocumentControllerTest {
    private BorrowOrRemoveDocumentController controller;
    private JPanel jpnView;
    private JTextField jtfBorrowOrRemoveDocument;
    private JButton jbtBorrowOrRemoveDocument;
    private Borrower borrower;
    private LibraryManagementSystem libraryManagementSystem;
    private Document document;

    private static final String DOCUMENT_ISBN_BORROWING = "978-0743273565"; // Example borrowed ISBN
    private static final String DOCUMENT_ISBN = "9780061120084"; // Example available ISBN
    private static final String DOCUMENT_ISBN_NOT_FOUND = "144454565565";
    private static final int USER_ID = 5;


    @BeforeEach
    void setUp() {
        libraryManagementSystem = TestDataInitializer.initializeLibraryData();
        jpnView = new JPanel(); // Khởi tạo JPanel
        jtfBorrowOrRemoveDocument = new JTextField();
        jbtBorrowOrRemoveDocument = new JButton();
        borrower = (Borrower) libraryManagementSystem.findUserByID(USER_ID);
        controller = new BorrowOrRemoveDocumentController(jpnView, jtfBorrowOrRemoveDocument, jbtBorrowOrRemoveDocument, borrower, libraryManagementSystem);
    }
    @AfterEach
    void tearDown() {
        controller = null;
        document = null;
        libraryManagementSystem = null; // Clean up resources
    }
    @Test
    void testDocumentNotFound() {
        jtfBorrowOrRemoveDocument.setText(DOCUMENT_ISBN_NOT_FOUND);
        String result = controller.isAbleToBorrow(DOCUMENT_ISBN_NOT_FOUND, libraryManagementSystem);
        assertEquals("The book with ISBN " + DOCUMENT_ISBN_NOT_FOUND + " was not found.", result);
    }

    @Test
    void testMaxBorrowLimitReached() {
        // Setup: Simulate that the borrower has reached the maximum borrow limit
        borrower.borrowingBookCount = LibraryManagementSystem.MAX_BORROW_LIMIT; // Assuming MAX_BORROW_LIMIT is defined
        String result = controller.isAbleToBorrow(DOCUMENT_ISBN, libraryManagementSystem);
        assertEquals("You have reached the maximum borrow limit of " + LibraryManagementSystem.MAX_BORROW_LIMIT + " books.", result);
    }
    @Test
    void testOutOfStock() {

    }
    @Test
    void testValidBorrow() {
        // Setup: Create a document with quantity > 0
        Document document = libraryManagementSystem.findDocumentByISBN(DOCUMENT_ISBN);
        String result = controller.isAbleToBorrow(DOCUMENT_ISBN, libraryManagementSystem);
        assertNull(result, "Should be able to borrow an available document.");
    }
}