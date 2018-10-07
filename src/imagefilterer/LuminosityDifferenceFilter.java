/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefilterer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 *
 * @author Calvin
 */
public class LuminosityDifferenceFilter
    extends ImageFilter {

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {
        
        this.image = img;
        this.raster = data;
        
        BufferedImage filteredImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.OPAQUE);
        Graphics2D g2 = filteredImage.createGraphics();
        
        int width = filteredImage.getWidth();
        int height = filteredImage.getHeight();
        
        recursiveLuminosityDifference(new Rectangle(0,0,width,height), g2);
        
        return filteredImage;
    }
    
    private void recursiveLuminosityDifference(Rectangle r, Graphics2D g2) {
        if (r.width < this.smallestSizeForLuminosity || r.height < this.smallestSizeForLuminosity || r.width == 1 || r.height == 1) {
            Color c = FilterMethods.getAverageColorInSelection(r, image, raster);
            g2.setColor(c);
            g2.fillRect(r.x, r.y, r.width, r.height);
            return;
        }
        assert r.x >= 0;
        assert r.y >= 0;

        if (this.getLuminosityDifference(r) > luminosityDifferenceThreshold) {
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

            this.recursiveLuminosityDifference(topLeft, g2);
            this.recursiveLuminosityDifference(topRight, g2);
            this.recursiveLuminosityDifference(bottomLeft, g2);
            this.recursiveLuminosityDifference(bottomRight, g2);

        } else {
            //draw the hopefully plain rectangle with average color
            Color c = FilterMethods.getAverageColorInSelection(r, image, raster);
            g2.setColor(c);
            g2.fillRect(r.x, r.y, r.width, r.height);
        }

    }
    
    private double getLuminosityDifference(Rectangle r) {
        double highestLuminosity = this.getLuminosityOfPixel(0, 0);
        double lowestLuminosity = this.getLuminosityOfPixel(0, 0);
        
        for (int x = r.x; x < r.x + r.width; x++) {
            for (int y = r.y; y < r.y + r.height; y++) {
                double lum = this.getLuminosityOfPixel(x, y);
                if (lum > highestLuminosity) highestLuminosity = lum;
                if (lum < lowestLuminosity) lowestLuminosity = lum;
            }
        }
        
        return highestLuminosity - lowestLuminosity;
    }
    
    private double getLuminosityDifference(int x, int y, int width, int height) {
        return this.getLuminosityDifference(new Rectangle(x,y,width,height));
    }
    
    private double getLuminosityOfPixel(int x, int y) {
        int[] pixel = raster.getPixel(x, y, new int[3]);
        int luminosity = pixel[0] + pixel[1] + pixel[2];
        return luminosity / 765.0;
    }

    @Override
    public String getFilterName() {
        return "Luminosity Difference Filter";
    }
    
    @Override
    public String toString() {
        return "Luminosity Difference Filter";
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
        luminosityDifferenceThreshold = values[0] * 1.0 / options[0].getMax();
        smallestSizeForLuminosity = values[1];
    }
    
    
    private static final Option[] options = new Option[] { new Option("Threshold", 0.25),
                                                           new Option("Depth", 1, 10, 1, 5, 1) };
    
    private BufferedImage image;
    private Raster raster;
    private double luminosityDifferenceThreshold = 0.25;
    private int smallestSizeForLuminosity = 1;
}
