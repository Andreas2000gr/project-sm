/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerGUI;

import LocalDB.*;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import supermarket.DBmanager;
import supermarket.SuperMarketParentFrame;

/**
 *
 * @author Euh
 */
public class ViewBasketJPanel extends javax.swing.JPanel {

    private final DBmanager db = new DBmanager();
    private SuperMarketParentFrame ParentFrame;
    private Purchase Basket = new Purchase();
    private Customer Usr;
    private final Object[] columnNames = {"Όνομα Προϊόντος", "Κωδικός", "Πόντοι", "Τιμή", "Ποσότητα"};
    private DefaultTableModel DTModel;
    private EntityManager loc;
    private Collection<Voucher> allVouch = new ArrayList<>(0);
    private Collection<Voucher> refVouch = new ArrayList<>(0);
    private Collection<Voucher> avVouch = new ArrayList<>(0);
    private Float totalPrice;
    private Integer totalPointsEarned;
  

    /**
     * Creates new form ViewBasketJPanel
     */

    public ViewBasketJPanel(
            SuperMarketParentFrame ParentFrame, Purchase Bask
    ) {
        this.ParentFrame = ParentFrame;
        this.Usr = ParentFrame.cust;
        this.Basket = Bask;
        this.allVouch = getAvVoucher(Usr);
        initComponents();
        this.loc = ParentFrame.getLoc();
        if (!loc.getTransaction().isActive()) {
            loc.getTransaction().begin();
        }
        JOptionPane.showMessageDialog(null,Usr.getFirstName());
        repaintAvVouc(Usr);
        repaintRefVouch(Usr);
        PopulateCustPurchase();
    }
    
    private void repaintAvVouc(Customer cust){
        avVouch = allVouch;
        if(avVouch.removeAll(refVouch)){
            DefaultListModel<Voucher> model = new DefaultListModel();
            for (Voucher v : avVouch) {
                model.addElement(v);
            }
            this.AvVoucherList.setModel(model); 
        };
    }
    
    private void repaintRefVouch(Customer cust){
        DefaultListModel<Voucher> model = new DefaultListModel();
        for (Voucher p : refVouch) {
            model.addElement(p);
        }
        this.RefundedVoucherList.setModel(model);
    }
    
    private Collection<Voucher> getAvVoucher(Customer cust){
        Collection<Voucher> avVouchCol = new ArrayList<>();
        Iterator iter = cust.getVoucherCollection().iterator();
        Voucher v = new Voucher();
        while (iter.hasNext()) {
            v = (Voucher)iter.next();
            if (v.getVoucherStatus()) {
                avVouchCol.add(v);
            }
        }
        return avVouchCol;
    }
    
