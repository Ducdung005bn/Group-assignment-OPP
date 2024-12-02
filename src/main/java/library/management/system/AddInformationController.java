package library.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

import main.classes.*;

/**
 * Controller for managing the addition of new users and documents to the library management system.
 * Supports adding borrowers, librarians, and documents (books, magazines, or theses).
 */
public class AddInformationController {
    private String kind;
    private JPanel jpnView;
    private JButton jbtAdd;
    private LibraryManagementSystem libraryManagementSystem;

    public AddInformationController(){}
    /**
     * Constructor to initialize the AddInformationController.
     *
     * @param kind The type of entity being added (either "borrower", "librarian", or "document").
     * @param jpnView The JPanel where the form will be displayed.
     * @param jbtAdd The JButton that will trigger the addition of the entity.
     * @param libraryManagementSystem The instance of the library management system.
     */
    public AddInformationController(String kind, JPanel jpnView, JButton jbtAdd, LibraryManagementSystem libraryManagementSystem) {
        this.kind = kind;
        this.jpnView = jpnView;
        this.jbtAdd = jbtAdd;
        this.libraryManagementSystem = libraryManagementSystem;

        if (kind.equals("borrower") || kind.equals("librarian")) {
            setupAddUserForm();
        } else if (kind.equals("document")) {
          setupAddDocumentForm();
        }

    }

