package main.classes.other.opponents;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.FileNotFoundException;

/**
 * A builder class to generate a PDF document using iText.
 * It supports adding titles, paragraphs, and table rows.
 */
public class PdfDocumentBuilder {
    private PdfWriter writer;
    private PdfDocument pdf;
    private Document document;

    public PdfDocumentBuilder(String filePath) throws FileNotFoundException {
        this.writer = new PdfWriter(filePath);
        this.pdf = new PdfDocument(writer);
        this.document = new Document(pdf);
    }

    /**
     * Adds a title to the document.
     *
     * @param title The title text
     * @return The builder instance for method chaining
     */
    public PdfDocumentBuilder addTitle(String title) {
        document.add(new Paragraph(title).setBold().setFontSize(18));
        return this;
    }

    /**
     * Adds a table row to the document.
     *
     * @param rowData The array of strings to be added as a table row
     * @return The builder instance for method chaining
     */
    public PdfDocumentBuilder addTableRow(String[] rowData) {
        Table table = new Table(rowData.length);
        for (String data : rowData) {
            table.addCell(data);
        }
        document.add(table);
        return this;
    }

    /**
     * Closes the document after the content has been added.
     */
    public void close() {
        document.close();
    }
}
