/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerGUI;

import LocalDB.Customer;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import supermarket.SuperMarketParentFrame;

/**
 *
 * @author Euh
 */
public class ChangePasswordJPanel extends javax.swing.JPanel {

    private SuperMarketParentFrame ParentFrame;
    private Customer Usr;
    private EntityManager loc;

    /**
     * Creates new form ChangePasswordJPanel
     */
    public ChangePasswordJPanel(SuperMarketParentFrame ParentFrame) {
        initComponents();
        this.ParentFrame = ParentFrame;
        this.Usr = ParentFrame.cust;
        this.loc = ParentFrame.getLoc();

        /* εντοπίζουμε τον χρήστη και αρχικοποιούμε τα πεδία jtexfields
         της φόρμας, ώστε να εμφανίζεται η πληροφορία.*/
        usrLAST_NAME.setText(ParentFrame.cust.getLastName().toString());
        usrFIRST_NAME.setText(ParentFrame.cust.getFirstName().toString());
        usrPointsCardNum.setText(ParentFrame.cust.getPointsCardNumber().toString());

        /* Τα παρακάτω πεδία δε μπορεί να επεξεργαστεί ο χρήστης */
        usrLAST_NAME.setEditable(false);
        usrFIRST_NAME.setEditable(false);
        usrPointsCardNum.setEditable(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        ReturnToMainCustomerForm = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        usrPointsCardNum = new javax.swing.JTextField();
        usrLAST_NAME = new javax.swing.JTextField();
        usrFIRST_NAME = new javax.swing.JTextField();
        label5 = new java.awt.Label();
        label4 = new java.awt.Label();
        label6 = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        usrOLD_PASSWORD = new javax.swing.JPasswordField();
        usrNEW_PASSWORD = new javax.swing.JPasswordField();
        usrNEW_PASSWORD_Confirm = new javax.swing.JPasswordField();
        label3 = new java.awt.Label();

        jButton2.setText("Αποθήκευση");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ReturnToMainCustomerForm.setText("επιστροφή");
        ReturnToMainCustomerForm.setActionCommand("return");
        ReturnToMainCustomerForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnToMainCustomerFormActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Στοιχεία πελάτη"));

        label5.setText("Αρ. Κάρτας πόντων:");

        label4.setText("Επώνυμο:");

        label6.setName(""); // NOI18N
        label6.setText("Όνομα:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usrPointsCardNum, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(label6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usrLAST_NAME)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(usrFIRST_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usrPointsCardNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usrLAST_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usrFIRST_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        label6.getAccessibleContext().setAccessibleDescription("");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Στοιχεία Πρόσβασης"));

        label1.setText("Παλιός Κωδικός Πρόσβασης:");

        label2.setText("Νέος Κωδικός Πρόσβασης:");

        label3.setText("Επιβεβαίωση Νέου Κωδικού:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usrNEW_PASSWORD)
                    .addComponent(usrOLD_PASSWORD)
                    .addComponent(usrNEW_PASSWORD_Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usrOLD_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usrNEW_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usrNEW_PASSWORD_Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ReturnToMainCustomerForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(ReturnToMainCustomerForm))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ReturnToMainCustomerFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnToMainCustomerFormActionPerformed

    }//GEN-LAST:event_ReturnToMainCustomerFormActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // διαβάζουμε τον ισχύον κωδικό πρόσβασης, που πληκτολόγησε ο χρήστης
        char[] usrOldPASSWORD = usrOLD_PASSWORD.getPassword();
        String usrFINAL_PASSWORD = "";
        for (char x : usrOldPASSWORD) {
            usrFINAL_PASSWORD += x;
        }
        // διαβάζουμε τον νέο κωδικό πρόσβασης, που πληκτολόγησε ο χρήστης
        char[] usrNewPASSWORD = usrNEW_PASSWORD.getPassword();
        String usrNEWFINAL_PASSWORD = "";
        for (char x : usrNewPASSWORD) {
            usrNEWFINAL_PASSWORD += x;
        }
        // διαβάζουμε τον νέο κωδικό πρόσβασης, που πληκτολόγησε ο χρήστης - 2η φορά (επιβεβαίωση)
        char[] usrConfirmedPASSWORD = usrNEW_PASSWORD_Confirm.getPassword();
        String usrConfirmedFINAL_PASSWORD = "";
        for (char x : usrConfirmedPASSWORD) {
            usrConfirmedFINAL_PASSWORD += x;
        }

        // αν ο κωδικό πρόσβασης που εινα αποθηκευμένος στη ΒΔ
        // δεν ταιριάζει με τον κωδικό πρόσβασης που πληκτρολόγησε ο χρήστης
        // τότε εμφανίζει μήνυμα σφάλματος
        if (!Usr.getPassword().equals(usrFINAL_PASSWORD)) {
            this.repaint();
            usrOLD_PASSWORD.requestFocus();
            JOptionPane.showMessageDialog(null, "Λανθασμένος κωδικός πρόσβασης.");
            return;
        }
        //ελέγχουμε εαν το νέο και επιβεβαιωμένο password ταιριάζουν
        if (!usrNEWFINAL_PASSWORD.equals(usrConfirmedFINAL_PASSWORD)
                || usrNEWFINAL_PASSWORD.equals("")) {
            this.repaint();
            usrOLD_PASSWORD.requestFocus();
            JOptionPane.showMessageDialog(null, "Ο νέος κωδικός πρόσβασης δεν είναι εφικτό να επιβεβαιωθεί.");
            return;
        }

        try {
            Usr.setPassword(usrNEWFINAL_PASSWORD);
            JOptionPane.showMessageDialog(null, "NEW PASSWORD=" + Usr.getPassword());
            ParentFrame.CUSTOMER_UPDATE_PASSWORD(Usr);
            ParentFrame.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Ο νέος κωδικός πρόσβασης αποθηκεύτηκε στο σύστημα.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR!!! " + e.getMessage().toString());

        }

    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ReturnToMainCustomerForm;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private javax.swing.JTextField usrFIRST_NAME;
    private javax.swing.JTextField usrLAST_NAME;
    private javax.swing.JPasswordField usrNEW_PASSWORD;
    private javax.swing.JPasswordField usrNEW_PASSWORD_Confirm;
    private javax.swing.JPasswordField usrOLD_PASSWORD;
    private javax.swing.JTextField usrPointsCardNum;
    // End of variables declaration//GEN-END:variables
}
