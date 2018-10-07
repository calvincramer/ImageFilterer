package imagefilterer;

import java.awt.Polygon;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

public class PointWithConnection {
    
    public PointWithConnection() {
        this(0.0 ,0.0);
    }
    
    public PointWithConnection(double x, double y) {
        this.y = y;
        this.x = x;
        this.connections = new ArrayList<>();
        this.flag = false;
    }
    
    public void addConnection(PointWithConnection point) {
        this.connections.add(point);
        point.connections.add(this);
    }
    
    public void addConnection(double x, double y) {
        this.addConnection(new PointWithConnection(x, y));
    }
    
    public boolean removeConnection(PointWithConnection point) {
        for (int i = 0; i < this.connections.size(); i++) {
            if (connections.get(i).equals(point)) {
                connections.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean removeConnection(int index) {
        if (index < 0 || index >= this.connections.size()) return false;
        
        this.connections.remove(index);
        return true;
        
    }
    
    public int getNumberOfConnections() {
        return connections.size();
    }
    
    public PointWithConnection[] getConnections() {
        return connections.toArray(new PointWithConnection[connections.size()]);
    }
    
    public boolean isConnectedTo(PointWithConnection otherPoint) {
        for (int i = 0; i < this.connections.size(); i++) {
            if (this.connections.get(i).equals(otherPoint)) return true;
        }
        return false;
    }
    
    @Override
    public boolean equals(Object other) {
        if ( ! (other instanceof PointWithConnection) || other == null) return false;
        PointWithConnection otherPoint = (PointWithConnection) other;
        return (this.x == otherPoint.x) && (this.y == otherPoint.y);
    }
    
    public void printConnections() {
        System.out.println("Connections to: " + this.toString());
        for (PointWithConnection p : this.connections) {
            System.out.println(p.toString());
        }
        if (this.connections.size() == 0) {
            System.out.println("(no connections)");
        }
    }
    
    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        String xNum = formatter.format(x);
        String yNum = formatter.format(y);
        return "(x: " + xNum + "|y: " + yNum + ")";
    }
    
    
    public static Polygon[] getPolygonsInField(PointWithConnection[] field) {
        ArrayList<Polygon> polygons = new ArrayList<>();
        
        for (PointWithConnection point : field) {
            if (point.connections.size() > MAX_CONNECTIONS) continue;
            Polygon p = PointWithConnection.getPolygonAroundPoint(point, field);
            if (p != null) {
                polygons.add(p);
            }
        }
        
        return polygons.toArray(new Polygon[polygons.size()]);
    }
    
    public static Polygon getPolygonAroundPoint(PointWithConnection p, PointWithConnection[] field) {
        if (field.length < 3) {
            System.out.println("Cannot make polygon with only " + field.length + " points");
            return null;
        }
        
        ArrayList<PointWithConnection> pointsInPolygon = new ArrayList<>();
        double radius = 10.0;
        ArrayList<PointWithConnection> possiblePoints = PointWithConnection.getPointsAround(p, radius, field);
        
        while(possiblePoints == null || possiblePoints.size() < 3) {
            radius += 5;
            possiblePoints = PointWithConnection.getPointsAround(p, radius, field);
        }

        pointsInPolygon.add(p);
        possiblePoints.remove(p);
        
        if (possiblePoints.size() == 2) {
            //System.out.println("Only a triangle");
            for (PointWithConnection point : possiblePoints) {
                pointsInPolygon.add(point);
            }
            
            PointWithConnection.connectPointsInPolygon(pointsInPolygon);
            return PointWithConnection.pointsToPolygon(pointsInPolygon);
        }
        
        boolean done = false;
        while(pointsInPolygon.size() < 3 && !done) {
            if (pointsInPolygon.size() >= 3) {
                if(r.nextBoolean()) {
                    done = true;
                    continue;
                }
            }
            
            int n = r.nextInt(possiblePoints.size());
            pointsInPolygon.add(possiblePoints.get(n));
            
            //System.out.println("Adding a point: " + possiblePoints.get(n).toString());
            
            possiblePoints.remove(n);
        }
        
        PointWithConnection.connectPointsInPolygon(pointsInPolygon);
        return PointWithConnection.pointsToPolygon(pointsInPolygon);
    }
    
    public static ArrayList<PointWithConnection> getPointsAround(PointWithConnection p, double radius, PointWithConnection[] field) {
        ArrayList<PointWithConnection> points = new ArrayList<>();
        for (PointWithConnection point : field) {
            if (!point.equals(p) && distanceBetweenTwoPoints(p, point) <= radius && point.connections.size() <= MAX_CONNECTIONS) 
                points.add(point);
        }
        
        if (points.size() > 1) {
            return points;
        } else {
            return null;
        }
    }
    
    public static Polygon pointsToPolygon(ArrayList<PointWithConnection> points) {
        Polygon poly = new Polygon();
        for (PointWithConnection p : points) {
            poly.addPoint((int) Math.round(p.x), (int) Math.round(p.y));
        }
        return poly;
    }
    
    public static PointWithConnection getClosestPoint(PointWithConnection point, PointWithConnection[] field) {
        if (field == null) {
            System.out.println("FIELD IS NULL");
            return null;
        }
        
        PointWithConnection closestPoint = field[0];
        int index = 0;
        while (closestPoint == null && index < field.length - 1) {
            index++;
            closestPoint = field[index];
        }
        if (closestPoint == null) return null;
        
        assert closestPoint != null;
        
        for (PointWithConnection tempPoint : field) {
            if (tempPoint == null) continue;
            if (distanceBetweenTwoPoints(point, tempPoint) < distanceBetweenTwoPoints(point, closestPoint)) {
                if (!tempPoint.equals(point)) closestPoint = tempPoint;
            }
        }
        return closestPoint;
    }
    
    public static PointWithConnection getClosestPoint(PointWithConnection point, ArrayList<PointWithConnection> field) {
        return getClosestPoint(point, field.toArray(new PointWithConnection[field.size()]));
    }
    
    public static double getDistanceToClosestPoint(PointWithConnection point, ArrayList<PointWithConnection> field) {
        PointWithConnection closestPoint = getClosestPoint(point, field);
        return distanceBetweenTwoPoints(point, closestPoint);
    }
    
    public static PointWithConnection getClosestPointThatThisIsntConnectedTo(PointWithConnection point, PointWithConnection[] field) {
        PointWithConnection[] copiedArray = field.clone();
        
        PointWithConnection closestPoint = getClosestPoint(point, copiedArray);
        if (closestPoint == null) return null;
                
        while(closestPoint.isConnectedTo(point)) {
            
            for (int index = 0; index < copiedArray.length; index++) {
                if (copiedArray[index] == null) continue;
                if (copiedArray[index].equals(closestPoint)) copiedArray[index] = null;
            }
            
            closestPoint = getClosestPoint(point, copiedArray);
            if (closestPoint == null) return null;
        }
        
        return closestPoint;
    }
    
    public static double distanceBetweenTwoPoints(PointWithConnection p1, PointWithConnection p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y, 2)); 
    }
    
