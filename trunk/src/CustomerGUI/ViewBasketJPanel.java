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
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import supermarket.DBmanager;
import supermarket.SuperMarketParentFrame;

/**
 *
 * @author Euh
 */
public class ViewBasketJPanel extends javax.swing.JPanel {

    private final DBmanager db = new DBmanager();
    private SuperMarketParentFrame ParentFrame;
    private Purchase Basket;
    private Customer Usr;
    private final Object[] columnNames = {"Όνομα Προϊόντος", "Κωδικός", "Πόντοι", "Τιμή", "Ποσότητα"};
    private EntityManager loc;
    private Collection<Voucher> refVouch = new ArrayList<>(0);
    private Collection<Voucher> avVouch = new ArrayList<>(0);
    private Float totalPrice;
    private Integer totalPointsEarned;
    private Integer attempts = 0;
    private Integer retval = 0;
    private boolean tableChanged = false;
    private List<ProductPurchase> PPurList = new ArrayList<>();
  

    /**
     * Creates new form ViewBasketJPanel
     */

    public ViewBasketJPanel(
            SuperMarketParentFrame ParentFrame, Purchase Bask
    ) {
        this.ParentFrame = ParentFrame;
        this.Usr = ParentFrame.cust;
        this.Basket = Bask;
        this.PPurList = (List<ProductPurchase>)(Basket.getProductPurchaseCollection());
        initComponents();
        this.loc = ParentFrame.getLoc();
        if (!loc.getTransaction().isActive()) {
            loc.getTransaction().begin();
        }
        repaintAvVouc(Usr);
        repaintRefVouch(Usr);
        PopulateCustPurchase();
        ShopButton.setSelected(true);
        if (Usr.getAddress().isEmpty()) {
            DeliveryButton.setEnabled(false);
        }
        updateUI();
    }
    
    private void repaintAvVouc(Customer cust){
        avVouch = getAvVoucher(Usr);
        avVouch.removeAll(refVouch);
            DefaultListModel<Voucher> model = new DefaultListModel();
            for (Voucher v : avVouch) {
                model.addElement(v);
        }
        this.AvVoucherList.setModel(model);
        AvVoucherList.updateUI();
        revalidate();            
    }
    
    private void repaintRefVouch(Customer cust){
        DefaultListModel<Voucher> model = new DefaultListModel();
        for (Voucher p : refVouch) {
            model.addElement(p);
        }
        this.RefundedVoucherList.setModel(model);
        RefundedVoucherList.updateUI();
        revalidate();
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
        revalidate();
        return avVouchCol;
    }
    
