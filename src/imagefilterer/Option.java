package imagefilterer;

public class Option {
    private String title;
    private int min;
    private int max;
    private int minorTick;
    private int majorTick;
    private int startValue;
    
    /**
     * Creates an option which represents a range for 0.0 to 1.0 with an accuracy of 0.00001
     * @param title 
     */
    public Option(String title, double startValue) {
        this.title = title;
        this.min = 0;
        this.max = 100000;
        this.minorTick = 0;
        this.majorTick = this.max / 2;
        this.startValue = (int) (startValue * this.max);
    }
    
    public Option(String title, int min, int max, int minorTick, int majorTick, int startValue) {
        this.title = title;
        this.min = min;
        this.max = max;
        this.minorTick = minorTick;
        this.majorTick = majorTick;
        this.startValue = startValue;
    }

    public String getTitle() {
        return title;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getMinorTick() {
        return minorTick;
    }

    public int getMajorTick() {
        return majorTick;
    }

    public int getStartValue() {
        return startValue;
    }
    
    
    
    
}
