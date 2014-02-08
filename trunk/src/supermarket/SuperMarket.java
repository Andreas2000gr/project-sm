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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;




public class SuperMarket {

    /**
     * @param args the command line arguments
     */

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
    
}


