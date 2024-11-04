package library.management.system;

import main.classes.Book;
import main.classes.Document;
import main.classes.LibraryManagementSystem;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import main.classes.Magazine;
import main.classes.Thesis;

public class DisplayDocumentController {
    private JPanel jpnView;
    private JTextField jtfDisplayDocument;
    private LibraryManagementSystem libraryManagementSystem; 

    public DisplayDocumentController(JPanel jpnView, JTextField jtfDisplayDocument, LibraryManagementSystem libraryManagementSystem) {
        this.jpnView = jpnView;
        this.jtfDisplayDocument = jtfDisplayDocument;
        this.libraryManagementSystem = libraryManagementSystem;

        jtfDisplayDocument.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndDisplay();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndDisplay();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndDisplay();
            }
        });
    }
    
    private void searchAndDisplay() {
        String isbnInput = jtfDisplayDocument.getText().trim();
    
        List<Document> matchingDocuments = libraryManagementSystem.getAllDocuments().stream()
                .filter(doc -> doc.getDocumentISBN().startsWith(isbnInput))
                .collect(Collectors.toList());
    
        if (matchingDocuments.size() == 1) {
            Document matchedDocument = matchingDocuments.get(0);
            displayDocumentDetails(matchedDocument);
        } else {
            clearDisplay();
        }
    }
    private void displayDocumentDetails(Document document) {
        jpnView.removeAll();
        jpnView.setLayout(new GridLayout(0, 1));

        Font font = new Font("Arial", Font.PLAIN, 16); 

        JLabel quantityLabel = new JLabel("Quantity: " + document.documentQuantity);
        quantityLabel.setFont(font);
        jpnView.add(quantityLabel);

        JLabel titleLabel = new JLabel("Title: " + document.getDocumentTitle());
        titleLabel.setFont(font);
        jpnView.add(titleLabel);

        JLabel authorLabel = new JLabel("Author: " + document.getDocumentAuthor());
        authorLabel.setFont(font);
        jpnView.add(authorLabel);

        JLabel descriptionLabel = new JLabel("Description: " + document.getDocumentDescription());
        descriptionLabel.setFont(font);
        jpnView.add(descriptionLabel);

        JLabel languageLabel = new JLabel("Language: " + document.getDocumentLanguage());
        languageLabel.setFont(font);
        jpnView.add(languageLabel);

        JLabel pageLabel = new JLabel("Page: " + document.getDocumentPage());
        pageLabel.setFont(font);
        jpnView.add(pageLabel);

        JLabel isbnLabel = new JLabel("ISBN: " + document.getDocumentISBN());
        isbnLabel.setFont(font);
        jpnView.add(isbnLabel);

        if (document instanceof Book) {
            Book book = (Book) document;
            JLabel genreLabel = new JLabel("Genre: " + book.getBookGenre());
            genreLabel.setFont(font);
            jpnView.add(genreLabel);

            JLabel publisherLabel = new JLabel("Publisher: " + book.getBookPublisher());
            publisherLabel.setFont(font);
            jpnView.add(publisherLabel);
        } else if (document instanceof Thesis) {
            Thesis thesis = (Thesis) document;
            JLabel genreLabel = new JLabel("Genre: Thesis");
            genreLabel.setFont(font);
            jpnView.add(genreLabel);
    
            JLabel subjectLabel = new JLabel("Subject: " + thesis.getThesisSubject());
            subjectLabel.setFont(font);
            jpnView.add(subjectLabel);

            JLabel degreeLabel = new JLabel("Degree: " + thesis.getThesisDegree());
            degreeLabel.setFont(font);
            jpnView.add(degreeLabel);

            JLabel universityLabel = new JLabel("University: " + thesis.getThesisUniversity());
            universityLabel.setFont(font);
            jpnView.add(universityLabel);
        } else if (document instanceof Magazine) {
            Magazine magazine = (Magazine) document;
            JLabel genreLabel = new JLabel("Genre: Magazine");
            genreLabel.setFont(font);
            jpnView.add(genreLabel);

            JLabel subjectLabel = new JLabel("Subject: " + magazine.getMagazineSubject());
            subjectLabel.setFont(font);
            jpnView.add(subjectLabel);

            JLabel frequencyLabel = new JLabel("Frequency: " + magazine.getMagazineFrequency());
            frequencyLabel.setFont(font);
            jpnView.add(frequencyLabel);

            JLabel issueNumberLabel = new JLabel("Issue Number: " + magazine.getMagazineIssueNumb());
            issueNumberLabel.setFont(font);
            jpnView.add(issueNumberLabel);
        }

        jpnView.revalidate();
        jpnView.repaint();
    }

    
    private void clearDisplay() {
        jpnView.removeAll();
        jpnView.revalidate();
        jpnView.repaint();
    }
}
