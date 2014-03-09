package threads;

import LocalDB.Customer;
import LocalDB.Product;
import LocalDB.ProductPurchase;
import LocalDB.Purchase;
import LocalDB.Store;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import javax.persistence.EntityManager;
import supermarket.DBmanager;

/**
 *
 * @author Euh
 *
 *
 * Να γίνει προσομοίωση αγορών από διαφορετικούς πελάτες χρησιμοποιώντας νήματα
 * (threads). Συγκεκριμένα, κάθε αγορά θα πραγματοποιείται από ένα νήμα το οποίο
 * θα επιλέγει τυχαία ένα πελάτη από τη βάση, θα γεμίζει το καλάθι του με ένα
 * τυχαίο αριθμό διαφορετικών προϊόντων με άνω όριο τα 20 προϊόντα ανά καλάθι,
 * και θα επιχειρεί να προσπελάσει την «Αρχή Πιστοποίησης Καρτών» για να
 * ολοκληρώσει τη συναλλαγή με την πιστωτική κάρτα που είναι αποθηκευμένη στο
 * προφίλ. Όμως, κατά την επικοινωνία με την «Αρχή Πιστοποίησης Καρτών» τίθενται
 * κάποιοι περιορισμοί:
 *
 */
class Simulator {

    private DBmanager db;//Σύνδεση με τη Βάση Δεδομένων
    private Purchase Basket;


    public Simulator(DBmanager db) {
        this.db = db;
        Basket = new Purchase();
    }

    /**
     * Μέθοδος που επιλέγει τυχαία ένα πελάτη από τη βάση και επιστρέφει το
     * αντικείμενο του πελάτη
     */
    public Customer RandomFindCustomer() {
        //χρήση της random
        Random r = new Random();
        Customer customer = new Customer();

        //βρίσκουμε όλους τους πελάτες και τους επιστρέφουμε στη βάση
        ArrayList<Customer> customers = db.FindAllCustomers();
        //επιλέγουμε μια τυχαία θέση, με ανώτατο όριο, το όριο της λίστας μας
        int customerIndex = r.nextInt(customers.size());
        //και βρίσκουμε τον πελάτη που βρίσκεται στη συγκεκριμέη θέση
        customer = customers.get(customerIndex);

        return customer;
    }

    public Purchase PopulateBasket(Customer customer) {
        Random r = new Random();
        //αποθηκεύουμε όλα τα καταστήματα σε μια λίστα
        ArrayList<Store> stores = db.FindAllStores();
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

        //Προσθέτουμε στο καλάθι μας(Purchase) το προϊόν που 
        Basket.setCustomer(customer);
        Basket.setDatetime(null);
        Basket.setAmount(0);
        Basket.setPointsEarned(0);
        Basket.setProductPurchaseCollection(ProdPurchCollection);
        //Basket.getProductPurchaseCollection().add(ProdPurchCollection);
        Basket.setStore(s);
        Basket.setDelivery(false);
        
        return Basket;
    }


}