package ImagePanel;

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;


//panel used to draw image on

/**
 *
 * @author Navneet
 */
public class ImagePanel extends JPanel
{
	//path of image
	private String path;

	//image object
	private Image img,tempImage;


        /**
         * 
         * @param path
         * @param x 
         * @param y
         * @throws IOException
         */
        public ImagePanel(String path, int x, int y)	 throws IOException
	{
		//save path
		this.path = path;

		//load image
		tempImage= ImageIO.read(new File(path));

        System.out.println(tempImage.toString());

        img=tempImage.getScaledInstance(x,y, Image.SCALE_AREA_AVERAGING);
    }
        /**
         * 
         * @param i
         */
        public ImagePanel(Image i)
    {
        img=i;
    }
	//override paint method of panel
    @Override
	public void paint(Graphics g)
	{		//draw the image
		if( img != null)
			g.drawImage(img,0,0, this);
	}

    /**
     * 
     * @return
     */
    public Image getImage()
    {
        return img;
    }

    /**
     * 
     */
    public void setImage()
    {
        img=null;
    }
}