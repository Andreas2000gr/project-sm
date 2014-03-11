/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import LocalDB.*;
import java.util.ArrayList;
import java.util.List;
import supermarket.*;

/**
 *
 * @author Euh
 */
public class Threads {

    private Simulator[] threads;
    private DBmanager db;
    private final String XML_FILE_NAME = "purchases.xml"; // Το όνομα του XML αρχείου
    private static final int NumOfRetries = 3;

    //constructor
    public Threads(DBmanager db, Customer customer, int NumOfThreads) {
        this.db = db;
        this.threads = new Simulator[NumOfThreads];//NumOfThreads:: μας το δίνει ο χρήστης από UI
    }

    // Η παρακάτω μέθοδος εκκινεί τα νήματα
    public void startThreads() throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    //Δημιουργούμε τα νήματα 
    //και αναθέτουμε ένα πελάτη σε κάθε ένα από αυτά
    public void ThreadsAssingRadCustomr() {
        //επιστρέφει μια λίστα με πελάτες με τυχαία σειρά
        ArrayList<Customer> CustomerList = db.FindAllCustomers();
        for(int i=0; i< CustomerList.size(); i++){
            Customer cust = threads[i].RandomFindCustomer();
            if(!CustomerList.contains(cust))
            CustomerList.add(cust);
        }
        
        //για κάθε ένα από τα threads
        int Counter = 1;
        for (int i = 0; i < threads.length; i++) {
            //δημιουργία νέου νήματος
            threads[i] = new Simulator(db);
            //θέτουμε όνομα στο νήμα
            threads[i].setName("Thread name::[" + Counter + "]");
            Counter++;

        }
    }

    //Προσθήκη προϊόντων στο καλάθι του πελάτη
    //Για κάθε νήμα 
    public void BuyProducts(Customer customer) {
        for (int i = 0; i < threads.length; i++) {
            threads[i].PopulateBasket(customer);
        }

    }

}
