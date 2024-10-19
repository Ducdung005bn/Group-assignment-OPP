/**
 * The {@code Book} class is a subclass of {@code Document} that represents a book
 * with additional properties such as genre and publisher.
 * <p>
 * It inherits all the properties of the {@code Document} class, and provides
 * additional methods to set and retrieve the genre and publisher of the book.
 * </p>
 */
public class Book extends Document {
    private String bookGenre;
    private String bookPublisher;



    /**
     * Overrides the {@code printDocInfo} method from the {@code Document} class
     * to print additional information specific to a book, such as its genre and publisher.
     */
    @Override
    public void printDocInfo() {
        super.printDocInfo();
        System.out.println("Book Genre: " + bookGenre);
        System.out.println("Book Publisher: " + bookPublisher);
    }

    /**
     * Gets the genre of the book.
     *
     * @return the genre of the book
     */
    public String getBookGenre() {
        return bookGenre;
    }

    /**
     * Sets the genre of the book.
     *
     * @param bookGenre the genre to be set
     */
    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    /**
     * Gets the publisher of the book.
     *
     * @return the publisher of the book
     */
    public String getBookPublisher() {
        return bookPublisher;
    }

    /**
     * Sets the publisher of the book.
     *
     * @param bookPublisher the publisher to be set
     */
    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
}
