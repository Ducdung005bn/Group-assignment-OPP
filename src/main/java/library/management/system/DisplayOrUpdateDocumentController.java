package library.management.system;

import main.classes.Book;
import main.classes.Document;
import main.classes.LibraryManagementSystem;
import main.classes.MyQr;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import main.classes.Magazine;
import main.classes.Thesis;

/**
 * Controller for displaying or updating documents in the library management system.
 * This class listens for changes in the ISBN input field and performs a search
 * for matching documents. If a document is found, it displays its details and allows
 * for updating the information if required.
 */
public class DisplayOrUpdateDocumentController {
    private JPanel jpnView;
    private JTextField jtfDisplayOrUpdateDocument;
    private LibraryManagementSystem libraryManagementSystem;
    private String kind;

    /**
     * Constructs a DisplayOrUpdateDocumentController.
     *
     * @param kind                    The kind of operation ('display' or 'update')
     * @param jpnView                 The panel where the document details will be displayed
     * @param jtfDisplayOrUpdateDocument The text field for ISBN input
     * @param libraryManagementSystem The system containing the library's documents
     */
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

    /**
     * Searches for documents matching the input ISBN and displays or clears the document details.
     */
    private void searchAndDisplayOrUpdate() {
        String isbnInput = jtfDisplayOrUpdateDocument.getText().trim();

        // Find matching documents by comparing first letters of the given ISBN
        List<Document> matchingDocuments = libraryManagementSystem.getAllDocuments().stream()
                .filter(doc -> doc.getDocumentISBN().startsWith(isbnInput))
                .collect(Collectors.toList());

        //There is only one document starting with the given letters
        if (matchingDocuments.size() == 1) {
            Document matchedDocument = matchingDocuments.get(0);
            displayOrUpdateDocumentDetails(jpnView, kind, matchedDocument, libraryManagementSystem);
        } else {
            clearDisplay();
        }
    }

    public static void displayOrUpdateDocumentDetails(JPanel jpnView, String kind, Document document, LibraryManagementSystem libraryManagementSystem) {
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout(10, 10)); // Set BorderLayout
        String blank = "                    ";

        // Create a panel for QR Code at the top
        JPanel picturePanel = new JPanel(new GridLayout(0, 2, 5, 5));

        addBookQrToJPanel(document, picturePanel);
        addBookCoverToJPanel(document.getDocumentImageUrl(), picturePanel);

        jpnView.add(picturePanel, BorderLayout.NORTH); // Place QR Panel at the top

