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

public class DisplayOrUpdateDocumentController {
    private JPanel jpnView;
    private JTextField jtfDisplayOrUpdateDocument;
    private LibraryManagementSystem libraryManagementSystem; 
    private String kind;

    public DisplayOrUpdateDocumentController(String kind, JPanel jpnView, JTextField jtfDisplayOrUpdateDocument, LibraryManagementSystem libraryManagementSystem) {
        this.kind = kind;
        this.jpnView = jpnView;
        this.jtfDisplayOrUpdateDocument = jtfDisplayOrUpdateDocument;
        this.libraryManagementSystem = libraryManagementSystem;

        jtfDisplayOrUpdateDocument.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndDisplayOrUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndDisplayOrUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndDisplayOrUpdate();
            }
        });
    }
    
    private void searchAndDisplayOrUpdate() {
        String isbnInput = jtfDisplayOrUpdateDocument.getText().trim();
    
        List<Document> matchingDocuments = libraryManagementSystem.getAllDocuments().stream()
                .filter(doc -> doc.getDocumentISBN().startsWith(isbnInput))
                .collect(Collectors.toList());
    
        if (matchingDocuments.size() == 1) {
            Document matchedDocument = matchingDocuments.get(0);
            displayOrUpdateDocumentDetails(matchedDocument);
        } else {
            clearDisplay();
        }
    }
    private void displayOrUpdateDocumentDetails(Document document) {
        jpnView.removeAll();
        jpnView.setLayout(new GridLayout(0, 1));
        
        JButton updateButton = new JButton("UPDATE");
        if (kind.equals("update")){
            jpnView.add(updateButton);
        }
           
        Font font = new Font("Arial", Font.PLAIN, 16); 
        
        // Shared fields
        JLabel quantityLabel = new JLabel("Quantity: ");
        JTextField quantityField = new JTextField(String.valueOf(document.documentQuantity));
        jpnView.add(quantityLabel);
        jpnView.add(quantityField);

        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField(document.getDocumentTitle());
        jpnView.add(titleLabel);
        jpnView.add(titleField);

        JLabel authorLabel = new JLabel("Author: ");
        JTextField authorField = new JTextField(document.getDocumentAuthor());
        jpnView.add(authorLabel);
        jpnView.add(authorField);

        JLabel descriptionLabel = new JLabel("Description: ");
        JTextField descriptionField = new JTextField(document.getDocumentDescription());
        jpnView.add(descriptionLabel);
        jpnView.add(descriptionField);

        JLabel languageLabel = new JLabel("Language: ");
        JTextField languageField = new JTextField(document.getDocumentLanguage());
        jpnView.add(languageLabel);
        jpnView.add(languageField);

        JLabel pagesLabel = new JLabel("Number of Pages: ");
        JTextField pagesField = new JTextField(String.valueOf(document.getDocumentPage()));
        jpnView.add(pagesLabel);
        jpnView.add(pagesField);

        JLabel isbnLabel = new JLabel("ISBN: ");
        JTextField isbnField = new JTextField(document.getDocumentISBN());
        jpnView.add(isbnLabel);
        jpnView.add(isbnField);

        // Dynamic fields
        JLabel dynamicField1Label = new JLabel();
        JTextField dynamicField1Field = new JTextField(20);
        jpnView.add(dynamicField1Label);
        jpnView.add(dynamicField1Field);

        JLabel dynamicField2Label = new JLabel();
        JTextField dynamicField2Field = new JTextField(20);
        jpnView.add(dynamicField2Label);
        jpnView.add(dynamicField2Field);

        JLabel dynamicField3Label = new JLabel();
        JTextField dynamicField3Field = new JTextField(20);
        jpnView.add(dynamicField3Label);
        jpnView.add(dynamicField3Field);
        
        if (document instanceof Book) {
            dynamicField1Label.setText("Genre: ");
            dynamicField1Field.setText(((Book) document).getBookGenre());
            dynamicField2Label.setText("Publisher: ");
            dynamicField2Field.setText(((Book) document).getBookPublisher());
            dynamicField3Label.setText("");
            dynamicField3Field.setVisible(false);
        } else if (document instanceof Thesis) {
            dynamicField1Label.setText("Subject: ");
            dynamicField1Field.setText(((Thesis) document).getThesisSubject());
            dynamicField2Label.setText("Degree: ");
            dynamicField2Field.setText(((Thesis) document).getThesisDegree());
            dynamicField3Label.setText("University: ");
            dynamicField3Field.setText(((Thesis) document).getThesisUniversity());
            dynamicField3Field.setVisible(true);
        } else if (document instanceof Magazine) {
            dynamicField1Label.setText("Subject: ");
            dynamicField1Field.setText(((Magazine) document).getMagazineSubject());
            dynamicField2Label.setText("Frequency: ");
            dynamicField2Field.setText(String.valueOf(((Magazine) document).getMagazineFrequency()));
            dynamicField3Label.setText("Issue Number: ");
            dynamicField3Field.setText(String.valueOf(((Magazine) document).getMagazineIssueNumb()));
            dynamicField3Field.setVisible(true);
        }
        
        if (kind.equals("display")) {
            quantityField.setEditable(false);
            titleField.setEditable(false);
            authorField.setEditable(false);
            descriptionField.setEditable(false);
            languageField.setEditable(false);
            pagesField.setEditable(false);
            isbnField.setEditable(false);

            dynamicField1Field.setEditable(false);
            dynamicField2Field.setEditable(false);
            dynamicField3Field.setEditable(false);
            
            jpnView.revalidate();
            jpnView.repaint();
        } else if (kind.equals("update")) {
           
            quantityField.setEditable(true);
            titleField.setEditable(true);
            authorField.setEditable(true);
            descriptionField.setEditable(true);
            languageField.setEditable(true);
            pagesField.setEditable(true);
            isbnField.setEditable(true);

            dynamicField1Field.setEditable(true);
            dynamicField2Field.setEditable(true);
            dynamicField3Field.setEditable(true);

            
            updateButton.addActionListener(e -> {
                if (quantityField.getText().isEmpty() || titleField.getText().isEmpty() ||
                        authorField.getText().isEmpty() || descriptionField.getText().isEmpty() ||
                        languageField.getText().isEmpty() || pagesField.getText().isEmpty() || 
                        isbnField.getText().isEmpty() || dynamicField1Field.getText().isEmpty() || 
                        dynamicField2Field.getText().isEmpty() || !(document instanceof Book) &&
                        dynamicField3Field.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(jpnView, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    document.documentQuantity = Integer.parseInt(quantityField.getText());
                    document.setDocumentTitle(titleField.getText());
                    document.setDocumentAuthor(authorField.getText());
                    document.setDocumentDescription(descriptionField.getText());
                    document.setDocumentLanguage(languageField.getText());
                    document.setDocumentPage(Integer.parseInt(pagesField.getText()));
                    document.setDocumentISBN(isbnField.getText());

                    if (document instanceof Book) {
                        Book book = (Book) document;
                        book.setBookGenre(dynamicField1Field.getText());
                        book.setBookPublisher(dynamicField2Field.getText());
                    } else if (document instanceof Thesis) {
                        Thesis thesis = (Thesis) document;
                        thesis.setThesisSubject(dynamicField1Field.getText());
                        thesis.setThesisDegree(dynamicField2Field.getText());
                        thesis.setThesisUniversity(dynamicField3Field.getText());
                    } else if (document instanceof Magazine) {
                        Magazine magazine = (Magazine) document;
                        magazine.setMagazineSubject(dynamicField1Field.getText());
                        magazine.setMagazineFrequency(Integer.parseInt(dynamicField2Field.getText()));
                        magazine.setMagazineIssueNumb(Integer.parseInt(dynamicField3Field.getText()));
                    }

                    JOptionPane.showMessageDialog(jpnView, "Document updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayOrUpdateDocumentDetails(document);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(jpnView, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(jpnView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
 
            });
            jpnView.revalidate();
            jpnView.repaint();
        }
    }
    

    
    private void clearDisplay() {
        jpnView.removeAll();
        jpnView.revalidate();
        jpnView.repaint();
    }
}
