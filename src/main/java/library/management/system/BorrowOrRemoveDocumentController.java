package library.management.system;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.classes.Book;
import main.classes.BorrowData;
import main.classes.Borrower;
import main.classes.Document;
import main.classes.LibraryManagementSystem;
import main.classes.Magazine;
import main.classes.Thesis;

/**
 * Controller for handling document borrowing or removal functionality.
 * It manages user interactions for borrowing and removing documents from the library.
 */
public class BorrowOrRemoveDocumentController {
    private JPanel jpnView;
    protected JTextField jtfBorrowOrRemoveDocument;
    private JButton jbtBorrowOrRemoveDocument;
    private Borrower borrower;
    private LibraryManagementSystem libraryManagementSystem;

    public BorrowOrRemoveDocumentController(LibraryManagementSystem libraryManagementSystem,JTextField jtfBorrowOrRemoveDocument,Borrower borrower){
        this.libraryManagementSystem = libraryManagementSystem;
        this.jtfBorrowOrRemoveDocument = jtfBorrowOrRemoveDocument;
        this.borrower = borrower;
    }
    /**
     * Constructor for handling document borrowing functionality.
     *
     * @param jpnView                The panel where the document details will be displayed.
     * @param jtfBorrowOrRemoveDocument The text field for entering the document ISBN.
     * @param jbtBorrowOrRemoveDocument The button to trigger the borrow action.
     * @param borrower               The borrower attempting to borrow the document.
     * @param libraryManagementSystem The system managing the library data.
     */
    public BorrowOrRemoveDocumentController(JPanel jpnView, JTextField jtfBorrowOrRemoveDocument, JButton jbtBorrowOrRemoveDocument, Borrower borrower, LibraryManagementSystem libraryManagementSystem) {
        this.jpnView = jpnView;
        this.jtfBorrowOrRemoveDocument = jtfBorrowOrRemoveDocument;
        this.jbtBorrowOrRemoveDocument = jbtBorrowOrRemoveDocument;
        this.borrower = borrower;
        this.libraryManagementSystem = libraryManagementSystem;
        this.jbtBorrowOrRemoveDocument.setText("BORROW");

        jbtBorrowOrRemoveDocument.setEnabled(false);

        searchAndDisplay(); //If the JText field is filled already, display it without any listener

        jtfBorrowOrRemoveDocument.getDocument().addDocumentListener(new DocumentListener() {
            //new text is inserted into the text field
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndDisplay();
            }

            //text is removed from the text field
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndDisplay();
            }

            //the text field undergoes a change in attributes (text font, ...)
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndDisplay();
            }
        });

        jbtBorrowOrRemoveDocument.addActionListener(e -> handleBorrowDocument());
    }

    /**
     * Constructor for handling document removal functionality.
     *
     * @param jpnView                The panel where the document details will be displayed.
     * @param jtfBorrowOrRemoveDocument The text field for entering the document ISBN.
     * @param jbtBorrowOrRemoveDocument The button to trigger the remove action.
     * @param libraryManagementSystem The system managing the library data.
     */
    public BorrowOrRemoveDocumentController (JPanel jpnView, JTextField jtfBorrowOrRemoveDocument, JButton jbtBorrowOrRemoveDocument, LibraryManagementSystem libraryManagementSystem) {
        this.jpnView = jpnView;
        this.jtfBorrowOrRemoveDocument = jtfBorrowOrRemoveDocument;
        this.jbtBorrowOrRemoveDocument = jbtBorrowOrRemoveDocument;
        this.libraryManagementSystem = libraryManagementSystem;
        this.jbtBorrowOrRemoveDocument.setText("REMOVE");

        jtfBorrowOrRemoveDocument.getDocument().addDocumentListener(new DocumentListener() {
            //new text is inserted into the text field
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndDisplay();
            }

            //text is removed from the text field
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndDisplay();
            }

            //the text field undergoes a change in attributes (text font, ...)
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndDisplay();
            }
        });

        jbtBorrowOrRemoveDocument.addActionListener(e -> handleRemoveDocument());
        jbtBorrowOrRemoveDocument.setEnabled(false);
    }

    /**
     * Searches for documents based on ISBN input and updates the display.
     * If exactly one matching document is found, its details are shown.
     */
    private void searchAndDisplay() {
        String isbnInput = jtfBorrowOrRemoveDocument.getText().trim();

        // Find matching documents by comparing first letters of the given ISBN
        List<Document> matchingDocuments = libraryManagementSystem.getAllDocuments().stream()
                .filter(doc -> doc.getDocumentISBN().startsWith(isbnInput))
                .collect(Collectors.toList());

        //There is only one document starting with the given letters
        if (matchingDocuments.size() == 1) {
            Document matchedDocument = matchingDocuments.get(0);
            DisplayOrUpdateDocumentController.displayOrUpdateDocumentDetails(jpnView, "display", matchedDocument, libraryManagementSystem);

            jbtBorrowOrRemoveDocument.setEnabled(true);
            jpnView.revalidate();
            jpnView.repaint();
        } else {
            //clear display
            jpnView.removeAll();
            jbtBorrowOrRemoveDocument.setEnabled(false);
            jpnView.revalidate();
            jpnView.repaint();
        }
    }

    /**
     * Handles the process of borrowing a document.
     * It checks if the borrower can borrow the document and updates borrowing history.
     */
    protected void handleBorrowDocument() {
        //Get the ISBN of the book appearing on the screen
        List<Document> matchingDocuments = libraryManagementSystem.getAllDocuments().stream()
            .filter(doc -> doc.getDocumentISBN().startsWith(jtfBorrowOrRemoveDocument.getText().trim()))
            .collect(Collectors.toList());
        String isbn = matchingDocuments.get(0).getDocumentISBN();

        // Check if the borrower is allowed to borrow the document
        String reason = isAbleToBorrow(isbn, libraryManagementSystem);

        if (reason == null) {  //is able to borrow the book
            BorrowData borrowData = new BorrowData();
            borrowData.setBorrowerID(borrower.getUserID());
            borrowData.setBorrowedBookISBN(isbn);
            borrowData.setBorrowDate(new Date());

            // Set the planned return date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, LibraryManagementSystem.BORROW_DURATION_DAYS);
            borrowData.setPlannedReturnDate(calendar.getTime());

            borrowData.setBorrowStatus("Not Returned");
            borrower.borrowingHistory.add(borrowData);
            borrower.borrowingBookCount++;
            Document document = libraryManagementSystem.findDocumentByISBN(isbn);
            document.documentQuantity--;

            displayBorrowData(borrowData);
        } else {
            // If borrowing is not allowed, display the reason
            JOptionPane.showMessageDialog(null, "You cannot borrow the book.", "Error", JOptionPane.ERROR_MESSAGE);

            jpnView.removeAll();
            jpnView.setLayout(new GridLayout(0, 1));
            JLabel unsuccessfulBorrow = new JLabel(reason);
            unsuccessfulBorrow.setFont(new Font("Arial", Font.PLAIN, 16));
            jpnView.add(unsuccessfulBorrow);

            jpnView.revalidate();
            jpnView.repaint();
        }
        libraryManagementSystem.saveData();
    }

    /**
     * Checks if the borrower is eligible to borrow the document.
     *
     * @param documentISBN The ISBN of the document to borrow.
     * @param libraryManagementSystem The system managing library data.
     * @return A message indicating why the borrower cannot borrow the document, or null if they can.
     */
    protected String isAbleToBorrow(String documentISBN, LibraryManagementSystem libraryManagementSystem) {
        Document document = libraryManagementSystem.findDocumentByISBN(documentISBN);

        boolean alreadyBorrow = false;
        for (BorrowData borrowData : borrower.borrowingHistory) {
            if (borrowData.getBorrowedBookISBN() == documentISBN) {
                alreadyBorrow = true;
                break;
            }
        }
        if (document == null) {
            return "The book with ISBN " + documentISBN + " was not found.";
        } else if (document.documentQuantity == 0) {
            return "The book with ISBN " + documentISBN + " is out of stock.";
        } else if (alreadyBorrow) {
            return "You have already borrowed this book.";
        } else if (borrower.borrowingBookCount >= LibraryManagementSystem.MAX_BORROW_LIMIT) {
            return "You have reached the maximum borrow limit of " + LibraryManagementSystem.MAX_BORROW_LIMIT + " books.";
        } else {
            return null;  //is able to borrow the book
        }
    }

    /**
     * Displays the borrow data in a notification window using a JOptionPane.
     * This method creates a message containing the borrower's information and
     * displays it in a message dialog.
     *
     * @param borrowData The borrow data object containing the borrower's information.
     */
    private void displayBorrowData(BorrowData borrowData) {
        // Create a message string with borrower's information
        String message = "Borrower ID: " + borrowData.getBorrowerID() + "\n" +
                "Book ISBN: " + borrowData.getBorrowedBookISBN() + "\n" +
                "Borrow Date: " + borrowData.getBorrowDate() + "\n" +
                "Planned Return Date: " + borrowData.getPlannedReturnDate() + "\n" +
                "Status: " + borrowData.getBorrowStatus();

        // Display the information in a notification dialog
        JOptionPane.showMessageDialog(null, message, "Borrow Information", JOptionPane.INFORMATION_MESSAGE);
        jtfBorrowOrRemoveDocument.setText("");
    }

    /**
     * Handles the process of removing a document from the library.
     * This action removes the document from the library's system.
     */
    private void handleRemoveDocument() {
        List<Document> matchingDocuments = libraryManagementSystem.getAllDocuments().stream()
            .filter(doc -> doc.getDocumentISBN().startsWith(jtfBorrowOrRemoveDocument.getText().trim()))
            .collect(Collectors.toList());
        Document removedDocument = matchingDocuments.get(0);

        if (removedDocument.documentQuantity < removedDocument.getDocumentQuantityAll()) {
            JOptionPane.showMessageDialog(null, "This document hasn't been returned.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (removedDocument instanceof Book) {
            libraryManagementSystem.bookList.remove((Book) removedDocument);
        } else if (removedDocument instanceof Thesis) {
            libraryManagementSystem.thesisList.remove( (Thesis) removedDocument);
        } else if (removedDocument instanceof Magazine) {
            libraryManagementSystem.magazineList.remove( (Magazine) removedDocument);
        }

        JOptionPane.showMessageDialog(null, "You have removed the book successfully.", "Notification", JOptionPane.INFORMATION_MESSAGE);
        jtfBorrowOrRemoveDocument.setText("");
        LogInController.librarian.dealWithDocumentAction(removedDocument.getDocumentISBN(), "Remove");
        libraryManagementSystem.saveData();
    }
}
