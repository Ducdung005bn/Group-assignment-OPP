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
import main.classes.BorrowData;
import main.classes.Borrower;
import main.classes.Document;
import main.classes.LibraryManagementSystem;
import main.classes.PendingUser;

public class BorrowDocumentController {
    private JPanel jpnView;
    private JTextField jtfBorrowDocument;
    private JButton jbtBorrowDocument;
    private PendingUser pendingUser;
    private LibraryManagementSystem libraryManagementSystem; 
    
    public BorrowDocumentController (JPanel jpnView, JTextField jtfBorrowDocument, JButton jbtBorrowDocument, PendingUser pendingUser, LibraryManagementSystem libraryManagementSystem) {
        this.jpnView = jpnView;
        this.jtfBorrowDocument = jtfBorrowDocument;
        this.jbtBorrowDocument = jbtBorrowDocument;
        this.pendingUser = pendingUser;
        this.libraryManagementSystem = libraryManagementSystem;
        
        jtfBorrowDocument.getDocument().addDocumentListener(new DocumentListener() {
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
        
        jbtBorrowDocument.addActionListener(e -> handleBorrowDocument());
        jbtBorrowDocument.setEnabled(false);
    }
    
    private void searchAndDisplay() {
        String isbnInput = jtfBorrowDocument.getText().trim();
    
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
            
            jbtBorrowDocument.setEnabled(true);
            jpnView.revalidate();
            jpnView.repaint();
        
        } else {
            //clear display
            jpnView.removeAll();
            jbtBorrowDocument.setEnabled(false);
            jpnView.revalidate();
            jpnView.repaint();
        }
    }
    
    private void handleBorrowDocument() {
        List<Document> matchingDocuments = libraryManagementSystem.getAllDocuments().stream()
            .filter(doc -> doc.getDocumentISBN().startsWith(jtfBorrowDocument.getText().trim()))
            .collect(Collectors.toList());
            
        String isbn = matchingDocuments.get(0).getDocumentISBN();
           
        Borrower borrower = (Borrower) libraryManagementSystem.findUser(pendingUser.getPendingUserID());
        String reason = borrower.isAbleToBorrow(isbn, libraryManagementSystem);
            
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
}
