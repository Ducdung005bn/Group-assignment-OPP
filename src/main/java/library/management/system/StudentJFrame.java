package library.management.system;

import java.util.ArrayList;
import java.util.List;
import main.classes.main.opponents.Borrower;
import main.classes.main.opponents.LibraryManagementSystem;

public class StudentJFrame extends javax.swing.JFrame {

    public StudentJFrame(Borrower borrower,LibraryManagementSystem libraryManagementSystem) {
        initComponents();
        setTitle("Library Management System");

        ScreenSwitchController controller = new ScreenSwitchController(jpnView, borrower, libraryManagementSystem);
        controller.setView(jpnMainInterface, jlbMainInterface);

        List<MenuItem> itemList = new ArrayList<>();
        itemList.add(new MenuItem("MainInterface", jpnMainInterface, jlbMainInterface));
        itemList.add(new MenuItem("FindDocument", jpnFindDocument, jlbFindDocument));
        itemList.add(new MenuItem("DisplayDocument", jpnDisplayDocument, jlbDisplayDocument));
        itemList.add(new MenuItem("BorrowDocument", jpnBorrowDocument, jlbBorrowDocument));
        itemList.add(new MenuItem("ReturnDocument", jpnReturnDocument, jlbReturnDocument));
        itemList.add(new MenuItem("DisplayUserInfo", jpnDisplayUserInfo, jlbDisplayUserInfo));

        controller.setEvent(jbtExit, itemList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jpnRoot = new javax.swing.JPanel();
        jpnMenu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpnMainInterface = new javax.swing.JPanel();
        jlbMainInterface = new javax.swing.JLabel();
        jpnFindDocument = new javax.swing.JPanel();
        jlbFindDocument = new javax.swing.JLabel();
        jpnDisplayDocument = new javax.swing.JPanel();
        jlbDisplayDocument = new javax.swing.JLabel();
        jpnBorrowDocument = new javax.swing.JPanel();
        jlbBorrowDocument = new javax.swing.JLabel();
        jpnReturnDocument = new javax.swing.JPanel();
        jlbReturnDocument = new javax.swing.JLabel();
        jpnDisplayUserInfo = new javax.swing.JPanel();
        jlbDisplayUserInfo = new javax.swing.JLabel();
        jbtExit = new javax.swing.JButton();
        jpnView = new javax.swing.JPanel();

        jPanel2.setPreferredSize(new java.awt.Dimension(175, 52));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 52, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnRoot.setPreferredSize(new java.awt.Dimension(1000, 500));

        jpnMenu.setPreferredSize(new java.awt.Dimension(217, 515));

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 9)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Test\\171322.png")); // NOI18N
        jLabel1.setText("LIBRARY MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnMainInterface.setBackground(new java.awt.Color(76, 175, 80));
        jpnMainInterface.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbMainInterface.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbMainInterface.setText("MAIN INTERFACE");

        javax.swing.GroupLayout jpnMainInterfaceLayout = new javax.swing.GroupLayout(jpnMainInterface);
        jpnMainInterface.setLayout(jpnMainInterfaceLayout);
        jpnMainInterfaceLayout.setHorizontalGroup(
                jpnMainInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnMainInterfaceLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbMainInterface)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnMainInterfaceLayout.setVerticalGroup(
                jpnMainInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnMainInterfaceLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbMainInterface)
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        jpnFindDocument.setBackground(new java.awt.Color(76, 175, 80));
        jpnFindDocument.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbFindDocument.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbFindDocument.setText("FIND DOCUMENT");

        javax.swing.GroupLayout jpnFindDocumentLayout = new javax.swing.GroupLayout(jpnFindDocument);
        jpnFindDocument.setLayout(jpnFindDocumentLayout);
        jpnFindDocumentLayout.setHorizontalGroup(
                jpnFindDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnFindDocumentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbFindDocument)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnFindDocumentLayout.setVerticalGroup(
                jpnFindDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnFindDocumentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbFindDocument)
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        jpnDisplayDocument.setBackground(new java.awt.Color(76, 175, 80));
        jpnDisplayDocument.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbDisplayDocument.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbDisplayDocument.setText("DISPLAY DOCUMENT");

        javax.swing.GroupLayout jpnDisplayDocumentLayout = new javax.swing.GroupLayout(jpnDisplayDocument);
        jpnDisplayDocument.setLayout(jpnDisplayDocumentLayout);
        jpnDisplayDocumentLayout.setHorizontalGroup(
                jpnDisplayDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnDisplayDocumentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbDisplayDocument)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnDisplayDocumentLayout.setVerticalGroup(
                jpnDisplayDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnDisplayDocumentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbDisplayDocument)
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        jpnBorrowDocument.setBackground(new java.awt.Color(76, 175, 80));
        jpnBorrowDocument.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbBorrowDocument.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbBorrowDocument.setText("BORROW DOCUMENT");

        javax.swing.GroupLayout jpnBorrowDocumentLayout = new javax.swing.GroupLayout(jpnBorrowDocument);
        jpnBorrowDocument.setLayout(jpnBorrowDocumentLayout);
        jpnBorrowDocumentLayout.setHorizontalGroup(
                jpnBorrowDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnBorrowDocumentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbBorrowDocument)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnBorrowDocumentLayout.setVerticalGroup(
                jpnBorrowDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnBorrowDocumentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbBorrowDocument)
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        jpnReturnDocument.setBackground(new java.awt.Color(76, 175, 80));
        jpnReturnDocument.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbReturnDocument.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbReturnDocument.setText("RETURN DOCUMENT");

        javax.swing.GroupLayout jpnReturnDocumentLayout = new javax.swing.GroupLayout(jpnReturnDocument);
        jpnReturnDocument.setLayout(jpnReturnDocumentLayout);
        jpnReturnDocumentLayout.setHorizontalGroup(
                jpnReturnDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnReturnDocumentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbReturnDocument)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnReturnDocumentLayout.setVerticalGroup(
                jpnReturnDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnReturnDocumentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbReturnDocument)
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        jpnDisplayUserInfo.setBackground(new java.awt.Color(76, 175, 80));
        jpnDisplayUserInfo.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbDisplayUserInfo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbDisplayUserInfo.setText("DISPLAY USER INFORMATION");

        javax.swing.GroupLayout jpnDisplayUserInfoLayout = new javax.swing.GroupLayout(jpnDisplayUserInfo);
        jpnDisplayUserInfo.setLayout(jpnDisplayUserInfoLayout);
        jpnDisplayUserInfoLayout.setHorizontalGroup(
                jpnDisplayUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnDisplayUserInfoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbDisplayUserInfo)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnDisplayUserInfoLayout.setVerticalGroup(
                jpnDisplayUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnDisplayUserInfoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbDisplayUserInfo)
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        jbtExit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jbtExit.setText("EXIT");
        jbtExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
                jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpnMainInterface, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addComponent(jpnFindDocument, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addComponent(jpnDisplayDocument, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addComponent(jpnReturnDocument, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addComponent(jpnDisplayUserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addComponent(jpnBorrowDocument, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addGroup(jpnMenuLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jbtExit)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnMenuLayout.setVerticalGroup(
                jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnMenuLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpnMainInterface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpnFindDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpnDisplayDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpnBorrowDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpnReturnDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpnDisplayUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtExit)
                                .addContainerGap(129, Short.MAX_VALUE))
        );

        jpnView.setPreferredSize(new java.awt.Dimension(624, 0));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
                jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 624, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
                jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
                jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnRootLayout.createSequentialGroup()
                                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnRootLayout.setVerticalGroup(
                jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                        .addComponent(jpnView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jpnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>

    private void jbtExitActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtExit;
    private javax.swing.JLabel jlbBorrowDocument;
    private javax.swing.JLabel jlbDisplayDocument;
    private javax.swing.JLabel jlbDisplayUserInfo;
    private javax.swing.JLabel jlbFindDocument;
    private javax.swing.JLabel jlbMainInterface;
    private javax.swing.JLabel jlbReturnDocument;
    private javax.swing.JPanel jpnBorrowDocument;
    private javax.swing.JPanel jpnDisplayDocument;
    private javax.swing.JPanel jpnDisplayUserInfo;
    private javax.swing.JPanel jpnFindDocument;
    private javax.swing.JPanel jpnMainInterface;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnReturnDocument;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnView;
    // End of variables declaration
}