        // Create a panel for the form fields (Quantity, Title, etc.)
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5)); // Two columns for labels and fields
        Font font = new Font("Arial", Font.PLAIN, 16);

        // Shared fields
        formPanel.add(new JLabel(blank + "Remaining Quantity: "));
        JTextField remainingQuantityField = new JTextField(String.valueOf(document.documentQuantity));
        formPanel.add(remainingQuantityField);
        remainingQuantityField.setEditable(false);

        formPanel.add(new JLabel(blank + "Total Quantity: "));
        JTextField totalQuantityField = new JTextField(String.valueOf(document.getDocumentQuantityAll()));
        formPanel.add(totalQuantityField);

        formPanel.add(new JLabel(blank + "Title: "));
        JTextField titleField = new JTextField(document.getDocumentTitle());
        formPanel.add(titleField);

        formPanel.add(new JLabel(blank + "Author: "));
        JTextField authorField = new JTextField(document.getDocumentAuthor());
        formPanel.add(authorField);

        formPanel.add(new JLabel(blank + "Description: "));
        JTextField descriptionField = new JTextField(document.getDocumentDescription());
        formPanel.add(descriptionField);

        formPanel.add(new JLabel(blank + "Language: "));
        JTextField languageField = new JTextField(document.getDocumentLanguage());
        formPanel.add(languageField);

        formPanel.add(new JLabel(blank + "Number of Pages: "));
        JTextField pagesField = new JTextField(String.valueOf(document.getDocumentPage()));
        formPanel.add(pagesField);

        formPanel.add(new JLabel(blank + "ISBN: "));
        JTextField isbnField = new JTextField(document.getDocumentISBN());
        formPanel.add(isbnField);

        formPanel.add(new JLabel(blank + "Google Link: "));
        JTextField googleLinkField = new JTextField(document.getDocumentGoogleLink());
        formPanel.add(googleLinkField);
        googleLinkField.setEditable(false);

        formPanel.add(new JLabel(blank + "Image URL: "));
        JTextField imageUrlField = new JTextField(document.getDocumentImageUrl());
        formPanel.add(imageUrlField);
        imageUrlField.setEditable(false);

        // Dynamic fields based on document type
        JLabel dynamicField1Label = new JLabel("");
        JTextField dynamicField1Field = new JTextField();
        formPanel.add(dynamicField1Label);
        formPanel.add(dynamicField1Field);

        JLabel dynamicField2Label = new JLabel("");
        JTextField dynamicField2Field = new JTextField();
        formPanel.add(dynamicField2Label);
        formPanel.add(dynamicField2Field);

        JLabel dynamicField3Label = new JLabel("");
        JTextField dynamicField3Field = new JTextField();
        formPanel.add(dynamicField3Label);
        formPanel.add(dynamicField3Field);

        if (document instanceof Book) {
            dynamicField1Label.setText(blank + "Genre: ");
            dynamicField1Field.setText(((Book) document).getBookGenre());
            dynamicField2Label.setText(blank + "Publisher: ");
            dynamicField2Field.setText(((Book) document).getBookPublisher());
            dynamicField3Label.setText(blank + "");
            dynamicField3Field.setVisible(false);
        } else if (document instanceof Thesis) {
            dynamicField1Label.setText(blank + "Subject: ");
            dynamicField1Field.setText(((Thesis) document).getThesisSubject());
            dynamicField2Label.setText(blank + "Degree: ");
            dynamicField2Field.setText(((Thesis) document).getThesisDegree());
            dynamicField3Label.setText(blank + "University: ");
            dynamicField3Field.setText(((Thesis) document).getThesisUniversity());
            dynamicField3Field.setVisible(true);
        } else if (document instanceof Magazine) {
            dynamicField1Label.setText(blank + "Subject: ");
            dynamicField1Field.setText(((Magazine) document).getMagazineSubject());
            dynamicField2Label.setText(blank + "Frequency: ");
            dynamicField2Field.setText(String.valueOf(((Magazine) document).getMagazineFrequency()));
            dynamicField3Label.setText(blank + "Issue Number: ");
            dynamicField3Field.setText(String.valueOf(((Magazine) document).getMagazineIssueNumb()));
            dynamicField3Field.setVisible(true);
        }

        jpnView.add(formPanel, BorderLayout.CENTER); // Add the form panel to the center of the main panel

        // Button Panel for Update Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton updateButton = new JButton("UPDATE");
        if (kind.equals("update")) {
            buttonPanel.add(updateButton);
        }
        jpnView.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel at the bottom

        // Disable fields for 'display' mode, enable them for 'update' mode
        if (kind.equals("display")) {
            totalQuantityField.setEditable(false);
            titleField.setEditable(false);
            authorField.setEditable(false);
            descriptionField.setEditable(false);
            languageField.setEditable(false);
            pagesField.setEditable(false);
            isbnField.setEditable(false);

            dynamicField1Field.setEditable(false);
            dynamicField2Field.setEditable(false);
            dynamicField3Field.setEditable(false);
        } else if (kind.equals("update")) {
            totalQuantityField.setEditable(true);
            titleField.setEditable(true);
            authorField.setEditable(true);
            descriptionField.setEditable(true);
            languageField.setEditable(true);
            pagesField.setEditable(true);
            isbnField.setEditable(true);

            dynamicField1Field.setEditable(true);
            dynamicField2Field.setEditable(true);
            dynamicField3Field.setEditable(true);

            // Action for the update button
            updateButton.addActionListener(e -> {
                JTextField[] fields = new JTextField[]{
                        remainingQuantityField,
                        totalQuantityField,   // index 0
                        titleField,      // index 1
                        authorField,     // index 2
                        descriptionField,// index 3
                        languageField,   // index 4
                        pagesField,      // index 5
                        isbnField,       // index 6
                        dynamicField1Field, // index 7
                        dynamicField2Field, // index 8
                        dynamicField3Field  // index 9
                };

                updateDocument(fields, document, jpnView, libraryManagementSystem, kind);
            });
        }

        jpnView.revalidate();
        jpnView.repaint();
    }

    /**
     * Updates the details of a document (Book, Thesis, or Magazine) based on the values entered the input fields.
     * This method checks if any changes have been made to the document attributes and logs the changes.
     * It also validates the input data for required fields and correct formats.
     *
     * @param fields An array of JTextFields containing the document's updated details.
     * @param document The document to be updated (can be a Book, Thesis, or Magazine).
     * @param jpnView The JPanel where the document update action is taking place (used for displaying messages).
     * @param libraryManagementSystem The system containing the library's documents
     * @param kind A string representing kind of displaying or updating
     */
    private static void updateDocument(JTextField[] fields, Document document, JPanel jpnView, LibraryManagementSystem libraryManagementSystem, String kind) {
        try {
            // Retrieve data from input fields
            int totalDocumentQuantity = Integer.parseInt(fields[1].getText());
            String documentTitle = fields[2].getText();
            String documentAuthor = fields[3].getText();
            String documentDescription = fields[4].getText();
            String documentLanguage = fields[5].getText();
            int documentPage = Integer.parseInt(fields[6].getText());
            String documentISBN = fields[7].getText();
            String dynamicField1 = fields[8].getText();
            String dynamicField2 = fields[9].getText();
            String dynamicField3 = fields[10].getText();  // Optional for certain document types

            // Validate document quantity and page number
            int borrowedDocumentNumber = document.getDocumentQuantityAll() - document.documentQuantity;
            if (totalDocumentQuantity <= borrowedDocumentNumber) {
                JOptionPane.showMessageDialog(jpnView, "Please check the total number of documents", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            fields[0].setText(String.valueOf(document.documentQuantity + totalDocumentQuantity - document.getDocumentQuantityAll()));

            // Check if any required fields are empty
            if (fields[1].getText().isEmpty() || fields[2].getText().isEmpty() || fields[3].getText().isEmpty() ||
                    fields[4].getText().isEmpty() || fields[5].getText().isEmpty() || fields[6].getText().isEmpty() ||
                    fields[7].getText().isEmpty() || dynamicField1.isEmpty() || dynamicField2.isEmpty() ||
                    !(document instanceof Book) && dynamicField3.isEmpty()) {
                JOptionPane.showMessageDialog(jpnView, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the name only contains letters, single spaces between words, and dots followed by a space
            String regex = "^(?!.*\\s\\s)(?!.*\\.\\S)[a-zA-Z\\s.]*\\.$|^(?!.*\\s\\s)(?!.*\\.\\S)[a-zA-Z\\s.]*$";
            if (!documentAuthor.matches(regex) || !documentLanguage.matches(regex)) {
                JOptionPane.showMessageDialog(jpnView, "Name must only contain letters and single spaces between words.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (documentPage <= 0) {
                JOptionPane.showMessageDialog(jpnView, "Pages must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Prepare a StringBuilder to store the update history
            StringBuilder updateRecord = new StringBuilder();

            // Check if quantity has changed
            if (document.getDocumentQuantityAll() != totalDocumentQuantity) {
                updateRecord.append("(quantity: ").append(document.documentQuantity).append(" -> ").append(totalDocumentQuantity).append(") ");
                document.setDocumentQuantityAll(totalDocumentQuantity);
                document.documentQuantity = Integer.parseInt(fields[0].getText());
            }

            // Check if title has changed
            if (!document.getDocumentTitle().equals(documentTitle)) {
                updateRecord.append("(title: ").append(document.getDocumentTitle()).append(" -> ").append(documentTitle).append(") ");
                document.setDocumentTitle(documentTitle);
            }

            // Check if author has changed
            if (!document.getDocumentAuthor().equals(documentAuthor)) {
                updateRecord.append("(author: ").append(document.getDocumentAuthor()).append(" -> ").append(documentAuthor).append(") ");
                document.setDocumentAuthor(documentAuthor);
            }

            // Check if description has changed
            if (!document.getDocumentDescription().equals(documentDescription)) {
                updateRecord.append("(description: ").append(document.getDocumentDescription()).append(" -> ").append(documentDescription).append(") ");
                document.setDocumentDescription(documentDescription);
            }

            // Check if language has changed
            if (!document.getDocumentLanguage().equals(documentLanguage)) {
                updateRecord.append("(language: ").append(document.getDocumentLanguage()).append(" -> ").append(documentLanguage).append(") ");
                document.setDocumentLanguage(documentLanguage);
            }

            // Check if pages have changed
            if (document.getDocumentPage() != documentPage) {
                updateRecord.append("(pages: ").append(document.getDocumentPage()).append(" -> ").append(documentPage).append(") ");
                document.setDocumentPage(documentPage);
            }

            // Check if ISBN has changed
            if (!document.getDocumentISBN().equals(documentISBN)) {
                updateRecord.append("(ISBN: ").append(document.getDocumentISBN()).append(" -> ").append(documentISBN).append(") ");
                document.setDocumentISBN(documentISBN);
            }

            // Handle specific document types (Book, Thesis, Magazine)
            if (document instanceof Book) {
                Book book = (Book) document;

                // Check if book genre has changed
                if (!book.getBookGenre().equals(dynamicField1)) {
                    updateRecord.append("(genre: ").append(book.getBookGenre()).append(" -> ").append(dynamicField1).append(") ");
                    book.setBookGenre(dynamicField1);
                }

                // Check if book publisher has changed
                if (!book.getBookPublisher().equals(dynamicField2)) {
                    updateRecord.append("(publisher: ").append(book.getBookPublisher()).append(" -> ").append(dynamicField2).append(") ");
                    book.setBookPublisher(dynamicField2);
                }

            } else if (document instanceof Thesis) {
                Thesis thesis = (Thesis) document;

                // Check if thesis subject has changed
                if (!thesis.getThesisSubject().equals(dynamicField1)) {
                    updateRecord.append("(subject: ").append(thesis.getThesisSubject()).append(" -> ").append(dynamicField1).append(") ");
                    thesis.setThesisSubject(dynamicField1);
                }

                // Check if thesis degree has changed
                if (!thesis.getThesisDegree().equals(dynamicField2)) {
                    updateRecord.append("(degree: ").append(thesis.getThesisDegree()).append(" -> ").append(dynamicField2).append(") ");
                    thesis.setThesisDegree(dynamicField2);
                }

                // Check if thesis university has changed
                if (!thesis.getThesisUniversity().equals(dynamicField3)) {
                    updateRecord.append("(university: ").append(thesis.getThesisUniversity()).append(" -> ").append(dynamicField3).append(") ");
                    thesis.setThesisUniversity(dynamicField3);
                }

            } else if (document instanceof Magazine) {
                Magazine magazine = (Magazine) document;

                // Check if magazine subject has changed
                if (!magazine.getMagazineSubject().equals(dynamicField1)) {
                    updateRecord.append("(subject: ").append(magazine.getMagazineSubject()).append(" -> ").append(dynamicField1).append(") ");
                    magazine.setMagazineSubject(dynamicField1);
                }

                // Check if magazine frequency has changed
                if (magazine.getMagazineFrequency() != Integer.parseInt(dynamicField2)) {
                    updateRecord.append("(frequency: ").append(magazine.getMagazineFrequency()).append(" -> ").append(dynamicField2).append(") ");
                    magazine.setMagazineFrequency(Integer.parseInt(dynamicField2));
                }

                // Check if magazine issue number has changed
                if (magazine.getMagazineIssueNumb() != Integer.parseInt(dynamicField3)) {
                    updateRecord.append("(issue number: ").append(magazine.getMagazineIssueNumb()).append(" -> ").append(dynamicField3).append(") ");
                    magazine.setMagazineIssueNumb(Integer.parseInt(dynamicField3));
                }
            }

            // Log the update record and save the updated data
            LogInController.librarian.dealWithDocumentAction(documentISBN, "Update " + updateRecord.toString());
            libraryManagementSystem.saveData();

            // Notify the user of the successful update
            JOptionPane.showMessageDialog(jpnView, "Document updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            displayOrUpdateDocumentDetails(jpnView, kind, document, libraryManagementSystem);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(jpnView, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpnView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static void addBookQrToJPanel(Document document, JPanel picturePanel) {
        try {
            // Generate QR Code file for the document
            MyQr myQr = new MyQr();
            myQr.generateQRDocument(document);

            if (document.getDocumentGoogleLink().isEmpty() || document.getDocumentGoogleLink() == null) {
                return;
            }

            // Load the QR Code image
            String qrFilePath = "qr_" + document.getDocumentISBN() + ".png";
            ImageIcon qrIcon = new ImageIcon(qrFilePath);

            // Scale image for better display
            Image scaledImage = qrIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            qrIcon = new ImageIcon(scaledImage);

            // Create a label to hold the QR image
            JLabel qrLabel = new JLabel(qrIcon);
            qrLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Add the QR label to the panel
            picturePanel.add(qrLabel);
        } catch (Exception e) {
            return;
        }
    }

    private static void addBookCoverToJPanel(String imageUrl, JPanel picturePanel) {
        if (imageUrl.isEmpty()) {
            return;
        }

        try {
            // Load the image from the URL
            ImageIcon imageIcon = new ImageIcon(new URL(imageUrl));

            // Optionally scale the image to fit desired dimensions
            Image scaledImage = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);

            // Create a JLabel to hold the image
            JLabel label = new JLabel(imageIcon);
            label.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the image in the label

            // Add the JLabel to the JFrame
            picturePanel.add(label);
        } catch (Exception e) {
            // Handle exceptions if the image fails to load
            e.printStackTrace(); // Print the stack trace for debugging
            JOptionPane.showMessageDialog(
                    picturePanel,
                    "Failed to load image: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            ); // Show an error message dialog to the user
        }
    }

    private void clearDisplay() {
        jpnView.removeAll();
        jpnView.revalidate();
        jpnView.repaint();
    }

}