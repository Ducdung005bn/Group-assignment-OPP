package library.management.system;

import main.classes.main.opponents.Borrower;
import main.classes.main.opponents.LibraryManagementSystem;

public class BorrowOrRemoveDocumentJPanel extends javax.swing.JPanel {
    
    //JPanel for borrowing documents
    public BorrowOrRemoveDocumentJPanel(Borrower borrower, LibraryManagementSystem libraryManagementSystem, String documentISBN) {
        initComponents();
        jLabel2.setText(libraryManagementSystem.translate("BORROW_DOCUMENT_ISBN"));
        jtfBorrowOrRemoveDocument.setText(documentISBN);
        BorrowOrRemoveDocumentController controller = new BorrowOrRemoveDocumentController(jpnView, jtfBorrowOrRemoveDocument, jbtBorrowOrRemoveDocument, borrower, libraryManagementSystem);
    }
    
    //JPanel for removing documents
    public BorrowOrRemoveDocumentJPanel(LibraryManagementSystem libraryManagementSystem) {
        initComponents();
        jLabel2.setText(libraryManagementSystem.translate("REMOVE_DOCUMENT_ISBN"));
        BorrowOrRemoveDocumentController controller = new BorrowOrRemoveDocumentController(jpnView, jtfBorrowOrRemoveDocument, jbtBorrowOrRemoveDocument, libraryManagementSystem);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfBorrowOrRemoveDocument = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();
        jbtBorrowOrRemoveDocument = new javax.swing.JButton();

        jtfBorrowOrRemoveDocument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfBorrowOrRemoveDocumentActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel2.setText("Deal with the document with ISBN:");

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );

        jbtBorrowOrRemoveDocument.setActionCommand("BUTTON");
        jbtBorrowOrRemoveDocument.setLabel("BUTTON\n");
        jbtBorrowOrRemoveDocument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBorrowOrRemoveDocumentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtBorrowOrRemoveDocument)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jtfBorrowOrRemoveDocument, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtBorrowOrRemoveDocument))
                    .addComponent(jtfBorrowOrRemoveDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jpnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtfBorrowOrRemoveDocumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfBorrowOrRemoveDocumentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfBorrowOrRemoveDocumentActionPerformed

    private void jbtBorrowOrRemoveDocumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBorrowOrRemoveDocumentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtBorrowOrRemoveDocumentActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtBorrowOrRemoveDocument;
    private javax.swing.JPanel jpnView;
    private javax.swing.JTextField jtfBorrowOrRemoveDocument;
    // End of variables declaration//GEN-END:variables
}
