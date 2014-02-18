/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import LocalDB.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    public void CUSTOMER_UPDATE_PASSWORD(Customer customer) {
        try {
            // αρχικοποίηση transaction
            if (!getLoc().getTransaction().isActive()) {
                getLoc().getTransaction().begin();
            }

            System.out.println(2);
            getLoc().persist(customer);

            System.out.println(3);
            if (getLoc().getTransaction().isActive()) {
                System.out.println("IS ACTIVE");
            } else {
                System.out.println("NO ACTIVE");
            }

            getLoc().getTransaction().commit();
            //  return true;
        } catch (Exception e) {
            e.printStackTrace();
            getLoc().getTransaction().rollback();
            //  return false;
        }
    }

}
