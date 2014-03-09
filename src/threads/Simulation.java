package threads;

import LocalDB.Customer;
import java.util.ArrayList;
import java.util.Random;
import supermarket.DBmanager;

/**
 *
 * @author Euh
 * 
 * 
 * Να γίνει προσομοίωση αγορών από διαφορετικούς πελάτες χρησιμοποιώντας νήματα (threads). 
 * Συγκεκριμένα, κάθε αγορά θα πραγματοποιείται από ένα νήμα το οποίο θα επιλέγει τυχαία 
 * ένα πελάτη από τη βάση, θα γεμίζει το καλάθι του 
 * με ένα τυχαίο αριθμό διαφορετικών προϊόντων με άνω όριο τα 20 προϊόντα ανά καλάθι, 
 * και θα επιχειρεί να προσπελάσει την «Αρχή Πιστοποίησης Καρτών» για να ολοκληρώσει 
 * τη συναλλαγή με την πιστωτική κάρτα που είναι αποθηκευμένη στο προφίλ. 
 * Όμως, κατά την επικοινωνία με την «Αρχή Πιστοποίησης Καρτών» τίθενται κάποιοι περιορισμοί:
 * 
 */
class Simulation {
    
    private DBmanager db;//Σύνδεση με τη Βάση Δεδομένων
    private Threads[] threads;
    
    public Simulation(DBmanager db){
        this.db = db;
    }
    
    /*** Τυχαία επιλογή πελάτη από τη βάση ***/
    public void RandomFindCustomer() { 
        //χρήση της random
        Random r = new Random();
        int counter = 1;
        
        //βρίσκουμε όλους τους πελάτες και τους επιστρέφουμε στη βάση
        ArrayList<Customer> customers = db.FindAllCustomers();
        //Για κάθε ένα νήμα
        for (int i = 0; i < threads.length; i++) { 
            //τυχαία επέλεξε έναν πελάτη
             int customerIndex = r.nextInt(customers.size());
             threads[i] = new Threads(db,customers.get(customerIndex),this);
             threads[i].setName("Thread " + counter);
             counter++;
             customers.remove(customerIndex);
        }
    }    
    
}
