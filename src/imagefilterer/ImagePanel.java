
package imagefilterer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
       this.image = image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) { 
            double aspectRatioOfPanel = this.getWidth() * 1.0 / this.getHeight();
            double aspectRatioOfImage = image.getWidth() * 1.0 / image.getHeight();
            
            //System.out.println("Normal AR of image: " + aspectRatioOfImage);
            
            int x;
            int y;
            int width;
            int height;
            
            if (aspectRatioOfImage > aspectRatioOfPanel) {
                //System.out.println("Image to wide");
                height = (int) (image.getHeight() / (image.getWidth() * 1.0 / this.getWidth() ) );
                y = (this.getHeight() / 2) - (height / 2);
                x = 0;
                width = this.getWidth();
            } else {
                //System.out.println("Image to thin");
                width = (int) (image.getWidth() / (image.getHeight() * 1.0 / this.getHeight() ) );
                x = (this.getWidth() / 2) - (width / 2);
                y = 0;
                height = this.getHeight();
            }
            
            //System.out.println("Resulting AR of image: " + (width * 1.0 / height));
            
            g.drawImage(image, x, y, width, height, null);
            //g.drawImage(image, 0, 0, null);
            
        }    
    }

}