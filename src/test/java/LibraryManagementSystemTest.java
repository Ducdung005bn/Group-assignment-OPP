
import main.classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class LibraryManagementSystemTest {

    private LibraryManagementSystem libraryManagementSystem;

    @BeforeEach
    void setUp() {
        libraryManagementSystem = new LibraryManagementSystem();
    }

    @Test
    void testLoadData() {

        File dataFile = new File("libraryData.dat");
        if (dataFile.exists()) {
            dataFile.delete();
        }

        // Load data should initialize an empty library
        libraryManagementSystem.loadData();
        assertEquals(0, libraryManagementSystem.userNumb);
        assertTrue(libraryManagementSystem.bookList.isEmpty());
        assertTrue(libraryManagementSystem.thesisList.isEmpty());
        assertTrue(libraryManagementSystem.magazineList.isEmpty());
        assertTrue(libraryManagementSystem.borrowerList.isEmpty());
        assertTrue(libraryManagementSystem.librarianList.isEmpty());
    }

    @Test
    void testSaveAndLoadData() {
        // Add some test data
        Book book = new Book("1234567890", "Test Book", "Test Author");
        libraryManagementSystem.bookList.add(book);
        libraryManagementSystem.userNumb = 1;


        libraryManagementSystem.saveData();

        // Create a new instance and load data
        LibraryManagementSystem loadedLibrary = new LibraryManagementSystem();
        loadedLibrary.loadData();

        // Check if data is loaded correctly
        assertEquals(1, loadedLibrary.userNumb);
        assertEquals(1, loadedLibrary.bookList.size());
        assertEquals("1234567890", loadedLibrary.bookList.get(0).getDocumentISBN());
    }

    @Test
    void testFindDocumentByISBN() {
        Book book = new Book("1234567890", "Test Book", "Test Author");
        libraryManagementSystem.bookList.add(book);

        Document foundDocument = libraryManagementSystem.findDocumentByISBN("1234567890");
        assertNotNull(foundDocument);
        assertEquals("Test Book", foundDocument.getDocumentTitle());

        Document notFoundDocument = libraryManagementSystem.findDocumentByISBN("0987654321");
        assertNull(notFoundDocument);
    }

    @Test
    void testFindDocumentByTitle() {
        Book book = new Book("1234567890", "Unique Title", "Test Author");
        libraryManagementSystem.bookList.add(book);

        Vector<Document> foundDocuments = libraryManagementSystem.findDocumentByTitle("Unique Title");
        assertNotNull(foundDocuments);
        assertEquals(1, foundDocuments.size());
        assertEquals("Unique Title", foundDocuments.get(0).getDocumentTitle());

        Vector<Document> notFoundDocuments = libraryManagementSystem.findDocumentByTitle("Nonexistent Title");
        assertNull(notFoundDocuments);
    }

    @Test
    void testFindDocumentByAuthor() {
        Book book = new Book("1234567890", "Test Book", "Unique Author");
        libraryManagementSystem.bookList.add(book);

        Vector<Document> foundDocuments = libraryManagementSystem.findDocumentByAuthor("Unique Author");
        assertNotNull(foundDocuments);
        assertEquals(1, foundDocuments.size());
        assertEquals("Unique Author", foundDocuments.get(0).getDocumentAuthor());

        Vector<Document> notFoundDocuments = libraryManagementSystem.findDocumentByAuthor("Nonexistent Author");
        assertNull(notFoundDocuments);
    }

    @Test
    void testFindUser() {
        Borrower borrower = new Borrower(1, "John Doe");
        libraryManagementSystem.borrowerList.add(borrower);

        User foundUser = libraryManagementSystem.findUser(1);
        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getUserName());

        User notFoundUser = libraryManagementSystem.findUser(2);
        assertNull(notFoundUser);
    }
}
