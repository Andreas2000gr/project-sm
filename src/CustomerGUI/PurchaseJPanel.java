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
import LocalDB.Store;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import supermarket.DBmanager;
import supermarket.SuperMarketParentFrame;

/**
 *
 * @author Euh
 */
public class PurchaseJPanel extends javax.swing.JPanel {

    private final DBmanager db = new DBmanager();
    private SuperMarketParentFrame frame;
    private Store store;
    private final Object[] columnNames = {"Όνομα Προϊόντος", "Κωδικός", "Πόντοι", "Τιμή"};
    private DefaultTableModel DTModel;
    public  Purchase Basket;
    private Collection<ProductPurchase> ProductPurchaseCollection = new ArrayList<>(0);
    private Customer Usr;
    private EntityManager loc;

    /**
     * Creates new form PurchaseJPanel Πραγματοποίηση αγορών: Θα ανοίγει
     * κατάλληλη φόρμα στην οποία ο πελάτης θα επιλέγει αρχικά το κατάστημα από
     * το οποίο θα πραγματοποιήσει τις αγορές του και στη συνέχεια θα
     * εμφανίζονται όλα τα προϊόντα που είναι διαθέσιμα στο κατάστημα σε μορφή
     * πίνακα.
     */
    public PurchaseJPanel(SuperMarketParentFrame ParentFrame) {
        this.frame = ParentFrame;
        this.store = new Store();
        this.Usr = ParentFrame.cust;
        this.Basket = new Purchase();
        initComponents();
        this.loc = ParentFrame.getLoc();
        if (!loc.getTransaction().isActive()) {
            loc.getTransaction().begin();
        }
    }

