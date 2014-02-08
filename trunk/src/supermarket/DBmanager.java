/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package supermarket;

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
    
    public DBmanager(){
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
}
