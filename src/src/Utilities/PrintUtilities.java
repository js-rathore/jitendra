package Utilities;

import java.awt.*;
import javax.swing.*;
import java.awt.print.*;

/**
 *
 * @author Navneet
 */
public class PrintUtilities implements Printable {

 
  private Component componentToBePrinted;

  /**
   * 
   * @param c
   */
  public static void printComponent(Component c) {
    new PrintUtilities(c).print();
  }

  /**
   * 
   * @param componentToBePrinted
   */
  public PrintUtilities(Component componentToBePrinted) {
    this.componentToBePrinted = componentToBePrinted;
  }

  /**
   * 
   */
  public void print() {
    PrinterJob printJob = PrinterJob.getPrinterJob();
    printJob.setPrintable(this);
    if (printJob.printDialog())
      try {
        printJob.print();
      } catch(PrinterException pe) {
        System.err.println("Error printing: " + pe);
        JOptionPane.showMessageDialog(null, "Error printing:"+pe, "Printing Error", JOptionPane.ERROR_MESSAGE);            

      }
  }

/*  public int print(Graphics g, PageFormat pageFormat, int pageIndex)
  {
    if (pageIndex > 0) {
      return(NO_SUCH_PAGE);
    } 
    else {
      Graphics2D g2d = (Graphics2D)g;
      g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
      disableDoubleBuffering(componentToBePrinted);
      componentToBePrinted.paint(g2d);
      enableDoubleBuffering(componentToBePrinted);
      return(PAGE_EXISTS);
    }
  }
*/
  /**
   * 
   * @param c
   */
  public static void disableDoubleBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(false);
  }

  /**
   * 
   * @param c
   */
  public static void enableDoubleBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(true);
  }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
       // throw new UnsupportedOperationException("Not supported yet.");
            if (pageIndex > 0) {
      return(NO_SUCH_PAGE);
    } 
    else {
      Graphics2D g2d = (Graphics2D)g;
      g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
      disableDoubleBuffering(componentToBePrinted);
      componentToBePrinted.paint(g2d);
      enableDoubleBuffering(componentToBePrinted);
      return(PAGE_EXISTS);
    }
    }
}
