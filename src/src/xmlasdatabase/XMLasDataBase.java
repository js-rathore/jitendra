/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlasdatabase;

import java.awt.Dimension;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.*;
import view.splash.SplashScreen;

/**
 *
 * @author Navneet
 */
public class XMLasDataBase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
               UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(XMLasDataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(XMLasDataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(XMLasDataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(XMLasDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            File f=new File("./data/");

        if(f.exists())
        {
     //  new License().setVisible(true);
        Login l=new Login();
        Dimension screenSize=l.getToolkit().getScreenSize();
        l.setLocation(  screenSize.width   /  2  -  l.getWidth()   /  2,
                                 screenSize.height  /  2  -  l.getHeight()  /  2  );

        l.setVisible(true);
        }
        else
        {
             Main_form m=new Main_form();
             
               Dimension screenSize=m.getToolkit().getScreenSize();
        m.setLocation(  screenSize.width   /  2  -  m.getWidth()   /  2,
                                 screenSize.height  /  2  -  m.getHeight()  /  2  );
           //  m.setVisible(true);
                 new SplashScreen(m);
        }
        
    }

    
 
}
