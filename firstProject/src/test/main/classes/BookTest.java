package main.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@code Book}.
 */
public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        // Initialize a new Book object before each test
        book = new Book();
    }

    @Test
    public void testSetAndGetBookGenre() {
        String genre = "Science Fiction";
        book.setBookGenre(genre);
        assertEquals(genre, book.getBookGenre(), "The book genre should be 'Science Fiction'.");
    }

    @Test
    public void testSetAndGetBookPublisher() {
        String publisher = "Penguin Random House";
        book.setBookPublisher(publisher);
        assertEquals(publisher, book.getBookPublisher(), "The book publisher should be 'Penguin Random House'.");
    }

    @Test
    public void testPrintDocInfo() {
        // Set up the book details
        book.setBookGenre("Fantasy");
        book.setBookPublisher("HarperCollins");

        final String[] output = new String[2];
        Document document = new Document() {
            @Override
            public void printDocInfo() {

                output[0] = "Document Info";
            }
        };

        Book testBook = new Book() {
            @Override
            public void printDocInfo() {
                super.printDocInfo();
                output[1] = "Book Genre: " + getBookGenre() + "\nBook Publisher: " + getBookPublisher();
            }
        };

        testBook.setBookGenre("Fantasy");
        testBook.setBookPublisher("HarperCollins");
        testBook.printDocInfo();

        assertEquals("Document Info", output[0]);
        assertEquals("Book Genre: Fantasy\nBook Publisher: HarperCollins", output[1]);
    }
}