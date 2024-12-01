package main.classes.other.opponents;

import main.classes.main.opponents.Document;

import com.itextpdf.layout.element.Table;

/**
 * Strategy interface to define the formatting behavior of different document types.
 */
public interface DocumentStrategy {
    /**
     * Formats the given document and adds its information to the table.
     *
     * @param table    The table to which the document's information will be added.
     * @param document The document to format.
     */
    void formatDocumentInTable(Table table, Document document);
}


