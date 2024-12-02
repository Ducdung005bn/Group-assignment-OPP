package library.management.system;

import main.classes.main.opponents.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


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
    private LibraryManagementSystem libraryManagementSystem;

    public DisplayUserInfoController(JPanel jpnUserInfo, JPanel jpnHistory, Borrower borrower, LibraryManagementSystem libraryManagementSystem) {
        this.jpnUserInfo = jpnUserInfo;
        this.jpnHistory = jpnHistory;
        this.borrower = borrower;
        this.isDealingWithBorrower = true;
        this.libraryManagementSystem = libraryManagementSystem;
        
        displayUserInfo();
        displayBorrowerHistory();
    }

    public DisplayUserInfoController(JPanel jpnUserInfo, JPanel jpnHistory, Librarian librarian, LibraryManagementSystem libraryManagementSystem) {
        this.jpnUserInfo = jpnUserInfo;
        this.jpnHistory = jpnHistory;
        this.librarian = librarian;
        this.isDealingWithBorrower = false;
        this.libraryManagementSystem = libraryManagementSystem;
        
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
            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("UserID") + ":"));
            jpnUserInfo.add(new JLabel(String.valueOf(borrower.getUserID())));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("Name") + ":"));
            jpnUserInfo.add(new JLabel(borrower.getUserName()));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("DateOfBirth") + ":"));
            jpnUserInfo.add(new JLabel(borrower.getUserDateOfBirth().toString()));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("EmailAddress") + ":"));
            jpnUserInfo.add(new JLabel(borrower.getUserEmailAddress()));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("IsStudent") + ":"));
            jpnUserInfo.add(new JLabel(borrower.getIsStudent() ? libraryManagementSystem.translate("Yes") : libraryManagementSystem.translate("No")));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("OverdueCount") + ":"));
            jpnUserInfo.add(new JLabel(String.valueOf(borrower.getOverdueCount())));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("Password") + ":"));
            jpnUserInfo.add(new JLabel(maskPassword(borrower.getUserPassword())));
        } else {
            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("UserID") + ":"));
            jpnUserInfo.add(new JLabel(String.valueOf(librarian.getUserID())));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("Name") + ":"));
            jpnUserInfo.add(new JLabel(librarian.getUserName()));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("DateOfBirth") + ":"));
            jpnUserInfo.add(new JLabel(librarian.getUserDateOfBirth().toString()));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("EmailAddress") + ":"));
            jpnUserInfo.add(new JLabel(librarian.getUserEmailAddress()));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("Salary") + ":"));
            jpnUserInfo.add(new JLabel(String.valueOf(librarian.getLibrarianSalary()) + " " + libraryManagementSystem.translate("DollarsPerMonth")));

            jpnUserInfo.add(new JLabel(blank + libraryManagementSystem.translate("Password") + ":"));
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
        jpnHistory.removeAll();

        // Borrowed history table columns, add a new column for ratings
        String[] columns = {
                libraryManagementSystem.translate("BookISBN"),
                libraryManagementSystem.translate("BorrowDate"),
                libraryManagementSystem.translate("PlannedReturnDate"),
                libraryManagementSystem.translate("Status"),
                libraryManagementSystem.translate("Rating")
        };

        // Fetch the borrowing history
        List<BorrowData> borrowHistory = borrower.borrowedHistory;

        // Define a table model
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow editing only in the last column (Rating)
                return column == 4;
            }
        };

        // Populate the table model
        for (BorrowData data : borrowHistory) {
            String ratingAppearance = "X";
            if (data.getUserRating() != 0) {
                ratingAppearance = String.valueOf(data.getUserRating());
            }

            Object[] row = {
                    data.getBorrowedBookISBN(),
                    data.getBorrowDate(),
                    data.getPlannedReturnDate(),
                    data.getBorrowStatus(),
                    ratingAppearance
            };
            model.addRow(row);
        }

        // Create JTable with model
        JTable historyTable = new JTable(model);
        historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        historyTable.setRowHeight(30);

        // Set custom renderer for the Rating column
        historyTable.getColumnModel().getColumn(4).setCellRenderer(new RatingCellRenderer());

        // Add combo box editor for Rating column
        JComboBox<String> ratingComboBox = new JComboBox<>(new String[]{"X", "1", "2", "3", "4", "5"});
        historyTable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(ratingComboBox));

        AtomicBoolean isRatingComboBoxInUse = new AtomicBoolean(false);

        ratingComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    // Make sure the table is fully visible before making changes
                    if (!historyTable.isShowing()) {
                        return; // Avoid interacting if the table is not showing
                    }

                    // Set the flag to disable editing temporarily
                    isRatingComboBoxInUse.set(true);

                    String selectedValue = (String) e.getItem();
                    int rowIndex = historyTable.getSelectedRow();

                    if (rowIndex != -1) {
                        BorrowData currentBorrowData = borrowHistory.get(rowIndex);
                        int currentRating = currentBorrowData.getUserRating();
                        int selectedRating = selectedValue.equals("X") ? 0 : Integer.parseInt(selectedValue);

                        if (currentRating != selectedRating) {
                            handleUserRating(currentRating, selectedRating, currentBorrowData.getBorrowedBookISBN());
                        }
                        // Wait until the combo box interaction is done before refreshing the table
                        SwingUtilities.invokeLater(this::displayBorrowerHistory);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // Log any exceptions
                } finally {
                    // Reset the flag and re-enable editing when done
                    isRatingComboBoxInUse.set(false);
                }
            }
        });


        // Wrap table in JScrollPane
        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        // Add table to panel
        jpnHistory.setLayout(new BorderLayout());
        jpnHistory.add(scrollPane, BorderLayout.CENTER);

        jpnHistory.revalidate();
        jpnHistory.repaint();
    }

    // Custom renderer for the Rating column
    class RatingCellRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            if (value == null || value.equals("X")) {
                setText(""); // Display empty for "X"
            } else {
                int rating = Integer.parseInt(value.toString());
                StringBuilder stars = new StringBuilder();
                for (int i = 0; i < rating; i++) {
                    stars.append("★");
                }
                setText(stars.toString());
            }
        }
    }

    /**
     * Handles user rating for a specific borrowed book.
     * Updates the average rating and rating count in the document and ensures all instances of the same book (with the same ISBN)
     * in the borrower's history are updated.
     *
     * @param currentRating   the current rating assigned by the user for the book.
     * @param selectedRating  the new rating selected by the user.
     * @param borrowedBookISBN the ISBN of the borrowed book being rated.
     */
    private void handleUserRating(int currentRating, int selectedRating, String borrowedBookISBN) {
        // Find the document in the library system by its ISBN
        Document document = libraryManagementSystem.findDocumentByISBN(borrowedBookISBN);

        if (document == null) {
            JOptionPane.showMessageDialog(jpnHistory,
                    libraryManagementSystem.translate("DocumentRemovedFromSystem"),
                    libraryManagementSystem.translate("Error"),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Filter the list of all borrowed items by ISBN to find matching borrow records
        List<BorrowData> matchingBorrowData = borrower.borrowedHistory.stream()
                .filter(data -> data.getBorrowedBookISBN().equals(borrowedBookISBN))
                .collect(Collectors.toList());

        // If no matching borrowed records exist, exit early
        if (matchingBorrowData.isEmpty()) {
            return;
        }

        // Handle case where the document has not been rated before
        if (document.getDocumentRatingsCount() == 0) {
            // First rating: set rating count to 1 and average rating to the selected rating
            document.setDocumentRatingsCount(1);
            document.setDocumentAverageRating(selectedRating);

            // Update all matching BorrowData entries with the new rating
            for (BorrowData data : matchingBorrowData) {
                data.setUserRating(selectedRating);
            }
            libraryManagementSystem.saveData();
            return;
        }

        if (currentRating == 0) {
            // Adding a new rating
            double currentAvgRating = document.getDocumentAverageRating();
            int currentRatingsCount = document.getDocumentRatingsCount();

            // Increment the count of ratings
            currentRatingsCount++;

            // Recalculate the new average rating
            double newAvgRating = ((currentAvgRating * (currentRatingsCount - 1)) + selectedRating) / currentRatingsCount;

            // Update the document's rating count and average rating
            document.setDocumentRatingsCount(currentRatingsCount);
            document.setDocumentAverageRating(newAvgRating);

            // Update all matching BorrowData entries with the new rating
            for (BorrowData data : matchingBorrowData) {
                data.setUserRating(selectedRating);
            }
        } else if (selectedRating == 0) {
            // Deleting the current rating
            double currentAvgRating = document.getDocumentAverageRating();
            int currentRatingsCount = document.getDocumentRatingsCount();

            // If more than one rating exists, adjust the count and recalculate the average
            if (currentRatingsCount > 1) {
                currentRatingsCount--;

                double newAvgRating = (currentAvgRating * (currentRatingsCount + 1) - currentRating) / currentRatingsCount;

                // Update the document's rating count and average rating
                document.setDocumentRatingsCount(currentRatingsCount);
                document.setDocumentAverageRating(newAvgRating);

                // Set the rating to 0 (deleted) for all matching BorrowData entries
                for (BorrowData data : matchingBorrowData) {
                    data.setUserRating(0);
                }
            } else {
                // If it's the only rating, reset the rating count and average to 0
                document.setDocumentRatingsCount(0);
                document.setDocumentAverageRating(0.0);

                for (BorrowData data : matchingBorrowData) {
                    data.setUserRating(0);
                }
            }
        } else {
            // Updating an existing rating
            double currentAvgRating = document.getDocumentAverageRating();
            int currentRatingsCount = document.getDocumentRatingsCount();

            // Recalculate the new average based on the updated rating
            double newAvgRating = (currentAvgRating * currentRatingsCount - currentRating + selectedRating) / currentRatingsCount;

            // Update the document's average rating
            document.setDocumentAverageRating(newAvgRating);

            // Update all matching BorrowData entries with the new rating
            for (BorrowData data : matchingBorrowData) {
                data.setUserRating(selectedRating);
            }
        }

        // Save changes to the library data
        libraryManagementSystem.saveData();

        // Refresh the GUI to reflect the changes
        jpnHistory.revalidate();
        jpnHistory.repaint();
    }

    private void displayLibrarianHistory() {
        String[] columns = {
                libraryManagementSystem.translate("ActionHistory")
        };

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
