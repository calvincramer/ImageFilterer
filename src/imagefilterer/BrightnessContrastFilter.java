/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefilterer;
    
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RescaleOp;

public class BrightnessContrastFilter
    extends ImageFilter {

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {
        BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.OPAQUE);
        
        System.out.println("Contrast: " + contrast + " Brightness: " + brightness);
        RescaleOp rescaleOp = new RescaleOp( (float) (contrast), brightness, null);
        rescaleOp.filter(img, filteredImage);
        
        return filteredImage;
    }

    @Override
    public String getFilterName() {
        return "Brightness Contrast Filter";
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
        brightness = values[0];
        contrast = values[1] * 16.0 / options[1].getMax();
    }

    @Override
    public String toString() {
        return "Brightness Contrast Filter";
    }
    
    private int brightness;
    private double contrast;
    
    private static final Option[] options = new Option[] { new Option("Brightness", -100,100,10,10,0),
                                                           new Option("Contrast", (1/16.0))};
    
}
