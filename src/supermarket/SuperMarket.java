/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supermarket;
/**
 *
 * @author Loukatos
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import supermarket.DBmanager;


public class SuperMarket {

    private DBmanager db = new DBmanager();
    
    /**
     * @param args the command line arguments
     */
    
    public SuperMarket(){
    this.db=db;
    }

    private static void createAndShowGUI() {
        // Δημιουργούμε το SuperMarket
        SuperMarketParentFrame s = new SuperMarketParentFrame();
        s.setVisible(true);
        s.pack();
    }
        
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
                }
        });

    }
    
    // @EPA:: Καθαρίζει όλους τους πίνακες της ΒΔ     
    private void CleanDB(){        
        db.getLoc().getTransaction().begin();
        try {
            Query q;
            q=db.getLoc().createQuery("DELETE FROM ProductPurchase pp");
            q.executeUpdate();                        
            q=db.getLoc().createQuery("DELETE FROM Product p");
            q.executeUpdate();            
            q=db.getLoc().createQuery("DELETE FROM Purchase pu");
            q.executeUpdate();                                    
            q=db.getLoc().createQuery("DELETE FROM Store s");
            q.executeUpdate();            
            q=db.getLoc().createQuery("DELETE FROM Customer c");
            q.executeUpdate();                        
            q=db.getLoc().createQuery("DELETE FROM Voucher v");
            q.executeUpdate();  
            
            db.getLoc().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            db.getLoc().getTransaction().rollback();
        } 
    }   
}


