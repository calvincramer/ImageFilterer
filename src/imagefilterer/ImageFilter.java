
package imagefilterer;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;


public abstract class ImageFilter {
    public abstract BufferedImage filterImage(BufferedImage img, Raster data);
    public abstract String getFilterName();
    public abstract int getNumOptions();
    public abstract Option[] getOptions();
    public abstract void setParameters(int[] values);
    public abstract String toString();
}