    public void PopulateCustPurchase() {
        totalPrice = 0.0f;
        totalPointsEarned = 0;
        for (ProductPurchase pp : productPurchaseList) {
            Product p = pp.getProductId();
            totalPrice = totalPrice + (p.getPrice() * pp.getQuantity());
            totalPointsEarned = totalPointsEarned + (p.getPoints() * pp.getQuantity());
        }

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
        
        BasketTable.updateUI();
        BasketTable.revalidate();
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
        javax.persistence.Query productPurchaseQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM ProductPurchase p");
        productPurchaseList = PPurList;
        purchaseQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM Purchase p");
        purchaseList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : purchaseQuery.getResultList();
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
        ReturnToMainCustomerForm = new javax.swing.JButton();
        CheckOutButton = new javax.swing.JButton();
        label2 = new java.awt.Label();
        jTextFieldTotalPrice = new javax.swing.JTextField();
        label1 = new java.awt.Label();
        jTextFieldSumPontoi = new javax.swing.JTextField();
        RemovefromBasketButton = new java.awt.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        BasketTable = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Καλάθι"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Επιταγές"));

        AvVoucherList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Voucher) {
                    setText("Επιταγή Αρ. "+((Voucher)value).getVoucherId().toString());
                }
                return renderer;
            }
        });
        jScrollPane2.setViewportView(AvVoucherList);

        RefundedVoucherList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Voucher) {
                    setText("Επιταγή Αρ. "+((Voucher)value).getVoucherId().toString());
                }
                return renderer;
            }
        });
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        ReturnToMainCustomerForm.setText("Επιστροφή");
        ReturnToMainCustomerForm.setActionCommand("return");
        ReturnToMainCustomerForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnToMainCustomerFormActionPerformed(evt);
            }
        });

        CheckOutButton.setLabel("Ολοκλήρωση αγοράς");
        CheckOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ReturnToMainCustomerForm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CheckOutButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ShopButton)
                        .addGap(18, 18, 18)
                        .addComponent(DeliveryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShopButton)
                    .addComponent(DeliveryButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReturnToMainCustomerForm)
                    .addComponent(CheckOutButton))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        label2.setText("Συνολική Τιμή:");

        jTextFieldTotalPrice.setEnabled(false);

        label1.setText("Συνολικοί Πόντοι:");

        jTextFieldSumPontoi.setEnabled(false);

        RemovefromBasketButton.setLabel("Αφαίρεση από το καλάθι");
        RemovefromBasketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovefromBasketButtonActionPerformed(evt);
            }
        });

        BasketTable.setColumnSelectionAllowed(true);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, productPurchaseList, BasketTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${productId}"));
        columnBinding.setColumnName("Προϊόν");
        columnBinding.setColumnClass(LocalDB.Product.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${quantity}"));
        columnBinding.setColumnName("Ποσότητα");
        columnBinding.setColumnClass(Integer.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        BasketTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BasketTableFocusGained(evt);
            }
        });
        jScrollPane4.setViewportView(BasketTable);
        BasketTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (BasketTable.getColumnModel().getColumnCount() > 0) {
            BasketTable.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
                @Override

                public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus,int row, int column){

                    //get the label
                    JLabel label = (JLabel)super.getTableCellRendererComponent(table, value,isSelected, hasFocus,row, column);

                    label.setText(((Product)value).getName());
                    return label;
                }
            });
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jTextFieldTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSumPontoi, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RemovefromBasketButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RemovefromBasketButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSumPontoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void CheckOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckOutButtonActionPerformed
        CreditCardDialog dialog = new CreditCardDialog(ParentFrame,true);
        dialog.setVisible(true);
        retval = dialog.getReturnStatus();
        if (retval==0) {
            attempts++;
            if (attempts>2) {
                JOptionPane.showMessageDialog(null, "Έχετε ξεπεράσει το όριο προσπαθειών. "
                        + "Η παραγγελία ακυρώθηκε...");
                ParentFrame.pnl = new CustMainPanel(ParentFrame);
                ParentFrame.addPanelInMain();
            }
        } else {  // Η πληρωμή έγινε
            //Ακύρωση επιταγών
            for (Voucher v : refVouch) {
                for (Voucher w : Usr.getVoucherCollection()) {
                    if (v.equals(w)) {
                        w.setVoucherStatus(false);
                    }
                }
            }
            // Ενημέρωση λοιπών στοιχείων παραγγελίας
            Basket.setCustomer(Usr); 
            Basket.setAmount(totalPrice);
            Basket.setPointsEarned(totalPointsEarned);
            Basket.setProductPurchaseCollection(productPurchaseList);

            if (DeliveryButton.isSelected()) {
                Basket.setDelivery(true);
            } else {
                Basket.setDelivery(false);
            }

            if (ParentFrame.persistPurchase(Basket)) { 
                JOptionPane.showMessageDialog(null, "Η παραγγελία καταχωρήθηκε επιτυχώς."
                        + " Ευχαριστούμε!!!");
                ParentFrame.pnl = new CustMainPanel(ParentFrame);
                ParentFrame.addPanelInMain();
            }else {
                JOptionPane.showMessageDialog(null, "Σφάλμα!!!"
                        + " Ακύρωση παραγγελίας!!!");
                ParentFrame.pnl = new CustMainPanel(ParentFrame);
                ParentFrame.addPanelInMain();
            }
        }      
    }//GEN-LAST:event_CheckOutButtonActionPerformed

    private void ReturnToMainCustomerFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnToMainCustomerFormActionPerformed
        ParentFrame.pnl = new PurchaseJPanel(this.ParentFrame);
        ParentFrame.addPanelInMain();
    }//GEN-LAST:event_ReturnToMainCustomerFormActionPerformed

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
            repaintRefVouch(Usr);
            repaintAvVouc(Usr);
            PopulateCustPurchase();        
        }else {
            JOptionPane.showMessageDialog(null,"Δεν εχετε επιλέξει επιταγή...");
        }
    }//GEN-LAST:event_RemoveVoucherActionPerformed

    private void ShopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShopButtonActionPerformed
        ShopButton.setSelected(true);        
        DeliveryButton.setSelected(false);
    }//GEN-LAST:event_ShopButtonActionPerformed

    private void DeliveryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeliveryButtonActionPerformed
        ShopButton.setSelected(false);
        DeliveryButton.setSelected(true);        
    }//GEN-LAST:event_DeliveryButtonActionPerformed

    private void RemovefromBasketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemovefromBasketButtonActionPerformed
        List<ProductPurchase> ppurList = new ArrayList<>();
        if (BasketTable.getSelectedRows().length != 0) {
            RemovefromBasketButton.setEnabled(false);
            for (Integer i : BasketTable.getSelectedRows()) {
                PPurList.remove(productPurchaseList.get(BasketTable.convertRowIndexToModel(i)));
            }

            BasketTable.updateUI();
            PopulateCustPurchase();
            //Basket.setProductPurchaseCollection(productPurchaseList);
        }
    }//GEN-LAST:event_RemovefromBasketButtonActionPerformed

    private void BasketTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BasketTableFocusGained
        RemovefromBasketButton.setEnabled(true);
    }//GEN-LAST:event_BasketTableFocusGained
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button AddVoucher;
    private javax.swing.JList AvVoucherList;
    private javax.swing.JTable BasketTable;
    private javax.swing.JButton CheckOutButton;
    private javax.swing.JRadioButton DeliveryButton;
    private javax.swing.JList RefundedVoucherList;
    private java.awt.Button RemoveVoucher;
    private java.awt.Button RemovefromBasketButton;
    private javax.swing.JButton ReturnToMainCustomerForm;
    private javax.swing.JRadioButton ShopButton;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextFieldSumPontoi;
    private javax.swing.JTextField jTextFieldTotalPrice;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label5;
    private java.util.List<LocalDB.ProductPurchase> productPurchaseList;
    private java.util.List<LocalDB.Purchase> purchaseList;
    private javax.persistence.Query purchaseQuery;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
