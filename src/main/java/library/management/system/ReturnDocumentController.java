package library.management.system;

import main.classes.LibraryManagementSystem;
import main.classes.BorrowData;
import main.classes.Borrower;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import main.classes.Document;

/**
 * The controller responsible for handling the return of borrowed documents by a borrower.
 * It updates the borrowing history, status, and library system accordingly.
 */
public class ReturnDocumentController {
    private JPanel jpnView;
    private Borrower borrower;
    private LibraryManagementSystem libraryManagementSystem;
    private TableDisplay tableDisplay;
    private final String[] COLUMNS = {"Borrower ID", "Book ISBN", "Borrow Date", "Planned Return Date", "Status", "Return"};
    private DefaultTableModel model;

    /**
     * Constructs a ReturnDocumentController object with the given panel, borrower, and library management system.
     *
     * @param jpnView The panel where the table will be shown.
     * @param borrower The borrower whose borrowed documents will be displayed.
     * @param libraryManagementSystem The library system instance.
     */
    public ReturnDocumentController(JPanel jpnView, Borrower borrower, LibraryManagementSystem libraryManagementSystem) {
        this.jpnView = jpnView;
        this.borrower = borrower;
        this.libraryManagementSystem = libraryManagementSystem;
        this.tableDisplay = new TableDisplay();
        setDataToTable();
    }

    /**
     * Sets the data to the table by retrieving the borrowing history of the borrower.
     * It adds a "Return" button to the last column of the table.
     */
    public void setDataToTable() {
        List<BorrowData> borrowDataList = borrower.borrowingHistory;
        model = tableDisplay.setBorrowDataTable(borrowDataList, COLUMNS);
        
        JTable table = new JTable(model);

        // Add "Return" button to the last column
        Action returnAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                BorrowData selectedBorrowData = borrowDataList.get(row);
                if (selectedBorrowData.getBorrowStatus().equals("Not Returned")) {
                    returnDocument(selectedBorrowData);
                    setDataToTable();
                }
            }
        };
        
        // Use ButtonColumn to add button functionality
        ButtonColumn buttonColumn = new ButtonColumn(table, returnAction, 5);

        // Table design
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(40);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(800, 300));

        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scroll, BorderLayout.CENTER);
        jpnView.validate();
        jpnView.repaint();
    }

    /**
     * Handles the return of a borrowed document, updates the borrowing history,
     * and adjusts the library inventory accordingly.
     *
     * @param borrowData The borrow data entry representing the borrowed document.
     */
    protected void returnDocument(BorrowData borrowData) {
        borrower.borrowingHistory.remove(borrowData);
        borrower.borrowingBookCount--;
    
        Date actualReturnDate = new Date();
    
        if (actualReturnDate.after(borrowData.getPlannedReturnDate())) {
            // Notification for late return
            JOptionPane.showMessageDialog(
                null,
                "You have returned the book after the due date.",
                "Late Return",
                JOptionPane.WARNING_MESSAGE
            );
            borrowData.setBorrowStatus("Not Returned On Time");
            int overdueCountTemp = borrower.getOverdueCount()+1;
            borrower.setOverdueCount(overdueCountTemp);
        } else {
            // Notification for true return
            JOptionPane.showMessageDialog(
                null,
                "You have returned the book before the due date.",
                "On Time Return",
                JOptionPane.INFORMATION_MESSAGE
            );
            borrowData.setBorrowStatus("Returned On Time");
        }

        //If the document was borrowed before, set the same rating.
        for (BorrowData data : borrower.borrowedHistory) {
            if (data.getBorrowedBookISBN().equals(borrowData.getBorrowedBookISBN())) {
                borrowData.setUserRating(data.getUserRating());
                break;
            }
        }
    
        // update borrow history
        borrower.borrowedHistory.add(borrowData);
    
        // increase the numbs of the given book
        Document document = libraryManagementSystem.findDocumentByISBN(borrowData.getBorrowedBookISBN());
        if (document != null) {
            document.documentQuantity++;
        }
        libraryManagementSystem.saveData();
    }

    /**
     * Helper class that sets the borrow data into a table model.
     */
    private class TableDisplay {
        /**
         * Creates a DefaultTableModel for the borrow data list with the given column names.
         *
         * @param borrowDataList The list of borrow data entries.
         * @param columnList The column names for the table.
         * @return The DefaultTableModel for displaying the borrow data.
         */
        public DefaultTableModel setBorrowDataTable(List<BorrowData> borrowDataList, String[] columnList) {
            int columns = columnList.length;
            DefaultTableModel dtm = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return colIndex == 5; // Only allow interaction with "Return" button
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnIndex == 5 ? JButton.class : String.class;
                }
            };
            dtm.setColumnIdentifiers(columnList);

            for (BorrowData borrowData : borrowDataList) {
                Object[] row = new Object[columns];
                row[0] = String.valueOf(borrowData.getBorrowerID());
                row[1] = borrowData.getBorrowedBookISBN();
                row[2] = borrowData.getBorrowDate();
                row[3] = borrowData.getPlannedReturnDate();
                row[4] = borrowData.getBorrowStatus();
                row[5] = "Return"; // Text for the "Return" button
                dtm.addRow(row);
            }
            return dtm;
        }
    }

    /**
     * Helper class to create a button in a specific table column.
     */
    private class ButtonColumn extends AbstractAction {
        private final JTable table;
        private final int column;

        public ButtonColumn(JTable table, Action action, int column) {
            super();
            this.table = table;
            this.column = column;

            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer(new ButtonRenderer());
            columnModel.getColumn(column).setCellEditor(new ButtonEditor(new JCheckBox(), action));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, e.getActionCommand());
            ((AbstractAction) this).actionPerformed(event);
        }

        private void fireEditingStopped() {
            if (table.getCellEditor() != null) {
                table.getCellEditor().stopCellEditing();
            }
        }
    }

    /**
     * Renderer for rendering the button in the table cells.
     */
    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    /**
     * Editor for handling the button actions in the table cells.
     */
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private boolean clicked;

        public ButtonEditor(JCheckBox checkBox, Action action) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(action);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText((value == null) ? "" : value.toString());
            button.setActionCommand(String.valueOf(row));
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            clicked = false;
            return button.getText();
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }
}
