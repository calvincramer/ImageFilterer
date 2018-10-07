package imagefilterer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FilterManipulator 
    extends JFrame {
    
    public FilterManipulator() {
        
        this.model = new DefaultComboBoxModel(new ImageFilter[] {
            new ColorDifferenceFilter(),
            new LuminosityDifferenceFilter(),
            new PixelatingFilter(),
            new PolygonFilter(),
            new TriangleFilter(),
            new StiplingFilter(),
            new BlurFilter(),
            new RecursiveSquareFilter(),
            new PixelMapBlurFilter(),
            new AffineTransformFilter(),
            new BrightnessContrastFilter()
        });
        
        this.initComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        this.setBounds(0, 300, 500, this.getHeight());
        
        
        this.sourceImage = null;
        this.numTimesFiltered = 0;
        this.labels = new JLabel[] { label0, label1, label2, label3, label4, label5, label6, label7 };
        this.sliders = new JSlider[] { slider0, slider1, slider2, slider3, slider4, slider5, slider6, slider7 };
        
        this.imageFrame = new ImageViewFrame();
        this.manipulator = new JFrameManipulator(this);
        this.manipulator.setVisible(true);
        
        this.updateComponentsForNewFilter();
    }
                           
    private void initComponents() {

        contentPanel = new JPanel();
        slider0 = new JSlider();
        label0 = new JLabel();
        label1 = new JLabel();
        slider1 = new JSlider();
        label2 = new JLabel();
        slider2 = new JSlider();
        label3 = new JLabel();
        slider3 = new JSlider();
        label4 = new JLabel();
        slider4 = new JSlider();
        label5 = new JLabel();
        slider5 = new JSlider();
        label6 = new JLabel();
        slider6 = new JSlider();
        label7 = new JLabel();
        slider7 = new JSlider();
        topPanel = new JPanel();
        filterComboBox = new JComboBox();
        label = new JLabel();
        workingLightPanel = new JPanel();
        menuBar = new JMenuBar();
        openPictureMenu = new JMenu();
        savePictureMenu = new JMenu();
        repaintMenu = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Filter Manipulator");
        setMaximumSize(new Dimension(2147483647, 500));
        setResizable(false);

        slider0.setMajorTickSpacing(50000);
        slider0.setMaximum(100000);
        slider0.setPaintTicks(true);
        slider0.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                slider0MouseDragged(evt);
            }
        });

        label0.setHorizontalAlignment(SwingConstants.RIGHT);
        label0.setText("Label 1");
        label0.setVerticalAlignment(SwingConstants.TOP);

        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        label1.setText("Label 1");
        label1.setVerticalAlignment(SwingConstants.TOP);

        slider1.setMajorTickSpacing(50000);
        slider1.setMaximum(100000);
        slider1.setPaintTicks(true);
        slider1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                slider1MouseDragged(evt);
            }
        });

        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        label2.setText("Label 1");
        label2.setVerticalAlignment(SwingConstants.TOP);

        slider2.setMajorTickSpacing(50000);
        slider2.setMaximum(100000);
        slider2.setPaintTicks(true);
        slider2.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                slider2MouseDragged(evt);
            }
        });

        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        label3.setText("Label 1");
        label3.setVerticalAlignment(SwingConstants.TOP);

        slider3.setMajorTickSpacing(50000);
        slider3.setMaximum(100000);
        slider3.setPaintTicks(true);
        slider3.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                slider3MouseDragged(evt);
            }
        });

        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        label4.setText("Label 1");
        label4.setVerticalAlignment(SwingConstants.TOP);

        slider4.setMajorTickSpacing(50000);
        slider4.setMaximum(100000);
        slider4.setPaintTicks(true);
        slider4.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                slider4MouseDragged(evt);
            }
        });

        label5.setHorizontalAlignment(SwingConstants.RIGHT);
        label5.setText("Label 1");
        label5.setVerticalAlignment(SwingConstants.TOP);

        slider5.setMajorTickSpacing(50000);
        slider5.setMaximum(100000);
        slider5.setPaintTicks(true);
        slider5.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                slider5MouseDragged(evt);
            }
        });
        
        label6.setHorizontalAlignment(SwingConstants.RIGHT);
        label6.setText("Label 1");
        label6.setVerticalAlignment(SwingConstants.TOP);

        slider6.setMajorTickSpacing(50000);
        slider6.setMaximum(100000);
        slider6.setPaintTicks(true);
        slider6.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                slider6MouseDragged(evt);
            }
        });

        label7.setHorizontalAlignment(SwingConstants.RIGHT);
        label7.setText("Label 1");
        label7.setVerticalAlignment(SwingConstants.TOP);

        slider7.setMajorTickSpacing(50000);
        slider7.setMaximum(100000);
        slider7.setPaintTicks(true);
        slider7.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                slider7MouseDragged(evt);
            }
        });

        GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slider2, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slider3, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slider4, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slider5, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slider6, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slider7, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(label0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(slider1, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(slider0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(label0, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(label7, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        filterComboBox.setModel(this.model);
        filterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                filterComboBoxActionPerformed(evt);
            }
        });
        filterComboBox.setMaximumRowCount(this.filterComboBox.getItemCount());
        filterComboBox.setSelectedIndex(this.filterComboBox.getItemCount() - 1);

        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setText("Choose filter:");

        workingLightPanel.setBackground(FilterManipulator.NOT_WORKING_COLOR);

        GroupLayout workingLightPanelLayout = new GroupLayout(workingLightPanel);
        workingLightPanel.setLayout(workingLightPanelLayout);
        workingLightPanelLayout.setHorizontalGroup(
            workingLightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        workingLightPanelLayout.setVerticalGroup(
            workingLightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        GroupLayout topPanelLayout = new GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(label, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workingLightPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(workingLightPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(filterComboBox, GroupLayout.Alignment.TRAILING)
                    .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        
        menuBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuBarMouseExited(evt);
            }
        });

        openPictureMenu.setText("Open Picture");
        openPictureMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                openPictureMenuMousePressed(evt);
            }
        });
        menuBar.add(openPictureMenu);

        savePictureMenu.setText("Save Picture");
        savePictureMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                savePictureMenuMousePressed(evt);
            }
        });
        menuBar.add(savePictureMenu);

        repaintMenu.setText("Repaint");
        repaintMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                repaintMenuMousePressed(evt);
            }
        });
        menuBar.add(repaintMenu);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contentPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(topPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        pack();
    }                      

    // <editor-fold defaultstate="collapsed" desc="Events">
    private void filterComboBoxActionPerformed(ActionEvent evt) {
        //System.out.println(this.filterComboBox.getSelectedItem().toString());
        this.updateComponentsForNewFilter();
    }                                              
    
    private void openPictureMenuMousePressed(java.awt.event.MouseEvent evt) {
        //System.out.println("Openning Picture");
        this.sourceImage = this.getImageFromDialog();
        this.imageFrame.setSourcePicture(this.sourceImage);
    }                                 

    private void savePictureMenuMousePressed(java.awt.event.MouseEvent evt) {
        
        if (savingThread != null && savingThread.t.isAlive()) {
            return;
        }
        if (this.sourceImage == null) {
            return;
        }
        
        ImageFilter filter  = (ImageFilter) (this.filterComboBox.getSelectedItem());
        filter.setParameters(this.getParameters());
        
        savingThread = new FilterAndSaveImageThread("saving image thread");
        savingThread.setFileName(filter.getFilterName() + fileNumber);
        savingThread.setFilter(filter);
        savingThread.setSourceImage(sourceImage);
        
        savingThread.start();
        
        this.fileNumber++;
    }                                  

    private void repaintMenuMousePressed(java.awt.event.MouseEvent evt) {
        this.filter();
    }     
    
    private void menuBarMouseExited(java.awt.event.MouseEvent evt) {                                    
        for (int i = 0; i < this.menuBar.getMenuCount(); i++) {
            this.menuBar.getMenu(i).setSelected(false);
        }
    }
    
    private void slider0MouseDragged(MouseEvent evt) {       
        this.filter();
        //System.out.println(this.slider0.getValue() * 1.0 / this.slider0.getMaximum());
    }                                    

    private void slider1MouseDragged(MouseEvent evt) {                                     
        this.filter();
    }                                    

    private void slider2MouseDragged(MouseEvent evt) {                                     
        this.filter();
    }                                    

    private void slider3MouseDragged(MouseEvent evt) {                                     
        this.filter();
    }                                    

    private void slider4MouseDragged(MouseEvent evt) {                                     
        this.filter();
    }                                    

    private void slider5MouseDragged(MouseEvent evt) {                                     
        this.filter();
    }                                    

    private void slider6MouseDragged(MouseEvent evt) {                                     
        this.filter();
    }                                    

    private void slider7MouseDragged(MouseEvent evt) {                                     
        this.filter();
    }                                    
    // </editor-fold>
    
    private void filter() {
        if (filterThread != null && filterThread.t.isAlive()) {
            return;
        }
        if (this.sourceImage == null) {
            return;
        }
        
        this.workingLightPanel.setBackground(WORKING_COLOR);
        this.workingLightPanel.repaint();
        
        this.numTimesFiltered++;
        //System.out.println(this.numTimesFiltered);
        
        
        
        ImageFilter filter  = (ImageFilter) (this.filterComboBox.getSelectedItem());
        filter.setParameters(this.getParameters());
        
        filterThread = new FilteringThread("filter thread");
        filterThread.setFilter(filter);
        filterThread.setSourceImage(sourceImage);
        filterThread.setOutputFrame(imageFrame);
        filterThread.setFilterManipulator(this);
        
        filterThread.start();
        
    }
    
    private void updateComponentsForNewFilter() {
        
        if (this.sliders == null) return;
        
        ImageFilter filter = (ImageFilter) (this.filterComboBox.getSelectedItem());
        Option[] options = filter.getOptions();
        
        for (int i = 0; i < this.sliders.length; i++) {
            if (i < options.length) {
                labels[i].setText(options[i].getTitle());
                sliders[i].setMinimum(options[i].getMin());
                sliders[i].setMaximum(options[i].getMax());
                sliders[i].setMinorTickSpacing(options[i].getMinorTick());
                sliders[i].setMajorTickSpacing(options[i].getMajorTick());
                sliders[i].setValue(options[i].getStartValue());
                sliders[i].setEnabled(true);
            } else {
                sliders[i].setEnabled(false);
                sliders[i].setMinimum(0);
                sliders[i].setValue(0);
                labels[i].setText("");
            }
        }
    }
    
    private int[] getParameters() {
        int size = ((ImageFilter) (this.filterComboBox.getSelectedItem())).getOptions().length;
        int[] params = new int[size];
        for (int i = 0; i < params.length; i++) {
            params[i] = sliders[i].getValue();
        }
        return params;
    }
    
    protected void filterThreadDone() {
        this.workingLightPanel.setBackground(FilterManipulator.NOT_WORKING_COLOR);
        this.repaint();
    }
    
    private BufferedImage getImageFromDialog() {
        
        BufferedImage img = null;
        
        JFileChooser chooser = new JFileChooser("C:\\Users\\Calvin Cramer\\Desktop");
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
    
    public static void main(String[] args) {
        new FilterManipulator().setVisible(true);
    }
    
    
    private int fileNumber = 0;
    
    private BufferedImage sourceImage;
    private BufferedImage currentFilteredImage;
    
    private ImageViewFrame imageFrame;
    private JFrameManipulator manipulator;
    
    private FilteringThread filterThread;
    private FilterAndSaveImageThread savingThread;
    
    private int numTimesFiltered;
    //private long lastTimeFiltered;
    //private final long MIN_TIME_BETWEEN_FILTERS = 1000; //in millis
    
    private static final Color NOT_WORKING_COLOR = new Color(0,255,0);
    private static final Color WORKING_COLOR = new Color(255,0,0);
    
    private JLabel[] labels;
    private JSlider[] sliders;
    private DefaultComboBoxModel model;
    
    private JPanel contentPanel;
    private JComboBox filterComboBox;
    private JLabel label;
    private JLabel label0;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JMenuBar menuBar;
    private JMenu openPictureMenu;
    private JMenu repaintMenu;
    private JMenu savePictureMenu;
    private JSlider slider0;
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JSlider slider4;
    private JSlider slider5;
    private JSlider slider6;
    private JSlider slider7;
    private JPanel topPanel;
    private JPanel workingLightPanel;
}