    /**
     * Sets up the form to add a new user (borrower or librarian).
     */
    private void setupAddUserForm() {
         // Clear the current view and set gridLayout for label/input pairs
        jpnView.removeAll();
        jpnView.setLayout(new GridLayout(0, 2, 5, 5));
        String blank = "      ";

        // Label and TextField for userName
        JLabel userNameLabel = new JLabel(blank + "Enter Name: ");
        JTextField userNameField = new JTextField(20);
        jpnView.add(userNameLabel);
        jpnView.add(userNameField);

        // Label and TextField for userDateOfBirth
        JLabel userDateOfBirthLabel = new JLabel(blank + "Enter Date of Birth (yyyy-MM-dd):           ");
        JTextField userDateOfBirthField = new JTextField(20);
        jpnView.add(userDateOfBirthLabel);
        jpnView.add(userDateOfBirthField);

        // Label and CheckBox for isStudent
        JCheckBox isStudentCheckBox = new JCheckBox("Yes");
        JLabel isStudentLabel = new JLabel(blank + "Be a student or not (yes/no): ");
        jpnView.add(isStudentLabel);
        jpnView.add(isStudentCheckBox);

        if (kind.equals("borrower")) {
            isStudentCheckBox.setVisible(true);
            isStudentLabel.setVisible(true);
        } else if (kind.equals("librarian")) {
            isStudentCheckBox.setVisible(false);
            isStudentLabel.setVisible(false);
        }

        // Label and TextField for userEmailAddress
        JLabel userEmailAddressLabel = new JLabel(blank + "Enter Email Address: ");
        JTextField userEmailAddressField = new JTextField(20);
        jpnView.add(userEmailAddressLabel);
        jpnView.add(userEmailAddressField);

        // Button for sending verification code
        JButton jbtSendCode = new JButton("Send Verification Code");
        jbtSendCode.setVisible(true);
        jpnView.add(new JLabel(""));
        jpnView.add(jbtSendCode);

        // Label and TextField for verificationCode
        JLabel verificationCodeLabel = new JLabel(blank + "Enter Verification Code: ");
        JTextField verificationCodeField = new JTextField(20);
        jpnView.add(verificationCodeLabel);
        jpnView.add(verificationCodeField);

        // Label and TextField for userPassword
        JLabel userPasswordLabel = new JLabel(blank + "Enter Password: ");
        JTextField userPasswordField = new JTextField(20);
        jpnView.add(userPasswordLabel);
        jpnView.add(userPasswordField);

        // Create a verification code
        String verificationCode = generateVerificationCode(6);

        jbtSendCode.addActionListener(e -> {
            String userEmailAddress = userEmailAddressField.getText();
            sendVerificationCode(userEmailAddress, verificationCode, libraryManagementSystem, jpnView);
        });

        JTextField[] inputFields = {
                userNameField,
                userDateOfBirthField,
                userEmailAddressField,
                verificationCodeField,
                userPasswordField
        };

        jbtAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processUserForm(kind, inputFields, isStudentCheckBox, verificationCode);
            }
        });

        // Refresh the view
        jpnView.revalidate();
        jpnView.repaint();
    }

    /**
     * Processes the input fields to create either a Borrower or Librarian object,
     * validates the input data, and saves it to the library management system.
     *
     * @param kind            the type of user to create ("borrower" or "librarian").
     * @param inputFields     an array of JTextFields containing the input data.
     *                        Expected order: [userNameField, userDateOfBirthField, userEmailAddressField, verificationCodeField, userPasswordField].
     * @param isStudentCheckBox a JCheckBox indicating whether the user is a student (applicable for Borrower only).
     * @param verificationCode a code sent to the user's email to verify.
     */
    private void processUserForm(String kind, JTextField[] inputFields, JCheckBox isStudentCheckBox, String verificationCode) {
        String userName = inputFields[0].getText();
        String userDateOfBirth = inputFields[1].getText();
        String userEmailAddress = inputFields[2].getText();
        String enteredVerificationCode = inputFields[3].getText();
        String userPassword = inputFields[4].getText();
        boolean isStudent = isStudentCheckBox.isSelected();

        // Validate form fields
        if (userName.isEmpty() || userDateOfBirth.isEmpty() || userEmailAddress.isEmpty() ||
                enteredVerificationCode.isEmpty() || userPassword.isEmpty()) {
            JOptionPane.showMessageDialog(jpnView, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidName(userName)) return;

        if (!isValidDate(userDateOfBirth)) {
            JOptionPane.showMessageDialog(jpnView, "Invalid format for Date", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(userEmailAddress)) {
            JOptionPane.showMessageDialog(jpnView, "Invalid format for Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!enteredVerificationCode.equals(verificationCode)) {
            JOptionPane.showMessageDialog(jpnView, "Invalid Verification Code", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Process user creation based on type
        if (kind.equals("borrower")) {
            Borrower borrower = new Borrower();
            borrower.setUserName(userName);
            borrower.setUserDateOfBirth(java.sql.Date.valueOf(userDateOfBirth));
            borrower.setUserEmailAddress(userEmailAddress);
            borrower.setUserPassword(userPassword);
            borrower.setIsStudent(isStudent);

            libraryManagementSystem.userNumb++;
            borrower.setUserID(libraryManagementSystem.userNumb);
            libraryManagementSystem.borrowerList.add(borrower);
            LogInController.librarian.addUserAction(borrower);
            libraryManagementSystem.saveData();

            JOptionPane.showMessageDialog(jpnView, "Borrower added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (kind.equals("librarian")) {
            Librarian librarian = new Librarian();
            librarian.setUserName(userName);
            librarian.setUserDateOfBirth(java.sql.Date.valueOf(userDateOfBirth));
            librarian.setUserEmailAddress(userEmailAddress);
            librarian.setUserPassword(userPassword);

            libraryManagementSystem.userNumb++;
            librarian.setUserID(libraryManagementSystem.userNumb);
            libraryManagementSystem.librarianList.add(librarian);
            LogInController.librarian.addUserAction(librarian);
            libraryManagementSystem.saveData();

            JOptionPane.showMessageDialog(jpnView, "Librarian added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        setupAddUserForm();
    }

    /**
     * Generates a random verification code consisting of letters and numbers.
     *
     * @param length the length of the verification code
     * @return a randomly generated verification code as a String
     */
    private String generateVerificationCode(int length) {
        // Define the character pool (uppercase letters and digits)
        String characterPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Use Random to generate the code
        Random random = new Random();
        StringBuilder verificationCode = new StringBuilder();

        // Generate a random character from the pool for the specified length
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterPool.length());
            verificationCode.append(characterPool.charAt(randomIndex));
        }

        return verificationCode.toString();
    }

    /**
     * Validates the date input using the format yyyy-MM-dd.
     *
     * @param dateInput The date string to validate.
     * @return true if the date format is valid, false otherwise.
     */
    protected boolean isValidDate(String dateInput) {
        // Regular expression for matching date in "yyyy-MM-dd" format
        String regex = "^\\d{4}-(\\d{1,2})-(\\d{1,2})$";

        // If the input doesn't match the regular expression for the date format, return false
        if (!dateInput.matches(regex)) {
            return false;
        }

        // Split the date into year, month, and day parts
        String[] parts = dateInput.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        // Normalize the date into "yyyy-MM-dd" format, ensuring two-digit month and day values
        String normalizedDate = String.format("%04d-%02d-%02d", year, month, day);

        try {
            // Attempt to parse the normalized date and check if it's a valid date
            java.sql.Date.valueOf(normalizedDate);
            return true;
        } catch (IllegalArgumentException e) {
            // If an exception is thrown, it means the date is not valid (e.g., invalid day or month)
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false; // Email cannot be null or empty
        }

        // Regular expression to validate email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Create a Pattern object from the regex
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a matcher to check if the email matches the pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, false otherwise
        return matcher.matches();
    }

    protected boolean isValidName(String name) {
        // Check if the name only contains letters, single spaces between words, and optional dots
        if (!name.matches("^(?!.*\\s\\s)(?!.*\\.\\S)[a-zA-Z\\s.]*\\.$|^(?!.*\\s\\s)(?!.*\\.\\S)[a-zA-Z\\s.]*$")) {
            JOptionPane.showMessageDialog(null, "Name must only contain letters, single spaces, and optional dots between words", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    /**
     * Sends a verification code to the specified email address.
     *
     * @param userEmailAddress the email address to send the verification code to
     * @param verificationCode the code generated randomly and sent to the email address
     * @param libraryManagementSystem the LibraryManagementSystem instance for validation
     * @param jpnView the parent component for displaying dialogs
     */
    public void sendVerificationCode(String userEmailAddress, String verificationCode, LibraryManagementSystem libraryManagementSystem, JPanel jpnView) {
        // Check if the email already exists in the system
        if (libraryManagementSystem.findUserByEmail(userEmailAddress) != null) {
            JOptionPane.showMessageDialog(jpnView, "This email has existed in the system.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate the email format
        if (!isValidEmail(userEmailAddress)) {
            JOptionPane.showMessageDialog(jpnView, "Invalid format for Email Address", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create and send the email
        EmailSender emailSender = new EmailSender();
        String subject = "Verification Code";
        String messageText = "Your verification code is: " + verificationCode + ".";

        emailSender.sendEmail(userEmailAddress, subject, messageText);
    }

    /**
     * Sets up the form for adding a new document (book, magazine, or thesis) to the library management system.
     * It includes dynamic fields that change based on the selected document type.
     */
    private void setupAddDocumentForm() {
        jpnView.removeAll();
        jpnView.setLayout(new GridLayout(0, 2, 5, 5));
        String blank = "      ";

        // Dropdown for selecting document type
        JLabel typeLabel = new JLabel(blank + "Select Type (book, magazine, thesis):       ");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"book", "magazine", "thesis"});
        jpnView.add(typeLabel);
        jpnView.add(typeComboBox);

        // Shared fields
        JLabel quantityLabel = new JLabel(blank + "Enter Quantity: ");
        JTextField quantityField = new JTextField(20);
        jpnView.add(quantityLabel);
        jpnView.add(quantityField);

        JLabel titleLabel = new JLabel(blank + "Enter Title: ");
        JTextField titleField = new JTextField(20);
        jpnView.add(titleLabel);
        jpnView.add(titleField);

        JLabel authorLabel = new JLabel(blank + "Enter Author: ");
        JTextField authorField = new JTextField(20);
        jpnView.add(authorLabel);
        jpnView.add(authorField);

        JLabel descriptionLabel = new JLabel(blank + "Enter Description: ");
        JTextField descriptionField = new JTextField(20);
        jpnView.add(descriptionLabel);
        jpnView.add(descriptionField);

        JLabel languageLabel = new JLabel(blank + "Enter Language: ");
        JTextField languageField = new JTextField(20);
        jpnView.add(languageLabel);
        jpnView.add(languageField);

        JLabel pagesLabel = new JLabel(blank + "Enter Number of Pages: ");
        JTextField pagesField = new JTextField(20);
        jpnView.add(pagesLabel);
        jpnView.add(pagesField);

        JLabel isbnLabel = new JLabel(blank + "Enter ISBN: ");
        JTextField isbnField = new JTextField(20);
        jpnView.add(isbnLabel);
        jpnView.add(isbnField);

        JLabel googleLinkLabel = new JLabel(blank + "Enter Google Link: ");
        JTextField googleLinkField = new JTextField(20);
        jpnView.add(googleLinkLabel);
        jpnView.add(googleLinkField);
        googleLinkField.setEditable(false);

        JLabel imageUrlLable = new JLabel(blank + "Enter Image URL: ");
        JTextField imageUrlField = new JTextField(20);
        jpnView.add(imageUrlLable);
        jpnView.add(imageUrlField);
        imageUrlField.setEditable(false);

        // Dynamic fields
        JLabel dynamicField1Label = new JLabel();
        JTextField dynamicField1Field = new JTextField(20);
        jpnView.add(dynamicField1Label);
        jpnView.add(dynamicField1Field);

        JLabel dynamicField2Label = new JLabel();
        JTextField dynamicField2Field = new JTextField(20);
        jpnView.add(dynamicField2Label);
        jpnView.add(dynamicField2Field);

        JLabel dynamicField3Label = new JLabel();
        JTextField dynamicField3Field = new JTextField(20);
        jpnView.add(dynamicField3Label);
        jpnView.add(dynamicField3Field);

        //the default in the combo box is "book"
        if ("book".equals((String) typeComboBox.getSelectedItem())) {
            dynamicField1Label.setText(blank + "Enter Genre: ");
            dynamicField2Label.setText(blank + "Enter Publisher: ");
            dynamicField3Label.setText("");
            dynamicField3Field.setVisible(false);
        }

        // Add action listener to update dynamic fields
        typeComboBox.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            updateDynamicFields(selectedType, dynamicField1Label, dynamicField2Label, dynamicField3Label, dynamicField3Field, jpnView);
        });

        // jcbUseAPI to choose whether to use API or not
        JCheckBox jcbUseAPI = new JCheckBox("Use API to autofill details");
        jpnView.add(jcbUseAPI);

        JButton jbtAutoFill = new JButton("Auto-fill Data");
        jbtAutoFill.setVisible(false);  // Hide initially
        jpnView.add(jbtAutoFill);

        // Add action listener to jcbUseAPI to toggle button visibility
        jcbUseAPI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jcbUseAPI.isSelected()) {
                    jbtAutoFill.setVisible(true);  // Show the button if checkbox is selected
                } else {
                    jbtAutoFill.setVisible(false);  // Hide the button if checkbox is deselected
                }
                jpnView.revalidate();
                jpnView.repaint();
            }
        });

        JTextField[] fields = {
                titleField, authorField, descriptionField, languageField,
                pagesField, googleLinkField, imageUrlField, dynamicField1Field,
                dynamicField2Field, dynamicField3Field, quantityField, isbnField
        };
        JLabel[] labels = {dynamicField1Label, dynamicField2Label, dynamicField3Label};

        // Action listener for auto-fill button to populate fields using an external API
        jbtAutoFill.addActionListener(e -> {
            handleAutoFillAction(isbnField, fields, labels, typeComboBox, jpnView);
        });

        // Add action listener to add button
        jbtAdd.addActionListener(e -> {
            //Check whether the document was added or not
            Document document = libraryManagementSystem.findDocumentByISBN(fields[11].getText());
            if (document == null) {
                String selectedType = (String) typeComboBox.getSelectedItem();
                handleAddDocument(selectedType, fields, libraryManagementSystem, jpnView);
            }
        });

        jpnView.revalidate();
        jpnView.repaint();
    }

    /**
     * Handles the auto-fill action for the form by fetching book information
     * from an external API based on the provided ISBN and populating the fields.
     *
     * @param isbnField    the text field where the ISBN is entered
     * @param fields       an array of text fields to populate with book information:
     * @param labels       an array of labels corresponding to dynamic fields:
     * @param typeComboBox the combo box for selecting the document type
     * @param jpnView      the panel to display error messages if validation fails
     */
    private void handleAutoFillAction(
            JTextField isbnField,
            JTextField[] fields,
            JLabel[] labels,
            JComboBox<String> typeComboBox,
            JPanel jpnView
    ) {
        String blank = "      ";
        typeComboBox.setSelectedItem("book");
        labels[0].setText(blank + "Enter Genre: ");
        labels[1].setText(blank + "Enter Publisher: ");
        labels[2].setText("");
        fields[9].setVisible(false); // Hide dynamicField3Field

        String isbn = isbnField.getText();

        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(jpnView, "Please enter ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String title = "";
        String author = "";
        String description = "";
        String language = "";
        int pages = 0;
        String dynamicField1 = "";
        String dynamicField2 = "";
        String dynamicField3 = "";
        String googleLink = "";
        String imageUrl = "";

        Object result = GoogleBooksAPI.fetchBook(isbn);

        if (result instanceof Book) {
            Book book = (Book) result;
            title = book.getDocumentTitle();
            author = book.getDocumentAuthor();
            description = book.getDocumentDescription();
            language = book.getDocumentLanguage();
            pages = book.getDocumentPage();
            googleLink = book.getDocumentGoogleLink();
            imageUrl = book.getDocumentImageUrl();
            dynamicField1 = book.getBookGenre();
            dynamicField2 = book.getBookPublisher();
            dynamicField3 = "";
        } else if (result instanceof String) {
            JOptionPane.showMessageDialog(jpnView, result, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        fields[0].setText(title);        // Title
        fields[1].setText(author);       // Author
        fields[2].setText(description);  // Description
        fields[3].setText(language);     // Language
        fields[4].setText(String.valueOf(pages)); // Pages
        fields[5].setText(googleLink);   // Google Link
        fields[6].setText(imageUrl);     // Image URL
        fields[7].setText(dynamicField1); // Genre
        fields[8].setText(dynamicField2); // Publisher
        fields[9].setText(dynamicField3); // Dynamic Field 3

        if (pages == 0) {
            fields[4].setText(""); // Clear pages field if 0
        }
    }

    /**
     * Updates the dynamic fields based on the selected document type in the combo box.
     *
     * @param selectedType the selected document type from the combo box
     * @param dynamicField1Label the label for the first dynamic field
     * @param dynamicField2Label the label for the second dynamic field
     * @param dynamicField3Label the label for the third dynamic field
     * @param dynamicField3Field the input field for the third dynamic field
     * @param jpnView the parent panel to revalidate and repaint
     */
    public void updateDynamicFields(String selectedType, JLabel dynamicField1Label, JLabel dynamicField2Label, JLabel dynamicField3Label, JTextField dynamicField3Field, JPanel jpnView) {
        String blank = "      ";

        switch (selectedType) {
            case "book":
                dynamicField1Label.setText(blank + "Enter Genre: ");
                dynamicField2Label.setText(blank + "Enter Publisher: ");
                dynamicField3Label.setText("");
                dynamicField3Field.setVisible(false);
                break;
            case "magazine":
                dynamicField1Label.setText(blank + "Enter Subject: ");
                dynamicField2Label.setText(blank + "Enter Frequency: ");
                dynamicField3Label.setText(blank + "Enter Issue Number: ");
                dynamicField3Field.setVisible(true);
                break;
            case "thesis":
                dynamicField1Label.setText(blank + "Enter Subject: ");
                dynamicField2Label.setText(blank + "Enter Degree: ");
                dynamicField3Label.setText(blank + "Enter University: ");
                dynamicField3Field.setVisible(true);
                break;
        }
        jpnView.revalidate();
        jpnView.repaint();
    }

    /**
     * Adds a new document to the library management system based on the selected type.
     *
     * @param selectedType the type of the document (book, magazine, thesis)
     * @param fields the input fields for the document attributes
     * @param libraryManagementSystem the library management system instance
     * @param jpnView the parent panel to display messages
     */
    private void handleAddDocument(String selectedType, JTextField[] fields, LibraryManagementSystem libraryManagementSystem, JPanel jpnView) {
        try {
            // Validate empty fields
            for (JTextField field : fields) {
                if (field.isVisible() && field.isEditable() && field.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(jpnView, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Parse common fields
            int quantity = Integer.parseInt(fields[10].getText());
            String title = fields[0].getText();
            String author = fields[1].getText();
            String description = fields[2].getText();
            String language = fields[3].getText();
            int pages = Integer.parseInt(fields[4].getText());
            String isbn = fields[11].getText();
            String dynamicField1 = fields[7].getText();
            String dynamicField2 = fields[8].getText();
            String dynamicField3 = fields[9].isVisible() ? fields[9].getText() : null;
            String googleLink = fields[5].getText();
            String imageUrl = fields[6].getText();

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(jpnView, "Quantity must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (pages <= 0) {
                JOptionPane.showMessageDialog(jpnView, "Pages must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isValidName(author) || !isValidName(language)) {
                return;
            }

            //Check whether the document was added or not
            Document document = libraryManagementSystem.findDocumentByISBN(isbn);
            if (document != null) {
                JOptionPane.showMessageDialog(jpnView, "The document has existed in this system.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create and add the document based on its type
            switch (selectedType) {
                case "book":
                    Book book = new Book();
                    book.setDocumentQuantityAll(quantity);
                    book.setDocumentTitle(title);
                    book.setDocumentAuthor(author);
                    book.setDocumentDescription(description);
                    book.setDocumentLanguage(language);
                    book.setDocumentPage(pages);
                    book.setDocumentISBN(isbn);
                    book.setBookGenre(dynamicField1);
                    book.setBookPublisher(dynamicField2);
                    book.setDocumentGoogleLink(googleLink);
                    book.setDocumentImageUrl(imageUrl);

                    libraryManagementSystem.bookList.add(book);
                    break;

                case "magazine":
                    Magazine magazine = new Magazine();
                    magazine.setDocumentQuantityAll(quantity);
                    magazine.setDocumentTitle(title);
                    magazine.setDocumentAuthor(author);
                    magazine.setDocumentDescription(description);
                    magazine.setDocumentLanguage(language);
                    magazine.setDocumentPage(pages);
                    magazine.setDocumentISBN(isbn);
                    magazine.setMagazineSubject(dynamicField1);
                    magazine.setMagazineFrequency(Integer.parseInt(dynamicField2));
                    magazine.setMagazineIssueNumb(Integer.parseInt(dynamicField3));
                    magazine.setDocumentGoogleLink(googleLink);
                    magazine.setDocumentImageUrl(imageUrl);
                    libraryManagementSystem.magazineList.add(magazine);
                    break;

                case "thesis":
                    Thesis thesis = new Thesis();
                    thesis.setDocumentQuantityAll(quantity);
                    thesis.setDocumentTitle(title);
                    thesis.setDocumentAuthor(author);
                    thesis.setDocumentDescription(description);
                    thesis.setDocumentLanguage(language);
                    thesis.setDocumentPage(pages);
                    thesis.setDocumentISBN(isbn);
                    thesis.setThesisSubject(dynamicField1);
                    thesis.setThesisDegree(dynamicField2);
                    thesis.setThesisUniversity(dynamicField3);
                    thesis.setDocumentGoogleLink(googleLink);
                    thesis.setDocumentImageUrl(imageUrl);
                    libraryManagementSystem.thesisList.add(thesis);
                    break;

                default:
                    JOptionPane.showMessageDialog(jpnView, "Invalid document type.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            // Save and notify success
            LogInController.librarian.dealWithDocumentAction(isbn, "Add");
            libraryManagementSystem.saveData();
            JOptionPane.showMessageDialog(jpnView, "The document is added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpnView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        setupAddDocumentForm();

        jpnView.revalidate();
        jpnView.repaint();
    }
}
