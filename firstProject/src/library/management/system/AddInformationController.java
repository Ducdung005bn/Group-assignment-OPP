package library.management.system;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import main.classes.Borrower;
import main.classes.Document;
import main.classes.Librarian;
import main.classes.LibraryManagementSystem;

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
//            setupAddDocumentForm();
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

}