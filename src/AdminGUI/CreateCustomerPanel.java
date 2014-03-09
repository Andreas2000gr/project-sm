/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AdminGUI;

import LocalDB.Customer;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import supermarket.*;

/**
 *
 * @author Panagis
 */
public class CreateCustomerPanel extends javax.swing.JPanel {

    /**
     * Creates new form CreateCustomerPanel
     */
    private final SuperMarketParentFrame frame;
    private final EntityManager loc;
    private Customer cust;
    private boolean acceptFn = false;
    private boolean acceptLn = false;
    private boolean acceptAd = true;
    
    public CreateCustomerPanel(SuperMarketParentFrame frame) {
        this.frame = frame;
        this.loc = frame.getLoc();
        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (!loc.getTransaction().isActive()) {
            loc.getTransaction().begin();
        }
    }
    
    private Integer validateCCard(String input){
        
        return (0);
    }
    
    private String createPointsCard(){
        String output;
        Random ran = new Random(); //Κατασκευή γεννήτριας τυχαίων αριθμών
        Integer ran1 = ran.nextInt(1000);  //Δημιουργία αριθμών 0-999
        Integer ran2 = ran.nextInt(1000);
        output= String.format("%03d",ran1) //μετατροπή τους σε τριψήφιους
            +"-"+String.format("%03d",ran2); //και απόδοση του String στην κάρτα του πελάτη
        return output;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ReturnButton = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        FirstNameLabel = new javax.swing.JLabel();
        LastNameLabel = new javax.swing.JLabel();
        AddressLabel = new javax.swing.JLabel();
        FnField = new javax.swing.JTextField();
        LnField = new javax.swing.JTextField();
        AdField = new javax.swing.JTextField();
        validation1 = new javax.swing.JLabel();
        validation2 = new javax.swing.JLabel();
        CCard = new javax.swing.JTextField();
        AddressLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Δημιουργία Πελάτη"));
        setOpaque(false);

        ReturnButton.setText("Επιστροφή");
        ReturnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnButtonActionPerformed(evt);
            }
        });

        ClearButton.setText("Καθαρισμός");
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("Αποθήκευση");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        FirstNameLabel.setText("Όνομα");

        LastNameLabel.setText("Επώνυμο");

        AddressLabel.setLabelFor(AdField);
        AddressLabel.setText("Διεύθυνση");

        FnField.setToolTipText("εώς 30 χαρακτήρες");
        FnField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FnFieldActionPerformed(evt);
            }
        });

        LnField.setToolTipText("εώς 40 χαρακτήρες");
        LnField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LnFieldActionPerformed(evt);
            }
        });

        AdField.setToolTipText("εώς 50 χαρακτήρες");

        validation1.setText("* ");

        validation2.setText("* ");

        CCard.setToolTipText("εώς 50 χαρακτήρες");
        CCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CCardActionPerformed(evt);
            }
        });

        AddressLabel1.setLabelFor(AdField);
        AddressLabel1.setText("Αριθμός Πιστωτικής");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AddressLabel)
                            .addComponent(LastNameLabel)
                            .addComponent(FirstNameLabel)
                            .addComponent(AddressLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CCard, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AdField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LnField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FnField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(validation2)
                            .addComponent(validation1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ReturnButton)
                        .addGap(336, 336, 336)
                        .addComponent(ClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FirstNameLabel)
                    .addComponent(validation1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LastNameLabel)
                    .addComponent(LnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validation2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddressLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddressLabel1))
                .addGap(131, 131, 131)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReturnButton)
                    .addComponent(ClearButton)
                    .addComponent(SaveButton))
                .addGap(11, 11, 11))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ReturnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnButtonActionPerformed
        frame.dispose();
    }//GEN-LAST:event_ReturnButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        if (acceptFn && acceptLn) {
//            cust = new Customer(
//                    null, 
//                    FnField.getText(), 
//                    LnField.getText(),
//                    , int availablePoints) {

            
            {
            
            JOptionPane.showMessageDialog(null,"Κάρτα πελάτη:"
                    + cust.getPointsCardNumber());
            }
            
            Object[] options = {"Ναι","Οχι"};
            Integer choice = JOptionPane.showOptionDialog(null,
            "Επιβεβαίωση αλλαγών;",
            null,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

            if (choice == JOptionPane.YES_OPTION){
                if (loc.getTransaction().isActive()) {
                    try {
                        loc.getTransaction().commit();
                        frame.dispose();
                    } catch (Exception e) {
                            e.printStackTrace();
                        loc.getTransaction().rollback();            
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Δεν έχουν δοθεί τα απαιτούμενα στοιχεία...");
            FnField.requestFocusInWindow();
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
        if (loc.getTransaction().isActive()) {
                try {
                    loc.getTransaction().rollback();
                    frame.pnl = new CreateCustomerPanel(frame);
                    frame.addPanelInMain();                    
                } catch (Exception e) {
                        e.printStackTrace();
                    loc.getTransaction().rollback();            
                }
        }
    }//GEN-LAST:event_ClearButtonActionPerformed

    private void FnFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FnFieldActionPerformed
        if (FnField.getText().length()<=30 && FnField.getText().length()>0 ) {
            acceptFn = true;
            LnField.requestFocusInWindow();            
        } else {
            acceptFn = false;
        }        
    }//GEN-LAST:event_FnFieldActionPerformed

    private void CCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CCardActionPerformed
        Integer cCardId = validateCCard(CCard.getText());
        if (cCardId > 0) {
            SaveButton.requestFocusInWindow();            
        } else {
            CCard.setText(null);
        } 
    }//GEN-LAST:event_CCardActionPerformed

    private void LnFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LnFieldActionPerformed
        if (LnField.getText().length()<=30 && LnField.getText().length()>0 ) {
            acceptLn = true;
            LnField.requestFocusInWindow();            
        } else {
            acceptLn = false;
        } 
    }//GEN-LAST:event_LnFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AdField;
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JLabel AddressLabel1;
    private javax.swing.JTextField CCard;
    private javax.swing.JButton ClearButton;
    private javax.swing.JLabel FirstNameLabel;
    private javax.swing.JTextField FnField;
    private javax.swing.JLabel LastNameLabel;
    private javax.swing.JTextField LnField;
    private javax.swing.JButton ReturnButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel validation1;
    private javax.swing.JLabel validation2;
    // End of variables declaration//GEN-END:variables
}