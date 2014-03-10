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
import LocalDB.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SuperMarket {

    // Δημιουργία του ενιαίου DB manager
    private static DBmanager db = new DBmanager();

    /**
     * @param args the command line arguments
     */
    public SuperMarket() {

    }

    // @EPA:: Καθαρίζει όλους τους πίνακες της ΒΔ     
    private void CleanDB() {
        db.getLoc().getTransaction().begin();
        try {
            Query q;
            q = db.getLoc().createQuery("DELETE FROM ProductPurchase pp");
            q.executeUpdate();
            q = db.getLoc().createQuery("DELETE FROM Product p");
            q.executeUpdate();
            q = db.getLoc().createQuery("DELETE FROM Purchase pu");
            q.executeUpdate();
            q = db.getLoc().createQuery("DELETE FROM Store s");
            q.executeUpdate();
            q = db.getLoc().createQuery("DELETE FROM Voucher v");
            q.executeUpdate();
            q = db.getLoc().createQuery("DELETE FROM Customer c");
            q.executeUpdate();

            db.getLoc().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            db.getLoc().getTransaction().rollback();
        }
    }

    private static void createAndShowGUI() {
        SuperMarketParentFrame s = new SuperMarketParentFrame();
        s.setVisible(true);
        s.pack();
    }

    /**
     * @EPA:: Δημιουργεί προϊόντα και καταστήματα καθορίζει τα προϊόντα που
     * εμπορεύεται κάθε κατάστημα και τα αποθηκεύει στη ΒΔ.
     */
    public void createStoresAndProducts() {
        // αρχικοποίηση transaction
        db.getLoc().getTransaction().begin();
        try {
            /* Δημιουργία Προϊόντων */
            Product odokrema = new Product(null, "Aim Οδοντόκρεμα 75 ml", "1100", 10, 4.35f);
            Product makaroniano7 = new Product(null, "Μακαρόνια No7", "1110", 5, 2.35f);
            Product alevri = new Product(null, "Αλεύρι Ολικής", "1120", 4, 8.88f);
            Product ladi = new Product(null, "Λάδι 5lt", "1130", 3, 22.34f);
            Product dimitriaka = new Product(null, "Δημητριακά ολικής", "1140", 2, 4.61f);
            Product xartikouzinas = new Product(null, "Χαρτί κουζίνας", "1150", 50, 4.66f);
            Product makaroniano3 = new Product(null, "Μακαρόνια No3", "1160", 20, 4.71f);
            Product badedas = new Product(null, "Badedas Αφρόλουτρο 750 ml", "1170", 15, 4.76f);
            Product kafesellinikos = new Product(null, "Καφές Ελλ. 250gr", "1180", 12, 4.81f);
            Product kafesfiltrou = new Product(null, "Καφές Φίλτρου 250gr", "1190", 10, 4.86f);
            Product kafesespresso = new Product(null, "Καφές espresso 250gr", "1200", 5, 4.91f);
            Product aporrouxwn = new Product(null, "Απορρυπαντικό Ρούχων", "1210", 16, 24.96f);
            Product kyboimaggi = new Product(null, "Maggi Κύβοι Ζωμό 12τεμ.", "1220", 16, 5.01f);
            Product moustarda = new Product(null, "Μουστάρδα 500 gr", "1230", 17, 5.06f);
            Product ketsap = new Product(null, "Κέτσαπ 500 gr", "1240", 17, 5.11f);
            Product malaktikoroux = new Product(null, "Μαλακτικό Ρούχων 1lt", "1250", 18, 15.16f);
            Product ryzibasmati = new Product(null, "Ρύζι Basmati 500gr", "1260", 18, 5.21f);
            Product tsixles = new Product(null, "Τσίχλες Trdent Μέντα", "1270", 19, 5.26f);
            Product galafresko = new Product(null, "Γάλα φρέσκο 1Ltr", "1280", 20, 5.31f);
            Product sokolata = new Product(null, "Σολολάτες Lakta 12τμ.", "1290", 20, 15.21f);


            /* Δημιουργία Καταστημάτων */
            Store abAlimou = new Store(null, "AB Αλίμου", "Καλαμακίου 120, Άλιμος");
            Store abFalirou = new Store(null, "AB Φαλήρου", "Ποσειδώνος 300, Παλαιό Φάληρο");
            Store abGeraka = new Store(null, "AB Γέρακα", "Λεωφόρος Σπάτων 81, Γέρακας");

            //Παρακάτω φαίνεται ο σωστός τρόπος ώστε να ενημερωθεί και ο πίνακας 
            //Store_Product. O λόγος που δεν δουλεύει το ανάποδο είναι διότι η κλάση Product
            // έχει τα στοιχεία για τον join table και μόνο μία εκ των δύο μπορεί να τα έχει.
            Collection<Store> store = new ArrayList<>(); //αρχικοποίηση
            store.add(abAlimou);
            store.add(abFalirou);
            store.add(abGeraka);

            //Σύνδεση καταστημάτων με προϊόντα
            odokrema.setStoreCollection(store);
            makaroniano7.setStoreCollection(store);
            alevri.setStoreCollection(store);
            ladi.setStoreCollection(store);
            dimitriaka.setStoreCollection(store);
            xartikouzinas.setStoreCollection(store);
            makaroniano3.setStoreCollection(store);
            badedas.setStoreCollection(store);
            kafesellinikos.setStoreCollection(store);
            kafesfiltrou.setStoreCollection(store);
            kafesespresso.setStoreCollection(store);
            aporrouxwn.setStoreCollection(store);
            kyboimaggi.setStoreCollection(store);
            moustarda.setStoreCollection(store);
            ketsap.setStoreCollection(store);
            malaktikoroux.setStoreCollection(store);
            ryzibasmati.setStoreCollection(store);
            tsixles.setStoreCollection(store);
            galafresko.setStoreCollection(store);
            sokolata.setStoreCollection(store);

            //Δημιούργησε μια νέα εγγραφή στη βάση
            db.getLoc().persist(odokrema);
            db.getLoc().persist(makaroniano7);
            db.getLoc().persist(alevri);
            db.getLoc().persist(ladi);
            db.getLoc().persist(dimitriaka);
            db.getLoc().persist(xartikouzinas);
            db.getLoc().persist(makaroniano3);
            db.getLoc().persist(badedas);
            db.getLoc().persist(kafesellinikos);
            db.getLoc().persist(kafesfiltrou);
            db.getLoc().persist(kafesespresso);
            db.getLoc().persist(aporrouxwn);
            db.getLoc().persist(kyboimaggi);
            db.getLoc().persist(moustarda);
            db.getLoc().persist(ketsap);
            db.getLoc().persist(malaktikoroux);
            db.getLoc().persist(ryzibasmati);
            db.getLoc().persist(tsixles);
            db.getLoc().persist(galafresko);
            db.getLoc().persist(sokolata);

            //ενημέρωσε τη βάση
            db.getLoc().merge(odokrema);
            db.getLoc().merge(makaroniano7);
            db.getLoc().merge(alevri);
            db.getLoc().merge(ladi);
            db.getLoc().merge(dimitriaka);
            db.getLoc().merge(xartikouzinas);
            db.getLoc().merge(makaroniano3);
            db.getLoc().merge(badedas);
            db.getLoc().merge(kafesellinikos);
            db.getLoc().merge(kafesfiltrou);
            db.getLoc().merge(kafesespresso);
            db.getLoc().merge(aporrouxwn);
            db.getLoc().merge(kyboimaggi);
            db.getLoc().merge(moustarda);
            db.getLoc().merge(ketsap);
            db.getLoc().merge(malaktikoroux);
            db.getLoc().merge(ryzibasmati);
            db.getLoc().merge(tsixles);
            db.getLoc().merge(galafresko);
            db.getLoc().merge(sokolata);

            /**
             * Κάνοντας commit το transaction θα δημιουργηθούν οι αντίστοιχες
             * εγγραφές Store και Product στη ΒΔ ενώ τα αντίστοιχα αντικείμενα
             * θα γίνουν managed.
             */
            db.getLoc().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            db.getLoc().getTransaction().rollback();
        }
    }

    /**
     * @EPA:: Δημιουργεί κάποιους πελάτες στη ΒΔ για να μπορούν να
     * χρησιμοποιηθούν για τους σκοπούς της εργασίας
     */
    public void createCustomersAndVouchers() {
        String myDateAsString;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //Example: 2012-03-04
        Date date;

        // αρχικοποίηση transaction
        db.getLoc().getTransaction().begin();
        try {
            /* Δημιουργία Πελατών */
            //δημιουργούμε ένα πελάτη
            Customer CustomerA = new Customer(1, "Andreas", "Paradise", "111-111", 0,"aparadis");
            Customer CustomerB = new Customer(2, "Panagis", "Loukatos", "222-222", 0,"ploukato");
            Customer CustomerC = new Customer(3, "Anestis", "Passas", "333-333", 0,  "apassas");
            Customer CustomerD = new Customer(4, "Euh", "Papavasileiou", "444-444",0,"epapavas");

            //δημιουργούμε νέες επιταγές      
            myDateAsString = "2012-05-10";
            formatter = new SimpleDateFormat("yyyy-MM-dd"); //Example: 2012-03-04
            date = formatter.parse(myDateAsString);
            Voucher VoucherA1 = new Voucher(true, date, CustomerA);

            myDateAsString = "2000-12-14";
            formatter = new SimpleDateFormat("yyyy-MM-dd"); //Example: 2012-03-04
            date = formatter.parse(myDateAsString);
            Voucher VoucherA2 = new Voucher(true, date, CustomerA);

            //----------------
            myDateAsString = "2002-02-03";
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(myDateAsString);
            Voucher VoucherB1 = new Voucher(false, date, CustomerB);

            myDateAsString = "2001-12-11";
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(myDateAsString);
            Voucher VoucherB2 = new Voucher(true, date, CustomerB);

            VoucherB1.setCustomer(CustomerB);
            VoucherB2.setCustomer(CustomerB);

            //----------------
            myDateAsString = "2004-04-12";
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(myDateAsString);
            Voucher VoucherC1 = new Voucher(true, date, CustomerC);

            //----------------
            myDateAsString = "2004-04-12";
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(myDateAsString);
            Voucher VoucherD1 = new Voucher(false, date, CustomerD);

           
            //----------------
            myDateAsString = "2008-07-02";
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(myDateAsString);
            Voucher VoucherD2 = new Voucher(false, date, CustomerD);

            //----------------
            myDateAsString = "3013-05-03";
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(myDateAsString);
            Voucher VoucherD3 = new Voucher(false, date, CustomerD);
            
            //Σχετίζουμε τον πελάτη με την πιστωτική κάρτα
            CustomerA.setCreditCardId(2);
            CustomerB.setCreditCardId(3);
            CustomerC.setCreditCardId(4);
            CustomerD.setCreditCardId(5);

            //ορίζουμε διευθύνση στον πελάτη
            CustomerA.setAddress("Ag. Georgiou 89, Athina");
            CustomerD.setAddress("Grigoriou 20, Kallithea");

            //Δημιούργησε μια νέα εγγραφή στη βάση
            db.getLoc().persist(CustomerA);
            db.getLoc().persist(CustomerB);
            db.getLoc().persist(CustomerC);
            db.getLoc().persist(CustomerD);

          //Δημιούργησε μια νέα εγγραφή στη βάση για τις επιταγές
            db.getLoc().persist(VoucherA1);
            db.getLoc().persist(VoucherA2);
            db.getLoc().persist(VoucherB1);
            db.getLoc().persist(VoucherB2);
            db.getLoc().persist(VoucherC1);
            db.getLoc().persist(VoucherD1);
            db.getLoc().persist(VoucherD2);
            db.getLoc().persist(VoucherD3);              
            
            //ενημέρωσε τη βάση
            db.getLoc().merge(CustomerA);
            db.getLoc().merge(CustomerB);
            db.getLoc().merge(CustomerC);
            db.getLoc().merge(CustomerD);
            //ενημέρωσε τη βάση
            db.getLoc().merge(VoucherA1);
            db.getLoc().merge(VoucherA2);
            db.getLoc().merge(VoucherB1);
            db.getLoc().merge(VoucherB2);
            db.getLoc().merge(VoucherC1);
            db.getLoc().merge(VoucherD1);
            db.getLoc().merge(VoucherD2);
            db.getLoc().merge(VoucherD3);  
            

            //εκτέλεση την εντολή
            db.getLoc().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            db.getLoc().getTransaction().rollback();
        }

    }

    public static void main(String[] args) {
        // Δημιουργούμε το SuperMarket
        SuperMarket sm = new SuperMarket();
        sm.CleanDB();
        sm.createStoresAndProducts();
        sm.createCustomersAndVouchers();

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
}
