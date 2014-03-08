/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerGUI;

import LocalDB.Customer;
import LocalDB.Product;
import LocalDB.ProductPurchase;
import LocalDB.Purchase;
import LocalDB.Voucher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
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
    private Collection<ProductPurchase> ProductPurchaseCollection = new ArrayList<>(0);
    private Customer Usr;
    private final Object[] columnNames = {"Όνομα Προϊόντος", "Κωδικός", "Πόντοι", "Τιμή", "Ποσότητα"};
    private DefaultTableModel DTModel;
    private Collection<Voucher> voucher;
    private DefaultListModel CustEarnedChecks;

    /**
     * Creates new form ViewBasketJPanel
     */
    public ViewBasketJPanel(SuperMarketParentFrame ParentFrame) {
        initComponents();
        this.ParentFrame = ParentFrame;
        this.Usr = ParentFrame.cust;
    }

    public ViewBasketJPanel(
            SuperMarketParentFrame ParentFrame, Purchase Bask, Collection<ProductPurchase> PPCol
    ) {
        this.ParentFrame = ParentFrame;
        this.Usr = ParentFrame.cust;
        this.Basket = Bask;
        this.ProductPurchaseCollection = PPCol;
        PopulateCustPurchase();
        populateAvailableVoucher();
        initComponents();
    }

    public void PopulateCustPurchase() {
        jTableBasket.removeAll();

        float totalPrice = 0.0f;
        int totalPointsEarned = 0;

        this.DTModel = new DefaultTableModel(new Object[0][0], columnNames);
        for (ProductPurchase pp : ProductPurchaseCollection) {
            Product p = pp.getProductId();

            Object[] object = new Object[5];
            object[0] = p.getName();
            object[1] = p.getCode();
            object[2] = p.getPoints();
            object[3] = p.getPrice();
            object[4] = pp.getQuantity();
            this.DTModel.addRow(object);
            totalPrice = totalPrice + (p.getPrice() * pp.getQuantity());
            totalPointsEarned = totalPointsEarned + (p.getPoints() * pp.getQuantity());
        }
        this.jTableBasket.setModel(this.DTModel);
        this.repaint();

        jTextFieldSumPontoi.setText(String.valueOf(totalPrice));
        jTextFieldTotalPrice.setText(String.valueOf(totalPointsEarned));
    }

    public void populateAvailableVoucher() {
        CustEarnedChecks = new DefaultListModel();
        Object[] object = new Object[1];
        // παίρνουμε τις επιταγές που έχει ο χρήστης 
        this.voucher = Usr.getVoucherCollection();

        for (Voucher v : voucher) {
            //βρίσκουμε μόνο τις έγκυρες
            if (v.getVoucherStatus()) {
                object[0] = "[" + v.getVoucherId() + "]:" + v.getVoucherDate();
                CustEarnedChecks.addElement(object);
            }
        }

        EarnedChecksLists.setModel(CustEarnedChecks);
        this.repaint();
    }

    public Purchase getBasket() {
        return Basket;
    }

    public Collection<ProductPurchase> getProductPurchaseCollection() {
        return ProductPurchaseCollection;
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

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("SuperMarket-local-PU").createEntityManager();
        productPurchaseQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM ProductPurchase p");
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
        button1 = new java.awt.Button();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        EarnedChecksLists = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        button2 = new java.awt.Button();
        button3 = new java.awt.Button();
        label3 = new java.awt.Label();
        label5 = new java.awt.Label();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        ReturnToMainCustomerForm = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Καλαθι"));

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, purchaseList, jTableBasket);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${customer}"));
        columnBinding.setColumnName("Customer");
        columnBinding.setColumnClass(LocalDB.Customer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${store}"));
        columnBinding.setColumnName("Store");
        columnBinding.setColumnClass(LocalDB.Store.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${productPurchaseCollection}"));
        columnBinding.setColumnName("Product Purchase Collection");
        columnBinding.setColumnClass(java.util.Collection.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${delivery}"));
        columnBinding.setColumnName("Delivery");
        columnBinding.setColumnClass(java.io.Serializable.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pointsEarned}"));
        columnBinding.setColumnName("Points Earned");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${amount}"));
        columnBinding.setColumnName("Amount");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${datetime}"));
        columnBinding.setColumnName("Datetime");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${purchaseId}"));
        columnBinding.setColumnName("Purchase Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTableBasket);

        label1.setText("Συνολικοί Πόντοι:");

        jTextFieldSumPontoi.setEnabled(false);

        label2.setText("Συνολική Τιμή:");

        jTextFieldTotalPrice.setEnabled(false);

        button1.setLabel("Αφαίρεση από το καλάθι");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldTotalPrice)
                            .addComponent(jTextFieldSumPontoi, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldSumPontoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTotalPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Επιταγές"));

        jScrollPane2.setViewportView(EarnedChecksLists);

        jScrollPane3.setViewportView(jList2);

        button2.setLabel("αφαίρεση");

        button3.setLabel("προσθήκη");

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
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(70, 70, 70))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Τρόπος αποστολής"));

        jRadioButton1.setText("Παραλαβή από το κατάστημα");

        jRadioButton2.setText("Αποστολή στο χώρο του πελάτη");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jButton1.setLabel("Ολοκλήρωση αγοράς");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(jButton1)))
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
                    .addComponent(jButton1))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ReturnToMainCustomerFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnToMainCustomerFormActionPerformed
        ParentFrame.pnl = new PurchaseJPanel(this.ParentFrame, this.getBasket(), this.getProductPurchaseCollection());
        ParentFrame.addPanelInMain();
    }//GEN-LAST:event_ReturnToMainCustomerFormActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        //αφαιρεί από το καλάθι το επιλεγμένο προϊόν
        int row = jTableBasket.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,
                    "Επιλέξτε ένα προϊόν.",
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
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
            Collection<ProductPurchase> PPurchList = new ArrayList<>();

            //αν ο χρήστης δεν έχει επιλέξει προϊόν προς διαγραφή.
            if (jTableBasket.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Επιλέξτε ένα προϊον για να αφαιρεθεί από το καλάθι.");
                return;//επιστρέφει στην φόρμα, χωρίς να προχωρήσει παρακάτω
            }
            //αν έχει επιλέξει τότε θα βρει ποιο είαι και θα το διαγράψει από τη λίστα
            ProductPurchase pprch;
            for (Integer i : jTableBasket.getSelectedRows()) {
//                System.out.println("i="+i);
//                System.out.println("jTableBasket.convertRowIndexToModel(i)="+jTableBasket.convertRowIndexToModel(i));
                PPurchList.add(productPurchaseList.get(jTableBasket.convertRowIndexToModel(i)));
            }
            productPurchaseList.removeAll(PPurchList);
            jTableBasket.updateUI();
            
            for (ProductPurchase pp : PPurchList) {
                try {
                    pprch = db.getLoc().find(ProductPurchase.class, pp.getProductPurchaseId());
                    db.getLoc().remove(pprch);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Απέτυχε! Η διαγραφή του προϊόντος από το καλάθι.");
                }
            }
            PPurchList.clear();
    }//GEN-LAST:event_button1ActionPerformed
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList EarnedChecksLists;
    private javax.swing.JButton ReturnToMainCustomerForm;
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JButton jButton1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
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
    private javax.persistence.Query productPurchaseQuery;
    private java.util.List<LocalDB.Purchase> purchaseList;
    private javax.persistence.Query purchaseQuery;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
