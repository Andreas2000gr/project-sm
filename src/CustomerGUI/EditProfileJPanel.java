/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerGUI;

import LocalDB.Customer;
import externalDB.CreditCardAuthority;
import externalDB.ExternalBank;
import java.util.Collection;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import supermarket.DBmanager;
import supermarket.SuperMarketParentFrame;
import supermarket.WelcomePanel;

/**
 *
 * @author Euh
 *
 * Επεξεργασία προφίλ: Θα ανοίγει κατάλληλη φόρμα που θα εμφανίζει τα ήδη
 * καταχωρημένα στοιχεία του πελάτη σε επεξεργάσιμα πεδία, ενώ κάτω από τη φόρμα
 * θα υπάρχουν κουμπιά «Τροποποίηση» και «Διαγραφή». Αν πατηθεί το κουμπί
 * τροποποίηση, οι όποιες αλλαγές έχει κάνει ο χρήστης στα στοιχεία του θα
 * αποθηκεύονται στη βάση. Αν πατηθεί το διαγραφή, θα διαγράφεται ο λογαριασμός
 * του πελάτη ύστερα από προειδοποιητικό μήνυμα επιβεβαίωσης και ο χρήστης θα
 * αποσυνδέεται αυτόματα από την εφαρμογή.
 */
public class EditProfileJPanel extends javax.swing.JPanel {

    private final DBmanager db = new DBmanager();
    private SuperMarketParentFrame ParentFrame;
    private Customer Usr;
    private CreditCardAuthority CreditCard;

    /**
     * Creates new form EditProfileJPanel
     */
    public EditProfileJPanel(SuperMarketParentFrame ParentFrame) {
        initComponents();
        this.ParentFrame = ParentFrame;
        this.Usr = ParentFrame.cust;
        this.CreditCard = CreditCard;

        /* εντοπίζουμε τον χρήστη και αρχικοποιούμε τα πεδία jtexfields
         της φόρμας, ώστε να εμφανίζεται η πληροφορία.*/
        usrCUSTOMER_ID.setText(ParentFrame.cust.getCustomerId().toString());
        usrLAST_NAME.setText(ParentFrame.cust.getLastName().toString());
        usrFIRST_NAME.setText(ParentFrame.cust.getFirstName().toString());
        usrADDRESS.setText(ParentFrame.cust.getAddress().toString());

        /* EXTERNAL DATABASE */
        CreditCardAuthority creditcard = getCreditCardAuthority(ParentFrame.cust.getCreditCardId());

        if (!creditcard.equals(null)) {
            usrOWNER_NAME.setText(creditcard.getOwnerName().toString());
            usrCARD_NUMBER.setText(creditcard.getNumber().toString());
            usrCVV.setText(creditcard.getCvv().toString());
        }
    }

    //Method:: to get credit card 
    private CreditCardAuthority getCreditCardAuthority(Integer CREDIT_CARD_ID) {

        TypedQuery<CreditCardAuthority> QueryCreditfindByPkCardId = db.getExt().createNamedQuery("CreditCardAuthority.findByPkCardId", CreditCardAuthority.class);
        QueryCreditfindByPkCardId.setParameter("pkCardId", CREDIT_CARD_ID);//set dynamic data for query
        List<CreditCardAuthority> CreditCardList = QueryCreditfindByPkCardId.getResultList();

        if (CreditCardList.size() != 1) {
            return null;
        } else {
            // Εφόσον βρεθεί μοναδικό αποτέλεσμα το επιστρέφουμε.
            CreditCardAuthority c = CreditCardList.get(0);
            return c;
        }
    }

