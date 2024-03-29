/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import LocalDB.Customer;
import LocalDB.Store;
import externalDB.CreditCardAuthority;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Euh
 */
public class DBmanager {

    private EntityManager localDB;
    private EntityManager externalDB;

    public DBmanager() {
        EntityManagerFactory emLoc = Persistence.createEntityManagerFactory("SuperMarket-local-PU");
        EntityManagerFactory emExt = Persistence.createEntityManagerFactory("SuperMarket-external-PU");

        localDB = emLoc.createEntityManager();
        externalDB = emExt.createEntityManager();
    }

    public EntityManager getLoc() {
        return localDB;
    }

    public void setLoc(EntityManager localDB) {
        this.localDB = localDB;
    }

    public EntityManager getExt() {
        return externalDB;
    }

    public void setExt(EntityManager externalDB) {
        this.externalDB = externalDB;
    }

    // METHOD:: UPDATE CUSTOMER'S PASSWORD
    public void UPDATE_CUSTOMER(Customer customer) {
        try {
            // αρχικοποίηση transaction
            if (!getLoc().getTransaction().isActive()) {
                getLoc().getTransaction().begin();
            }
            getLoc().merge(customer);//update customer
            getLoc().getTransaction().commit();
            //  return true;
        } catch (Exception e) {
            e.printStackTrace();
            getLoc().getTransaction().rollback();
            //  return false;
        }
    }
    //METHOD: DELETE CUSTOMER
    public void DELETE_CUSTOMER(Customer customer) {
        try {
            // αρχικοποίηση transaction
            if (!getLoc().getTransaction().isActive()) {
                getLoc().getTransaction().begin();
            }
            getLoc().merge(customer);
            getLoc().remove(customer);//update customer
            getLoc().getTransaction().commit();
            //  return true;
        } catch (Exception e) {
            e.printStackTrace();
            getLoc().getTransaction().rollback();
            //  return false;
        }
    }    
    // METHOD:: UPDATE CUSTOMER'S PASSWORD
    public void UPDATE_CREDIT_CARD(CreditCardAuthority CreditCard) {
        try {
            // αρχικοποίηση transaction
            if (!getExt().getTransaction().isActive()) {
                getExt().getTransaction().begin();
            }
            getExt().merge(CreditCard);//update customer
            getExt().getTransaction().commit();
            //  return true;
        } catch (Exception e) {
            e.printStackTrace();
            getExt().getTransaction().rollback();
            //  return false;
        }
    }    
    //METHOD: DELETE CREDIT CARD
    public void DELETE_CREDIT_CARD(CreditCardAuthority CreditCard) {
        try {
            // αρχικοποίηση transaction
            if (!getExt().getTransaction().isActive()) {
                getExt().getTransaction().begin();
            }
            getExt().remove(CreditCard);//update customer
            getExt().getTransaction().commit();
            //  return true;
        } catch (Exception e) {
            e.printStackTrace();
            getExt().getTransaction().rollback();
            //  return false;
        }
    }
    
    // Βρίσκουμε όλους του πελάτες
    public ArrayList<Customer> FindAllCustomers() { 
        Query FindAllCustomers;
         //sql query για την ανάκτηση όλων των πελατών
        FindAllCustomers = getLoc().createNamedQuery("Customer.findAll",Customer.class);
        return new ArrayList<Customer>(FindAllCustomers.getResultList());
    }    
    
// Η παρακάτω μέθοδος επιστρέφει όλα τα καταστήματα
    public ArrayList<Store> FindAllStores() { 
        Query FindAllStores;
        // sql query για την ανάκτηση όλων των καταστημάτων
        FindAllStores = getLoc().createNamedQuery("Store.findAll",Store.class);
        return new ArrayList<Store>(FindAllStores.getResultList());
    }
}
