package main.classes.other.opponents;

import main.classes.main.opponents.Document;
import main.classes.main.opponents.Thesis;

import com.itextpdf.layout.element.Table;

/**
 * Concrete strategy for formatting a Thesis document in PDF.
 */
public class ThesisStrategy implements DocumentStrategy {

    @Override
    public void formatDocumentInTable(Table table, Document document) {
        Thesis thesis = (Thesis) document; // Cast document to Thesis
        table.addCell(thesis.getDocumentTitle());
        table.addCell(thesis.getDocumentAuthor());
        table.addCell("Thesis");
        table.addCell("Subject: " + thesis.getThesisSubject() + ", Degree: " + thesis.getThesisDegree() +
                ", University: " + thesis.getThesisUniversity());
        table.addCell(thesis.getDocumentISBN());
    }
}