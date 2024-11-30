package library.management.system;

import main.classes.Document;
import main.classes.LibraryManagementSystem;
import main.classes.Borrower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowOrRemoveDocumentControllerTest {
    private BorrowOrRemoveDocumentController controller;
    private Document document;
    private LibraryManagementSystem libraryManagementSystem;
    private Borrower borrower;
    // Sample ISBNs (replace with actual test data)
    private static final String DOCUMENT_ISBN_BORROWING = "978-0743273565"; // Example borrowed ISBN
    private static final String DOCUMENT_ISBN = "978-0451524935"; // Example available ISBN
    private static final String DOCUMENT_ISBN_NOT_FOUND = "144454565565";
    @BeforeEach
    void setUp() {
        libraryManagementSystem = new LibraryManagementSystem(); // Initialize the library management system
        controller = new BorrowOrRemoveDocumentController(); // Pass the library system to the controller
        document = libraryManagementSystem.findDocumentByISBN(DOCUMENT_ISBN); // Initialize document
        Borrower borrower = new Borrower();
        libraryManagementSystem.userNumb++;
        libraryManagementSystem.borrowerList.add(borrower);
    }

    @AfterEach
    void tearDown() {
        controller = null;
        document = null;
        libraryManagementSystem = null; // Clean up resources
    }
    @Test
    void testDocumentNotFound() {
        String result = controller.isAbleToBorrow(DOCUMENT_ISBN_NOT_FOUND, libraryManagementSystem);
        assertEquals("The book with ISBN " + DOCUMENT_ISBN_NOT_FOUND + " was not found.", result);
    }
    void testOutOfStock() {
    }

    @Test
    void testAlreadyBorrowed() {
        String result = controller.isAbleToBorrow(DOCUMENT_ISBN_BORROWING, libraryManagementSystem);
        assertEquals("You have already borrowed this book.", result);
    }

    @Test
    void testMaxBorrowLimitReached() {
        // Setup: Simulate that the borrower has reached the maximum borrow limit
        borrower.borrowingBookCount = LibraryManagementSystem.MAX_BORROW_LIMIT; // Assuming MAX_BORROW_LIMIT is defined
        String result = controller.isAbleToBorrow(DOCUMENT_ISBN, libraryManagementSystem);
        assertEquals("You have reached the maximum borrow limit of " + LibraryManagementSystem.MAX_BORROW_LIMIT + " books.", result);
    }

    @Test
    void testValidBorrow() {
        // Setup: Create a document with quantity > 0
        Document document = libraryManagementSystem.findDocumentByISBN(DOCUMENT_ISBN);
        String result = controller.isAbleToBorrow(DOCUMENT_ISBN, libraryManagementSystem);
        assertNull(result, "Should be able to borrow an available document.");
    }
}