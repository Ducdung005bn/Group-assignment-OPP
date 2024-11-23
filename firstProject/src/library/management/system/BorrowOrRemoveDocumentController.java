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

public class BorrowOrRemoveDocumentController {
    private JPanel jpnView;
    private JTextField jtfBorrowOrRemoveDocument;
    private JButton jbtBorrowOrRemoveDocument;
    private Borrower borrower;
    private LibraryManagementSystem libraryManagementSystem; 
    
    //Constructer for borrowing documents
    public BorrowOrRemoveDocumentController (JPanel jpnView, JTextField jtfBorrowOrRemoveDocument, JButton jbtBorrowOrRemoveDocument, Borrower borrower, LibraryManagementSystem libraryManagementSystem) {
        this.jpnView = jpnView;
        this.jtfBorrowOrRemoveDocument = jtfBorrowOrRemoveDocument;
        this.jbtBorrowOrRemoveDocument = jbtBorrowOrRemoveDocument;
        this.borrower = borrower;
        this.libraryManagementSystem = libraryManagementSystem;
        this.jbtBorrowOrRemoveDocument.setText("BORROW");
        
        jtfBorrowOrRemoveDocument.getDocument().addDocumentListener(new DocumentListener() {
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
        
        jbtBorrowOrRemoveDocument.addActionListener(e -> handleBorrowDocument());
        jbtBorrowOrRemoveDocument.setEnabled(false);
    }
    
    //Constructer for removing documents
    public BorrowOrRemoveDocumentController (JPanel jpnView, JTextField jtfBorrowOrRemoveDocument, JButton jbtBorrowOrRemoveDocument, LibraryManagementSystem libraryManagementSystem) {
        this.jpnView = jpnView;
        this.jtfBorrowOrRemoveDocument = jtfBorrowOrRemoveDocument;
        this.jbtBorrowOrRemoveDocument = jbtBorrowOrRemoveDocument;
        this.libraryManagementSystem = libraryManagementSystem;
        this.jbtBorrowOrRemoveDocument.setText("REMOVE");
        
        jtfBorrowOrRemoveDocument.getDocument().addDocumentListener(new DocumentListener() {
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
        
        jbtBorrowOrRemoveDocument.addActionListener(e -> handleRemoveDocument());
        jbtBorrowOrRemoveDocument.setEnabled(false);
    }
    
    private void searchAndDisplay() {
        String isbnInput = jtfBorrowOrRemoveDocument.getText().trim();
    
        List<Document> matchingDocuments = libraryManagementSystem.getAllDocuments().stream()
                .filter(doc -> doc.getDocumentISBN().startsWith(isbnInput))
                .collect(Collectors.toList());
    
        if (matchingDocuments.size() == 1) {
            Document matchedDocument = matchingDocuments.get(0);
            jpnView.removeAll();
            jpnView.setLayout(new GridLayout(0, 1));

            Font font = new Font("Arial", Font.PLAIN, 16);
            
            JLabel isbnLabel = new JLabel("ISBN: " + matchedDocument.getDocumentISBN());
            isbnLabel.setFont(font);
            jpnView.add(isbnLabel);

            JLabel quantityLabel = new JLabel("Quantity: " + matchedDocument.documentQuantity);
            quantityLabel.setFont(font);
            jpnView.add(quantityLabel);

            JLabel titleLabel = new JLabel("Title: " + matchedDocument.getDocumentTitle());
            titleLabel.setFont(font);
            jpnView.add(titleLabel);

            JLabel authorLabel = new JLabel("Author: " + matchedDocument.getDocumentAuthor());
            authorLabel.setFont(font);
            jpnView.add(authorLabel);
            
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
    
    private void handleBorrowDocument() {
        List<Document> matchingDocuments = libraryManagementSystem.getAllDocuments().stream()
            .filter(doc -> doc.getDocumentISBN().startsWith(jtfBorrowOrRemoveDocument.getText().trim()))
            .collect(Collectors.toList());
            
        String isbn = matchingDocuments.get(0).getDocumentISBN();
           
        String reason = isAbleToBorrow(isbn, libraryManagementSystem);
            
        if (reason == null) {  //is able to borrow the book
        BorrowData borrowData = new BorrowData();
        borrowData.setBorrowerID(borrower.getUserID());
        borrowData.setBorrowedBookISBN(isbn);
        borrowData.setBorrowDate(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, LibraryManagementSystem.BORROW_DURATION_DAYS);

        borrowData.setPlannedReturnDate(calendar.getTime());
        borrowData.setBorrowStatus("Not Returned");
        borrower.borrowingHistory.add(borrowData);
        borrower.borrowingBookCount++;
        Document document = libraryManagementSystem.findDocumentByISBN(isbn);
        document.documentQuantity--;
            
        JOptionPane.showMessageDialog(null, "You have borrowed the book successfully.", "Notification", JOptionPane.INFORMATION_MESSAGE);
        displayBorrowData(borrowData);
        } else {
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
    
    private String isAbleToBorrow(String documentISBN, LibraryManagementSystem libraryManagementSystem) {
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
        
    private void displayBorrowData(BorrowData borrowData) {
        jpnView.removeAll();
        jpnView.setLayout(new GridLayout(0, 1)); 

        JLabel borrowerIDLabel = new JLabel("Borrower ID: " + borrowData.getBorrowerID());
        borrowerIDLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel borrowedISBNLabel = new JLabel("Book ISBN: " + borrowData.getBorrowedBookISBN());
        borrowedISBNLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel borrowDateLabel = new JLabel("Borrow Date: " + borrowData.getBorrowDate());
        borrowDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel plannedReturnDateLabel = new JLabel("Planned Return Date: " + borrowData.getPlannedReturnDate());
        plannedReturnDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel borrowStatusLabel = new JLabel("Status: " + borrowData.getBorrowStatus());
        borrowStatusLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        jpnView.add(borrowerIDLabel);
        jpnView.add(borrowedISBNLabel);
        jpnView.add(borrowDateLabel);
        jpnView.add(plannedReturnDateLabel);
        jpnView.add(borrowStatusLabel);

        jpnView.revalidate();
        jpnView.repaint();
    }
    
    private void handleRemoveDocument() {
        List<Document> matchingDocuments = libraryManagementSystem.getAllDocuments().stream()
            .filter(doc -> doc.getDocumentISBN().startsWith(jtfBorrowOrRemoveDocument.getText().trim()))
            .collect(Collectors.toList());
        Document removedDocument = matchingDocuments.get(0);
        if (removedDocument instanceof Book) {
            libraryManagementSystem.bookList.remove((Book) removedDocument);
        } else if (removedDocument instanceof Thesis) {
            libraryManagementSystem.thesisList.remove( (Thesis) removedDocument);
        } else if (removedDocument instanceof Magazine) {
            libraryManagementSystem.magazineList.remove( (Magazine) removedDocument);
        }
                      
        JOptionPane.showMessageDialog(null, "You have removed the book successfully.", "Notification", JOptionPane.INFORMATION_MESSAGE);
        jtfBorrowOrRemoveDocument.setText("");
        libraryManagementSystem.saveData();
    }
}
