package imagefilterer;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class JFrameManipulator extends javax.swing.JFrame {


    public JFrameManipulator(JFrame frameToActOn) {
        this.frameToActOn = frameToActOn;
        
        initComponents();
        this.setTitle("Frame Manipulator");
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        this.setBounds(screenWidth / 4 - this.getWidth(), 30, this.getWidth(), this.getHeight());
        
        updateTitle();
    }

    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        packButton = new javax.swing.JButton();
        revalidateButton = new javax.swing.JButton();
        paintButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        repaintButton = new javax.swing.JButton();
        titleTextField = new javax.swing.JTextField();

        jButton3.setText("jButton3");

        jButton6.setText("jButton6");

        jButton7.setText("jButton7");

        jButton8.setText("jButton8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 0));
        setForeground(java.awt.Color.gray);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        packButton.setText("Pack");
        packButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                packButtonMouseReleased(evt);
            }
        });

        revalidateButton.setText("Revalidate");
        revalidateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                revalidateButtonMouseReleased(evt);
            }
        });

        paintButton.setText("Paint");
        paintButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                paintButtonMouseReleased(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.setEnabled(false);
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                updateButtonMouseReleased(evt);
            }
        });

        repaintButton.setText("Repaint");
        repaintButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                repaintButtonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(packButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revalidateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(repaintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(packButton)
                    .addComponent(revalidateButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paintButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repaintButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        titleTextField.setEditable(false);
        titleTextField.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        titleTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        titleTextField.setText("<no frame>");
        titleTextField.setHighlighter(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleTextField))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JFrame getFrameToActOn() {
        return frameToActOn;
    }

    public void setFrameToActOn(JFrame frameToActOn) {
        this.frameToActOn = frameToActOn;
        this.updateTitle();
    }
    
    private void updateTitle() {
        
        if (this.frameToActOn == null) {
            this.titleTextField.setText("<no frame>");
        } else {
            this.titleTextField.setText(this.frameToActOn.getTitle());
        }
    }
    
    private void packButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_packButtonMouseReleased
        if (this.frameToActOn != null) {
            this.frameToActOn.pack();
        }
    }//GEN-LAST:event_packButtonMouseReleased

    private void revalidateButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revalidateButtonMouseReleased
        if (this.frameToActOn != null) {
            this.frameToActOn.revalidate();
        }
    }//GEN-LAST:event_revalidateButtonMouseReleased

    private void updateButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseReleased
        if (this.frameToActOn != null) {
            this.frameToActOn.update(this.frameToActOn.getGraphics());
        }
    }//GEN-LAST:event_updateButtonMouseReleased

    private void paintButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paintButtonMouseReleased
        if (this.frameToActOn != null) {
            this.frameToActOn.paint(this.frameToActOn.getGraphics());
        }
    }//GEN-LAST:event_paintButtonMouseReleased

    private void repaintButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_repaintButtonMouseReleased
        if (this.frameToActOn != null) {
            this.frameToActOn.repaint();
        }
    }//GEN-LAST:event_repaintButtonMouseReleased

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JFrameManipulator(null).setVisible(true);
            }
        });
    }
    
    private JFrame frameToActOn;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton packButton;
    private javax.swing.JButton paintButton;
    private javax.swing.JButton repaintButton;
    private javax.swing.JButton revalidateButton;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
