/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Navneet
 */
public class settheme extends JMenu{
    private static final long serialVersionUID = 1L;

    /**
     * @param frame 
     * @return 
     */
    
     public  static JMenu look(final JFrame frame)
        {
     JMenu menu = new JMenu("THEMES");
             UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < lookAndFeelInfos.length; i++) {
            final UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];
            JMenuItem item = new JMenuItem(lookAndFeelInfo.getName());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                        SwingUtilities.updateComponentTreeUI(frame);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(settheme.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(settheme.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(settheme.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(settheme.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
                }
            });
            menu.add(item);
        }
  // this.add(menu);
   return menu;
}
     /**
      * 
      * @param args
      */
   

}
