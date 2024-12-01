package main.classes.other.opponents;

import main.classes.main.opponents.Document;
import main.classes.main.opponents.Book;

import com.itextpdf.layout.element.Table;

/**
 * Concrete strategy for formatting a Book document in PDF.
 */
public class BookStrategy implements DocumentStrategy {
    @Override
    public void formatDocumentInTable(Table table, Document document) {
        Book book = (Book) document; // Cast document to Book
        table.addCell(book.getDocumentTitle());
        table.addCell(book.getDocumentAuthor());
        table.addCell("Book");
        table.addCell("Genre: " + book.getBookGenre() + ", Publisher: " + book.getBookPublisher());
        table.addCell(book.getDocumentISBN());
    }
}
