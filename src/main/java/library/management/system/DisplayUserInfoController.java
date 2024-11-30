package library.management.system;

import main.classes.BorrowData;
import main.classes.Borrower;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
    private JPanel jpnHistory;
    private Borrower borrower;
    private Librarian librarian;
    private boolean isDealingWithBorrower;

    /**
     * Constructs a controller for displaying user info and borrowed history for a Borrower.
     *
     * @param jpnUserInfo      The panel to display user info.
     * @param jpnHistory The panel to display borrowed history.
     * @param borrower          The Borrower whose details are to be displayed.
     * @param isDealingWithBorrower Flag to indicate if the user is a Borrower (true) or Librarian (false).
     */
    public DisplayUserInfoController(JPanel jpnUserInfo, JPanel jpnHistory, Borrower borrower, boolean isDealingWithBorrower) {
        this.jpnUserInfo = jpnUserInfo;
        this.jpnHistory = jpnHistory;
        this.borrower = borrower;
        this.isDealingWithBorrower = isDealingWithBorrower;
        
        displayUserInfo();
        displayBorrowerHistory();
    }

    /**
     * Constructs a controller for displaying user info for a Librarian.
     *
     * @param jpnUserInfo      The panel to display user info.
     * @param jpnHistory The panel to display borrowed history (be empty for Librarians).
     * @param librarian         The Librarian whose details are to be displayed.
     * @param isDealingWithBorrower Flag to indicate if the user is a Borrower (true) or Librarian (false).
     */
    public DisplayUserInfoController(JPanel jpnUserInfo, JPanel jpnHistory, Librarian librarian, boolean isDealingWithBorrower) {
        this.jpnUserInfo = jpnUserInfo;
        this.jpnHistory = jpnHistory;
        this.librarian = librarian;
        this.isDealingWithBorrower = isDealingWithBorrower;
        
        displayUserInfo();
        displayLibrarianHistory();
    }

    /**
     * Displays the user information (either Borrower or Librarian) on the jpnUserInfo panel.
     * The displayed fields vary depending on whether the user is a Borrower or Librarian.
     */
    private void displayUserInfo() {
        // Set up the layout for user info
        jpnUserInfo.setLayout(new GridLayout(7, 2, 10, 10));
        String blank = "                    ";

        if (isDealingWithBorrower) {     
            jpnUserInfo.add(new JLabel(blank + "User ID:"));
            jpnUserInfo.add(new JLabel(String.valueOf(borrower.getUserID())));
                
            jpnUserInfo.add(new JLabel(blank + "Name:"));
            jpnUserInfo.add(new JLabel(borrower.getUserName()));
        
            jpnUserInfo.add(new JLabel(blank + "Date of Birth:"));
            jpnUserInfo.add(new JLabel(borrower.getUserDateOfBirth().toString()));
        
            jpnUserInfo.add(new JLabel(blank + "Email Address:"));
            jpnUserInfo.add(new JLabel(borrower.getUserEmailAddress()));
            
            jpnUserInfo.add(new JLabel(blank + "Is Student:"));
            jpnUserInfo.add(new JLabel(borrower.getIsStudent() ? "Yes" : "No"));
        
            jpnUserInfo.add(new JLabel(blank + "Overdue Count:"));
            jpnUserInfo.add(new JLabel(String.valueOf(borrower.getOverdueCount())));

            jpnUserInfo.add(new JLabel(blank + "Password:"));
            jpnUserInfo.add(new JLabel(maskPassword(borrower.getUserPassword())));
        } else {
            jpnUserInfo.add(new JLabel(blank + "User ID:"));
            jpnUserInfo.add(new JLabel(String.valueOf(librarian.getUserID())));
                
            jpnUserInfo.add(new JLabel(blank + "Name:"));
            jpnUserInfo.add(new JLabel(librarian.getUserName()));
        
            jpnUserInfo.add(new JLabel(blank + "Date of Birth:"));
            jpnUserInfo.add(new JLabel(librarian.getUserDateOfBirth().toString()));
        
            jpnUserInfo.add(new JLabel(blank + "Email Address:"));
            jpnUserInfo.add(new JLabel(librarian.getUserEmailAddress()));
            
            jpnUserInfo.add(new JLabel(blank + "Salary:"));
            jpnUserInfo.add(new JLabel(String.valueOf(librarian.getLibrarianSalary()) + " dollars per month"));

            jpnUserInfo.add(new JLabel(blank + "Password:"));
            jpnUserInfo.add(new JLabel(maskPassword(librarian.getUserPassword())));
        }

        jpnUserInfo.revalidate();
        jpnUserInfo.repaint();
    }

    /**
     * Displays the borrowed history of the Borrower in the jpnBorrowedHistory panel.
     * The history is displayed in a JTable with columns for Book ISBN, Borrow Date, Planned Return Date, and Status.
     */
    private void displayBorrowerHistory() {
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
        jpnHistory.setLayout(new BorderLayout());
        jpnHistory.add(scrollPane, BorderLayout.CENTER);

        jpnHistory.revalidate();
        jpnHistory.repaint();
    }

    private void displayLibrarianHistory() {
        String[] columns = {"Action History"};

        // Fetch the action history
        List<String> actionHistory = librarian.getActionHistory();

        // Define a read-only table model to STORE data
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Make all cells non-editable
            }
        };

        // Populate the table model with action history data in reverse order
        for (int i = actionHistory.size() - 1; i >= 0; i--) {
            // Add each action history string as a single row in the table
            model.addRow(new Object[]{actionHistory.get(i)});
        }

        // Set up JTable with the read-only model to APPEAR data on the screen
        JTable historyTable = new JTable(model);
        historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Set header font

        // Set up the renderer to wrap text and dynamically adjust the row height
        historyTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JTextArea textArea = new JTextArea(value != null ? value.toString() : "");
                textArea.setWrapStyleWord(true);  // Wrap at word boundaries
                textArea.setLineWrap(true);  // Enable line wrapping
                textArea.setBackground(table.getBackground()); // Match table background
                textArea.setFont(table.getFont()); // Match table font
                textArea.setEditable(false);  // Make it non-editable
                textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Optional padding

                // Set the maximum width of the column for wrapping
                int maxWidth = table.getColumnModel().getColumn(0).getWidth(); // Maximum width of the column
                textArea.setPreferredSize(new Dimension(maxWidth, textArea.getPreferredSize().height));

                // Dynamically adjust row height based on content
                int preferredHeight = textArea.getPreferredSize().height + 10; // Add padding
                if (preferredHeight > table.getRowHeight()) {
                    table.setRowHeight(row, preferredHeight);
                }

                return textArea;
            }
        });

        // Set a maximum row height and ensure the table can scroll
        historyTable.setRowHeight(42);  // Default row height
        historyTable.setPreferredScrollableViewportSize(new Dimension(500, 200));

        // Wrap table in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        // Add table and scroller to jpnHistory
        jpnHistory.setLayout(new BorderLayout());
        jpnHistory.add(scrollPane, BorderLayout.CENTER);

        jpnHistory.revalidate();
        jpnHistory.repaint();
    }

    /**
     * Converts a password to a string of asterisks with the same length.
     *
     * @param password the input password
     * @return a string of asterisks
     */
    private String maskPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "";
        }
        StringBuilder maskedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            maskedPassword.append("*");
        }
        return maskedPassword.toString();
    }

}
