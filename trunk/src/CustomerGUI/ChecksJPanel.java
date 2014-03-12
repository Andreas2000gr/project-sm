/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerGUI;

import LocalDB.Customer;
import LocalDB.Voucher;
import java.util.Collection;
import javax.swing.table.DefaultTableModel;
import supermarket.SuperMarketParentFrame;

/**
 *
 * @author Euh
 */
public class ChecksJPanel extends javax.swing.JPanel {

    // private final DBmanager db = new DBmanager();
    private SuperMarketParentFrame ParentFrame;
    private Customer Usr;
    private DefaultTableModel DTModel;
    private Collection<Voucher> voucher;

    //declare the column names
    private final Object[] columnNames = {"Αριθμός Επιταγής", "Ημερομηνία που κερδήθηκε", "Κατάσταση Επιταγής"};

    /**
     * Creates new form ChecksJPanel
     */
    public ChecksJPanel(SuperMarketParentFrame ParentFrame) {
        initComponents();
        this.ParentFrame = ParentFrame;

        this.LoadUSRVouchers();
    }

    public void LoadUSRVouchers() {//φορτώνει τις επιταγές στον jtable από τον πίνακα της βάσης
        initComponents();
        this.Usr = ParentFrame.cust;
        this.VouchersTable.removeAll();
        this.voucher = Usr.getVoucherCollection();
        this.AvailablePoints.setText(String.valueOf(this.Usr.getAvailablePoints()));

        this.DTModel = new DefaultTableModel(new Object[0][0], columnNames);
        this.VouchersTable.setModel(this.DTModel);

        for (Voucher v : voucher) {
            Object[] object = new Object[3];
            object[0] = v.getVoucherId();
            object[1] = v.getVoucherDate();
            object[2] = v.getVoucherStatus();

            if (v.getVoucherStatus()) {
                object[2] = "Εγκυρη";
            } else {
                object[2] = "Ακυρη";
            }
            this.DTModel.addRow(object);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        VouchersTable = new javax.swing.JTable();
        ReturnToMainCustomerForm = new javax.swing.JButton();
        AvailablePoints = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jScrollPane1.setViewportView(VouchersTable);

        ReturnToMainCustomerForm.setText("επιστροφή");
        ReturnToMainCustomerForm.setActionCommand("return");
        ReturnToMainCustomerForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnToMainCustomerFormActionPerformed(evt);
            }
        });

        AvailablePoints.setEditable(false);

        jLabel1.setText("Διαθέσμοι Πόντοι:");

        jButton1.setText("Ανανέωση");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AvailablePoints, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(408, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(106, 106, 106))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(ReturnToMainCustomerForm)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(349, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(AvailablePoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ReturnToMainCustomerForm)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ReturnToMainCustomerFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnToMainCustomerFormActionPerformed
        ParentFrame.pnl = new CustMainPanel(this.ParentFrame);
        ParentFrame.addPanelInMain();
    }//GEN-LAST:event_ReturnToMainCustomerFormActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.LoadUSRVouchers();//ανανέωση της σελίδα - refresh\reload τις επιταγές
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AvailablePoints;
    private javax.swing.JButton ReturnToMainCustomerForm;
    private javax.swing.JTable VouchersTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
