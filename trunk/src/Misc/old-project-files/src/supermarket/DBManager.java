
package supermarket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

/**
 *
 * @author Euh
 */
public class DBManager {

    private EntityManagerFactory emf;
    private EntityManager em;

    // Constructor
    public DBManager() {
        try {
            // EntityManagerFactory το οποίο συνδέεται με Persistence Unit της ΒΔ
            emf = Persistence.createEntityManagerFactory("SuperMarketPU");
            em = emf.createEntityManager();// Δημιουργία ενός EntityManager
            em.setFlushMode(FlushModeType.AUTO);//the flush mode setting for the persistence context is AUTO (the default) 
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //get entity manager
    public EntityManager getEm() {
        return em;
    }

    // close DB connection
    public void closeConnection() {

        try {
            em.close();// Καταστροφή του EntityManager
            emf.close();// Καταστροφή του EntityManagerFactory
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
