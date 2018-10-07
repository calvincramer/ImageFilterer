
package imagefilterer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.Point;
import java.util.Random;

public class PolygonFilter 
    extends ImageFilter{

    @Override
    public BufferedImage filterImage(BufferedImage img, Raster data) {

        testMethod(img, data);
        //return method1(img, data);
        return method2(img, data);

    }
    
    //old method
    private BufferedImage method1(BufferedImage img, Raster data) { 
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.OPAQUE);
        Graphics2D g2 = filteredImage.createGraphics();
        
        if (drawImageInBackground == true)
            g2.drawImage(img, null, 0, 0);
        if (drawBackgroundColor == true) {
            g2.setColor(backgroundColor);
            g2.fillRect(0, 0, filteredImage.getWidth(), filteredImage.getHeight());
        }
        
        PointWithConnection[] points = PointWithConnection.getRandomPoints(filteredImage.getWidth(), filteredImage.getHeight(), 
                (filteredImage.getWidth() * filteredImage.getHeight() / 500), 3.14);
        

        if (drawPolygons == true) {
            
            //Polygon[] polygons = PointWithConnection.getPolygonsInField(points);
            Polygon[] polygons = PointWithConnection.getPolygons(filteredImage.getWidth(), filteredImage.getHeight());
            
            for (Polygon poly : polygons) {
                g2.setColor(FilterMethods.getAverageColorInPolygon(poly, img, data));
                g2.fillPolygon(poly);
            }
        }
        
        if (drawPoints == true) {
            g2.setColor(Color.BLACK);
            for (PointWithConnection tempPoint : points) {
                g2.drawRect( (int) Math.round(tempPoint.x), (int) Math.round(tempPoint.y), 0, 0);
            }
        }

        return filteredImage;
    }

    //net method
    private BufferedImage method2(BufferedImage img, Raster data) {
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.OPAQUE);
        Graphics2D g2 = filteredImage.createGraphics();
        
        Point[] points = new Point[filteredImage.getWidth() * filteredImage.getHeight() / 8]; //8
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point( (int) FilterMethods.randomNumber(filteredImage.getWidth(), 0.01), 
                                   (int) FilterMethods.randomNumber(filteredImage.getHeight(), 0.01));
        }
        
        Point.Double[] pointPoly = new Point.Double[5];
        pointPoly[0] = new Point.Double(0, 0);
        pointPoly[1] = new Point.Double(PLEN, 0);
        pointPoly[2] = new Point.Double(PLEN + (cos72 * PLEN ), sin72 * PLEN );
        pointPoly[3] = new Point.Double(PLEN / 2, tan54 * (PLEN / 2) + ( (PLEN / 2) / cos54 ) );
        pointPoly[4] = new Point.Double(0 - (cos72 * PLEN), sin72 * PLEN );
        
        Polygon poly = new Polygon();
        for (Point.Double pDouble : pointPoly) {
            poly.addPoint( (int) Math.round(pDouble.x), (int) Math.round(pDouble.y));
        }
        
        for (Point point : points) {
            double randAngle = random.nextDouble() * 2 * Math.PI;
            
            poly.translate(point.x, point.y);
            //g2.rotate(randAngle);
            
            g2.setColor(FilterMethods.getAverageColorInPolygon(poly, img, data));
            g2.fillPolygon(poly);
            
            poly.translate(-point.x, -point.y);
            //g2.rotate(-randAngle);
        }
        
        /*
        g2.setColor(Color.WHITE);
        for (Point.Double pDouble : pointPoly) {
            g2.fillOval((int) Math.round(pDouble.x) + 20, (int) Math.round(pDouble.y) + 20, 3, 3);
        }
        */
        
        return filteredImage;
    }
    
    @Override
    public String getFilterName() {
        return "Polygon Filter";
    }
    
    @Override
    public String toString() {
        return "Polygon Filter";
    }
    
    private void testMethod(BufferedImage img, Raster data) {
        BufferedImage filteredImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.OPAQUE);
        Graphics2D g2 = filteredImage.createGraphics();
        
        g2.setColor(Color.LIGHT_GRAY);
        
        int y = 300;
        int x = 30;
        
        for (int index = 1; index < 10; index ++) {
            Polygon p = PolygonFilter.getPolygon(index + 2, 7.0);
            p.translate(x, y);
            g2.drawPolygon(p);
            x += 100;
        }
        
        ImageFilterer.createPicture(filteredImage, "test picture");
    }
    
    private static Polygon getPolygon(int sides, double length) {
        if (sides < 3) return null;
        if (length < 0) return null;
        
        int numSides = sides;
        
        double totalInteriorAngle = (numSides - 2) * Math.PI;
        double interiorAngle = totalInteriorAngle / numSides;
        double interiorAngleCompliment = Math.PI - interiorAngle;
        
        Point.Double[] points = new Point.Double[numSides];
        points[0] = new Point.Double(0, 0); //x, y
        
        double angle = 0;
        for (int i = 1; i < numSides; i++) {
            double xToAdd = length * Math.cos(angle);
            double yToAdd = length * Math.sin(angle);
            angle += interiorAngleCompliment;
            points[i] = new Point.Double( points[i-1].x + xToAdd, points[i-1].y + yToAdd);
        }
        
        Polygon poly = new Polygon();
        for (Point.Double pDouble : points) {
            poly.addPoint( (int) Math.round(pDouble.x), (int) Math.round(pDouble.y));
        }
        
        return poly;
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
        PLEN = values[0];
    }
    
    
    private static final Option[] options = new Option[] { new Option("Length", 1, 100, 10, 20, 12) };
    
    ////////////////////////////////////////
    private static final boolean drawImageInBackground = false;
    private static final boolean drawBackgroundColor = true;
    private static final Color backgroundColor = Color.WHITE;
    private static final boolean drawPoints = false;
    private static final boolean drawPolygons = true;
    ////////////////////////////////////////
    private int PLEN = 12;
    
    private static final double sin72 = Math.sin(Math.toRadians(72));
    private static final double cos72 = Math.cos(Math.toRadians(72));
    private static final double tan54 = Math.tan(Math.toRadians(54));
    private static final double cos54 = Math.cos(Math.toRadians(54));
    
    private static final Random random = new Random();
}
