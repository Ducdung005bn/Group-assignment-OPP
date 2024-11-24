package main.classes;
import main.classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibrarianTest {

    private Librarian librarian;
    private LibraryManagementSystem libraryManagementSystem;

    @BeforeEach
    void setUp() {
        // Create a mock of the LibraryManagementSystem
        libraryManagementSystem = Mockito.mock(LibraryManagementSystem.class);
        // Instantiate the Librarian with the mocked LibraryManagementSystem
        librarian = new Librarian(libraryManagementSystem);
    }

    @Test
    void testAddDocument() {
        Book newBook = new Book("1234567890", "New Book", "Author Name");


        librarian.addDocument(newBook);


        verify(libraryManagementSystem).addDocument(newBook);
    }

    @Test
    void testRemoveDocument() {
        String isbn = "1234567890";


        librarian.removeDocument(isbn);


        verify(libraryManagementSystem).removeDocument(isbn);
    }

    @Test
    void testUpdateDocument() {
        Book updatedBook = new Book("1234567890", "Updated Title", "Updated Author");


        librarian.updateDocument(updatedBook);

        verify(libraryManagementSystem).updateDocument(updatedBook);
    }
}