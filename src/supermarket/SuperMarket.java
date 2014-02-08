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
import java.util.*;
import javax.persistence.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;




public class SuperMarket {

    /**
     * @param args the command line arguments
     */

    private static String adminUn;
    private static String adminPw;

    public SuperMarket() {
        this.adminPw = "admin";
        this.adminUn = "admin";
    }

    public static String getAdminUn() {
        return adminUn;
    }

    public static String getAdminPw() {
        return adminPw;
    }
    
    public static void main(String[] args) {
		// Δημιουργούμε το SuperMarket
        SuperMarket sm = new SuperMarket();     
        SuperMarketParentFrame s = new SuperMarketParentFrame();
        s.setVisible(true);
        s.pack();
    }
    
}


