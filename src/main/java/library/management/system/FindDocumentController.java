package library.management.system;

import main.classes.Magazine;
import main.classes.Thesis;
import main.classes.LibraryManagementSystem;
import main.classes.Book;
import main.classes.Document; 

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Controller class for searching and displaying documents in the library system.
 * It includes filtering functionalities for genre, title, and author.
 */
public class FindDocumentController {
    private JPanel jpnView;
    private JTextField jtfFindByGenre;
    private JTextField jtfFindByTitle;
    private JTextField jtfFindByAuthor;
    private TableDisplay tableDisplay = null;
    private final String[] COLUMNS = {"Quantity", "Genre", "ISBN", "Title", "Author"};
    private TableRowSorter<TableModel> rowSorter = null;

    /**
     * Constructor initializes the panel views and text fields for searching documents.
     *
     * @param jpnView The panel where the document table will be displayed.
     * @param jtfFindByGenre TextField for filtering by genre.
     * @param jtfFindByTitle TextField for filtering by title.
     * @param jtfFindByAuthor TextField for filtering by author.
     */
    public FindDocumentController(JPanel jpnView, JTextField jtfFindByGenre, JTextField jtfFindByTitle, JTextField jtfFindByAuthor) {
        this.jpnView = jpnView;
        this.jtfFindByGenre = jtfFindByGenre;
        this.jtfFindByTitle = jtfFindByTitle;
        this.jtfFindByAuthor = jtfFindByAuthor;
        this.tableDisplay = new TableDisplay();
    }

    /**
     * Sets the data for the document table, and initializes the filters and table.
     *
     * @param libraryManagementSystem The system to fetch the documents from.
     */
    public void setDataToTable(LibraryManagementSystem libraryManagementSystem) {
        List<Document> documentList = libraryManagementSystem.getAllDocuments();
        DefaultTableModel model = tableDisplay.setDocumentTable(documentList, COLUMNS);
        JTable table = new JTable(model);

        // Enable row sorting
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        jtfFindByGenre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { applyFilters(); }
            @Override
            public void removeUpdate(DocumentEvent e) { applyFilters(); }
            @Override
            public void changedUpdate(DocumentEvent e) { applyFilters(); }
        });

        jtfFindByTitle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { applyFilters(); }
            @Override
            public void removeUpdate(DocumentEvent e) { applyFilters(); }
            @Override
            public void changedUpdate(DocumentEvent e) { applyFilters(); }
        });

        jtfFindByAuthor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { applyFilters(); }
            @Override
            public void removeUpdate(DocumentEvent e) { applyFilters(); }
            @Override
            public void changedUpdate(DocumentEvent e) { applyFilters(); }
        });

        // design
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new CardLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }

    /**
     * Applies filters to the document table based on the current text in the search fields.
     */
    private void applyFilters() {
        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        String genreText = jtfFindByGenre.getText().trim();
        if (!genreText.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + genreText, 1)); // Apply to Genre column only
        }

        String titleText = jtfFindByTitle.getText().trim();
        if (!titleText.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + titleText, 3)); // Apply to Title column only
        }

        String authorText = jtfFindByAuthor.getText().trim();
        if (!authorText.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + authorText, 4)); // Apply to Author column only
        }

        if (!filters.isEmpty()) {
            rowSorter.setRowFilter(RowFilter.andFilter(filters));
        } else {
            rowSorter.setRowFilter(null); // Clear filters if no input is given
        }
    }

    /**
     * Helper class to manage the creation of the document table and populating it with data.
     */
    private class TableDisplay {
        /**
         * Sets up the document table based on the list of documents.
         *
         * @param documentList List of documents to populate in the table.
         * @param columnList List of column names for the table.
         * @return DefaultTableModel populated with document data.
         */
        public DefaultTableModel setDocumentTable(List<Document> documentList, String[] columnList) {
            int columns = columnList.length;

            DefaultTableModel dtm = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;  // Make cells non-editable
                }
    
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnIndex == 7 ? Boolean.class : String.class;
                    // Set class type for all columns to String (except Boolean)
                }
            };
            dtm.setColumnIdentifiers(columnList);

            // Populate the table with document data
            Object[] obj;
            Document document = null;
            for (int i = 0; i < documentList.size(); i++) {
                document = documentList.get(i);
                obj = new Object[columns];
                obj[0] = String.valueOf(document.documentQuantity) + " / " + String.valueOf(document.getDocumentQuantityAll());
                if (document instanceof Book) {
                    obj[1] = ((Book) document).getBookGenre();
                } else if (document instanceof Thesis) {
                    obj[1] = "Thesis";
                } else if (document instanceof Magazine) {
                    obj[1] = "Magazine";
                }
                obj[2] = document.getDocumentISBN();
                obj[3] = document.getDocumentTitle();
                obj[4] = document.getDocumentAuthor();
                dtm.addRow(obj);
            }
            return dtm;
        }
    }   
    
}
