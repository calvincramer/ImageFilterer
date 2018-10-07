package imagefilterer;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;


public class FilteringThread 
    implements Runnable {
    
    public FilteringThread(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        
        if (this.filter == null) {
            System.out.println("Filter is null");
            return;
        }
        if (this.outputFrame == null) {
            System.out.println("Output frame is null");
            return;
        }
        
        //System.out.println("Filtering thread is starting to filter!");
        
        BufferedImage img = this.filter.filterImage(this.sourceImage, this.data);
        this.outputFrame.setMainPicture(img);
        
        //System.out.println("Filtering thread has outputted filtered image");
        
        this.fm.filterThreadDone();
    }
    
    public void start () {
       //System.out.println("Starting " +  threadName );
       if (t == null) {
          t = new Thread (this, threadName);
          t.start();
       }
    }
    
    public void setFilter(ImageFilter filter) {
        this.filter = filter;
    }
    
    public void setSourceImage(BufferedImage img) {
        this.sourceImage = img;
        this.data = this.sourceImage.getData();
    }
    
    public void setOutputFrame(ImageViewFrame ivf) {
        this.outputFrame = ivf;
    }
    
    public void setFilterManipulator(FilterManipulator fm) {
        this.fm = fm;
    }
    
    private ImageFilter filter;
    private ImageViewFrame outputFrame;
    
    private BufferedImage sourceImage;
    private Raster data;
    
    private FilterManipulator fm;
    
    protected Thread t;
    private String threadName;
}