    private void PurchaseProducts(int row, int Quantity) {
        try {
            //Προσθέτουμε στο καλάθι τα προιόντα και την ποσότητά τους
            Product p = loc.find(Product.class, jTableProducts.getValueAt(row, 0));
            ProductPurchase ppp = new ProductPurchase();
            ppp.setPurchaseId(Basket);
            ppp.setProductId(p);
            ppp.setQuantity(Quantity);
            ProductPurchaseCollection = Basket.getProductPurchaseCollection();

            //Εάν ανανεωθεί η λίστα αγορών
            if (ProductPurchaseCollection.add(ppp)){
                //Ενημέρωση λίστας αγορών
                Basket.setProductPurchaseCollection(ProductPurchaseCollection);
            }

            /**
             * * θέτουμε τιμές για το Purchase με την τρέχουσα ημερομηνία/ώρα
             * και τον πελάτη που πραγματοποιεί την παραγγελία **
             */
            
            JOptionPane.showMessageDialog(this, "Το προϊόν " + p.getName() + " προστέθηκε στο καλάθι.");
            this.JComboBoxStore.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Απέτυχε! η προσθήκη του προϊόντος στο καλάθι.");
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

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("SuperMarket-local-PU").createEntityManager();
        productQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM Product p");
        productList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : productQuery.getResultList();
        storeQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT s FROM Store s");
        storeList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : storeQuery.getResultList();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProducts = new javax.swing.JTable();
        JComboBoxStore = new javax.swing.JComboBox();
        label1 = new java.awt.Label();
        jButtonGoToBasket = new javax.swing.JButton();
        jButtonAddProductToBasket = new javax.swing.JButton();

        jButton3.setText("επιστροφή");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Λίστα Προϊόντων"));

        jTableProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${selectedItem.productCollection}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JComboBoxStore, eLProperty, jTableProducts);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${productId}"));
        columnBinding.setColumnName("Product Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${price}"));
        columnBinding.setColumnName("Price");
        columnBinding.setColumnClass(Float.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${points}"));
        columnBinding.setColumnName("Points");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${code}"));
        columnBinding.setColumnName("Code");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTableProducts, org.jdesktop.beansbinding.ELProperty.create("${selectedElement}"), jTableProducts, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(jTableProducts);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        JComboBoxStore.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Store) {
                    store = (Store)value;
                    setText(store.getName());
                } else {
                    setText("Επιλέξτε Κατάστημα...");
                }
                return this;
            }
        });

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, storeList, JComboBoxStore);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JComboBoxStore, org.jdesktop.beansbinding.ELProperty.create("${selectedItem.name}"), JComboBoxStore, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        JComboBoxStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBoxStoreActionPerformed(evt);
            }
        });

        label1.setText("Κατάστημα:");

        jButtonGoToBasket.setLabel("Μετάβαση στο καλάθι");
        jButtonGoToBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGoToBasketActionPerformed(evt);
            }
        });

        jButtonAddProductToBasket.setText("Προσθήκη στο καλάθι");
        jButtonAddProductToBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddProductToBasketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JComboBoxStore, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jButtonGoToBasket)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAddProductToBasket)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JComboBoxStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonGoToBasket)
                        .addComponent(jButtonAddProductToBasket)))
                .addGap(12, 12, 12))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        frame.pnl = new CustMainPanel(frame);
        frame.addPanelInMain();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void JComboBoxStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboBoxStoreActionPerformed
        Store store = (Store) JComboBoxStore.getSelectedItem();
        jTableProducts.updateUI();
    }//GEN-LAST:event_JComboBoxStoreActionPerformed

    private void jButtonAddProductToBasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddProductToBasketActionPerformed
        /**
         * * Ο χρήστης θα έχει τη δυνατότητα να επιλέξει ένα προϊόν και να το
         * προσθέσει στο καλάθι. **
         */

        int row = jTableProducts.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,
                    "Επιλέξτε ένα προϊόν.",
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] Confirmation = {"Ναι", "Οχι"};
        Integer choiceC = JOptionPane.showOptionDialog(null,
                "Επιθυμείτει την προσθήκη του προϊόντος στο καλάθι;",
                "Προσθήκη στο καλάθι",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                Confirmation,
                Confirmation[0]);
        /**
         * * Αν επιλεχθεί η προσθήκη στο καλάθι, εμφανίζεται παράθυρο-διάλογος
         * ο οποίος επιβεβαιώνει την προσθήκη του προϊόντος στο καλάθι και
         * ζητάει από το χρήστη να επιλέξει τα ζητούμενα τεμάχια. **
         */
        if (choiceC == JOptionPane.YES_OPTION) {
            Object ChoiceQ = JOptionPane.showInputDialog(
                    null, "Αριθμός τεμαχίων", "Ποσότητα", JOptionPane.INFORMATION_MESSAGE, null, null, null
            );

            if (ChoiceQ == null) {
                //ο χρήστης ακύρωσε τη διαδικασία
                return;
            }
            //αν το Input δεν είναι αριθμός, τότε θα εμφανιστεί κατάλληλο μήνυμα στο χρήστη
            if (!isInteger(ChoiceQ.toString())) {
                JOptionPane.showMessageDialog(this,
                        "Πληκτρολογήσατε μη αριθμητική τιμή.",
                        "Σφάλμα",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // αν ο χρήστης πατήσει οκ, τότε θα προσθέσει στο καλάθι το επιλεγμένο προϊόν
            int Quantity = Integer.parseInt(ChoiceQ.toString());
            PurchaseProducts(
                    jTableProducts.getSelectedRow(), Quantity
            );//βάλε το προιόν στο καλάθι
        }
    }//GEN-LAST:event_jButtonAddProductToBasketActionPerformed

    private void jButtonGoToBasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGoToBasketActionPerformed
            Basket.setCustomer(Usr);
            Basket.setDatetime(frame.date);
            Basket.setAmount(0);
            Basket.setPointsEarned(0);
            Basket.setProductPurchaseCollection(ProductPurchaseCollection);
            Basket.setStore(store);
            Basket.setDelivery(false);
        
            // Καταχώρηση στη ΒΔ
            try {
                if (!loc.getTransaction().isActive()) {
                    loc.getTransaction().begin();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Σφάλμα κατά την καταχώρηση...");
                loc.getTransaction().rollback();
                return; // Έξοδος στο μενού
            }
        frame.pnl = new ViewBasketJPanel(this.frame, Basket);
        frame.addPanelInMain();
    }//GEN-LAST:event_jButtonGoToBasketActionPerformed
    // METHOD: check if value is integer
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JComboBoxStore;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAddProductToBasket;
    private javax.swing.JButton jButtonGoToBasket;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProducts;
    private java.awt.Label label1;
    private java.util.List<LocalDB.Product> productList;
    private javax.persistence.Query productQuery;
    private java.util.List<LocalDB.Store> storeList;
    private javax.persistence.Query storeQuery;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
