/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefilterer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RescaleOp;
import java.util.Random;

/**
 *
 * @author Calvin
 */
public class StiplingFilter 
    extends ImageFilter {

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {
        
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.numPoints = (int) ((width * height) * pointPercentage);
        
        /*
        //increase contrast
        RescaleOp rescaleOp = new RescaleOp(1.2f, 15, null);
        BufferedImage contrastedImage = null;
        rescaleOp.filter(img, img);
        //ImageFilterer.createPicture(img, "Contrasted image");
        */
        
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null) * MULTIPLIER, img.getHeight(null) * MULTIPLIER, BufferedImage.OPAQUE);
        Graphics2D g2 = filteredImage.createGraphics();
        
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, filteredImage.getWidth(), filteredImage.getHeight());
        g2.setColor(Color.BLACK);
        
        for (int i = 0; i < numPoints; i++) {
            int x = (int) FilterMethods.randomNumber(filteredImage.getWidth(), 0.01);
            int y = (int) FilterMethods.randomNumber(filteredImage.getHeight(), 0.01);
            double lum = getGreyScaleAsPercent(x / MULTIPLIER, y / MULTIPLIER, data);
            
            if (lum < 0) continue;
            
            if (rand.nextDouble() >= lum) {
                g2.fillOval(x, y, pointDiameter, pointDiameter);
            }
        }
        
        return filteredImage;
        
    }

    private double getGreyScaleAsPercent(int x, int y, Raster data) { //average method
        
        if (x < 0 || y < 0 || x >= width || y >= height) return -1;
        int[] pixel;
        double average = 0.0;
        try {
            pixel = data.getPixel(x, y, new int[3]); 
            average = ( pixel[0] + pixel[1] + pixel[2] ) / 3.0;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(x + " " + y);
        }

        average = average / 255.0;
        
        //dynamic changes
        if (average <= 0.125)
            return 0;
        if (average >= 0.875) 
            return 1;
        
        if (average < 0.25) 
            average -= 0.25 - average;
        if (average > 0.75)
            average += average - 0.75;
        
        return average;
    }
    
    @Override
    public String getFilterName() {
        return "Stipling Filter";
    }
    
    @Override
    public String toString() {
        return "Stipling Filter";
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
        pointDiameter = values[0];
        pointPercentage = values[1] * 1.0 / options[1].getMax();
    }
    
    private static final Option[] options = new Option[] { new Option("Point Diameter", 1, 40, 5, 10, 12),
                                                           new Option("Num Points", 0.1) };
    
    private int width;
    private int height;
    private int numPoints;
    
    private double pointPercentage;

    private int pointDiameter = 12;
    private static final Random rand = new Random();
    private static final int MULTIPLIER = 4;
    
}
