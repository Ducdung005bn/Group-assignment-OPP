package main.classes;
import main.classes.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @AfterEach
    void tearDown() {
        book = null;
    }

    @Test
    void setBookGenre() {
        String genre = "Fiction";
        book.setBookGenre(genre);
        assertEquals(genre, book.getBookGenre(), "Genre should be set correctly");
    }

    @Test
    void setBookPublisher() {
        String publisher = "Penguin Books";
        book.setBookPublisher(publisher);
        assertEquals(publisher, book.getBookPublisher(), "Publisher should be set correctly");
    }

    @Test
    void getBookGenre() {
        String genre = "Non-Fiction";
        book.setBookGenre(genre);
        assertEquals(genre, book.getBookGenre(), "Should return the correct genre");
    }

    @Test
    void getBookPublisher() {
        String publisher = "HarperCollins";
        book.setBookPublisher(publisher);
        assertEquals(publisher, book.getBookPublisher(), "Should return the correct publisher");
    }
}