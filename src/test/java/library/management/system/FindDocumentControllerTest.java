package library.management.system;

import main.classes.LibraryManagementSystem;
import main.classes.Book;
import main.classes.Magazine;
import main.classes.Thesis;
import main.classes.Document;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

class FindDocumentControllerTest {
    private FindDocumentController findDocumentController;
    private JPanel jpnView;
    private JTextField jtfFindByGenre;
    private JTextField jtfFindByTitle;
    private JTextField jtfFindByAuthor;
    private LibraryManagementSystem libraryManagementSystem;

    @BeforeEach
    void setUp() {
        // Initialize test data
        TestDataInitializer initializer = new TestDataInitializer();
        libraryManagementSystem = TestDataInitializer.initializeLibraryData();

        // Initialize UI components
        jpnView = new JPanel();
        jtfFindByGenre = new JTextField(20);
        jtfFindByTitle = new JTextField(20);
        jtfFindByAuthor = new JTextField(20);

        // Create the FindDocumentController instance
        findDocumentController = new FindDocumentController(jpnView, jtfFindByGenre, jtfFindByTitle, jtfFindByAuthor);
    }

    @AfterEach
    void tearDown() {
        jpnView.removeAll(); // Clear the panel after each test
    }

    @Test
    void setDataToTable() {
        // Call the method to set data to the table
        findDocumentController.setDataToTable(libraryManagementSystem);

        // Get the JTable from the JPanel
        JTable table = (JTable) ((JScrollPane) jpnView.getComponent(0)).getViewport().getView();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Assert that the correct number of rows is populated
        assertEquals(10, model.getRowCount(), "Expected 10 documents to be displayed."); // Ensure you actually have 10 documents.

        // Assert the content of the first row (Book)
        assertEquals("5 / 5", model.getValueAt(0, 0)); // Quantity for Book
        assertEquals("Fiction", model.getValueAt(0, 1)); // Genre for Book
        assertEquals("9780061120084", model.getValueAt(0, 2)); // ISBN for Book
        assertEquals("Book Title", model.getValueAt(0, 3)); // Title for Book
        assertEquals("Author A", model.getValueAt(0, 4)); // Author for Book

        // Assert the content of the second row (Magazine)
        assertEquals("5 / 5", model.getValueAt(1, 0)); // Quantity for Magazine
        assertEquals("Lifestyle", model.getValueAt(1, 1)); // Genre for Magazine
        assertEquals("9781408845611", model.getValueAt(1, 2)); // ISBN for Magazine
        assertEquals("Magazine Title", model.getValueAt(1, 3)); // Title for Magazine
        assertEquals("Author B", model.getValueAt(1, 4)); // Author for Magazine

        // Assert the content of the third row (Thesis)
        assertEquals("5 / 5", model.getValueAt(2, 0)); // Quantity for Thesis
        assertEquals("Science", model.getValueAt(2, 1)); // Genre for Thesis
        assertEquals("1122334455", model.getValueAt(2, 2)); // ISBN for Thesis
        assertEquals("Thesis Title", model.getValueAt(2, 3)); // Title for Thesis
        assertEquals("Author C", model.getValueAt(2, 4)); // Author for Thesis

        // Add additional assertions for rows 3 to 9 if you have more documents
    }
}
