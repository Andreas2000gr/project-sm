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
     
    private EntityManager loc;
    private EntityManager ext;
    
    public DBmanager(){
        EntityManagerFactory emLoc = Persistence.createEntityManagerFactory("SuperMarket-local-PU");
        EntityManagerFactory emExt = Persistence.createEntityManagerFactory("SuperMarket-external-PU");
       
        loc = emLoc.createEntityManager();  
        ext = emExt.createEntityManager();
    }

    public EntityManager getLoc() {
        return loc;
    }

    public void setLoc(EntityManager loc) {
        this.loc = loc;
    }

    public EntityManager getExt() {
        return ext;
    }

    public void setExt(EntityManager ext) {
        this.ext = ext;
    }
}
