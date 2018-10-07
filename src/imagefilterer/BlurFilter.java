package imagefilterer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class BlurFilter
    extends ImageFilter {

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.OPAQUE);
        
        for (int y = 0; y < img.getHeight(); y++) {
            //if (y % 100 == 0) { System.out.println("0    " + y); }
            
            for (int x = 0; x < img.getWidth(); x++) {

                //gets the average color
                Color averageColor = FilterMethods.getAverageColorInCircle(x, y, radius, img, data);
                //color pixel
                filteredImage.setRGB(x, y, averageColor.getRGB());
                
        }}
        
        return filteredImage;
    }
    
    @Override
    public String getFilterName() {
        return "Blur Filter";
    }
    
    @Override
    public String toString() {
        return "Blur Filter";
    }
    
    @Override
    public int getNumOptions() {
        return 1;
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
        radius = values[0] * 1.0 / options[0].getMax() * 50;
    }
    
    private double radius = 2;
    private static final Option[] options = new Option[] { new Option("Radius", 0.1) };
    

    
}
