/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Navneet
 */
public class openodbc {

    /**
     * @param args the command line arguments
     */
    static void callodbc()
    {
         try {
            // TODO code application logic here
            Runtime rt = Runtime.getRuntime();
            rt.exec("odbcad32.exe");
        } catch (IOException ex) {
            Logger.getLogger(openodbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
