/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

/**
 *
 * @author NAND
 */
import java.util.*;
import java.util.zip.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Navneet
 */
public class ZipRetrieve{
    /**
     * 
     * @param args
     */
  //  public static void main(String[] args){
		//  JOptionPane.showMessageDialog(null,"Data has been Successfully Uncompressed to "+"data", "Confirmation Message", JOptionPane.INFORMATION_MESSAGE);

     //   ZipRetrieveElements zr = new ZipRetrieveElements("C:\\Users\\Navneet\\Desktop\\vj.zip","C:\\Users\\Navneet\\");
       //   JOptionPane.showMessageDialog(null,"Data has been Successfully Uncompressed to"+zr, "Confirmation Message", JOptionPane.INFORMATION_MESSAGE);

	//}

        /**
         * 
     * @param srcfile
     * @param dstfolder  
     */
        public  ZipRetrieve(String srcfile ,String dstfolder ) {
		OutputStream out = null;
		
		try {
		

			ZipInputStream in = new ZipInputStream(new FileInputStream(srcfile));
			ZipFile zf = new ZipFile(srcfile);
			int a = 0;
			for(Enumeration em = zf.entries(); em.hasMoreElements();){
				String targetfile =dstfolder+ em.nextElement().toString();
				ZipEntry ze = in.getNextEntry();
				out = new FileOutputStream(targetfile);
				byte[] buf = new byte[4096];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				a = a + 1;
			}
			if(a > 0)
            System.out.println("Back up Files Restored.");
         //  JOptionPane.showMessageDialog(null,"Data has been Successfully Uncompressed to "+"data", "Confirmation Message", JOptionPane.INFORMATION_MESSAGE);

			out.close();
			in.close();
	    	} catch (IOException err) {
			System.out.println("Error: Operation failed!"+ err);
            JOptionPane.showMessageDialog(null,"Error has been Occured "+"\n"+err, "Message", JOptionPane.ERROR_MESSAGE);

			//System.exit(0);
		}
	}
}

