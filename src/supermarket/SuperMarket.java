/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import LocalDB.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Loukatos
 */

import javax.persistence.Query;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import supermarket.DBmanager;

public class SuperMarket {

    private DBmanager db = new DBmanager();

    /**
     * @param args the command line arguments
     */
    public SuperMarket() {
        this.db = db;
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
            q = db.getLoc().createQuery("DELETE FROM Customer c");
            q.executeUpdate();
            q = db.getLoc().createQuery("DELETE FROM Voucher v");
            q.executeUpdate();

            db.getLoc().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            db.getLoc().getTransaction().rollback();
        }
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
            Product odokrema = new Product("Aim Οδοντόκρεμα 75 ml", "1100", 10, 4.35f);
            Product makaroniano7 = new Product("Μακαρόνια No7", "1110", 5, 2.35f);
            Product alevri = new Product("Αλεύρι Ολικής", "1120", 4, 8.88f);
            Product ladi = new Product("Λάδι 5lt", "1130", 3, 22.34f);
            Product dimitriaka = new Product("Δημητριακά ολικής", "1140", 2, 4.61f);
            Product xartikouzinas = new Product("Χαρτί κουζίνας", "1150", 50, 4.66f);
            Product makaroniano3 = new Product("Μακαρόνια No3", "1160", 20, 4.71f);
            Product badedas = new Product("Badedas Αφρόλουτρο 750 ml", "1170", 15, 4.76f);
            Product kafesellinikos = new Product("Καφές Ελλ. 250gr", "1180", 12, 4.81f);
            Product kafesfiltrou = new Product("Καφές Φίλτρου 250gr", "1190", 10, 4.86f);
            Product kafesespresso = new Product("Καφές espresso 250gr", "1200", 5, 4.91f);
            Product aporrouxwn = new Product("Απορρυπαντικό Ρούχων", "1210", 16, 24.96f);
            Product kyboimaggi = new Product("Maggi Κύβοι Ζωμό 12τεμ.", "1220", 16, 5.01f);
            Product moustarda = new Product("Μουστάρδα 500 gr", "1230", 17, 5.06f);
            Product ketsap = new Product("Κέτσαπ 500 gr", "1240", 17, 5.11f);
            Product malaktikoroux = new Product("Μαλακτικό Ρούχων 1lt", "1250", 18, 15.16f);
            Product ryzibasmati = new Product("Ρύζι Basmati 500gr", "1260", 18, 5.21f);
            Product tsixles = new Product("Τσίχλες Trdent Μέντα", "1270", 19, 5.26f);
            Product galafresko = new Product("Γάλα φρέσκο 1Ltr", "1280", 20, 5.31f);
            Product sokolata = new Product("Σολολάτες Lakta 12τμ.", "1290", 20, 15.21f);


            /* Δημιουργία Καταστημάτων */
            Store abAlimou = new Store("AB Αλίμου", "Καλαμακίου 120, Άλιμος");
            Store abFalirou = new Store("AB Φαλήρου", "Ποσειδώνος 300, Παλαιό Φάληρο");
            Store abGeraka = new Store("AB Γέρακα", "Λεωφόρος Σπάτων 81, Γέρακας");

            /**
             * Οριζούμε τα προϊόντα που εμπορεύεται ένα κατάστημα,
             */
            List<Product> AlimouList = new ArrayList<>();
            AlimouList.add(odokrema);
            AlimouList.add(makaroniano7);
            AlimouList.add(alevri);
            AlimouList.add(ladi);
            AlimouList.add(dimitriaka);
            AlimouList.add(xartikouzinas);
            AlimouList.add(makaroniano3);
            AlimouList.add(badedas);
            AlimouList.add(kafesellinikos);
            AlimouList.add(kafesfiltrou);
            AlimouList.add(kafesespresso);
            AlimouList.add(aporrouxwn);
            AlimouList.add(kyboimaggi);
            AlimouList.add(moustarda);
            AlimouList.add(ketsap);
            AlimouList.add(malaktikoroux);
            AlimouList.add(ryzibasmati);
            AlimouList.add(tsixles);
            AlimouList.add(galafresko);
            AlimouList.add(sokolata);

            abAlimou.setProductList(AlimouList);
            db.getLoc().persist(abAlimou);

            // κάνουμε το ίδιο και για το άλλο κατάστημα
            List<Product> FalirouList = new ArrayList<>();
            FalirouList.add(ladi);
            FalirouList.add(dimitriaka);
            FalirouList.add(xartikouzinas);
            FalirouList.add(makaroniano3);
            FalirouList.add(badedas);
            FalirouList.add(kafesellinikos);
            FalirouList.add(kafesfiltrou);
            FalirouList.add(kafesespresso);
            FalirouList.add(aporrouxwn);
            FalirouList.add(kyboimaggi);
            FalirouList.add(moustarda);
            FalirouList.add(ketsap);
            FalirouList.add(malaktikoroux);

            abFalirou.setProductList(FalirouList);
            db.getLoc().persist(abFalirou);

            // κάνουμε το ίδιο και για το άλλο κατάστημα
            List<Product> GerakaList = new ArrayList<>();
            GerakaList.add(odokrema);
            GerakaList.add(makaroniano7);
            GerakaList.add(alevri);
            GerakaList.add(ketsap);
            GerakaList.add(malaktikoroux);
            GerakaList.add(ryzibasmati);
            GerakaList.add(tsixles);
            GerakaList.add(galafresko);
            GerakaList.add(sokolata);
            GerakaList.add(ladi);
            GerakaList.add(dimitriaka);
            GerakaList.add(xartikouzinas);

            abGeraka.setProductList(GerakaList);
            db.getLoc().persist(abGeraka);
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
}
