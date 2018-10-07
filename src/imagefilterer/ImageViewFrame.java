package imagefilterer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class ImageViewFrame 
    extends JFrame {
    
    public ImageViewFrame() {
        
        this.initComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        
        int widthOfFrame = 1420;
        
        this.setBounds(width - widthOfFrame, 0, widthOfFrame, height - 40);

        /*
        this.sourcePicturePanel = new ImagePanel(null);
        this.mainPicturePanel = new ImagePanel(null);
        
        this.sourcePicturePanel.setBorder(new LineBorder(Color.BLACK, 1));
        this.sourcePicturePanel.setBackground(new Color(0,0,0,0));
        
        this.mainPicturePanel.setBorder(new LineBorder(Color.BLACK, 1));
        this.mainPicturePanel.setBackground(new Color(0,0,0,0));
        
        int desiredWidth = this.getContentPane().getWidth() - (PICTURE_PADDING * 2);
        int y = PICTURE_PADDING;
        
        this.sourcePicturePanel.setBounds( (this.getContentPane().getWidth() / 2) - (desiredWidth / 2), y, desiredWidth, this.getHeight() / 4);
        y += this.sourcePicturePanel.getHeight();
        y += PICTURE_PADDING;
        this.mainPicturePanel.setBounds((this.getContentPane().getWidth() / 2) - (desiredWidth / 2), y, 
                                        desiredWidth, this.getContentPane().getHeight() - y - PICTURE_PADDING);
        */
        

        this.setVisible(true);
        
    }
    
    private void initComponents() {

        sourcePicturePanel = new ImagePanel(null);
        mainPicturePanel = new ImagePanel(null);

        this.getContentPane().setBackground(new Color(90,90,90));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setResizable(true);


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Image Viewer");

        sourcePicturePanel.setBackground(new java.awt.Color(0, 0, 0, 0));
        sourcePicturePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout sourcePicturePanelLayout = new javax.swing.GroupLayout(sourcePicturePanel);
        sourcePicturePanel.setLayout(sourcePicturePanelLayout);
        sourcePicturePanelLayout.setHorizontalGroup(
            sourcePicturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sourcePicturePanelLayout.setVerticalGroup(
            sourcePicturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        mainPicturePanel.setBackground(new java.awt.Color(0, 0, 0, 0));
        mainPicturePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout mainPicturePanelLayout = new javax.swing.GroupLayout(mainPicturePanel);
        mainPicturePanel.setLayout(mainPicturePanelLayout);
        mainPicturePanelLayout.setHorizontalGroup(
            mainPicturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );
        mainPicturePanelLayout.setVerticalGroup(
            mainPicturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mainPicturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sourcePicturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sourcePicturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPicturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }
    
    public void setSourcePicture(BufferedImage img) {
        this.sourcePicturePanel.setImage(img);
        this.repaint();
    }
    
    public void setMainPicture(BufferedImage img) {
        this.mainPicturePanel.setImage(img);
        this.repaint();
    }
    
    public static void main(String[] args) {
        new ImageViewFrame().setVisible(true);
    }
    
    private ImagePanel sourcePicturePanel;
    private ImagePanel mainPicturePanel;
    
    private static final int PICTURE_PADDING = 8;
}
