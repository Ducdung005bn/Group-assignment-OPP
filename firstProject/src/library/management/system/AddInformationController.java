package library.management.system;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.classes.*;

public class AddInformationController {
    private String kind;
    private JPanel jpnView;
    private JButton jbtAdd;
    private LibraryManagementSystem libraryManagementSystem; 
    
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
    
    private void setupAddUserForm() {
         // Clear the current view and set gridLayout for label/input pairs
        jpnView.removeAll();
        jpnView.setLayout(new GridLayout(0, 2, 5, 5));
        
        // Label and TextField for userName
        JLabel userNameLabel = new JLabel("Enter Name: ");
        JTextField userNameField = new JTextField(20);
        jpnView.add(userNameLabel);
        jpnView.add(userNameField);
        
        // Label and TextField for userDateOfBirth
        JLabel userDateOfBirthLabel = new JLabel("Enter Date of Birth (yyyy-MM-dd): ");
        JTextField userDateOfBirthField = new JTextField(20);
        jpnView.add(userDateOfBirthLabel);
        jpnView.add(userDateOfBirthField);

        // Label and CheckBox for isStudent
        JCheckBox isStudentCheckBox = new JCheckBox("Yes");
        if (kind.equals("borrower")) {
            JLabel isStudentLabel = new JLabel("Be a student or not (yes/no): ");
            jpnView.add(isStudentLabel);
            jpnView.add(isStudentCheckBox);
        }
    
        // Label and TextField for userPhoneNumb
        JLabel userPhoneNumbLabel = new JLabel("Enter Phone Number: ");
        JTextField userPhoneNumbField = new JTextField(20);
        jpnView.add(userPhoneNumbLabel);
        jpnView.add(userPhoneNumbField);
    
        // Label and TextField for userPassword
        JLabel userPasswordLabel = new JLabel("Enter Password: ");
        JTextField userPasswordField = new JTextField(20);
        jpnView.add(userPasswordLabel);
        jpnView.add(userPasswordField);
                        
        jbtAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                String userDateOfBirth = userDateOfBirthField.getText();
                String userPhoneNumb = userPhoneNumbField.getText();
                String userPassword = userPasswordField.getText();
                boolean isStudent = isStudentCheckBox.isSelected();
                
                if (userName.isEmpty() || userDateOfBirth.isEmpty() || userPhoneNumb.isEmpty() || userPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(jpnView, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!isValidDate(userDateOfBirth)) {
                    JOptionPane.showMessageDialog(jpnView, "Invalid Date format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Process the input depending on the user type (borrower or librarian)
                if (kind.equals("borrower")) {
                    Borrower borrower = new Borrower();
                    borrower.setUserName(userName);
                    borrower.setUserDateOfBirth(java.sql.Date.valueOf(userDateOfBirth));
                    borrower.setUserPhoneNumb(userPhoneNumb);
                    borrower.setUserPassword(userPassword);
                    borrower.setIsStudent(isStudent);
                    
                    libraryManagementSystem.userNumb ++;
                    borrower.setUserID(libraryManagementSystem.userNumb);
                    libraryManagementSystem.borrowerList.add(borrower);
                    libraryManagementSystem.saveData();

                    JOptionPane.showMessageDialog(jpnView, "Borrower added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (kind.equals("librarian")) {
                    Librarian librarian = new Librarian();
                    librarian.setUserName(userName);
                    librarian.setUserDateOfBirth(java.sql.Date.valueOf(userDateOfBirth));
                    librarian.setUserPhoneNumb(userPhoneNumb);
                    librarian.setUserPassword(userPassword);

                    libraryManagementSystem.userNumb ++;
                    librarian.setUserID(libraryManagementSystem.userNumb);
                    libraryManagementSystem.librarianList.add(librarian);
                    libraryManagementSystem.saveData();

                    JOptionPane.showMessageDialog(jpnView, "Librarian added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                setupAddUserForm();
            }
        });
        
        // Refresh the view
        jpnView.revalidate();
        jpnView.repaint();
    }
    
    private boolean isValidDate(String dateInput) {
        String regex = "^\\d{4}-(\\d{1,2})-(\\d{1,2})$";

        if (!dateInput.matches(regex)) {
            return false;
        }

        String[] parts = dateInput.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        String normalizedDate = String.format("%04d-%02d-%02d", year, month, day);

        try {
            java.sql.Date.valueOf(normalizedDate);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    private void setupAddDocumentForm() {
        jpnView.removeAll();
        jpnView.setLayout(new GridLayout(0, 2, 5, 5));

        // Dropdown for selecting document type
        JLabel typeLabel = new JLabel("Select Type (book, magazine, thesis): ");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"book", "magazine", "thesis"});
        jpnView.add(typeLabel);
        jpnView.add(typeComboBox);

        // Shared fields
        JLabel quantityLabel = new JLabel("Enter Quantity: ");
        JTextField quantityField = new JTextField(20);
        jpnView.add(quantityLabel);
        jpnView.add(quantityField);

        JLabel titleLabel = new JLabel("Enter Title: ");
        JTextField titleField = new JTextField(20);
        jpnView.add(titleLabel);
        jpnView.add(titleField);

        JLabel authorLabel = new JLabel("Enter Author: ");
        JTextField authorField = new JTextField(20);
        jpnView.add(authorLabel);
        jpnView.add(authorField);

        JLabel descriptionLabel = new JLabel("Enter Description: ");
        JTextField descriptionField = new JTextField(20);
        jpnView.add(descriptionLabel);
        jpnView.add(descriptionField);

        JLabel languageLabel = new JLabel("Enter Language: ");
        JTextField languageField = new JTextField(20);
        jpnView.add(languageLabel);
        jpnView.add(languageField);

        JLabel pagesLabel = new JLabel("Enter Number of Pages: ");
        JTextField pagesField = new JTextField(20);
        jpnView.add(pagesLabel);
        jpnView.add(pagesField);

        JLabel isbnLabel = new JLabel("Enter ISBN: ");
        JTextField isbnField = new JTextField(20);
        jpnView.add(isbnLabel);
        jpnView.add(isbnField);

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
            dynamicField1Label.setText("Enter Genre: ");
            dynamicField2Label.setText("Enter Publisher: ");
            dynamicField3Label.setText("");
            dynamicField3Field.setVisible(false);
        }

        // Add action listener to update dynamic fields
        typeComboBox.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            if ("book".equals(selectedType)) {
                dynamicField1Label.setText("Enter Genre: ");
                dynamicField2Label.setText("Enter Publisher: ");
                dynamicField3Label.setText("");
                dynamicField3Field.setVisible(false);
            } else if ("magazine".equals(selectedType)) {
                dynamicField1Label.setText("Enter Subject: ");
                dynamicField2Label.setText("Enter Frequency: ");
                dynamicField3Label.setText("Enter Issue Number: ");
                dynamicField3Field.setVisible(true);
            } else if ("thesis".equals(selectedType)) {
                dynamicField1Label.setText("Enter Subject: ");
                dynamicField2Label.setText("Enter Degree: ");
                dynamicField3Label.setText("Enter University: ");
                dynamicField3Field.setVisible(true);
            }
            jpnView.revalidate();
            jpnView.repaint();
        });
        
        // jcbUseAPI to choose whether to use API
        JCheckBox jcbUseAPI = new JCheckBox("Use API to autofill details");
        jpnView.add(jcbUseAPI);
        jpnView.add(new JLabel()); // Empty label for grid layout alignment
        
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
    
        
        jbtAutoFill.addActionListener(e -> {
            typeComboBox.setSelectedItem("book");
            dynamicField1Label.setText("Enter Genre: ");
            dynamicField2Label.setText("Enter Publisher: ");
            dynamicField3Label.setText("");
            dynamicField3Field.setVisible(false);

            String isbn = isbnField.getText();
                
            if (isbn.isEmpty()) {
                JOptionPane.showMessageDialog(jpnView, "Please enter ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Book book = null;
            try {
                book = GoogleBooksAPI.getBookFromISBN(isbn);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Failed to fetch book information. Please check your internet connection.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (book == null) {
                JOptionPane.showMessageDialog(null, "No book found with this ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String title = book.getDocumentTitle();
            String author = book.getDocumentAuthor();
            String description = book.getDocumentDescription();
            String language = book.getDocumentLanguage();
            int pages = book.getDocumentPage();
            String dynamicField1 = book.getBookGenre();
            String dynamicField2 = book.getBookPublisher();
            String dynamicField3 = "";  // Leave empty for book
            
            // Fill the fields with mock data
            titleField.setText(title);
            authorField.setText(author);
            descriptionField.setText(description);
            languageField.setText(language);
            pagesField.setText(String.valueOf(pages));
            dynamicField1Field.setText(dynamicField1);
            dynamicField2Field.setText(dynamicField2);
            dynamicField3Field.setText(dynamicField3);   
        });
        

        // Add action listener to add button
        jbtAdd.addActionListener(e -> {
            try {
                if (quantityField.getText().isEmpty() || titleField.getText().isEmpty() ||
                        authorField.getText().isEmpty() || descriptionField.getText().isEmpty() ||
                        languageField.getText().isEmpty() || pagesField.getText().isEmpty() || 
                        isbnField.getText().isEmpty() || dynamicField1Field.getText().isEmpty() || 
                        dynamicField2Field.getText().isEmpty() || (!"book".equals((String) typeComboBox.getSelectedItem()) &&
                        dynamicField3Field.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(jpnView, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }                

                int quantity = Integer.parseInt(quantityField.getText());
                String title = titleField.getText();
                String author = authorField.getText();
                String description = descriptionField.getText();
                String language = languageField.getText();
                int pages = Integer.parseInt(pagesField.getText());
                String isbn = isbnField.getText();

                String dynamicField1 = dynamicField1Field.getText();
                String dynamicField2 = dynamicField2Field.getText();
                String dynamicField3 = dynamicField3Field.getText();

                String selectedType = (String) typeComboBox.getSelectedItem();
                if ("book".equals(selectedType)) {
                    Book book = new Book();
                    book.documentQuantity = quantity;
                    book.setDocumentTitle(title);
                    book.setDocumentAuthor(author);
                    book.setDocumentDescription(description);
                    book.setDocumentLanguage(language);
                    book.setDocumentPage(pages);
                    book.setDocumentISBN(isbn);
                    book.setBookGenre(dynamicField1);
                    book.setBookPublisher(dynamicField2);

                    libraryManagementSystem.bookList.add(book);
                } else if ("magazine".equals(selectedType)) {
                    Magazine magazine = new Magazine();
                    magazine.documentQuantity = quantity;
                    magazine.setDocumentTitle(title);
                    magazine.setDocumentAuthor(author);
                    magazine.setDocumentDescription(description);
                    magazine.setDocumentLanguage(language);
                    magazine.setDocumentPage(pages);
                    magazine.setDocumentISBN(isbn);
                    magazine.setMagazineSubject(dynamicField1);
                    magazine.setMagazineFrequency(Integer.parseInt(dynamicField2));
                    magazine.setMagazineIssueNumb(Integer.parseInt(dynamicField3));

                    libraryManagementSystem.magazineList.add(magazine);
                } else if ("thesis".equals(selectedType)) {
                    Thesis thesis = new Thesis();
                    thesis.documentQuantity = quantity;
                    thesis.setDocumentTitle(title);
                    thesis.setDocumentAuthor(author);
                    thesis.setDocumentDescription(description);
                    thesis.setDocumentLanguage(language);
                    thesis.setDocumentPage(pages);
                    thesis.setDocumentISBN(isbn);
                    thesis.setThesisSubject(dynamicField1);
                    thesis.setThesisDegree(dynamicField2);
                    thesis.setThesisUniversity(dynamicField3);

                    libraryManagementSystem.thesisList.add(thesis);
                }
                

                libraryManagementSystem.saveData();
                JOptionPane.showMessageDialog(jpnView, "The document is added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                setupAddDocumentForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(jpnView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        jpnView.revalidate();
        jpnView.repaint();
    }

}
