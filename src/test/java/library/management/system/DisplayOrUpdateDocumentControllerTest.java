package library.management.system;

import main.classes.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

class DisplayOrUpdateDocumentControllerTest {
    private JPanel jpnView;
    private JTextField jtfDisplayOrUpdateDocument;
    private LibraryManagementSystem libraryManagementSystem;
    private String kind;
    private Borrower borrower;
    private Librarian librarian;
    private JPanel picturePanel;
    private JTextField[] fields;
    private Document document;

    private JTextField remainingQuantityField;
    private JTextField totalQuantityField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField descriptionField;
    private JTextField languageField;
    private JTextField pagesField;
    private JTextField isbnField;
    private JTextField dynamicField1Field;
    private JTextField dynamicField2Field;
    private JTextField dynamicField3Field;

    @BeforeEach
    void setUp() {
        TestDataInitializer initializer = new TestDataInitializer();
        libraryManagementSystem = TestDataInitializer.initializeLibraryData();
        borrower = libraryManagementSystem.borrowerList.get(0);
        librarian = libraryManagementSystem.librarianList.get(2);
        jpnView = new JPanel();
        jtfDisplayOrUpdateDocument = new JTextField();
        picturePanel = new JPanel();
        fields = new JTextField[10];
        document = libraryManagementSystem.bookList.get(0);
        DisplayOrUpdateDocumentController disPlayOrUpDateDocument = new DisplayOrUpdateDocumentController(kind,jpnView,jtfDisplayOrUpdateDocument,libraryManagementSystem);
        remainingQuantityField = new JTextField();
        totalQuantityField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        descriptionField = new JTextField();
        languageField = new JTextField();
        pagesField = new JTextField();
        isbnField = new JTextField();
        dynamicField1Field = new JTextField();
        dynamicField2Field = new JTextField();
        dynamicField3Field = new JTextField();

        // Initialize the JTextField array
        fields = new JTextField[]{
                remainingQuantityField,
                totalQuantityField,   // index 0
                titleField,          // index 1
                authorField,         // index 2
                descriptionField,    // index 3
                languageField,       // index 4
                pagesField,          // index 5
                isbnField,           // index 6
                dynamicField1Field,  // index 7
                dynamicField2Field,  // index 8
                dynamicField3Field   // index 9
        };
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void displayOrUpdateDocumentDetails() {

    }
    @Test
    void testAddBookCoverToJPanel_ValidImageUrl() {
        // Given a valid image URL (you can use a publicly accessible image URL)
        String imageUrl = "https://books.google.com/books/content?id=6dpGdCveb-YC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE73KPb_JX5oGAD5q7ybqrdeTFCktPkHnf3Ah88wlrQ-J9p76eG_Dgzsd2GTVDLHFuD08Ynl5ZW50Qz2H0TvVnZrp7TPG7R6TNdWt45Eiv_GdO-TvZqtttkV8PTVpfEIm0YvBf1bp"; // Replace with a valid image URL
        int width = 128;
        int height = 128;

        // When adding the book cover to the panel
        DisplayOrUpdateDocumentController.addBookCoverToJPanel(imageUrl, picturePanel, width, height);

        // Then the picturePanel should contain one JLabel
        assertEquals(1, picturePanel.getComponentCount());
        Component component = picturePanel.getComponent(0);
        assertTrue(component instanceof JLabel);

        // Check if the JLabel contains the correct ImageIcon
        JLabel label = (JLabel) component;
        assertNotNull(label.getIcon());
        assertEquals(width, label.getIcon().getIconWidth());
        assertEquals(height, label.getIcon().getIconHeight());
    }

    @Test
    void testAddBookCoverToJPanel_EmptyImageUrl() {
        // Given an empty image URL
        String imageUrl = "";
        int width = 100;
        int height = 100;

        // When adding the book cover to the panel
        DisplayOrUpdateDocumentController.addBookCoverToJPanel(imageUrl, picturePanel, width, height);

        // Then the picturePanel should be empty
        assertEquals(0, picturePanel.getComponentCount());
    }

    @Test
    void testAddBookCoverToJPanel_InvalidImageUrl() {
        // Given an invalid image URL
        String imageUrl = "https://www.google.com/"; // Replace with an invalid URL
        int width = 100;
        int height = 100;

        // When adding the book cover to the panel
        DisplayOrUpdateDocumentController.addBookCoverToJPanel(imageUrl, picturePanel, width, height);

        // Then the picturePanel should still be one
        assertEquals(1, picturePanel.getComponentCount());
        // Note: You might want to use a mocking framework to verify that the JOptionPane was called
    }
    @Test
    void testUpdateDocument_SuccessfulUpdate() {
        // Fill in the fields with valid data
        fields[1].setText("10"); // Total Document Quantity
        fields[2].setText("New Title"); // Document Title
        fields[3].setText("New Author"); // Document Author
        fields[4].setText("New Description"); // Document Description
        fields[5].setText("English"); // Document Language
        fields[6].setText("120"); // Document Page
        fields[7].setText("1234567890"); // Document ISBN
        fields[8].setText("Fiction"); // Dynamic Field 1 (Genre for Book)
        fields[9].setText("New Publisher"); // Dynamic Field 2 (Publisher for Book)
        fields[10].setText(""); // Dynamic Field 3 (Optional)

        // Call the method to test
        DisplayOrUpdateDocumentController.updateDocument(fields, document, jpnView, libraryManagementSystem, "Book");

        // Validate that the document was updated correctly
        assertEquals(10, document.getDocumentQuantityAll());
        assertEquals("New Title", document.getDocumentTitle());
        assertEquals("New Author", document.getDocumentAuthor());
        assertEquals("New Description", document.getDocumentDescription());
        assertEquals("English", document.getDocumentLanguage());
        assertEquals(120, document.getDocumentPage());
        assertEquals("1234567890", document.getDocumentISBN());

        // Validate specific fields for Book
        Book book = (Book) document;
        assertEquals("Fiction", book.getBookGenre());
        assertEquals("New Publisher", book.getBookPublisher());
    }

    @Test
    void testUpdateDocument_EmptyFields() {
        // Setup initial state
        fields[1].setText(""); // Total Document Quantity left empty
        fields[2].setText("New Title");
        fields[3].setText("New Author");
        fields[4].setText("New Description");
        fields[5].setText("English");
        fields[6].setText("120");
        fields[7].setText("1234567890");
        fields[8].setText("Fiction");
        fields[9].setText("Publisher");
        fields[10].setText(""); // Optional

        // Call the method to test
        DisplayOrUpdateDocumentController.updateDocument(fields, document, jpnView, libraryManagementSystem, "Book");

        // Validate that the document was not updated
        assertEquals(346, document.getDocumentPage()); // Assuming the original value was 100
        assertEquals("To Kill a Mockingbird", document.getDocumentTitle()); // Assuming the original value was unchanged
        // Add more assertions as necessary to validate no changes occurred
    }

    @Test
    void testUpdateDocument_InvalidNumberFormat() {
        // Setup initial state
        fields[1].setText("invalid"); // Total Document Quantity
        fields[2].setText("New Title");
        fields[3].setText("New Author");
        fields[4].setText("New Description");
        fields[5].setText("English");
        fields[6].setText("120");
        fields[7].setText("1234567890");
        fields[8].setText("Fiction");
        fields[9].setText("Publisher");
        fields[10].setText(""); // Optional

        // Call the method to test
        DisplayOrUpdateDocumentController.updateDocument(fields, document, jpnView, libraryManagementSystem, "Book");

        // Validate that the document was not updated
        assertEquals(346, document.getDocumentPage()); // Assuming the original value was 100
        assertEquals("To Kill a Mockingbird", document.getDocumentTitle()); // Assuming the original value was unchanged
        // Add more assertions as necessary to validate no changes occurred
    }
}