    //Method:: Επιστρέφει την τράπεζα του πελάτη
    private ExternalBank getExternalBank(String BankName) {

        TypedQuery<ExternalBank> Query = db.getExt().createNamedQuery("ExternalBank.findByName", ExternalBank.class);
        Query.setParameter("name", BankName);//set dynamic data for query
        List<ExternalBank> extBank = Query.getResultList();

        if (extBank.size() != 1) {
            return null;
        } else {
            // Εφόσον βρεθεί μοναδικό αποτέλεσμα το επιστρέφουμε.
            ExternalBank b = extBank.get(0);
            return b;
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("SuperMarket-external-PU").createEntityManager();
        externalBankQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT e.name FROM ExternalBank e");
        externalBankList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : externalBankQuery.getResultList();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        usrCUSTOMER_ID = new javax.swing.JTextField();
        usrLAST_NAME = new javax.swing.JTextField();
        usrADDRESS = new javax.swing.JTextField();
        usrFIRST_NAME = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usrOWNER_NAME = new javax.swing.JTextField();
        usrCVV = new javax.swing.JTextField();
        usrCARD_NUMBER = new javax.swing.JTextField();
        ExternalBankList = new javax.swing.JComboBox();
        ReturnToMainCustomerForm = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Στοιχεία Πελάτη"));

        jLabel9.setText("Κωδικός Πελάτη:");

        jLabel10.setText("Επώνυμο:");

        jLabel11.setText("Όνομα:");

        jLabel12.setText("Διεύθυνση:");

        usrCUSTOMER_ID.setEditable(false);
        usrCUSTOMER_ID.setBackground(new java.awt.Color(204, 204, 204));
        usrCUSTOMER_ID.setToolTipText("");
        usrCUSTOMER_ID.setName(""); // NOI18N

        usrLAST_NAME.setToolTipText("");

        usrADDRESS.setToolTipText("");

        usrFIRST_NAME.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usrADDRESS)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usrCUSTOMER_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usrFIRST_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usrLAST_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(usrCUSTOMER_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(usrLAST_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usrFIRST_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usrADDRESS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Στοιχεία Πιστωτικής Κάρτας"));
        jPanel1.setName("Στοιχεία Πιστωτικής Κάρτας"); // NOI18N

        jLabel1.setText("Τράπεζα:");

        jLabel2.setText("Ονοματεπώνυμο Κατόχου:");

        jLabel3.setText("Αριθμός Κάρτας:");

        jLabel4.setText("αριθμός CVV");
        jLabel4.setToolTipText("");

        usrOWNER_NAME.setToolTipText("");

        usrCVV.setToolTipText("");

        usrCARD_NUMBER.setToolTipText("");

        ExternalBankList.setName("ExternalBankList");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, externalBankList, ExternalBankList);
        bindingGroup.addBinding(jComboBoxBinding);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usrCVV, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExternalBankList, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usrOWNER_NAME, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(usrCARD_NUMBER))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ExternalBankList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usrOWNER_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(usrCARD_NUMBER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usrCVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        ReturnToMainCustomerForm.setText("επιστροφή");
        ReturnToMainCustomerForm.setActionCommand("return");
        ReturnToMainCustomerForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnToMainCustomerFormActionPerformed(evt);
            }
        });

        jButton2.setText("Τροποποίηση");
        jButton2.setToolTipText("");
        jButton2.setName("Τροποποίηση"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Διαγραφή");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 671, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(ReturnToMainCustomerForm)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ReturnToMainCustomerForm)
                        .addComponent(jButton2)
                        .addComponent(jButton1))
                    .addContainerGap()))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void ReturnToMainCustomerFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnToMainCustomerFormActionPerformed
        ParentFrame.pnl = new CustMainPanel(this.ParentFrame);
        ParentFrame.addPanelInMain();
    }//GEN-LAST:event_ReturnToMainCustomerFormActionPerformed

    // METHOD: START check if value is integer
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Integer CHARACTERS_ALLOWED;

        if (usrLAST_NAME.equals("")) {
            JOptionPane.showMessageDialog(null, "Το επώνυμο πελάτη δε μπορεί να είναι κενό.");
            return;
        }
        CHARACTERS_ALLOWED = 40;
        if (usrLAST_NAME.getText().length() > CHARACTERS_ALLOWED) {
            JOptionPane.showMessageDialog(null, "Το επώνυμο πελάτη δε μπορεί να περιέχει περισσότερους από " + CHARACTERS_ALLOWED + " χαρακτήρες.");
            return;
        }

        if (usrFIRST_NAME.equals("")) {
            JOptionPane.showMessageDialog(null, "Το όνομα πελάτη δε μπορεί να είναι κενό.");
            return;
        }
        CHARACTERS_ALLOWED = 30;
        if (usrFIRST_NAME.getText().length() > CHARACTERS_ALLOWED) {
            JOptionPane.showMessageDialog(null, "Το όνομα πελάτη δε μπορεί να περιέχει περισσότερους από " + CHARACTERS_ALLOWED + " χαρακτήρες.");
            return;
        }

        if (usrADDRESS.equals("")) {
            JOptionPane.showMessageDialog(null, "Η διεύθυνση πελάτη δε μπορεί να είναι κενό.");
            return;
        }
        CHARACTERS_ALLOWED = 50;
        if (usrADDRESS.getText().length() > CHARACTERS_ALLOWED) {
            JOptionPane.showMessageDialog(null, "Η διεύθυνση πελάτη δε μπορεί να περιέχει περισσότερους από " + CHARACTERS_ALLOWED + " χαρακτήρες.");
            return;
        }

        if (usrOWNER_NAME.equals("")) {
            JOptionPane.showMessageDialog(null, "Το ονοματεπώνυμο κατόχου της κάρτας δε μπορεί να είναι κενό.");
            return;
        }
        CHARACTERS_ALLOWED = 255;
        if (usrOWNER_NAME.getText().length() > CHARACTERS_ALLOWED) {
            JOptionPane.showMessageDialog(null, "Το ονοματεπώνυμο κατόχου της κάρτας δε μπορεί να περιέχει περισσότερους από " + CHARACTERS_ALLOWED + " χαρακτήρες.");
            return;
        }

        if (usrCARD_NUMBER.equals("")) {
            JOptionPane.showMessageDialog(null, "Ο αριθμός της κάρτας δε μπορεί να είναι κενό.");
            return;
        }
        CHARACTERS_ALLOWED = 16;
        if (usrCARD_NUMBER.getText().length() != CHARACTERS_ALLOWED) {
            JOptionPane.showMessageDialog(null, "Ο αριθμός της κάρτας πρέπει να ακριβώς " + CHARACTERS_ALLOWED + " χαρακτήρες.");
            return;
        }

        if (!isInteger(usrCARD_NUMBER.getText().substring(0, 4))
                || !usrCARD_NUMBER.getText().substring(4, 5).equals("-")
                || !isInteger(usrCARD_NUMBER.getText().substring(5, 11))
                || !usrCARD_NUMBER.getText().substring(11, 12).equals("-")
                || !isInteger(usrCARD_NUMBER.getText().substring(12, 15))) {
            JOptionPane.showMessageDialog(null, "Ο αριθμός της κάρτας πρέπει να έχει τη μορφή XXXX-XXXXXX-XXXX");
            return;
        }

        if (usrCVV.equals("")) {
            JOptionPane.showMessageDialog(null, "Το CVV της κάρτας δε μπορεί να είναι κενό.");
            return;
        }
        if (!isInteger(usrCVV.getText())) {
            JOptionPane.showMessageDialog(null, "Το CVV της κάρτας πρέπει να είναι ακέραιος αριθμός.");
            return;
        }
        CHARACTERS_ALLOWED = 3;
        if (usrCVV.getText().length() != CHARACTERS_ALLOWED) {
            JOptionPane.showMessageDialog(null, "Το CVV της κάρτας πρέπει να ακριβώς " + CHARACTERS_ALLOWED + " ψηφία.");
            return;
        }

        ExternalBank bank = getExternalBank(ExternalBankList.getSelectedItem().toString());
        if (bank == null) {
            JOptionPane.showMessageDialog(null, "Επιλέξτε μια από τις διαθέσιμες τράπεζες.");
            return;
        }
        
        String Message = "";//Προειδοποιητικό μήνυμα
       
        try {

            //Ενημέρωση των στοιχείων της κάρτας
            CreditCard.setOwnerName(usrOWNER_NAME.getText());
            CreditCard.setNumber(usrCARD_NUMBER.getText());
            CreditCard.setCvv(usrCVV.getText());
            CreditCard.setBank(bank);

            db.UPDATE_CREDIT_CARD(CreditCard);

            Message="Τα στοιχεία της πιστωτικής κάρτας ενημερώθηκαν επιτυχώς.";
         } catch (Exception e) {
            Message="Απέτυχε η ενημέρωση της κάρτας! Η εξωτερική βάση δεν δέχεται update της πιστωτικής κάρτας.";
        }        

        try {
            //Ενημέρωση των στοιχείων του πελάτη
            Usr.setLastName(usrLAST_NAME.getText());
            Usr.setFirstName(usrFIRST_NAME.getText());
            Usr.setAddress(usrADDRESS.getText());

            db.UPDATE_CUSTOMER(Usr);
            
            JOptionPane.showMessageDialog(null, "Τα στοιχεία του πελάτη ενημερώθηκαν επιτυχώς.\n"+Message);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Απέτυχε! η ενημέρωση των στοιχείων του πελάτη.\n"+Message);
        }
        
        
        ParentFrame.setEnabled(true);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //επιβεβαίωση διαγραφής λογαριασμού πελάτη
        int UsrChoice = JOptionPane.showConfirmDialog(this, "Οριστική διαγραφή του λογαριασμού πελάτη;.", "Διαγραφή Λογαριασμού", JOptionPane.YES_NO_OPTION
        );
        if (UsrChoice != 0) {
            return; //κλείσιμο παραθύρου
        }

        try {
            //ΟΡΙΣΤΙΚΗ ΔΙΑΓΡΑΦΗ ΠΕΛΑΤΗ ΑΠΟ ΤΗ ΒΑΣΗ ΔΕΔΕΟΜΕΝΩΝ
            Customer customer = db.getLoc().find(Customer.class, Usr.getCustomerId());
            db.DELETE_CUSTOMER(customer);
            JOptionPane.showMessageDialog(this, "Ο λογαριασμός διεγράφη. Θα επιστρέψετε στην αρχική οθόνη.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Απέτυχε! η διαγραφή των στοιχείων του πελάτη.");
        }

        ParentFrame.pnl = new WelcomePanel(this.ParentFrame);
        ParentFrame.addPanelInMain();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ExternalBankList;
    private javax.swing.JButton ReturnToMainCustomerForm;
    private javax.persistence.EntityManager entityManager;
    private java.util.List<externalDB.ExternalBank> externalBankList;
    private javax.persistence.Query externalBankQuery;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField usrADDRESS;
    private javax.swing.JTextField usrCARD_NUMBER;
    private javax.swing.JTextField usrCUSTOMER_ID;
    private javax.swing.JTextField usrCVV;
    private javax.swing.JTextField usrFIRST_NAME;
    private javax.swing.JTextField usrLAST_NAME;
    private javax.swing.JTextField usrOWNER_NAME;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
