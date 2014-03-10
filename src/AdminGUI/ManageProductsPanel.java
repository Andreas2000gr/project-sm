/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AdminGUI;

import LocalDB.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import supermarket.SuperMarketParentFrame;

/**
 *
 * @author Panagis
 */
public class ManageProductsPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageCustomersPanel
     */
    private final SuperMarketParentFrame frame;
    private EntityManager loc;
    private Boolean tableChanged = false;
    private String oldValue;
    private String newValue;
    private Integer row;
    private Integer column;
    private final List<Product> changedProd = new  ArrayList<>();
    private Product prod = new Product();
    
    public ManageProductsPanel(SuperMarketParentFrame frame) {
        this.loc = frame.getLoc();
        this.frame = frame;
        initComponents();
        if (!loc.getTransaction().isActive()) {
            loc.getTransaction().begin();
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
        customerQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT c FROM Customer c");
        customerList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : customerQuery.getResultList();
        productQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM Product p");
        productList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : productQuery.getResultList();
        ReturnButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        NewButton = new javax.swing.JButton();
        ClearChanges = new javax.swing.JButton();
        RemoveRow = new javax.swing.JButton();
        AddRow = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Διαχείριση Προϊόντων"));

        ReturnButton.setText("Επιστροφή");
        ReturnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("Αποθήκευση");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        NewButton.setText("Δημιουργία");

        ClearChanges.setText("Επαναφορά");
        ClearChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearChangesActionPerformed(evt);
            }
        });

        RemoveRow.setText("-");
        RemoveRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveRowActionPerformed(evt);
            }
        });

        AddRow.setText("+");
        AddRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRowActionPerformed(evt);
            }
        });

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, productList, ProductTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${points}"));
        columnBinding.setColumnName("Points");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${price}"));
        columnBinding.setColumnName("Price");
        columnBinding.setColumnClass(Float.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ProductTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement}"), ProductTable, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ProductTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElements}"), ProductTable, org.jdesktop.beansbinding.BeanProperty.create("selectedElements"));
        bindingGroup.addBinding(binding);

        ProductTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ProductTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(ProductTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ReturnButton)
                        .addGap(233, 233, 233)
                        .addComponent(ClearChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewButton, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RemoveRow, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(AddRow, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RemoveRow, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SaveButton)
                            .addComponent(NewButton)
                            .addComponent(ClearChanges))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ReturnButton)
                        .addGap(11, 11, 11))))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void ReturnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnButtonActionPerformed
        Object[] options = {"Ναι","Οχι"};
        Integer choice = JOptionPane.showOptionDialog(null,
        "Όλες οι αλλαγές θα χαθούν. Είστε σίγουρος;",
        "Επαναφορά στο αρχικό μενού.",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);

        if (choice == JOptionPane.YES_OPTION){
            if (loc.getTransaction().isActive()) {
                try {
                    loc.getTransaction().rollback();
                    frame.pnl = new MainPanel(frame);
                    frame.addPanelInMain();
                } catch (Exception e) {
                        e.printStackTrace();
                    loc.getTransaction().rollback();
                    frame.pnl = new MainPanel(frame);
                    frame.addPanelInMain();
                }
            }
        }
    }//GEN-LAST:event_ReturnButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        if (tableChanged) {
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
                        frame.pnl = new MainPanel(frame);
                        frame.addPanelInMain();                    
                    } catch (Exception e) {
                            e.printStackTrace();
                        loc.getTransaction().rollback();            
                    }
                }
            } 
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void ClearChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearChangesActionPerformed
                try {
                    loc.getTransaction().rollback();
                    frame.pnl = new ManageProductsPanel(frame);
                    frame.addPanelInMain();                    
                } catch (Exception e) {
                        e.printStackTrace();
                    loc.getTransaction().rollback();            
                }
    }//GEN-LAST:event_ClearChangesActionPerformed

    private void RemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveRowActionPerformed

    }//GEN-LAST:event_RemoveRowActionPerformed

    private void AddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRowActionPerformed

    }//GEN-LAST:event_AddRowActionPerformed

    private void ProductTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ProductTablePropertyChange
        if ("tableCellEditor".equals(evt.getPropertyName())) {
            row = ProductTable.getSelectedRow();
            column = ProductTable.getSelectedColumn();
            if (ProductTable.isEditing()) {
                oldValue = ProductTable.getValueAt(row, column).toString();
            } else {
                newValue = ProductTable.getValueAt(row, column).toString();
                if (!oldValue.equals(newValue)) {
                    tableChanged = true;
                    prod = productList.get(ProductTable.convertRowIndexToModel(row));
                    loc.merge(prod);
                }
            }
        }
    }//GEN-LAST:event_ProductTablePropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddRow;
    private javax.swing.JButton ClearChanges;
    private javax.swing.JButton NewButton;
    private javax.swing.JTable ProductTable;
    private javax.swing.JButton RemoveRow;
    private javax.swing.JButton ReturnButton;
    private javax.swing.JButton SaveButton;
    private java.util.List<LocalDB.Customer> customerList;
    private javax.persistence.Query customerQuery;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JScrollPane jScrollPane1;
    private java.util.List<LocalDB.Product> productList;
    private javax.persistence.Query productQuery;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
