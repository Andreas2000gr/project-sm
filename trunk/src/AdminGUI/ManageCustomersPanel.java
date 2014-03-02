/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AdminGUI;

import LocalDB.Customer;
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
public class ManageCustomersPanel extends javax.swing.JPanel {

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
    private final List<Customer> changedCust = new  ArrayList<>();
    private Customer cust = new Customer();
    
    public ManageCustomersPanel(SuperMarketParentFrame frame) {
        this.loc = frame.getLoc();
        this.frame = frame;
        initComponents();
        loc.getTransaction().begin();
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
        GoBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        CustomerTable = new javax.swing.JTable();
        SaveButton = new javax.swing.JButton();
        NewButton = new javax.swing.JButton();
        ClearChanges = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Διαχείριση Πελατών"));

        GoBack.setText("Επιστροφή");
        GoBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoBackActionPerformed(evt);
            }
        });

        CustomerTable.getTableHeader().setReorderingAllowed(false);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customerList, CustomerTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${customerId}"));
        columnBinding.setColumnName("Customer Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pointsCardNumber}"));
        columnBinding.setColumnName("PointsCard No.");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${firstName}"));
        columnBinding.setColumnName("First Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${lastName}"));
        columnBinding.setColumnName("Last Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${address}"));
        columnBinding.setColumnName("Address");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${creditCardId}"));
        columnBinding.setColumnName("Credit Card Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${availablePoints}"));
        columnBinding.setColumnName("Available Points");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${password}"));
        columnBinding.setColumnName("Password");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        CustomerTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CustomerTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(CustomerTable);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GoBack)
                        .addGap(233, 233, 233)
                        .addComponent(ClearChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewButton, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SaveButton)
                            .addComponent(NewButton)
                            .addComponent(ClearChanges))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GoBack)
                        .addGap(11, 11, 11))))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void GoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoBackActionPerformed
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
    }//GEN-LAST:event_GoBackActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
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
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void CustomerTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CustomerTablePropertyChange
        if ("tableCellEditor".equals(evt.getPropertyName())) {
            row = CustomerTable.getSelectedRow();
            column = CustomerTable.getSelectedColumn();
            if (CustomerTable.isEditing()) {
                oldValue = CustomerTable.getValueAt(row, column).toString();
            } else {
                newValue = CustomerTable.getValueAt(row, column).toString();
                if (!oldValue.equals(newValue)) {
                    tableChanged = true;
                    cust = customerList.get(CustomerTable.convertRowIndexToModel(row));
                    loc.merge(cust);
                }
            }
        }
    }//GEN-LAST:event_CustomerTablePropertyChange

    private void ClearChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearChangesActionPerformed
                try {
                    loc.getTransaction().rollback();
                    frame.pnl = new ManageCustomersPanel(frame);
                    frame.addPanelInMain();                    
                } catch (Exception e) {
                        e.printStackTrace();
                    loc.getTransaction().rollback();            
                }
    }//GEN-LAST:event_ClearChangesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearChanges;
    private javax.swing.JTable CustomerTable;
    private javax.swing.JButton GoBack;
    private javax.swing.JButton NewButton;
    private javax.swing.JButton SaveButton;
    private java.util.List<LocalDB.Customer> customerList;
    private javax.persistence.Query customerQuery;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
