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

    private final String adminUn = "admin";
    private final String adminPw = "admin";
    
    public String getUN(){
        return adminUn;
    }
    
    public String getPW(){
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