     public void PopulateCustPurchase() {
        totalPrice = 0.0f;
        totalPointsEarned = 0;
        DTModel = new DefaultTableModel(new Object[0][0], columnNames);
        for (ProductPurchase pp : Basket.getProductPurchaseCollection()) {
            Product p = pp.getProductId();

            Object[] object = new Object[5];
            object[0] = p.getName();
            object[1] = pp;
            object[2] = p.getPoints();
            object[3] = p.getPrice();
            object[4] = pp.getQuantity();
            DTModel.addRow(object);
            totalPrice = totalPrice + (p.getPrice() * pp.getQuantity());
            totalPointsEarned = totalPointsEarned + (p.getPoints() * pp.getQuantity());
        }
        jTableBasket.setModel(DTModel);
        
        jTableBasket.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override

            public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus,int row, int column){

                //get the label
                JLabel label = (JLabel)super.getTableCellRendererComponent(table, value,isSelected, hasFocus,row, column);

                label.setText(((ProductPurchase)value).getProductId().getCode());
                return label;
            }
        });
        if (!refVouch.isEmpty()) {
            for (Voucher v : refVouch) {
                totalPrice = totalPrice - 6.0f;
            }
            if (totalPrice < 0.0f) {
                totalPrice = 0.0f;
            }
        }
        jTextFieldSumPontoi.setText(String.valueOf(totalPointsEarned));
        jTextFieldTotalPrice.setText(String.format("%.2f",totalPrice));
        
        jTableBasket.updateUI();
    }

    public Purchase getBasket() {
        return Basket;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("SuperMarket-local-PU").createEntityManager();
        javax.persistence.Query productPurchaseQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM ProductPurchase p");
        productPurchaseList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : productPurchaseQuery.getResultList();
        purchaseQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM Purchase p");
        purchaseList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : purchaseQuery.getResultList();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBasket = new javax.swing.JTable();
        label1 = new java.awt.Label();
        jTextFieldSumPontoi = new javax.swing.JTextField();
        label2 = new java.awt.Label();
        jTextFieldTotalPrice = new javax.swing.JTextField();
        RemovefromBasketButton = new java.awt.Button();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AvVoucherList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        RefundedVoucherList = new javax.swing.JList();
        RemoveVoucher = new java.awt.Button();
        AddVoucher = new java.awt.Button();
        label3 = new java.awt.Label();
        label5 = new java.awt.Label();
        jPanel3 = new javax.swing.JPanel();
        ShopButton = new javax.swing.JRadioButton();
        DeliveryButton = new javax.swing.JRadioButton();
        CheckOutButton = new javax.swing.JButton();
        ReturnToMainCustomerForm = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Καλαθι"));

        jScrollPane1.setViewportView(jTableBasket);
        if (jTableBasket.getColumnModel().getColumnCount() > 0) {
            jTableBasket.getColumnModel().getColumn(0).setHeaderValue("Προϊόν");
            jTableBasket.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
                @Override

                public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus,int row, int column){

                    //get the label
                    JLabel label = (JLabel)super.getTableCellRendererComponent(table, value,isSelected, hasFocus,row, column);

                    label.setText(((ProductPurchase)value).getProductId().getName());
                    return label;
                }
            });
            jTableBasket.getColumnModel().getColumn(1).setHeaderValue("Quantity");
        }

        label1.setText("Συνολικοί Πόντοι:");

        jTextFieldSumPontoi.setEnabled(false);

        label2.setText("Συνολική Τιμή:");

        jTextFieldTotalPrice.setEnabled(false);

        RemovefromBasketButton.setLabel("Αφαίρεση από το καλάθι");
        RemovefromBasketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovefromBasketButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSumPontoi, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(RemovefromBasketButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldSumPontoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(RemovefromBasketButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Επιταγές"));

        jScrollPane2.setViewportView(AvVoucherList);

        jScrollPane3.setViewportView(RefundedVoucherList);

        RemoveVoucher.setLabel("αφαίρεση");
        RemoveVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveVoucherActionPerformed(evt);
            }
        });

        AddVoucher.setLabel("προσθήκη");
        AddVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddVoucherActionPerformed(evt);
            }
        });

        label3.setText("Κερδισμένες Επιταγές:");

        label5.setText("Προς εξαργύρωση:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 112, Short.MAX_VALUE)
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RemoveVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AddVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(AddVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RemoveVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Τρόπος αποστολής"));

        ShopButton.setText("Παραλαβή από το κατάστημα");
        ShopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShopButtonActionPerformed(evt);
            }
        });

        DeliveryButton.setText("Αποστολή στο χώρο του πελάτη");
        DeliveryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeliveryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ShopButton)
                .addGap(18, 18, 18)
                .addComponent(DeliveryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShopButton)
                    .addComponent(DeliveryButton))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        CheckOutButton.setLabel("Ολοκλήρωση αγοράς");
        CheckOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckOutButtonActionPerformed(evt);
            }
        });

        ReturnToMainCustomerForm.setText("επιστροφή");
        ReturnToMainCustomerForm.setActionCommand("return");
        ReturnToMainCustomerForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnToMainCustomerFormActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(ReturnToMainCustomerForm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CheckOutButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReturnToMainCustomerForm)
                    .addComponent(CheckOutButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CheckOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckOutButtonActionPerformed
        SuperMarketParentFrame s = new SuperMarketParentFrame();
        s.pnl =  new CreditCardVerification(s);
        s.addPanelInMain();
        s.pack();
        s.setVisible(true);
    }//GEN-LAST:event_CheckOutButtonActionPerformed

    private void ReturnToMainCustomerFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnToMainCustomerFormActionPerformed
        ParentFrame.pnl = new PurchaseJPanel(this.ParentFrame);
        ParentFrame.addPanelInMain();
    }//GEN-LAST:event_ReturnToMainCustomerFormActionPerformed

    private void RemovefromBasketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemovefromBasketButtonActionPerformed
        //αφαιρεί από το καλάθι το επιλεγμένο προϊόν
        int row = jTableBasket.getSelectedRow();
        if (row < 0) {
           //αν ο χρήστης δεν έχει επιλέξει προϊόν προς διαγραφή.
            JOptionPane.showMessageDialog(this,
                    "Επιλέξτε ένα προϊόν.",
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);

        } else {
           Object[] Confirmation = {"Ναι", "Οχι"};
           Integer choice = JOptionPane.showOptionDialog(null,
                "Επιθυμείτει την αφαίρεση του προϊόντος από το καλάθι;",
                "Αφαίρεση από το καλάθι",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                Confirmation,
                Confirmation[0]);

           if (choice == JOptionPane.YES_OPTION) {

               ProductPurchase PPur = new ProductPurchase();
            //αν έχει επιλέξει τότε θα βρει ποιο είναι και θα το διαγράψει από τη λίστα
               if (row > -1) {
                for (Integer i : jTableBasket.getSelectedRows()) {
                    PPur = (ProductPurchase)jTableBasket.getValueAt(jTableBasket.convertRowIndexToModel(i), 1);
                    Basket.getProductPurchaseCollection().remove(PPur);
                }
               JOptionPane.showMessageDialog(null, Basket.getProductPurchaseCollection().size());
            }
            PopulateCustPurchase();             
        }
        }
    }//GEN-LAST:event_RemovefromBasketButtonActionPerformed

    private void AddVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddVoucherActionPerformed
        if (!AvVoucherList.getSelectedValuesList().isEmpty()) {
            for (Object o : AvVoucherList.getSelectedValuesList()) {
                refVouch.add((Voucher)o);
            }
            repaintAvVouc(Usr);
            repaintRefVouch(Usr);
            PopulateCustPurchase();        
        }else {
            JOptionPane.showMessageDialog(null,"Δεν εχετε επιλέξει επιταγή...");
        }
    }//GEN-LAST:event_AddVoucherActionPerformed

    private void RemoveVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveVoucherActionPerformed
        if (!RefundedVoucherList.getSelectedValuesList().isEmpty()) {
            for (Object o : RefundedVoucherList.getSelectedValuesList()) {
                refVouch.remove((Voucher)o);
            }
            repaintAvVouc(Usr);
            repaintRefVouch(Usr);
            PopulateCustPurchase();        
        }else {
            JOptionPane.showMessageDialog(null,"Δεν εχετε επιλέξει επιταγή...");
        }
    }//GEN-LAST:event_RemoveVoucherActionPerformed

    private void ShopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShopButtonActionPerformed
        DeliveryButton.setSelected(false);
    }//GEN-LAST:event_ShopButtonActionPerformed

    private void DeliveryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeliveryButtonActionPerformed
        ShopButton.setSelected(false);
    }//GEN-LAST:event_DeliveryButtonActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button AddVoucher;
    private javax.swing.JList AvVoucherList;
    private javax.swing.JButton CheckOutButton;
    private javax.swing.JRadioButton DeliveryButton;
    private javax.swing.JList RefundedVoucherList;
    private java.awt.Button RemoveVoucher;
    private java.awt.Button RemovefromBasketButton;
    private javax.swing.JButton ReturnToMainCustomerForm;
    private javax.swing.JRadioButton ShopButton;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableBasket;
    private javax.swing.JTextField jTextFieldSumPontoi;
    private javax.swing.JTextField jTextFieldTotalPrice;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label5;
    private java.util.List<LocalDB.ProductPurchase> productPurchaseList;
    private java.util.List<LocalDB.Purchase> purchaseList;
    private javax.persistence.Query purchaseQuery;
    // End of variables declaration//GEN-END:variables
}
