package imagefilterer;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.Random;


public class FilterMethods {
    
    public static Color getRandomColor() {
        int red = r.nextInt(128) + 128;
        int grn = r.nextInt(128) + 128;
        int blu = r.nextInt(128) + 128;
        int alp = r.nextInt(100) + 156;
        
        return new Color(red,grn,blu,alp);
    }
    
    public static Color averageTwoColors(Color c1, Color c2) {
        
        return new Color(   (c1.getRed() + c2.getRed()) / 2 ,
                            (c1.getGreen() + c2.getGreen()) / 2,
                            (c1.getBlue() + c2.getBlue()) / 2);
        
                    
    }
    
    public static double randomNumber(double width, double padding) {
        Random r = new Random();
        return r.nextDouble() * (width + (width * padding * 2)) - (width * padding / 2);
    }    
    
    public static Color getColorAtPoint(int x, int y, Raster data) {
        int[] pixel = data.getPixel(x, y, new int[3]);
        return new Color(pixel[0], pixel[1], pixel[2]);
    }
    
    public static double colorDifference(Color c1, Color c2) {
        int total = 0;
        total += Math.abs(c1.getRed() - c2.getRed());
        total += Math.abs(c1.getGreen()- c2.getGreen());
        total += Math.abs(c1.getBlue()- c2.getBlue());
        return total / (255.0 * 3.0);
    }
    
    public static boolean isLighterThan(Color c1, Color c2) {
        int sumC1 = c1.getRed() + c1.getGreen() + c1.getBlue();
        int sumC2 = c2.getRed() + c2.getGreen() + c2.getBlue();
        return (sumC1 > sumC2);
    }
    
    public static Color getAverageColorInPolygon(Polygon poly, BufferedImage image, Raster data) {
        Rectangle boundsOfPolygon = poly.getBounds();
        
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        int numPixelsCrossed = 0;
        
        for (int row = boundsOfPolygon.y; row < boundsOfPolygon.y + boundsOfPolygon.height; row++ ) {
            for (int col = boundsOfPolygon.x; col < boundsOfPolygon.x + boundsOfPolygon.width; col++ ) {
                if (row < image.getHeight() && col < image.getWidth() && row >= 0 && col >= 0) {
                    
                    if (poly.contains(col, row)) {
                        int[] pixel = data.getPixel(col, row, new int[3]);

                        totalRed += pixel[0];
                        totalGreen += pixel[1];
                        totalBlue += pixel[2];

                        numPixelsCrossed++;
                    }
                }
        }}
        
        if (numPixelsCrossed == 0) return Color.BLACK;
        
        totalRed = totalRed / numPixelsCrossed;
        totalGreen = totalGreen / numPixelsCrossed;
        totalBlue = totalBlue / numPixelsCrossed;
        Color c = new Color(totalRed, totalGreen, totalBlue);
        return c;

    }
    
    public static Color getAverageColorInCircle(int x, int y, double radius, BufferedImage image, Raster data) {
        
        //System.out.println("getting average color in circle: (" + x + ", " + y + ") radius: " + radius);
        int intRadius = (int) (radius + 1);
        //System.out.println("int radius: " + intRadius);
        
        Rectangle boundsOfPolygon = new Rectangle(x - intRadius, y - intRadius, intRadius * 2, intRadius * 2);
        //System.out.println("rect: " + boundsOfPolygon.toString());
        
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        int numPixelsCrossed = 0;
        
        for (int row = boundsOfPolygon.y; row < boundsOfPolygon.y + boundsOfPolygon.height; row++ ) {
            for (int col = boundsOfPolygon.x; col < boundsOfPolygon.x + boundsOfPolygon.width; col++ ) {
                
                if (row < image.getHeight() && col < image.getWidth() && row >= 0 && col >= 0) {
                    //if distance between center of circle (x, y) and (row, col) is less than radius
                    if ( FilterMethods.distanceBetween(x ,y ,col ,row) < radius) {
                        int[] pixel = data.getPixel(col, row, new int[3]);

                        totalRed += pixel[0];
                        totalGreen += pixel[1];
                        totalBlue += pixel[2];

                        numPixelsCrossed++;
                    }
                }
                
        }}
        
        if (numPixelsCrossed == 0) return Color.BLACK;
        
        totalRed = totalRed / numPixelsCrossed;
        totalGreen = totalGreen / numPixelsCrossed;
        totalBlue = totalBlue / numPixelsCrossed;
        Color c = new Color(totalRed, totalGreen, totalBlue);
        return c;

    }
    
