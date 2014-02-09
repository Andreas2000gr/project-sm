/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supermarket;

import java.util.Arrays;
import javax.swing.JPanel;
import AdminGUI.*;
import CustomerGUI.*;
import javax.persistence.*;
import LocalDB.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Panagis
 */
public class SuperMarketParentFrame extends javax.swing.JFrame {
    
    //Hard-coded admin credentials!!!
    private static String adminUn  = "admin";
    private static String adminPw  = "admin";
    public static String getAdminUn() {
        return adminUn;
    }
    public static String getAdminPw() {
        return adminPw;
    }
    
    private final DBmanager db = new DBmanager();
    public Customer cust; //Μεταβλητή που θα κρατήσει τον πελάτη και θα τον πασάρει σε κάθε JPanel
    private EntityManager loc = db.getLoc();  
    private EntityManager ext = db.getExt(); 

    public JPanel pnl = new WelcomePanel(this); //Αρχική εικόνα κεντρικού panel
    public JPanel brd = new BorderPanel(this);
    /**
    * Creates new form superMarketParentFrame
    */
    public SuperMarketParentFrame() {
        initComponents();
        this.jPanel6.add(pnl);
        this.jPanel2.add(brd);
        this.loc = db.getLoc();
        this.ext = db.getExt();
    }
        

    
    /**
     * Όταν κληθεί αφαιρεί το component που υπάρχει στην κεντρική περιοχή και προσθέτει το νέο
     * @param 
     */
    public void addPanelInMain(){
        jPanel6.removeAll();
        jPanel6.add(pnl);
        jPanel6.repaint();
        jPanel6.revalidate();
    }
    
    public void addPanelInLeftBorder(){
        jPanel2.removeAll();
        jPanel2.add(brd);
        jPanel2.repaint();
        jPanel2.revalidate();
    }
    
    public boolean validateCredentials(String un, char[] pw){
        if (un.equals(adminUn) && Arrays.equals(pw,adminPw.toCharArray())) {
            pnl = new MainPanel(this);
            return true;
        } 
        try {
            loc.getTransaction().begin();
            Query q = loc.createNamedQuery("Customer.findByIdPassword");//Χρήση του προκατασκευασμένου query
            q.setParameter("cardno", un);
            q.setParameter("password", new String(pw)); //Μετατροπή του char[]->String
            cust = (Customer)q.getSingleResult();
            pnl = new CustMainPanel(this);
            loc.close();
            return false;
        } catch (NoResultException e) { //Σε περίπτωση που δεν υπάρχει αυτή η κάρτα
                JOptionPane.showMessageDialog(null, "Λάθος Στοιχεία Εισόδου...");
                loc.getTransaction().rollback(); //Δεν ξεχνάμε να κλείσουμε το transaction!!!!
                return true;
        } catch (NonUniqueResultException e) { //Για να πιάσουμε δύο ίδιες κάρτες εφόσον δεν είναι μοναδικές
                JOptionPane.showMessageDialog(null, "Όχι μοναδικός χρήστης...");
                loc.getTransaction().rollback();
                return true;
        } catch (Exception e) { //Just in case...
                JOptionPane.showMessageDialog(null, "Σφάλμα βάσης...");
                loc.getTransaction().rollback();
                return true;
        } finally {
            return true;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Misc/phpThumb_generated_thumbnailjpg.jpg"))); // NOI18N
        jLabel2.setFocusable(false);
        jLabel2.setName(""); // NOI18N

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(0, 172, 63));
        jTextPane1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jTextPane1.setText("Εφαρμογή ηλεκτρονικών αγορών.");
        jTextPane1.setEnabled(false);
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(0, 172, 63));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel6.setLayout(new java.awt.CardLayout());
        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SuperMarketParentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuperMarketParentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuperMarketParentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuperMarketParentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
