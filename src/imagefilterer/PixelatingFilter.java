package imagefilterer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;


public class PixelatingFilter
    extends ImageFilter{

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {
        
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.OPAQUE);
        Graphics2D g2 = filteredImage.createGraphics();
        
        if (pixelWidth < 1) pixelWidth = 16;
        
        for(int x = 0; x < filteredImage.getWidth(); x += pixelWidth) {
            for (int y = 0; y < filteredImage.getWidth(); y += pixelWidth) {
                Color c = FilterMethods.getAverageColorInSelection(x, y, pixelWidth, pixelWidth, img, data);
                g2.setColor(c);
                g2.fillRect(x, y, pixelWidth, pixelWidth);
                
            }
        }
        
        return filteredImage;
    }

    @Override
    public String getFilterName() {
        return "Pixelating Filter";
    }
    
    @Override
    public String toString() {
        return "Pixelating Filter";
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
        pixelWidth = values[0];
    }
    
    private static final Option[] options = new Option[] { new Option("Width", 1, 400, 25, 100, 16) };
    
    private int pixelWidth;

    
}
