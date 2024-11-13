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

public class ScreenSwitchController {
    private JButton jbtExit;
    private JPanel root;
    private String selectedKind = "";
    private List<MenuItem> itemList = null;
    private Borrower borrower;
    private Librarian librarian;
    private boolean isDealingWithBorrower;
    private LibraryManagementSystem libraryManagementSystem;
    
    public ScreenSwitchController (JPanel root, Borrower borrower, LibraryManagementSystem libraryManagementSystem) {
        this.root = root;
        this.borrower = borrower;
        this.libraryManagementSystem = libraryManagementSystem;
        isDealingWithBorrower = true;
    }
    
    public ScreenSwitchController (JPanel root, Librarian librarian, LibraryManagementSystem libraryManagementSystem) {
        this.root = root;
        this.librarian = librarian;
        this.libraryManagementSystem = libraryManagementSystem;
        isDealingWithBorrower = false;
    }
    
    public void setView(JPanel jpn, JLabel jlb) {
        selectedKind = "MainInterface";
        jpn.setBackground(new Color(96,100,191));
        jlb.setBackground(new Color(96,100,191));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new MainInterfaceJPanel());
        root.validate();
        root.repaint();
    }
      
    public void setEvent(JButton jbtExit, List<MenuItem> itemList) {
        this.jbtExit = jbtExit;
        this.itemList = itemList;
        
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

                // If the user confirms, exit the application
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        for (MenuItem item : itemList) {
            item.getPanel().addMouseListener(new LabelEvent(item.getKind(), item.getPanel(), item.getLabel()));
        }
    }
    
    private void setChangeBackground(String kind) {
        for (MenuItem item : itemList) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getPanel().setBackground(new Color(96, 100, 191));
                item.getLabel().setBackground(new Color(96, 100, 191));
            } else {
                item.getPanel().setBackground(new Color(76, 175, 80));
                item.getLabel().setBackground(new Color(76, 175, 80));
            }
        }
    }
    
    class LabelEvent implements MouseListener {
        private JPanel node;
        private String kind;
        private JPanel itemJpn;
        private JLabel itemLbl;
        
        public LabelEvent(String kind, JPanel itemJpn, JLabel itemLbl) {
            this.kind = kind;
            this.itemJpn = itemJpn;
            this.itemLbl = itemLbl;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isDealingWithBorrower) {
                switch(kind) {
                    case "MainInterface" :
                        node = new MainInterfaceJPanel();
                        break;
                    case "FindDocument" :
                        node = new FindDocumentJPanel(libraryManagementSystem);
                        break;
                    case "DisplayDocument" :
                        node = new DisplayDocumentJPanel(libraryManagementSystem);
                        break;
                    case "BorrowDocument" :
                        node = new BorrowDocumentJPanel(borrower, libraryManagementSystem);
                        break;
                    case "ReturnDocument" :
                        node = new ReturnDocumentJPanel(borrower, libraryManagementSystem);
                        break;
                    case "DisplayUserInfo" :
                        node = new DisplayUserInfoJPanel(borrower, isDealingWithBorrower);
                        break;
                    default: break;
                } 
            } else {
                switch(kind) {
                    case "MainInterface" :
                        node = new MainInterfaceJPanel();
                        break;
                    case "FindDocument" :
                        node = new FindDocumentJPanel(libraryManagementSystem);
                        break;
                    case "DisplayDocument" :
                        node = new DisplayDocumentJPanel(libraryManagementSystem);
                        break;
//                    case "AddDocument" :
//                        node = new AddDocumentJPanel(librarian, libraryManagementSystem);
//                        break;
//                    case "RemoveDocument" :
//                        node = new RemoveDocumentJPanel(librarian, libraryManagementSystem);
//                        break;
//                    case "UpdateDocument" :
//                        node = new UpdateDocumentJPanel(librarian, libraryManagementSystem);
//                        break;
                    case "AddBorrower" :
                        node = new AddInformationJPanel("borrower", libraryManagementSystem);
                        break;
                    case "AddLibrarian" :
                        node = new AddInformationJPanel("librarian", libraryManagementSystem);
                        break;
                    case "DisplayUserInfo" :
                        node = new DisplayUserInfoJPanel(librarian, isDealingWithBorrower);
                        break;
                    default: break;
                } 
            }
            
            
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint(); 
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            selectedKind = kind;
            itemJpn.setBackground(new Color(96, 100, 191));
            itemLbl.setBackground(new Color(96, 100, 191));
        }
      
        @Override
        public void mouseReleased(MouseEvent e) {
            //TO DO
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            itemJpn.setBackground(new Color(96, 100, 191));
            itemLbl.setBackground(new Color(96, 100, 191));
        }

      
        @Override
        public void mouseExited(MouseEvent e) {
            if (!selectedKind.equalsIgnoreCase(kind)) {
                itemJpn.setBackground(new Color(76, 175, 80));
                itemLbl.setBackground(new Color(76, 175, 80));
            }
        }
        
    }
}
