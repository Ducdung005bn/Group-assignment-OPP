package library.management.system;

import java.util.ArrayList;
import java.util.List;

import main.classes.main.opponents.Librarian;
import main.classes.main.opponents.LibraryManagementSystem;

public class LibrarianJFrame extends javax.swing.JFrame {

        public LibrarianJFrame(Librarian librarian,LibraryManagementSystem libraryManagementSystem) {
        initComponents();
        setTitle("Library Management System");
        
        ScreenSwitchController controller = new ScreenSwitchController(jpnView, librarian, libraryManagementSystem);
        controller.setView(jpnMainInterface, jlbMainInterface);
        
        List<MenuItem> itemList = new ArrayList<>();
        itemList.add(new MenuItem("MainInterface", jpnMainInterface, jlbMainInterface));
        itemList.add(new MenuItem("FindDocument", jpnFindDocument, jlbFindDocument));
        itemList.add(new MenuItem("DisplayDocument", jpnDisplayDocument, jlbDisplayDocument));
        itemList.add(new MenuItem("AddDocument", jpnAddDocument, jlbAddDocument));
        itemList.add(new MenuItem("RemoveDocument", jpnRemoveDocument, jlbRemoveDocument));
        itemList.add(new MenuItem("UpdateDocument", jpnUpdateDocument, jlbUpdateDocument));
        itemList.add(new MenuItem("AddBorrower", jpnAddBorrower, jlbAddBorrower));
        itemList.add(new MenuItem("AddLibrarian", jpnAddLibrarian, jlbAddLibrarian));
        itemList.add(new MenuItem("DisplayUserInfo", jpnDisplayUserInfo, jlbDisplayUserInfo));
                
        controller.setEvent(jbtExit, itemList);
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnView = new javax.swing.JPanel();
        jpnMenu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpnMainInterface = new javax.swing.JPanel();
        jlbMainInterface = new javax.swing.JLabel();
        jpnFindDocument = new javax.swing.JPanel();
        jlbFindDocument = new javax.swing.JLabel();
        jpnDisplayDocument = new javax.swing.JPanel();
        jlbDisplayDocument = new javax.swing.JLabel();
        jpnAddDocument = new javax.swing.JPanel();
        jlbAddDocument = new javax.swing.JLabel();
        jpnRemoveDocument = new javax.swing.JPanel();
        jlbRemoveDocument = new javax.swing.JLabel();
        jpnDisplayUserInfo = new javax.swing.JPanel();
        jlbDisplayUserInfo = new javax.swing.JLabel();
        jbtExit = new javax.swing.JButton();
        jpnUpdateDocument = new javax.swing.JPanel();
        jlbUpdateDocument = new javax.swing.JLabel();
        jpnAddBorrower = new javax.swing.JPanel();
        jlbAddBorrower = new javax.swing.JLabel();
        jpnAddLibrarian = new javax.swing.JPanel();
        jlbAddLibrarian = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jpnAddDocument.setBackground(new java.awt.Color(76, 175, 80));
        jpnAddDocument.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbAddDocument.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbAddDocument.setText("ADD DOCUMENT");

        javax.swing.GroupLayout jpnAddDocumentLayout = new javax.swing.GroupLayout(jpnAddDocument);
        jpnAddDocument.setLayout(jpnAddDocumentLayout);
        jpnAddDocumentLayout.setHorizontalGroup(
            jpnAddDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAddDocumentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbAddDocument)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnAddDocumentLayout.setVerticalGroup(
            jpnAddDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAddDocumentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbAddDocument)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jpnRemoveDocument.setBackground(new java.awt.Color(76, 175, 80));
        jpnRemoveDocument.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbRemoveDocument.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbRemoveDocument.setText("REMOVE DOCUMENT");

        javax.swing.GroupLayout jpnRemoveDocumentLayout = new javax.swing.GroupLayout(jpnRemoveDocument);
        jpnRemoveDocument.setLayout(jpnRemoveDocumentLayout);
        jpnRemoveDocumentLayout.setHorizontalGroup(
            jpnRemoveDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRemoveDocumentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbRemoveDocument)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jpnRemoveDocumentLayout.setVerticalGroup(
            jpnRemoveDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRemoveDocumentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbRemoveDocument)
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
                .addContainerGap(39, Short.MAX_VALUE))
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

