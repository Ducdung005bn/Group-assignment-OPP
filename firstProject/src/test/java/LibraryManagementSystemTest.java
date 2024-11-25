import main.classes.*;
import java.io.*;
import java.util.Vector;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class LibraryManagementSystemTest {

    private LibraryManagementSystem library;

    @BeforeEach
    void setUp() {
        library = new LibraryManagementSystem();

        // Add sample documents without using constructors
        addSampleDocuments();

        // Add sample users
        addSampleUsers();
    }

    @AfterEach
    void tearDown() {
        library = null;
    }

    private void addSampleDocuments() {
        // Create documents and set attributes using setter methods
        Book book1 = new Book();
        book1.setDocumentISBN("1234567890");
        book1.setDocumentTitle("Book Title 1");
        book1.setDocumentAuthor("Author A");

        Book book2 = new Book();
        book2.setDocumentISBN("0987654321");
        book2.setDocumentTitle("Book Title 2");
        book2.setDocumentAuthor("Author B");

        Thesis thesis = new Thesis();
        thesis.setDocumentISBN("1122334455");
        thesis.setDocumentTitle("Thesis Title 1");
        thesis.setDocumentAuthor("Author C");

        Magazine magazine = new Magazine();
        magazine.setDocumentISBN("5566778899");
        magazine.setDocumentTitle("Magazine Title 1");
        magazine.setDocumentAuthor("Author D");

        // Add to the list
        library.bookList.add(book1);
        library.bookList.add(book2);
        library.thesisList.add(thesis);
        library.magazineList.add(magazine);
    }

    private void addSampleUsers() {
        // Create and set information for a borrower
        Borrower borrower = new Borrower();
        borrower.setUserID(1);
        borrower.setUserPassword("password");
        borrower.setUserName("User 1");
        borrower.setUserDateOfBirth(new Date()); // Assume the current date as the date of birth
        borrower.setUserPhoneNumb("123-456-7890");
        borrower.setIsStudent(true);
        borrower.setOverdueCount(0);
        library.borrowerList.add(borrower); // Add borrower to the list

        // Create and set information for a librarian
        Librarian librarian = new Librarian();
//      librarian.setUserID(999);
        librarian.setUserPassword("password");
        librarian.setUserName("Librarian 1");
        librarian.setUserDateOfBirth(new Date()); // Assume the current date as the date of birth
        librarian.setUserPhoneNumb("098-765-4321");
        librarian.setLibrarianSalary(250);
        library.librarianList.add(librarian); // Add librarian to the list
    }

    @Test
    void findDocumentByISBN_existingISBN() {
        Document doc = library.findDocumentByISBN("1234567890");
        assertNotNull(doc, "Document should be found by existing ISBN.");
        assertTrue(doc instanceof Book, "Document should be an instance of Book.");
        assertEquals("Book Title 1", doc.getDocumentTitle(), "Expected title to match.");
    }

    @Test
    void findDocumentByISBN_nonExistingISBN() {
        Document doc = library.findDocumentByISBN("0000000000");
        assertNull(doc, "Document should not be found by non-existing ISBN.");
    }

    @Test
    void findDocumentByTitle_existingTitle() {
        Vector<Document> docs = library.findDocumentByTitle("Book Title 1");
        assertNotNull(docs, "Documents should be found by existing title.");
        assertEquals(1, docs.size(), "Expected one document to match.");
        assertTrue(docs.get(0) instanceof Book, "Expected document to be a Book.");
        assertEquals("Book Title 1", docs.get(0).getDocumentTitle(), "Expected title to match.");
    }

    @Test
    void findDocumentByTitle_nonExistingTitle() {
        Vector<Document> docs = library.findDocumentByTitle("Non-Existing Title");
        assertNull(docs, "Documents should not be found by non-existing title.");
    }

    @Test
    void findDocumentByAuthor_existingAuthor() {
        Vector<Document> docs = library.findDocumentByAuthor("Author A");
        assertNotNull(docs, "Documents should be found by existing author.");
        assertEquals(1, docs.size(), "Expected one document to match.");
        assertTrue(docs.get(0) instanceof Book, "Expected document to be a Book.");
        assertEquals("Author A", docs.get(0).getDocumentAuthor(), "Expected author to match.");
    }

    @Test
    void findDocumentByAuthor_nonExistingAuthor() {
        Vector<Document> docs = library.findDocumentByAuthor("Non-Existing Author");
        assertNull(docs, "Documents should not be found by non-existing author.");
    }

    @Test
    void findUser_existingUserID() {
        User user = library.findUser(1);
        assertNotNull(user, "User should be found by existing ID.");
        assertEquals("User 1", user.getUserName(), "Expected user name to match.");
    }

    @Test
    void findUser_nonExistingUserID() {
        User user = library.findUser(999);
        assertNull(user, "User should not be found by non-existing ID.");
    }

    // Edge case tests
    @Test
    void findDocumentByISBN_edgeCase_emptyISBN() {
        Document doc = library.findDocumentByISBN("");
        assertNull(doc, "Document should not be found by empty ISBN.");
    }

    @Test
    void findDocumentByTitle_edgeCase_emptyTitle() {
        Vector<Document> docs = library.findDocumentByTitle("");
        assertNull(docs, "Documents should not be found by empty title.");
    }

    @Test
    void findDocumentByAuthor_edgeCase_emptyAuthor() {
        Vector<Document> docs = library.findDocumentByAuthor("");
        assertNull(docs, "Documents should not be found by empty author.");
    }

    @Test
    void findUser_edgeCase_negativeUserID() {
        User user = library.findUser(-1);
        assertNull(user, "User should not be found by negative ID.");
    }
}
