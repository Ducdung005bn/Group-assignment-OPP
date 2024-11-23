package main.classes;

import java.io.Serializable;

/**
 * The {@code Book} class is a subclass of {@code Document} that represents a book
 * with additional properties such as genre and publisher.
 * <p>
 * It inherits all the properties of the {@code Document} class, and provides
 * additional methods to set and get the genre and publisher of the book.
 * </p>
 */
public class Book extends Document implements Serializable {
    private String bookGenre;
    private String bookPublisher;

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }
}
