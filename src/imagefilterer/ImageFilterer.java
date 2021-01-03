package imagefilterer;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;

public class ImageFilterer {

    public static void main(String[] args) {
        new ImageFilterer();
    }
    
    public ImageFilterer() {
        
        BufferedImage image = getImageFromDialog();
        Raster data = image.getData();
        
        ArrayList<ImageFilter> filters = new ArrayList<>();
        //filters.add(new AffineTransformFilter());
        //filters.add(new ColorDifferenceFilter());
        //filters.add(new LuminosityDifferenceFilter());
        //filters.add(new PixelatingFilter());
        //filters.add(new PolygonFilter());
        //filters.add(new TriangleFilter());
        //filters.add(new StiplingFilter());
        filters.add(new BlurFilter());
        //filters.add(new RecursiveSquareFilter());
        
        
        System.out.println("Number of filters: " + filters.size());

        for (int i = 0; i < filters.size(); i++) {
            System.out.print(i);
            ImageFilterer.createPicture(filters.get(i).filterImage(image, data), filters.get(i).getFilterName());
        }
        System.out.println();
        
    }

    
    ///////////////////////////////////////////////////////////////
    
    public static void createPicture(BufferedImage image, String fileName) {
        try {
            File outputfile = new File("C:\\Users\\Calvin\\Desktop\\FileFilter\\" + fileName + ".png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private BufferedImage getImageFromDialog() {
        
        BufferedImage img = null;
        
        JFileChooser chooser = new JFileChooser("C:\\Users\\Calvin\\Desktop");
        FileFilter filter = new FileNameExtensionFilter("filter that accepts only picture formats", "jpg", "jpeg", "tiff", "tif", "gif", "bmp", "png");
        chooser.addChoosableFileFilter(filter);
        chooser.setDialogTitle("Choose A Picture To Filter");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setMultiSelectionEnabled(false);

        int num = chooser.showDialog(null, "OK");

        if(num == JFileChooser.APPROVE_OPTION) {
            try {
                img = ImageIO.read(chooser.getSelectedFile());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else {
            System.exit(0);
        }
        
        if (img == null) {
            System.out.println("Image is null");
            System.exit(1);
        } else {
            return img;
        }
        return null;
    }
    

    ////////////////////////////////////////////////////////////////
    


    
}
