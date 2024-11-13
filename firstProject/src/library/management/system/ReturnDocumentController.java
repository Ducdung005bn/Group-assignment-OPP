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

public class ReturnDocumentController {
    private JPanel jpnView;
    private Borrower borrower;
    private LibraryManagementSystem libraryManagementSystem;
    private TableDisplay tableDisplay;
    private final String[] COLUMNS = {"Borrower ID", "Book ISBN", "Borrow Date", "Planned Return Date", "Status", "Return"};
    private DefaultTableModel model;

    public ReturnDocumentController(JPanel jpnView, Borrower borrower, LibraryManagementSystem libraryManagementSystem) {
        this.jpnView = jpnView;
        this.borrower = borrower;
        this.libraryManagementSystem = libraryManagementSystem;
        this.tableDisplay = new TableDisplay();
        setDataToTable();
    }

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

    private void returnDocument(BorrowData borrowData) {
        borrower.borrowingHistory.remove(borrowData);
        borrower.borrowingBookCount--;
    
        Date actualReturnDate = new Date();
    
        if (actualReturnDate.after(borrowData.getPlannedReturnDate())) {
            // Hiển thị thông báo trễ hạn
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
            // Hiển thị thông báo trả đúng hạn
            JOptionPane.showMessageDialog(
                null,
                "You have returned the book before the due date.",
                "On Time Return",
                JOptionPane.INFORMATION_MESSAGE
            );
            borrowData.setBorrowStatus("Returned On Time");
        }
    
        // Cập nhật lịch sử trả
        borrower.borrowedHistory.add(borrowData);
    
        // Tăng số lượng tài liệu trong thư viện
        Document document = libraryManagementSystem.findDocumentByISBN(borrowData.getBorrowedBookISBN());
        if (document != null) {
            document.documentQuantity++;
        }
        libraryManagementSystem.saveData();
    }

    private class TableDisplay {
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
