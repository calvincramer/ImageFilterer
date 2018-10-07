package imagefilterer;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;


public class TriangleFilter 
    extends ImageFilter {

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {
        
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.OPAQUE);
        Graphics2D g2 = filteredImage.createGraphics();
        
        int widthOfTriangles = triangleWidth;
        if (widthOfTriangles % 2 == 1) widthOfTriangles ++;
        
        int width = widthOfTriangles;
        int halfWidth = width / 2;
        int height = (int) Math.round(halfWidth * Math.sqrt(3));
        
        int numberOfRowsDone = 0;
        for (int y = 0; y < filteredImage.getHeight() + height; y += height) {
            int startX = - width;
            if (numberOfRowsDone % 2 == 1) 
                startX += halfWidth;
            for (int x = startX; x < filteredImage.getWidth() + width; x += width) {
                
                Polygon bottom = new Polygon();
                bottom.addPoint(x, y);
                bottom.addPoint(x + width, y);
                bottom.addPoint(x + halfWidth, y - height);
                
                Polygon top = new Polygon();
                top.addPoint(x, y);
                top.addPoint(x + halfWidth, y - height);
                top.addPoint(x - halfWidth, y - height);
                
                g2.setColor(FilterMethods.getAverageColorInPolygon(bottom, filteredImage, data));
                g2.fillPolygon(bottom);
                
                g2.setColor(FilterMethods.getAverageColorInPolygon(top, filteredImage, data));
                g2.fillPolygon(top);
            
            }
            numberOfRowsDone++;
        }
        
        return filteredImage;
    }

    @Override
    public String getFilterName() {
        return "Triangle Filter";
    }
    
    @Override
    public String toString() {
        return "Triangle Filter";
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
        triangleWidth = values[0];
    }
    
    private static final Option[] options = new Option[] { new Option("Width", 1, 300, 10, 50, 12) };
    
    private int triangleWidth;
}
