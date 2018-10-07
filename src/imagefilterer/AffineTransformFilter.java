package imagefilterer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class AffineTransformFilter 
    extends ImageFilter {

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.OPAQUE);
        Graphics2D g2 = filteredImage.createGraphics();
        
        System.out.println("x: " + this.scaleX);
        System.out.println("y: " + this.scaleY);

        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, img.getWidth() / 2.0, img.getHeight() / 2.0);
        transform.scale(this.scaleX, this.scaleY);
        //transform.shear(this.sheerX, this.sheerY);

        g2.drawImage(img, transform, null);
        
        return filteredImage;
    }

    @Override
    public String getFilterName() {
        return "Affine Transform Filter";
    }
    
    @Override
    public String toString() {
        return "Affine Transform Filter";
    }
    
    @Override
    public int getNumOptions() {
        return 0;
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
        angle = values[0] * 1.0 / options[0].getMax() * Math.PI * 4;
        sheerX = values[1] * 1.0 / options[0].getMax() * 10;
        sheerY = values[2] * 1.0 / options[0].getMax() * 10;
        scaleX = values[3] * 1.0 / options[0].getMax() * 10;
        scaleY = values[4] * 1.0 / options[0].getMax() * 10;
    }
    
    private double angle = Math.PI / 4;
    private double sheerX = 0;
    private double sheerY = 0;
    private double scaleX = 0;
    private double scaleY = 0;
    
    private static final Option[] options = new Option[] { new Option("Angle", 0.0),
                                                           new Option("Sheer x", 0.0),
                                                           new Option("Sheer y", 0.0),
                                                           new Option("Scale x", 0.0),
                                                           new Option("Scale y", 0.0) };
}
