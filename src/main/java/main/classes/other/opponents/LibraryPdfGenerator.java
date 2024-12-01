package main.classes.other.opponents;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;
import main.classes.main.opponents.Book;
import main.classes.main.opponents.Magazine;
import main.classes.main.opponents.Thesis;
import main.classes.main.opponents.Document;

import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * A utility class to generate a PDF containing information about library documents.
 * This class uses the Strategy design pattern for formatting each type of document.
 */
public class LibraryPdfGenerator {

    /**
     * Generates a PDF file containing all library documents in a tabular format.
     *
     * @param filePath  The file path where the PDF will be saved.
     * @param documents A list of library documents to include in the PDF.
     * @throws FileNotFoundException if the specified file path is invalid.
     */
    public void generateLibraryPdf(String filePath, List<Document> documents) throws FileNotFoundException {
        // Initialize PDF writer and document
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        com.itextpdf.layout.Document pdfDocument = new com.itextpdf.layout.Document(pdf);

        // Add title
        pdfDocument.add(new com.itextpdf.layout.element.Paragraph("Library Documents").setBold().setFontSize(18));

        // Create a table with appropriate headers
        Table table = new Table(new float[]{3, 3, 2, 5, 2}); // Column widths
        table.setWidth(UnitValue.createPercentValue(100));
        addTableHeader(table);

        // Add rows to the table based on the document data
        for (Document doc : documents) {
            DocumentStrategy strategy = selectDocumentStrategy(doc);
            strategy.formatDocumentInTable(table, doc);
        }

        // Add the table to the document and close it
        pdfDocument.add(table);
        pdfDocument.close();
        System.out.println("PDF created successfully at: " + filePath);
    }

    /**
     * Adds headers to the PDF table.
     *
     * @param table The table to which headers are added.
     */
    private void addTableHeader(Table table) {
        String[] headers = {"Title", "Author", "Type", "Details", "ISBN"};
        for (String header : headers) {
            Cell headerCell = new Cell().add(new Paragraph(header).setBold());
            headerCell.setBackgroundColor(DeviceGray.GRAY);
            headerCell.setFontColor(DeviceGray.BLACK);
            table.addHeaderCell(headerCell);
        }
    }

    /**
     * Selects the appropriate formatting strategy based on the document type.
     *
     * @param document The document whose strategy needs to be selected.
     * @return The selected strategy for formatting the document.
     * @throws IllegalArgumentException if the document type is unknown.
     */
    private DocumentStrategy selectDocumentStrategy(Document document) {
        if (document instanceof Book) {
            return new BookStrategy();
        } else if (document instanceof Thesis) {
            return new ThesisStrategy();
        } else if (document instanceof Magazine) {
            return new MagazineStrategy();
        } else {
            throw new IllegalArgumentException("Unknown document type: " + document.getClass().getName());
        }
    }
}
