package imagefilterer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;


public class ColorDifferenceFilter 
    extends ImageFilter {

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {
        
        this.image = img;
        this.raster = data;
        
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.OPAQUE);
        Graphics2D g2 = filteredImage.createGraphics();
        
        int width = filteredImage.getWidth();
        int height = filteredImage.getHeight();
        
        this.recursiveColorDifference(new Rectangle(0,0,width,height), g2);
        
        return filteredImage;
    }
    
    private void recursiveColorDifference(Rectangle r, Graphics2D g2) {
        if (r.width < smallestSize || r.height < smallestSize || r.width == 1 || r.height == 1) {
            Color c = FilterMethods.getAverageColorInSelection(r, image, raster);
            g2.setColor(c);
            g2.fillRect(r.x, r.y, r.width, r.height);
            return;
        }
        assert r.x >= 0;
        assert r.y >= 0;

        if (this.getAverageColorDifference(r) > COLOR_DIFFERENCE_THRESHOLD) {
                //split it up
                int wid = r.width / 2;
                int hei = r.height / 2;
                Rectangle topLeft = new Rectangle(r.x, r.y, wid, hei);
                Rectangle topRight = new Rectangle(r.x + wid, r.y, wid, hei);
                Rectangle bottomLeft = new Rectangle(r.x, r.y + hei, wid, hei);
                Rectangle bottomRight = new Rectangle(r.x + wid, r.y + hei, wid, hei);

                if (r.width % 2 == 1) {
                    topRight.setSize(topRight.width + 1, topRight.height);
                    bottomRight.setSize(bottomRight.width + 1, bottomRight.height);
                }
                if (r.height % 2 == 1) {
                    bottomLeft.setSize(bottomLeft.width, bottomLeft.height + 1);
                    bottomRight.setSize(bottomRight.width, bottomRight.height + 1);
                }

                this.recursiveColorDifference(topLeft, g2);
                this.recursiveColorDifference(topRight, g2);
                this.recursiveColorDifference(bottomLeft, g2);
                this.recursiveColorDifference(bottomRight, g2);

        } else {
            //draw the hopefully plain rectangle with average color
            Color c = FilterMethods.getAverageColorInSelection(r, image, raster);
            g2.setColor(c);
            g2.fillRect(r.x, r.y, r.width, r.height);
        }
    }

    private double getAverageColorDifference(Rectangle r) {
        double totalPercent = 0.0;
        Color averageColor = FilterMethods.getAverageColorInSelection(r, image, raster);
        for (int y = r.y; y < r.y + r.height; y++) {
            for (int x = r.x; x < r.x + r.width; x++) {
                totalPercent += FilterMethods.colorDifference(FilterMethods.getColorAtPoint(x, y, raster), averageColor);
            }
        }
        return totalPercent / (r.width * r.height);
    }
    
    @Override
    public String getFilterName() {
        return "Color Difference Filter - " + COLOR_DIFFERENCE_THRESHOLD;
    }
    
    @Override
    public String toString() {
        return "Color Difference Filter";
    }
    
    @Override
    public int getNumOptions() {
        return 2;
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
        COLOR_DIFFERENCE_THRESHOLD = values[0] * 1.0 / options[0].getMax();
        smallestSize = values[1];
    }
    
    private static final Option[] options = new Option[] { new Option("Threshold", 0.25),
                                                           new Option("Depth", 1, 10, 1, 5, 1) };
    
    private BufferedImage image;
    private Raster raster;
    private int smallestSize = 1;
    private double COLOR_DIFFERENCE_THRESHOLD = 0.05; //percent
    
}
