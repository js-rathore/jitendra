/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view.splash;

/**
 *
 * @author Navneet
 */

import java.io.File;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.Toolkit;
import model.Student_model;
import model.User_model;
import view.Main_form;


//import regular.student_model.User_model;

/**
 * 
 * @author amol
 */
public class SplashScreen implements Runnable {

   JWindow          splashScreen;
   JProgressBar    progressBar;
   Thread              thread;
 ClassLoader cl = this.getClass().getClassLoader();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
 
   Main_form m;

   /**
    * 
    */
   public SplashScreen()
   {

   }
   /**
    * 
    * @param m
    */
   public SplashScreen(Main_form m) {
       this.m=m;
        //ClassLoader cl = this.getClass().getClassLoader();
		//Toolkit toolkit = Toolkit.getDefaultToolkit();

      splashScreen = createSplashScreen(500, 270, new ImageIcon(toolkit.getImage(cl.getResource("image/java.png"))));
     // splashScreen = createSplashScreen(450, 390, new ImageIcon(toolkit.getImage(cl.getResource("image/java.png"))));
      
      //"D:\Documents and Settings\admin\Desktop\splash.png"
      splashScreen.setVisible(true);
      start();
   }

   /**
    * 
    */
   public void start() {
      thread = new Thread(this);
      //java.awt
      thread.setName("ProgressThread");
      thread.start();
   }

   // stop the thread
   /**
    * 
    */
   public void stop() {
      if(thread != null) {
         thread.interrupt();
         thread = null;
      }
   }

   // close the progress of loading
   /**
    * 
    */
   public void close() {
      stop();
      splashScreen.setVisible(false);
      System.out.println(Student_model.AllStudentKeys.size());
       System.out.println(Student_model.AllStudents.size());
          
 // give a 2 sec. break
     // try { Thread.sleep(500); } catch(Exception ex) {}
      //////  Your Application Frame
      Dimension Screen=m.getToolkit().getScreenSize();
    //  m.setLocation(Screen.width/2-497/2,Screen.height/2-336/2);
     m.setIconImage(new ImageIcon(cl.getResource("image/java.png")).getImage());
      m.repaint();
      m.setVisible(true);
      
   }

    @Override
    @SuppressWarnings("static-access")
   public void run() {
      
      progressBar.setString("Application is Loading, please wait...");
      progressBar.setString("Your Work Our Passion \n N. N. Rathi ");
      try{ thread.sleep(1000); } catch(Exception e) {}
      progressBar.setString("Loading all students...");
      

      //creating data dir to store data inside
      File d = new File("./data");
            if(!d.exists())
            {
            d.mkdir();
          }
      //dir to hold default image
      File t= new File("./data/temp");
            if(!t.exists())
            {
                t.mkdir();
          
            }


      progressBar.setValue(progressBar.getValue() + 10);

      //loading of all entries in the arraylist
      Student_model.loadAllEntries("./data/");
     
      User_model.loadAllEntries("./data/");

      progressBar.setString("Loading all stuednts keys....");
      progressBar.setValue(progressBar.getValue() + 10);

      //loading of all key entries in the arraylist
      Student_model.loadAllKeys("./data/");
     
      User_model.loadAllKeys("./data/");
      
    progressBar.setString("Loading...");
      progressBar.setValue(progressBar.getValue() + 10);

      progressBar.setString("Loading....");
      progressBar.setValue(progressBar.getValue() + 10);

      progressBar.setString("Loading.....");
      progressBar.setValue(progressBar.getValue() + 10);

      progressBar.setString("Loading.......");
      progressBar.setValue(progressBar.getValue() + 10);

      progressBar.setString("Loading............");
      progressBar.setValue(progressBar.getValue() + 10);

      progressBar.setString("Loading..................");
      progressBar.setValue(progressBar.getValue() + 10);

      progressBar.setString("Loading Complete... ");
      progressBar.setValue(progressBar.getValue() + 10);
      try{ Thread.sleep(1000); } catch(Exception e) {}
      progressBar.setString("Starting App........... ");
      progressBar.setValue(progressBar.getValue() + 10);
       try{ Thread.sleep(1000); } catch(Exception e) {}
      close();
   }

   private JWindow createSplashScreen(int width, int height, ImageIcon splashIcon) {
      JWindow splashScreen1 = new JWindow();
      splashScreen1.getContentPane().add( new JLabel( splashIcon ),
                                          BorderLayout.CENTER   );
      splashScreen1.getContentPane().add( progressBar = new JProgressBar(),

                                         BorderLayout.SOUTH );

      progressBar.setValue(0);
      progressBar.setStringPainted(true);

      Dimension screenSize = splashScreen1.getToolkit().getScreenSize();
      splashScreen1.setSize(width, height);
      // center the window

    splashScreen1.setLocation(  screenSize.width   /  2  -  width   /  2,
                                 screenSize.height  /  2  -  height  /  2  );
      splashScreen1.setVisible(false);
      return splashScreen1;
   }

  
}
