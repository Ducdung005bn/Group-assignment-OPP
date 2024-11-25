package library.management.system;

import main.classes.BorrowData;
import main.classes.Borrower;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import main.classes.Librarian;

/**
 * Controller for displaying user information (Borrower or Librarian) and borrower's borrowing history.
 * This class sets up the UI components to show the details of the selected user and their borrowing history.
 */
public class DisplayUserInfoController {
    private JPanel jpnUserInfo;
    private JPanel jpnBorrowedHistory;
    private Borrower borrower;
    private Librarian librarian;
    private boolean isDealingWithBorrower;

    /**
     * Constructs a controller for displaying user info and borrowed history for a Borrower.
     *
     * @param jpnUserInfo      The panel to display user info.
     * @param jpnBorrowedHistory The panel to display borrowed history.
     * @param borrower          The Borrower whose details are to be displayed.
     * @param isDealingWithBorrower Flag to indicate if the user is a Borrower (true) or Librarian (false).
     */
    public DisplayUserInfoController(JPanel jpnUserInfo, JPanel jpnBorrowedHistory, Borrower borrower, boolean isDealingWithBorrower) {
        this.jpnUserInfo = jpnUserInfo;
        this.jpnBorrowedHistory = jpnBorrowedHistory;
        this.borrower = borrower;
        this.isDealingWithBorrower = isDealingWithBorrower;
        
        displayUserInfo();
        displayBorrowedHistory();
    }

    /**
     * Constructs a controller for displaying user info for a Librarian.
     *
     * @param jpnUserInfo      The panel to display user info.
     * @param jpnBorrowedHistory The panel to display borrowed history (be empty for Librarians).
     * @param librarian         The Librarian whose details are to be displayed.
     * @param isDealingWithBorrower Flag to indicate if the user is a Borrower (true) or Librarian (false).
     */
    public DisplayUserInfoController(JPanel jpnUserInfo, JPanel jpnBorrowedHistory, Librarian librarian, boolean isDealingWithBorrower) {
        this.jpnUserInfo = jpnUserInfo;
        this.jpnBorrowedHistory = jpnBorrowedHistory;
        this.librarian = librarian;
        this.isDealingWithBorrower = isDealingWithBorrower;
        
        displayUserInfo();
    }

    /**
     * Displays the user information (either Borrower or Librarian) on the jpnUserInfo panel.
     * The displayed fields vary depending on whether the user is a Borrower or Librarian.
     */
    private void displayUserInfo() {
        // Set up the layout for user info
        jpnUserInfo.setLayout(new GridLayout(7, 2, 10, 10));
               
        if (isDealingWithBorrower) {     
            jpnUserInfo.add(new JLabel("User ID:"));
            jpnUserInfo.add(new JLabel(String.valueOf(borrower.getUserID())));
                
            jpnUserInfo.add(new JLabel("Name:"));
            jpnUserInfo.add(new JLabel(borrower.getUserName()));
        
            jpnUserInfo.add(new JLabel("Date of Birth:"));
            jpnUserInfo.add(new JLabel(borrower.getUserDateOfBirth().toString()));
        
            jpnUserInfo.add(new JLabel("Phone Number:"));
            jpnUserInfo.add(new JLabel(borrower.getUserPhoneNumb()));
            
            jpnUserInfo.add(new JLabel("Is Student:"));
            jpnUserInfo.add(new JLabel(borrower.getIsStudent() ? "Yes" : "No"));
        
            jpnUserInfo.add(new JLabel("Overdue Count:"));
            jpnUserInfo.add(new JLabel(String.valueOf(borrower.getOverdueCount())));
        } else {
            jpnUserInfo.add(new JLabel("User ID:"));
            jpnUserInfo.add(new JLabel(String.valueOf(librarian.getUserID())));
                
            jpnUserInfo.add(new JLabel("Name:"));
            jpnUserInfo.add(new JLabel(librarian.getUserName()));
        
            jpnUserInfo.add(new JLabel("Date of Birth:"));
            jpnUserInfo.add(new JLabel(librarian.getUserDateOfBirth().toString()));
        
            jpnUserInfo.add(new JLabel("Phone Number:"));
            jpnUserInfo.add(new JLabel(librarian.getUserPhoneNumb()));
            
            jpnUserInfo.add(new JLabel("Salary:"));
            jpnUserInfo.add(new JLabel(String.valueOf(librarian.getLibrarianSalary())));            
        }

        jpnUserInfo.revalidate();
        jpnUserInfo.repaint();
    }

    /**
     * Displays the borrowed history of the Borrower in the jpnBorrowedHistory panel.
     * The history is displayed in a JTable with columns for Book ISBN, Borrow Date, Planned Return Date, and Status.
     */
    private void displayBorrowedHistory() {
        // Borrowed history table columns
        String[] columns = {"Book ISBN", "Borrow Date", "Planned Return Date", "Status"};
    
        // Fetch the borrowing history
        List<BorrowData> borrowHistory = borrower.borrowedHistory;
    
        // Define a read-only table model to STORE data
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
    
        // Populate the table model with borrowing data
        for (BorrowData data : borrowHistory) {
            Object[] row = {
                data.getBorrowedBookISBN(),
                data.getBorrowDate(),
                data.getPlannedReturnDate(),
                data.getBorrowStatus()
            };
            model.addRow(row);
        }
    
        // Set up JTable with the read-only model to APPEAR data on the screen
        JTable historyTable = new JTable(model);
        historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Set header font
        historyTable.setRowHeight(30); // Set row height
    
        // Wrap table in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setPreferredSize(new Dimension(500, 200));
    
        // Add table and scroller to jpnBorrowedHistory
        jpnBorrowedHistory.setLayout(new BorderLayout());
        jpnBorrowedHistory.add(scrollPane, BorderLayout.CENTER);
    
        jpnBorrowedHistory.revalidate();
        jpnBorrowedHistory.repaint();
    }

}
