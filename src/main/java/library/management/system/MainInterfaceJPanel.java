package library.management.system;

import main.classes.main.opponents.Borrower;
import main.classes.main.opponents.Librarian;
import main.classes.main.opponents.LibraryManagementSystem;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainInterfaceJPanel extends javax.swing.JPanel {

    // Constructor with borrower and library management system
    public MainInterfaceJPanel(Borrower borrower, LibraryManagementSystem libraryManagementSystem) {
        initComponents(); // Initialize components

        jLabel2.setText(libraryManagementSystem.translate("BEST_BORROWED_BOOKS"));
        jLabel3.setText(libraryManagementSystem.translate("MOST_SUITABLE_BOOKS"));

        MainInterfaceController controller = new MainInterfaceController(
                jpnView,
                borrower,
                libraryManagementSystem,
                topBorrowedBooksjpn,
                topSuitableBooksjpn
        );
    }

    // Default constructor when no arguments are passed
    public MainInterfaceJPanel(Librarian librarian, LibraryManagementSystem libraryManagementSystem) {
        initComponents(); // Initialize components

        topBorrowedBooksjpn.setVisible(false); // Hide borrowed books section
        topSuitableBooksjpn.setVisible(false); // Hide suitable books section
        jLabel2.setVisible(false); // Hide the label for borrowed books
        jLabel3.setVisible(false); // Hide the label for suitable books

        MainInterfaceController controller = new MainInterfaceController(jpnView, librarian, libraryManagementSystem);
    }

    // Initialize UI components
    private void initComponents() {

        // Define components
        jpnView = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        topBorrowedBooksjpn = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        topSuitableBooksjpn = new javax.swing.JPanel();

        // Configure main panel
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(786, 372));

        // Configure container panel
        jpnView.setBackground(new java.awt.Color(255, 255, 255));
        jpnView.setLayout(null); // Use absolute positioning for flexibility

        // Configure title label
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("WELCOME TO MY APPLICATION");
        jpnView.add(jLabel1);
        jLabel1.setBounds(10, 10, 766, 30); // Set position and size

        // Configure borrowed books label
        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("BEST BORROWED BOOKS:");
        jpnView.add(jLabel2);
        jLabel2.setBounds(10, 50, 200, 20);

        // Configure borrowed books panel
        topBorrowedBooksjpn.setBackground(new java.awt.Color(240, 240, 240));
        topBorrowedBooksjpn.setLayout(null);
        jpnView.add(topBorrowedBooksjpn);
        topBorrowedBooksjpn.setBounds(10, 80, 766, 180);

        // Configure suitable books label
        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("MOST SUITABLE BOOKS:");
        jpnView.add(jLabel3);
        jLabel3.setBounds(10, 280, 200, 20);

        // Configure suitable books panel
        topSuitableBooksjpn.setBackground(new java.awt.Color(240, 240, 240));
        topSuitableBooksjpn.setLayout(null);
        jpnView.add(topSuitableBooksjpn);
        topSuitableBooksjpn.setBounds(10, 310, 766, 180);

        // Add the main container (jpnView) to this panel
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    // Variables declaration
    private javax.swing.JPanel jpnView; // Main container panel
    private javax.swing.JPanel topBorrowedBooksjpn; // Section for borrowed books
    private javax.swing.JPanel topSuitableBooksjpn; // Section for suitable books
    private javax.swing.JLabel jLabel1; // Title label
    private javax.swing.JLabel jLabel2; // Label for borrowed books
    private javax.swing.JLabel jLabel3; // Label for suitable books
    // End of variables declaration
}