    private static double randomCoordinate(double width, double padding) {
        Random r = new Random();
        return r.nextDouble() * (width + (width * padding * 2)) - (width * padding / 2);
    }  
    
    public static PointWithConnection[] getRandomPoints(int width, int height, int numTries, double minDistance) {
        ArrayList<PointWithConnection> points = new ArrayList<>();
        
        for(int i = 0; i < numTries; i++) {
            double x = randomCoordinate(width, 0.07);
            double y = randomCoordinate(height, 0.07);
            PointWithConnection possiblePoint = new PointWithConnection(x, y);
            
            if (points.size() <= 2) {
                points.add(new PointWithConnection(x, y));
            } else if (getDistanceToClosestPoint(possiblePoint, points) >= minDistance) {
                points.add(new PointWithConnection(x, y));
            }

        }
        
        return points.toArray( new PointWithConnection[ points.size() ] );
    }
    
    private static void connectPointsInPolygon(ArrayList<PointWithConnection> points) {
        for (int i = 0; i < points.size(); i++) {
            int nextIndex = i+1;
            if (nextIndex >= points.size()) nextIndex = 0;
            
            points.get(i).addConnection(points.get(nextIndex));
        }
    }
    
    //
    
    public static Polygon[] getPolygons(int width, int height) {
        ArrayList<Polygon> polygons = new ArrayList<>();
        ArrayList<PointWithConnection> field = new ArrayList<>();
        
        Polygon testPoly = new Polygon();
        testPoly.addPoint(4, 0);
        testPoly.addPoint(12, 0);
        testPoly.addPoint(16, 7);
        testPoly.addPoint(8, 10);
        testPoly.addPoint(0, 7);
        int[] xPoints = {0,10,13,5,-3};
        int[] yPoints = {0,0,9,15,9};
        
        for (int i = 0; i < 400000; i++) {
            //if (i % 1000 == 0) System.out.println(i);
            int x = (int) randomCoordinate(width, 0.03);
            int y = (int) randomCoordinate(height, 0.03);

            for (int index = 0; index < xPoints.length; index++) {
                xPoints[index] += x;
                yPoints[index] += y;
            }
            
            polygons.add(new Polygon(xPoints, yPoints, xPoints.length));

            
            for (int index = 0; index < xPoints.length; index++) {
                xPoints[index] -= x;
                yPoints[index] -= y;
            }
        }
        
        
        return polygons.toArray(new Polygon[polygons.size()]);
    }
    
