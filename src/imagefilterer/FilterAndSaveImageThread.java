package imagefilterer;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;


public class FilterAndSaveImageThread
    implements Runnable {
    
    public FilterAndSaveImageThread(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {

        if (this.filter == null) {
            System.out.println("Filter is null");
            return;
        }
        
        BufferedImage img = this.filter.filterImage(this.sourceImage, this.data);
        ImageFilterer.createPicture(img, this.outputFileName);
        

    }
    
    public void start () {
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
    
    public void setFileName(String s) {
        this.outputFileName = s;
    }
    
    private ImageFilter filter;
    private BufferedImage sourceImage;
    private Raster data;
    
    protected Thread t;
    private String threadName;
    private String outputFileName = "output";
}
