/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import LocalDB.*;
import supermarket.*;

/**
 *
 * @author Euh
 */
public class Threads{

    private Simulator[] threads;

    //constructor
    public Threads(DBmanager db, Customer customer, Simulator sim) {

    }

    // Η παρακάτω μέθοδος εκκινεί τα νήματα
    public void startThreads() throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i].wait();//start???
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