    public static Color getAverageColorInSelection(int x, int y, int width, int height, BufferedImage image, Raster data) {
        
        if (x > image.getWidth() || y > image.getHeight() || x < 0 || y < 0) {
            return Color.BLACK;
        }      
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0; 
        int numPixelsCrossed = 0;
        
        for (int row = y; row < y + height; row++ ) {
            for (int col = x; col < x + width; col++ ) {

                if (row < image.getHeight() && col < image.getWidth()) {
                    int[] pixel = data.getPixel(col, row, new int[3]);
                    totalRed += pixel[0];
                    totalGreen += pixel[1];
                    totalBlue += pixel[2];
                    numPixelsCrossed++;
                }
        }}  
        
        if (numPixelsCrossed <= 0) {
            return Color.BLACK;
        } else {
            return new Color(totalRed / numPixelsCrossed, totalGreen / numPixelsCrossed, totalBlue / numPixelsCrossed);
        }
    }
       
    public static Color getAverageColorInSelection(Rectangle r, BufferedImage image, Raster data) {
        return getAverageColorInSelection(r.x, r.y, r.width, r.height, image, data);
    }
    
    public static Color getColor(int y, int x, Raster data) {
        int[] pixel = data.getPixel(x, y, new int[3]);
        return new Color(pixel[0], pixel[1], pixel[2]);
    }
    
    public static double distanceBetween(double x1, double y1, double x2, double y2) {
        double dy = y2 - y1;
        double dx = x2 - x1;
        return Math.sqrt( (dy * dy) + (dx * dx) );
    }
    
    public static  Color getAverageColorAroundPixelMap(PixelMap pm, BufferedImage image, Raster data) {
        int tRed = 0;
        int tGreen = 0;
        int tBlue = 0;
        int pixelsCrossed = 0;
        
        Point[] points = pm.getPixels();
        
        /*
        System.out.println("Points:");
        for (int i = 0; i < points.length; i++)
            System.out.println(points[i].x + " " + points[i].y);
        */
        
        for (Point p : points) {
            if (p == null) {
                System.out.println("null");
                continue;
            }
            if (p.x < 0 || p.y < 0 || p.x >= image.getWidth() || p.y >= image.getHeight()) {
                //System.out.println("x=" + p.x + "  y=" + p.y + "     imageDimensions=(" + image.getWidth() + ", " + image.getHeight() + ")");
            } else {
                
                try {
                    int[] pixel = data.getPixel(p.x, p.y, new int[3]);
                    tRed += pixel[0];
                    tGreen += pixel[1];
                    tBlue += pixel[2];
                    pixelsCrossed++;
                } catch (Exception e) {
                    System.out.println(p.x + " " + p.y);
                    e.printStackTrace();
                }
                
                
            }
        }
        
        if (pixelsCrossed == 0) {
            return Color.BLACK;
        }
        
        //System.out.println(tRed + " " + tGreen + " " + tBlue);
        return new Color(tRed / pixelsCrossed, tGreen / pixelsCrossed, tBlue / pixelsCrossed);
    }
    
    
    //////////////////////////////////////////////////////////
    private static Random r = new Random();
    
    public static void main(String[] args) {
        System.out.println(FilterMethods.distanceBetween(0, 0, -3, 4));
    }
}
