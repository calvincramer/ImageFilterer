
package imagefilterer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.Random;


public class RecursiveSquareFilter 
    extends ImageFilter {

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {
        
        this.image = img;
        this.raster = data;
        
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.OPAQUE);
        Graphics2D g2 = filteredImage.createGraphics();
        
        for(int x = 0; x < filteredImage.getWidth(); x += startSquareSize) {
            for (int y = 0; y < filteredImage.getWidth(); y += startSquareSize) {
                Color c = FilterMethods.getAverageColorInSelection(x, y, startSquareSize, startSquareSize, image, raster);
                g2.setColor(c);
                g2.fillRect(x, y, startSquareSize, startSquareSize);
                
                if(r.nextBoolean()) {
                    this.recursiveCutSelectionInFour(x, y, startSquareSize, g2);
                }
                
                
            }
        }
        
        return filteredImage;
    }

     private void recursiveCutSelectionInFour(int x, int y, int startSquareSize, Graphics2D g) {
        int halfSize = startSquareSize / 2;
        
        Rectangle topLeft = new Rectangle(x, y, halfSize, halfSize);
        Rectangle topRight = new Rectangle(x + halfSize, y, halfSize, halfSize);
        Rectangle bottomLeft = new Rectangle(x, y + halfSize, halfSize, halfSize);
        Rectangle bottomRight = new Rectangle(x + halfSize, y + halfSize, halfSize, halfSize);
        
        Color c = FilterMethods.getAverageColorInSelection(topLeft, image, raster);
        g.setColor(c);
        g.fillRect(topLeft.x, topLeft.y, topLeft.width, topLeft.height);
        if(r.nextBoolean() && topLeft.width / 2 >= smallestSquareSize) {
            recursiveCutSelectionInFour(topLeft.x, topLeft.y, topLeft.width, g);
        }
        
        c = FilterMethods.getAverageColorInSelection(topRight, image, raster);
        g.setColor(c);
        g.fillRect(topRight.x, topRight.y, topRight.width, topRight.height);
        if(r.nextBoolean() && topRight.width / 2 >= smallestSquareSize) {
            recursiveCutSelectionInFour(topRight.x, topRight.y, topRight.width, g);
        }
        
        c = FilterMethods.getAverageColorInSelection(bottomLeft, image, raster);
        g.setColor(c);
        g.fillRect(bottomLeft.x, bottomLeft.y, bottomLeft.width, bottomLeft.height);
        if(r.nextBoolean() && bottomLeft.width / 2 >= smallestSquareSize) {
            recursiveCutSelectionInFour(bottomLeft.x, bottomLeft.y, bottomLeft.width, g);
        }
        
        c = FilterMethods.getAverageColorInSelection(bottomRight, image, raster);
        g.setColor(c);
        g.fillRect(bottomRight.x, bottomRight.y, bottomRight.width, bottomRight.height);
        if(r.nextBoolean() && bottomRight.width / 2 >= smallestSquareSize) {
            recursiveCutSelectionInFour(bottomRight.x, bottomRight.y, bottomRight.width, g);
        }
        
    }
     
    @Override
    public int getNumOptions() {
        return 2;
    }
     
    @Override
    public String getFilterName() {
        return "Recursive Square Filter";
    }
    
    @Override
    public String toString() {
        return "Recursive Square Filter";
    }
    
    @Override
    public Option[] getOptions() {
        return options;
    }
    
    @Override
    public void setParameters(int[] values) {
        if (values.length != options.length) {
            System.out.println("wrong options in " + getFilterName());
            return;
        }
        smallestSquareSize = (int) Math.pow(2, values[0]);
        startSquareSize = (int) Math.pow(2, values[1]);
    }
    
    private static final Option[] options = new Option[] { new Option("Smallest", 1, 6, 1, 3, 2),
                                                           new Option("Largest", 1, 12, 1, 3, 5) };
    
    ///////////////////////////////////////
    private BufferedImage image;
    private Raster raster;
    private int smallestSquareSize = 16;
    private int startSquareSize = 64;
    private static final Random r = new Random();
}
