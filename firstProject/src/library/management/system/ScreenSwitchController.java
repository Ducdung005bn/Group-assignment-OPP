package library.management.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import main.classes.Borrower;
import main.classes.Librarian;
import main.classes.LibraryManagementSystem;

/**
 * Controller class responsible for switching between different screens or views
 * in the library management system based on the user's role (Borrower or Librarian).
 */
public class ScreenSwitchController {
    private JButton jbtExit; // Exit button to terminate the application
    private JPanel root; // Root panel to display the current screen
    private String selectedKind = ""; // The current screen type
    private List<MenuItem> itemList = null; // List of menu items for navigation
    private Borrower borrower; // Borrower object for borrower-specific actions
    private Librarian librarian; // Librarian object for librarian-specific actions
    private boolean isDealingWithBorrower; // Flag indicating if the user is a borrower or librarian
    private LibraryManagementSystem libraryManagementSystem; // The library management system instance

    /**
     * Constructor for borrower-specific screen switching.
     *
     * @param root                    the root panel for displaying screens
     * @param borrower                the current borrower user
     * @param libraryManagementSystem the library management system instance
     */
    public ScreenSwitchController(JPanel root, Borrower borrower, LibraryManagementSystem libraryManagementSystem) {
        this.root = root;
        this.borrower = borrower;
        this.libraryManagementSystem = libraryManagementSystem;
        this.isDealingWithBorrower = true; // Set the user role to borrower
    }

    /**
     * Constructor for librarian-specific screen switching.
     *
     * @param root                    the root panel for displaying screens
     * @param librarian               the current librarian user
     * @param libraryManagementSystem the library management system instance
     */
    public ScreenSwitchController(JPanel root, Librarian librarian, LibraryManagementSystem libraryManagementSystem) {
        this.root = root;
        this.librarian = librarian;
        this.libraryManagementSystem = libraryManagementSystem;
        this.isDealingWithBorrower = false; // Set the user role to librarian
    }

    /**
     * Sets the main interface view to the root panel.
     *
     * @param jpn the panel to display the main interface
     * @param jlb the label for the main interface
     */
    public void setView(JPanel jpn, JLabel jlb) {
        selectedKind = "MainInterface"; // Set the current screen as MainInterface
        jpn.setBackground(new Color(96, 100, 191)); // Set background color
        jlb.setBackground(new Color(96, 100, 191)); // Set label background color

        root.removeAll(); // Remove existing components from the root panel
        root.setLayout(new BorderLayout());
        root.add(new MainInterfaceJPanel()); // Add main interface panel
        root.validate();
        root.repaint();
    }

    /**
     * Sets the events for the exit button and menu items for navigation.
     *
     * @param jbtExit   the exit button to terminate the application
     * @param itemList  the list of menu items for navigation
     */
    public void setEvent(JButton jbtExit, List<MenuItem> itemList) {
        this.jbtExit = jbtExit;
        this.itemList = itemList;

        // Action listener for the exit button
        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirm exit
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                // If user confirms, exit the application
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Set mouse listeners for each menu item
        for (MenuItem item : itemList) {
            item.getPanel().addMouseListener(new LabelEvent(item.getKind(), item.getPanel(), item.getLabel()));
        }
    }

    /**
     * Changes the background color of menu items based on the selected kind.
     *
     * @param kind the kind of screen selected
     */
    private void setChangeBackground(String kind) {
        for (MenuItem item : itemList) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                // Highlight the selected menu item
                item.getPanel().setBackground(new Color(96, 100, 191));
                item.getLabel().setBackground(new Color(96, 100, 191));
            } else {
                // Reset background color for unselected items
                item.getPanel().setBackground(new Color(76, 175, 80));
                item.getLabel().setBackground(new Color(76, 175, 80));
            }
        }
    }

    /**
     * MouseListener implementation for menu item interactions.
     * Handles mouse clicks and mouse enter/exit events for menu items.
     */
    class LabelEvent implements MouseListener {
        private JPanel node; // Panel for the new screen
        private String kind; // The type of screen (e.g., "MainInterface", "FindDocument")
        private JPanel itemJpn; // The panel of the menu item
        private JLabel itemLbl; // The label of the menu item

        /**
         * Constructor for creating a LabelEvent instance for a specific menu item.
         *
         * @param kind      the kind of screen
         * @param itemJpn   the panel for the menu item
         * @param itemLbl   the label for the menu item
         */
        public LabelEvent(String kind, JPanel itemJpn, JLabel itemLbl) {
            this.kind = kind;
            this.itemJpn = itemJpn;
            this.itemLbl = itemLbl;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Handle screen switching based on user role
            if (isDealingWithBorrower) {
                switch (kind) {
                    case "MainInterface":
                        node = new MainInterfaceJPanel();
                        break;
                    case "FindDocument":
                        node = new FindDocumentJPanel(libraryManagementSystem);
                        break;
                    case "DisplayDocument":
                        node = new DisplayOrUpdateDocumentJPanel("display", libraryManagementSystem);
                        break;
                    case "BorrowDocument":
                        node = new BorrowOrRemoveDocumentJPanel(borrower, libraryManagementSystem);
                        break;
                    case "ReturnDocument":
                        node = new ReturnDocumentJPanel(borrower, libraryManagementSystem);
                        break;
                    case "DisplayUserInfo":
                        node = new DisplayUserInfoJPanel(borrower, isDealingWithBorrower);
                        break;
                    default:
                        break;
                }
            } else {
                switch (kind) {
                    case "MainInterface":
                        node = new MainInterfaceJPanel();
                        break;
                    case "FindDocument":
                        node = new FindDocumentJPanel(libraryManagementSystem);
                        break;
                    case "DisplayDocument":
                        node = new DisplayOrUpdateDocumentJPanel("display", libraryManagementSystem);
                        break;
                    case "AddDocument":
                        node = new AddInformationJPanel("document", libraryManagementSystem);
                        break;
                    case "RemoveDocument":
                        node = new BorrowOrRemoveDocumentJPanel(libraryManagementSystem);
                        break;
                    case "UpdateDocument":
                        node = new DisplayOrUpdateDocumentJPanel("update", libraryManagementSystem);
                        break;
                    case "AddBorrower":
                        node = new AddInformationJPanel("borrower", libraryManagementSystem);
                        break;
                    case "AddLibrarian":
                        node = new AddInformationJPanel("librarian", libraryManagementSystem);
                        break;
                    case "DisplayUserInfo":
                        node = new DisplayUserInfoJPanel(librarian, isDealingWithBorrower);
                        break;
                    default:
                        break;
                }
            }

            // Update the root panel with the new screen
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();

            // Change the background color of the selected menu item
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Set selected menu item background on mouse press
            selectedKind = kind;
            itemJpn.setBackground(new Color(96, 100, 191));
            itemLbl.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // No action required for mouse release
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Highlight menu item when mouse enters
            itemJpn.setBackground(new Color(96, 100, 191));
            itemLbl.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Reset background color when mouse exits (if not selected)
            if (!selectedKind.equalsIgnoreCase(kind)) {
                itemJpn.setBackground(new Color(76, 175, 80));
                itemLbl.setBackground(new Color(76, 175, 80));
            }
        }
    }
}
