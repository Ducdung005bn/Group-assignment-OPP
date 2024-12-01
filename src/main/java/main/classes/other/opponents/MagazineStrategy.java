package main.classes.other.opponents;

import main.classes.main.opponents.Document;
import main.classes.main.opponents.Magazine;

import com.itextpdf.layout.element.Table;

/**
 * Concrete strategy for formatting a Magazine document in PDF.
 */
public class MagazineStrategy implements DocumentStrategy {

    @Override
    public void formatDocumentInTable(Table table, Document document) {
        Magazine magazine = (Magazine) document; // Cast document to Magazine
        table.addCell(magazine.getDocumentTitle());
        table.addCell(magazine.getDocumentAuthor());
        table.addCell("Magazine");
        table.addCell("Subject: " + magazine.getMagazineSubject() +
                ", Frequency: " + magazine.getMagazineFrequency() +
                ", Issue: " + magazine.getMagazineIssueNumb());
        table.addCell(magazine.getDocumentISBN());
    }
}
