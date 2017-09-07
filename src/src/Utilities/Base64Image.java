/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import ImagePanel.ImagePanel;


/**
 *
 * @author Navneet
 */
public class Base64Image
{
    private byte [] getImgBytes(Image image)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        try {
//            ImageIO.write(getBufferedImage(image), "JPG", baos);
            ImageIO.write(getBufferedImage(image), "JPEG", baos);

        baos.flush();
        }
        catch (IOException ex) {
            System.err.println("error in creating image......N Rathi");
            //handle it here.... not implemented yet...
        }

        return baos.toByteArray();
    }

    private BufferedImage getBufferedImage(Image image)
    {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
       bi.createGraphics().drawImage( image, 0, 0, null /* observer */ );

        return bi;
    }

    /**
     * 
     * @param i
     * @return
     */
    public  String codeBase64(Image i)
    {
           String s = new sun.misc.BASE64Encoder().encode(getImgBytes(i));
           return s;
    }

    /**
     * 
     * @param sb
     * @param x 
     * @param y 
     * @return
     */
    public  ImagePanel decodeBase64(String sb ,int x,int y)
    {
        //Image IMGdecode=null;
        ImagePanel p=null;
        try
        {
            buffer_decode = new sun.misc.BASE64Decoder().decodeBuffer(sb);

            System.out.println("Image Byte Array length:"+buffer_decode.length);
//           Image IMGdecode = Toolkit.getDefaultToolkit().createImage(buffer_decode);

            BufferedImage image = ImageIO.read ( new ByteArrayInputStream (buffer_decode ) );

            ImageIO.write( image, "jpeg" /* "png" "jpeg" ... format desired */,
               new File ( "./data/temp/snap.jpeg" ) /* target */ );

           p=new ImagePanel("./data/temp/snap.jpeg",x,y);
//           p.setSize(imgP2.getSize());
//           imgP2.add(p);
//           repaint();
           //return p;
        }
        catch (IOException e)
        {
           // return p;
            System.out.println(e.getMessage());
        }
        return p;
    }


    private byte[] buffer_decode;



}
