/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import LocalDB.Customer;
import LocalDB.Product;
import LocalDB.ProductPurchase;
import LocalDB.Purchase;
import LocalDB.Store;
import externalDB.CreditCardAuthority;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import supermarket.DBmanager;
import supermarket.PurchaseXMLManager;
import supermarket.SuperMarketParentFrame;
import supermarket.WelcomePanel;
import java.io.File;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
/**
 *
 * @author Euh
 */
public class SimulationJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ThreadsJPanel
     */

    private DBmanager db = new DBmanager();
    private Calendar cal = new GregorianCalendar();
    private Date date = cal.getTime();
    private EntityManager loc;
    private EntityManager ext;
    private SuperMarketParentFrame frame;
    private Query q;
    private Purchase Basket;
    private List<Purchase> purchaseList = new ArrayList<>();
    private final CyclicBarrier gate = new CyclicBarrier(3);
    private PurchaseXMLManager xml;

    public SimulationJPanel(SuperMarketParentFrame frame) {
        this.frame = frame;
        initComponents();
        this.loc = db.getLoc();
        this.ext = db.getExt();
        this.Basket = new Purchase();
        if (!db.getLoc().getTransaction().isActive()) {
            db.getLoc().getTransaction().begin();
        }
    }
        
    public Customer RandomFindCustomer() {
        //χρήση της random
        Random r = new Random();
        Customer customer = new Customer();
        List<Customer> customers = new ArrayList<>();
        //βρίσκουμε όλους τους πελάτες και τους επιστρέφουμε στη βάση
        try {
            customers = db.FindAllCustomers();
        } catch (javax.persistence.NoResultException e) {
            e.printStackTrace();
            loc.getTransaction().rollback();
            return null;
        }
        //επιλέγουμε μια τυχαία θέση, με ανώτατο όριο, το όριο της λίστας μας
        int customerIndex = r.nextInt(customers.size());
        //και βρίσκουμε τον πελάτη που βρίσκεται στη συγκεκριμέη θέση
        customer = customers.get(customerIndex);

        return customer;
    }

    public boolean validateCCard(Customer customer){
        CreditCardAuthority CCard = new CreditCardAuthority();
        try {
            q = ext.createNamedQuery("CreditCardAuthority.findByPkCardId");
            q.setParameter("pkCardId", customer.getCreditCardId());
            CCard = (CreditCardAuthority)q.getSingleResult();
            return true;
        } catch (javax.persistence.NoResultException e) {
            JOptionPane.showMessageDialog(null, "Κάρτα δεν βρέθηκε...");
            return false;
        } catch (javax.persistence.NonUniqueResultException e) {
            JOptionPane.showMessageDialog(null, "Μή μοναδική κάρτα...");
            return false;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Σφάλμα...");
            return false; 
        }
    }
    
    public Purchase PopulateBasket(Customer customer) {
        Random r = new Random();
        Basket = new Purchase();
        List<Store> stores = new ArrayList<>();
        //αποθηκεύουμε όλα τα καταστήματα σε μια λίστα
        try {
            q = loc.createNamedQuery("Store.findAll");
            stores = q.getResultList();
        } catch (javax.persistence.NoResultException e) {
            e.printStackTrace();
            loc.getTransaction().rollback();
            return null;
        }
    
                 
        //βρίσκουμε ένα κατάστημα
        int storeIndex = r.nextInt(stores.size());
        Store s = stores.get(storeIndex);

        //αποθηκεόυμε όλα τα προϊόντα του επιλεγμένου καταστήματος σε μια λίστα
        ArrayList<Product> products = new ArrayList(0);
        products = new ArrayList<Product>(s.getProductCollection());
        //λίστα στην οποία θα αποθηκεύσουμε τα επιλεγμένα προϊόντα
        Collection<ProductPurchase> ProdPurchCollection = new ArrayList<>();

        //θα γεμίζει το καλάθι του με ένα τυχαίο αριθμό διαφορετικών προϊόντων 
        //με άνω όριο τα 20 προϊόντα ανά καλάθι
        int Limit = 20;
        //Αν στο κατάστημα υπάρχουν λιγότερα από 20 προϊόντα
        //τότε παίρνουμε το μέγεθος της λίστας
        if (Limit > products.size()) {
            Limit = products.size();
        }
        //επιλέγουμε τυχαία έναν αριθμό προϊόντων που θα αγοραστούν
        int NumOfSelectedProducts = 1 + r.nextInt(Limit);

        //επιλέγουμε NumOfSelectedProducts τυχαία προϊόντα 
        //Για κάθε ένα από τα επιλεγμένα προϊόντα
        for (int i = 0; i < NumOfSelectedProducts; i++) {
            //ορίζουμε έναν τυχαίο αριθμό τεμαχίων
            //ως ανώνατο όριο ορίζουμε τα 10 τεμάχια
            int Quantity = 1 + r.nextInt(10);

            //επιλέγουμε ένα τυχαίο προϊόν του επιλεγμένου καταστήματος
            int productIndex = r.nextInt(products.size());
            Product p = products.get(productIndex);

            //Ορίζουμε τη συχέτιση Αγορά-Προϊόν
            ProductPurchase PP = new ProductPurchase();
            PP.setProductId(p);
            PP.setQuantity(Quantity);
            PP.setProductPurchaseId(null);
            PP.setPurchaseId(Basket);

            //αποθηκεούμε τη συσχέτιση προϊόν-αγοράς στη λίστα
            ProdPurchCollection.add(PP);

            //αφαιρούμε από τη λίστα το προϊόν που επιλέξαμε
            //για να μην το επιλέξουμε πάλι
            products.remove(p);
        }

        //επιλέγουμε τυχαία τον τρόπο παράδοσης του προϊόντος
        boolean Delivery = false;
        if (r.nextInt(1) == 1) {
            Delivery = true;
        }
        
        Float cost = 0.0f;
        Integer points = 0;
        
        for (ProductPurchase pp : ProdPurchCollection) {
             cost = cost + (pp.getQuantity() * pp.getProductId().getPrice());
             points = points + (pp.getQuantity() * pp.getProductId().getPoints());
        }
        //Προσθέτουμε στο καλάθι μας(Purchase) το προϊόν που 
        Basket.setCustomer(customer);
        Basket.setDatetime(date);
        Basket.setAmount(cost);
        Basket.setPointsEarned(points);
        Basket.setStore(s);
        Basket.setDelivery(Delivery);
        Basket.getProductPurchaseCollection().addAll(ProdPurchCollection);
        
        try {
            // αρχικοποίηση transaction
            if (!loc.getTransaction().isActive()) {
                loc.getTransaction().begin();
            }
            loc.persist(Basket);
            return Basket;
        } catch (Exception e) {
            e.printStackTrace();
            loc.getTransaction().rollback();
            return null;
        }
    }
    
    public Purchase createPurchase(){
        
        //Γράφουμε τα αποτελέσματα στο textarea
        if (jTextAreaSimulation.getText().isEmpty()) {
            
        }
        jTextAreaSimulation.append("Έναρξη διαδικασίας προσωμείωσης νημάτων.\n");

        //για κάθε ένα από τα νήματα επιλέγεται ένας πελάτης
        jTextAreaSimulation.append("Τυχαία επιλογή πελατών\n");

        //εντοπισμός τυχαίων πελατών
        Customer customer = RandomFindCustomer();
        jTextAreaSimulation.append("Επιλέχθηκε ο πελάτης " + customer.getLastName() + "\n");

        //Αγορά τυχαίων προϊόντων και προσθήκη στο καλάθι του επιλεγμένου πελάτη
        jTextAreaSimulation.append("Τυχαία επιλογή προϊόντων για αγορά\n");
        Purchase purchase = PopulateBasket(customer);
        jTextAreaSimulation.append("Purchase ID: "+purchase.getPurchaseId()+ "\n");

        for(ProductPurchase pp : purchase.getProductPurchaseCollection()){
            jTextAreaSimulation.append("Επιλέχθηκε τυχαία το προϊόν: " + pp.getProductId().getName() +"- τεμάχια:" + pp.getQuantity() + "\n");
        }

        try {
            // αρχικοποίηση transaction
            if (!loc.getTransaction().isActive()) {
                loc.getTransaction().begin();
            }
                loc.persist(purchase);
                return Basket;
                
        } catch (Exception e) {
            e.printStackTrace();
            loc.getTransaction().rollback();
            return null;
        }
    }
    
    public void startThreads(Integer num){
        Thread t1 = new Thread(){
            public void run(){
                try {
                        gate.await();
                        Purchase pur = createPurchase();
                        try {
                        // αρχικοποίηση transaction
                        if (!loc.getTransaction().isActive()) {
                        loc.getTransaction().begin();
                        }
                        if (validateCCard(pur.getCustomer())) {
                            xml.writeXML(pur, true, 1, this.getName(), true);
                        }

                        } catch (Exception e) {
                        e.printStackTrace();
                        loc.getTransaction().rollback();
                        }
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimulationJPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BrokenBarrierException ex) {
                    Logger.getLogger(SimulationJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
           
            }};
        
         Thread t2 = new Thread(){
            public void run(){
                try {
                        gate.await();
                        Purchase pur = createPurchase();
                        try {
                        // αρχικοποίηση transaction
                        if (!loc.getTransaction().isActive()) {
                        loc.getTransaction().begin();
                        }
                        if (validateCCard(pur.getCustomer())) {
                            xml.writeXML(pur, true, 1, this.getName(), true);

                        }

                        } catch (Exception e) {
                        e.printStackTrace();
                        loc.getTransaction().rollback();
                        }
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimulationJPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BrokenBarrierException ex) {
                    Logger.getLogger(SimulationJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
           
            }};

        t1.start();
        t2.start();
        try {
            // αρχικοποίηση transaction
            if (!loc.getTransaction().isActive()) {
                loc.getTransaction().begin();
            }
        loc.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            loc.getTransaction().rollback();

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

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JSpinnerNumOfThreads = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSimulation = new javax.swing.JTextArea();

        jButton1.setText("επιστροφή");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Προσομοίωση αγορών"));

        jLabel1.setText("Αριθμός νημάτων:");

        JSpinnerNumOfThreads.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));

        jButton2.setText("εκκίνηση προσομοίωσης");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JSpinnerNumOfThreads, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JSpinnerNumOfThreads, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTextAreaSimulation.setEditable(false);
        jTextAreaSimulation.setColumns(20);
        jTextAreaSimulation.setRows(5);
        jScrollPane1.setViewportView(jTextAreaSimulation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frame.pnl = new WelcomePanel(frame);
        frame.addPanelInMain();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Integer choice = (Integer)(JSpinnerNumOfThreads.getValue());
        startThreads(choice);
        try {
            gate.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(SimulationJPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(SimulationJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner JSpinnerNumOfThreads;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaSimulation;
    // End of variables declaration//GEN-END:variables
}