    private static PointWithConnection getUntraversedPoint(ArrayList<PointWithConnection> field) {
        for (int i = 0; i < field.size(); i++) {
            if (field.get(i).flag == false) return field.get(i);
        }
        return null;
    }
    
    private static Boolean pointsAllTraversed(ArrayList<PointWithConnection> field) {
        for (int i = 0; i < field.size(); i++) {
            if (field.get(i).flag == false) return false;
        }
        return true;
    }
    
    //
    
    public static void main(String[] args) {
        PointWithConnection[] points = new PointWithConnection[9];
        points[0] = new PointWithConnection(0.0,0.0);
        points[1] = new PointWithConnection(1.0,0.0);
        points[2] = new PointWithConnection(1.0,1.0);
        points[3] = new PointWithConnection(0.0,1.0);
        points[4] = new PointWithConnection(-1.0,1.0);
        points[5] = new PointWithConnection(-1.0,0.0);
        points[6] = new PointWithConnection(-1.0,-1.0);
        points[7] = new PointWithConnection(0.0,-1.0);
        points[8] = new PointWithConnection(1.0,-1.0);
        
        /*
        points[0].addConnection(points[1]);
        points[0].addConnection(points[2]);
        points[0].addConnection(points[3]);
        points[0].addConnection(points[4]);
        */
        
        Polygon[] polygons = PointWithConnection.getPolygonsInField(points);
        for (Polygon poly : polygons) {
            System.out.println(polygonToString(poly));
            
        }
        
        /*
        System.out.println();
        Polygon testPolygon = new Polygon();
        testPolygon.addPoint(0, 0);
        testPolygon.addPoint(5, 0);
        testPolygon.addPoint(5, 5);
        System.out.println(polygonToString(testPolygon));
        */
    }
    
    public static String polygonToString(Polygon poly) {
        String s = "Polygon: ";
        for (int i = 0; i < poly.xpoints.length; i++) {
            s += "(" + poly.xpoints[i] + ", " + poly.ypoints[i] + ")";
            if (i < poly.xpoints.length - 1) s += " ";
        }
        return s;
    }
    
    
    protected ArrayList<PointWithConnection> connections;
    protected double x;
    protected double y;
    private static java.util.Random r = new java.util.Random();
    private static int MAX_CONNECTIONS = 5;
    
    protected boolean flag;
}
