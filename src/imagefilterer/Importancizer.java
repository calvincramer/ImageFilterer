
package imagefilterer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Importancizer extends javax.swing.JFrame {


    public Importancizer() {
        myInitComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int x = (width / 2) - (this.getWidth() / 2);
        int y = (height / 2) - (this.getHeight() / 2);
        this.setBounds(x, y, this.getWidth(), this.getHeight());
        
        this.setVisible(true);
        
        
    }
                       
    private void myInitComponents() {

        jPanel2 = new javax.swing.JPanel();
        choosePictureButton = new javax.swing.JButton();
        thresholdSlider = new javax.swing.JSlider();
        thersholdLabel = new javax.swing.JLabel();
        imagePanel = new javax.swing.JPanel() {
            @Override
            public void paint(Graphics g) {
                //super.paint(g);
                filterProcess7(g);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        choosePictureButton.setText("Choose Picture");
        choosePictureButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                choosePictureButtonMousePressed(evt);
            }
        });

        thresholdSlider.setMajorTickSpacing(100);
        thresholdSlider.setMaximum(765);
        thresholdSlider.setMinorTickSpacing(50);
        thresholdSlider.setPaintLabels(true);
        thresholdSlider.setPaintTicks(true);
        thresholdSlider.setValue(450);
        thresholdSlider.setFocusTraversalPolicyProvider(true);
        thresholdSlider.setInheritsPopupMenu(true);
        thresholdSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                thresholdSliderMouseDragged(evt);
            }
        });

        thersholdLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thersholdLabel.setText("450");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(choosePictureButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thersholdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(thresholdSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(thresholdSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(choosePictureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(thersholdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)))
                .addGap(5, 5, 5))
        );

        //imagePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        choosePictureButton = new javax.swing.JButton();
        thresholdSlider = new javax.swing.JSlider();
        thersholdLabel = new javax.swing.JLabel();
        imagePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        choosePictureButton.setText("Choose Picture");
        choosePictureButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                choosePictureButtonMousePressed(evt);
            }
        });

        thresholdSlider.setMajorTickSpacing(100);
        thresholdSlider.setMaximum(765);
        thresholdSlider.setMinorTickSpacing(50);
        thresholdSlider.setPaintLabels(true);
        thresholdSlider.setPaintTicks(true);
        thresholdSlider.setValue(450);
        thresholdSlider.setFocusTraversalPolicyProvider(true);
        thresholdSlider.setInheritsPopupMenu(true);
        thresholdSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                thresholdSliderMouseDragged(evt);
            }
        });

        thersholdLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thersholdLabel.setText("450");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(choosePictureButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thersholdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(thresholdSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(thresholdSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(choosePictureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(thersholdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)))
                .addGap(5, 5, 5))
        );

        imagePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void thresholdSliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thresholdSliderMouseDragged
        this.thersholdLabel.setText("" + this.thresholdSlider.getValue());
        this.redrawFilteredImage();
    }//GEN-LAST:event_thresholdSliderMouseDragged

    private void choosePictureButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choosePictureButtonMousePressed
        JFileChooser chooser = new JFileChooser("C:\\Users\\Calvin\\Desktop");
        FileFilter filter = new FileNameExtensionFilter("filter that accepts only picture formats", "jpg", "jpeg", "tiff", "tif", "gif", "bmp", "png");
        chooser.addChoosableFileFilter(filter);
        chooser.setDialogTitle("Choose A Picture To Filter");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setMultiSelectionEnabled(false);

        int num = chooser.showDialog(null, "OK");

        if(num == chooser.APPROVE_OPTION) {
            try {
                image = ImageIO.read(chooser.getSelectedFile());
                gotNewImage();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }

        } else {
            return;
        }
        
        
    }//GEN-LAST:event_choosePictureButtonMousePressed

    private void gotNewImage() {
        this.imagePanel.setBounds(this.imagePanel.getX(), this.imagePanel.getY(), image.getWidth(), image.getHeight());
        this.imagePanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        this.rasteredImage = image.getData();
        
        this.redrawFilteredImage();
    }
    
    private void redrawFilteredImage() {
        if (System.currentTimeMillis() - this.lastRedraw < 333) return;
        this.lastRedraw = System.currentTimeMillis();
        
        this.imagePanel.repaint();
        
        
    }
    
    private void filterProcess7(Graphics g) {
        
        if (image == null) return;
        
        Graphics2D g2 = (Graphics2D) g;
        
        int width = image.getWidth();
        int height = image.getHeight();
        
        recursiveLuminosityDifference(new Rectangle(0,0,width,height), g2);
    }
    
    private void recursiveLuminosityDifference(Rectangle r, Graphics2D g2) {
        if (r.width < this.smallestSizeForLuminosity || r.height < this.smallestSizeForLuminosity || r.width == 1 || r.height == 1) {
            Color c = this.getAverageColorInSelection(r);
            g2.setColor(c);
            g2.fillRect(r.x, r.y, r.width, r.height);
            return;
        }
        assert r.x >= 0;
        assert r.y >= 0;
        
        if (this.getLuminosityDifference(r) > this.thresholdSlider.getValue()) {
            //split it up
            int wid = r.width / 2;
            int hei = r.height / 2;
            Rectangle topLeft = new Rectangle(r.x, r.y, wid, hei);
            Rectangle topRight = new Rectangle(r.x + wid, r.y, wid, hei);
            Rectangle bottomLeft = new Rectangle(r.x, r.y + hei, wid, hei);
            Rectangle bottomRight = new Rectangle(r.x + wid, r.y + hei, wid, hei);
            
            if (r.width % 2 == 1) {
                topRight.setSize(topRight.width + 1, topRight.height);
                bottomRight.setSize(bottomRight.width + 1, bottomRight.height);
            }
            if (r.height % 2 == 1) {
                bottomLeft.setSize(bottomLeft.width, bottomLeft.height + 1);
                bottomRight.setSize(bottomRight.width, bottomRight.height + 1);
            }
            
            this.recursiveLuminosityDifference(topLeft, g2);
            this.recursiveLuminosityDifference(topRight, g2);
            this.recursiveLuminosityDifference(bottomLeft, g2);
            this.recursiveLuminosityDifference(bottomRight, g2);
            
        } else {
            //draw the hopefully plain rectangle with average color
            Color c = this.getAverageColorInSelection(r);
            g2.setColor(c);
            g2.fillRect(r.x, r.y, r.width, r.height);
        }
        
    }
    
    private Color getAverageColorInSelection(Rectangle r) {
        return this.getAverageColorInSelection(r.x, r.y, r.width, r.height);
    }

    private Color getAverageColorInSelection(int x, int y, int width, int height) {
        
        if (x > image.getWidth() || y > image.getHeight() || x < 0 || y < 0) {
            return Color.BLACK;
        }
                
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
               
        int numPixelsCrossed = 0;
        int savedY = -1;
        int savedX = -1;
        
        for (int row = y; row < y + height; row++ ) {
            for (int col = x; col < x + width; col++ ) {

                if (row < image.getHeight() && col < image.getWidth()) {
                    int[] pixel = rasteredImage.getPixel(col, row, new int[3]);

                    totalRed += pixel[0];
                    totalGreen += pixel[1];
                    totalBlue += pixel[2];
                    
                    numPixelsCrossed++;
                }
                
                savedX = col;
            }
            savedY = row;
        }
        
        if (numPixelsCrossed <= 0) {
            return Color.BLACK;
        }
        try {
            return new Color(totalRed / numPixelsCrossed, totalGreen / numPixelsCrossed, totalBlue / numPixelsCrossed);
        } catch (ArithmeticException e) {
            System.out.println("ARITHMETIC EXCEPTION");
            System.out.println("Row: " + savedY + "  Col: " + savedX + "   pixelsCrossed: " + numPixelsCrossed);
            System.out.println("Total red: " + totalRed + " Blue: " + totalBlue + " Green: " + totalGreen);
            System.out.println("x: " + x + " y: " + y + " Width: " + width + " Height: " + height);
        }
        return Color.BLACK;
    }
    
    private int getLuminosityDifference(Rectangle r) {
        int highestLuminosity = this.getLuminosityOfPixel(0, 0);
        int lowestLuminosity = this.getLuminosityOfPixel(0, 0);
        
        for (int x = r.x; x < r.x + r.width; x++) {
            for (int y = r.y; y < r.y + r.height; y++) {
                int lum = this.getLuminosityOfPixel(x, y);
                if (lum > highestLuminosity) highestLuminosity = lum;
                if (lum < lowestLuminosity) lowestLuminosity = lum;
            }
        }
        
        return highestLuminosity - lowestLuminosity;
    }
    
    private int getLuminosityDifference(int x, int y, int width, int height) {
        return this.getLuminosityDifference(new Rectangle(x,y,width,height));
    }
    
    private int getLuminosityOfPixel(int x, int y) {
        int[] pixel = rasteredImage.getPixel(x, y, new int[3]);
        int luminosity = pixel[0] + pixel[1] + pixel[2];
        assert luminosity > 0;
        assert luminosity <= 255 * 3;
        return luminosity;
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Importancizer();
            }
        });
    }

    
    private BufferedImage image;
    private long lastRedraw = System.currentTimeMillis();
    private final int smallestSizeForLuminosity = 1;
    private Raster rasteredImage;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton choosePictureButton;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel thersholdLabel;
    private javax.swing.JSlider thresholdSlider;
    // End of variables declaration//GEN-END:variables
}
