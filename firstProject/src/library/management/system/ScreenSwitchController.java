package library.management.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import main.classes.LibraryManagementSystem;
import main.classes.PendingUser;

public class ScreenSwitchController {
    private JPanel root;
    private String selectedKind = "";
    private List<MenuItem> itemList = null;
    private PendingUser pendingUser;
    private LibraryManagementSystem libraryManagementSystem;
    
    public ScreenSwitchController(JPanel root, PendingUser pendingUser, LibraryManagementSystem libraryManagementSystem) {
        this.root = root;
        this.pendingUser = pendingUser;
        this.libraryManagementSystem = libraryManagementSystem;
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
    
    public void setEvent(List<MenuItem> itemList) {
        this.itemList = itemList;
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
                    node = new BorrowDocumentJPanel(pendingUser, libraryManagementSystem);
                    break;
                case "ReturnDocument" :
                    node = new ReturnDocumentJPanel();
                    break;
                case "DisplayUserInfo" :
                    node = new DisplayUserInfoJPanel();
                    break;
                case "Exit" :
                    node = new ExitJPanel();
                    break;
                default: break;
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