        jpnUpdateDocument.setBackground(new java.awt.Color(76, 175, 80));
        jpnUpdateDocument.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbUpdateDocument.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbUpdateDocument.setText("UPDATE DOCUMENT");

        javax.swing.GroupLayout jpnUpdateDocumentLayout = new javax.swing.GroupLayout(jpnUpdateDocument);
        jpnUpdateDocument.setLayout(jpnUpdateDocumentLayout);
        jpnUpdateDocumentLayout.setHorizontalGroup(
            jpnUpdateDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnUpdateDocumentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbUpdateDocument)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jpnUpdateDocumentLayout.setVerticalGroup(
            jpnUpdateDocumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnUpdateDocumentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbUpdateDocument)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jpnAddBorrower.setBackground(new java.awt.Color(76, 175, 80));
        jpnAddBorrower.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbAddBorrower.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbAddBorrower.setText("ADD BORROWER");

        javax.swing.GroupLayout jpnAddBorrowerLayout = new javax.swing.GroupLayout(jpnAddBorrower);
        jpnAddBorrower.setLayout(jpnAddBorrowerLayout);
        jpnAddBorrowerLayout.setHorizontalGroup(
            jpnAddBorrowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAddBorrowerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbAddBorrower)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jpnAddBorrowerLayout.setVerticalGroup(
            jpnAddBorrowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAddBorrowerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbAddBorrower)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jpnAddLibrarian.setBackground(new java.awt.Color(76, 175, 80));
        jpnAddLibrarian.setPreferredSize(new java.awt.Dimension(0, 36));

        jlbAddLibrarian.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbAddLibrarian.setText("ADD LIBRARIAN");

        javax.swing.GroupLayout jpnAddLibrarianLayout = new javax.swing.GroupLayout(jpnAddLibrarian);
        jpnAddLibrarian.setLayout(jpnAddLibrarianLayout);
        jpnAddLibrarianLayout.setHorizontalGroup(
            jpnAddLibrarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAddLibrarianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbAddLibrarian)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnAddLibrarianLayout.setVerticalGroup(
            jpnAddLibrarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAddLibrarianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbAddLibrarian)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnMainInterface, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addComponent(jpnFindDocument, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addComponent(jpnDisplayDocument, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addComponent(jpnRemoveDocument, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addComponent(jpnDisplayUserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addComponent(jpnAddDocument, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addComponent(jpnUpdateDocument, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addComponent(jpnAddBorrower, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addComponent(jpnAddLibrarian, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jbtExit)
                .addContainerGap(85, Short.MAX_VALUE))
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
                .addComponent(jpnAddDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnRemoveDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnUpdateDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnAddBorrower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnAddLibrarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnDisplayUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jpnView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtExit;
    private javax.swing.JLabel jlbAddBorrower;
    private javax.swing.JLabel jlbAddDocument;
    private javax.swing.JLabel jlbAddLibrarian;
    private javax.swing.JLabel jlbDisplayDocument;
    private javax.swing.JLabel jlbDisplayUserInfo;
    private javax.swing.JLabel jlbFindDocument;
    private javax.swing.JLabel jlbMainInterface;
    private javax.swing.JLabel jlbRemoveDocument;
    private javax.swing.JLabel jlbUpdateDocument;
    private javax.swing.JPanel jpnAddBorrower;
    private javax.swing.JPanel jpnAddDocument;
    private javax.swing.JPanel jpnAddLibrarian;
    private javax.swing.JPanel jpnDisplayDocument;
    private javax.swing.JPanel jpnDisplayUserInfo;
    private javax.swing.JPanel jpnFindDocument;
    private javax.swing.JPanel jpnMainInterface;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnRemoveDocument;
    private javax.swing.JPanel jpnUpdateDocument;
    private javax.swing.JPanel jpnView;
    // End of variables declaration//GEN-END:variables
}
