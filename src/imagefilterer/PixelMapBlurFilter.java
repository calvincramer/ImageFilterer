package imagefilterer;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class PixelMapBlurFilter 
    extends ImageFilter {

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.OPAQUE);
        
        PixelMap pixelmap = new PixelMap();
        pixelmap.addPixelInSquare(radius);
        
        System.out.println("Input radius: " + radius);
        
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                pixelmap.setOrigin( new Point(x, y));
                Color c = FilterMethods.getAverageColorAroundPixelMap(pixelmap, img, data);
                filteredImage.setRGB(x, y, c.getRGB());
                
        }}
        
        return filteredImage;
    }
    
    

    @Override
    public String getFilterName() {
       return "Pixel Map Blur Filter";
    }
    
    @Override
    public String toString() {
        return "Pixel Map Blur Filter";
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
        radius = values[0];
    }
    
    private int radius = 1;
    private static final Option[] options = new Option[] { new Option("Radius", 0, 20, 1, 4, 1) };
}
