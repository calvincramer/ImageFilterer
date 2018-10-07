package imagefilterer;

import java.awt.Point;
import java.util.ArrayList;

public class PixelMap {
    
    public PixelMap() {
        map = new ArrayList<>();
        origin = new Point(0,0);
    }
    
    public void addPixel(int x, int y) {
        this.addPixel(new Point(x, y));
    }
    
    public void addPixel(Point p) {
        map.add(p);
    }
    
    public void addPixelInSquare(int radius) {
        for (int x = origin.x - radius; x <= origin.x + radius; x++) {
            for (int y = origin.y - radius; y <= origin.y + radius; y++) {
                map.add(new Point(x, y));
        }}
        
    }
    
    public boolean removePixel(int i) {
        if (i > 0 && i < map.size()) {
            map.remove(i);
            return true;
        }
        return false;
    }
    
    public boolean removePixel(int x, int y) {
        return this.removePixel(new Point(x, y));
    }
    
    public boolean removePixel(Point p) {
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).equals(p)) {
                map.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public Point[] getPixels() {
        return map.toArray(new Point[map.size()]);
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        
        for (int i = 0; i < map.size(); i++) {
            map.get(i).setLocation( map.get(i).x - this.origin.x + origin.x, map.get(i).y - this.origin.y + origin.y);
        }
        
        this.origin = origin;
    }
    
    /**
     * Removes duplicate pixels
     * @return Returns the number of pixels removed
     */
    public int removeDuplicatePixels() {
        
        int numDuplicates = 0;
        
        for (int i = 0; i < map.size(); i++) {
            
            for (int b = 0; b < map.size(); b++) {
                if (b != i && map.get(b).equals( map.get(i)) ) {
                    numDuplicates++;
                    map.remove(b);
                    b--;
                }
            }
        }
        
        return numDuplicates;
    }
    
    @Override
    public String toString() {
        String s = "";
        for (Point p : map) {
            s += "(" + p.x + ", " + p.y + ")\n";
        }
        return s;
    }
    
    public static void main(String[] args) {
        PixelMap pm = new PixelMap();
        pm.addPixelInSquare(2);
        
        System.out.println(pm.toString());
        

    }
    
    
    private ArrayList<Point> map;
    private Point origin;
}
