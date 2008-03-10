/*
 * MiscSettingsEditor.java
 *
 * Created on 6. M�rz 2008, 15:37
 */

package gralej.blocks.configurator;

import gralej.blocks.BlockPanelStyle;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

/**
 *
 * @author  Martin
 */
public class MiscSettingsEditor extends javax.swing.JDialog {
    
    BlockPanelStyle _style;
    boolean _isInitializing;
    
    public MiscSettingsEditor(java.awt.Frame parent, boolean modal, BlockPanelStyle style) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        
        JComponent c = (JComponent) getContentPane();
        c.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
                KeyStroke.getKeyStroke("ESCAPE"), "cancel");
        c.getActionMap().put("cancel",
            new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
        });
        
        reset(style);
    }
    
    void reset(BlockPanelStyle style) {
        _style = style;
        
        _isInitializing = false;
        // AVM tab
        _chkAVMBracketsRounded.setSelected(_style.isAVMBracketRounded());
        _chkAVMLayoutCompact.setSelected(_style.isAVMLayoutCompact());
        setColor(_colAVMEdge, _style.getAVMBracketColor());
        _spAVMEdgeLength.setValue(_style.getAVMBracketEdgeLength());
        
        // Tree tab
        _spTreeMinHDist.setValue(_style.getMinTreeNodesHorizontalDistance());
        _spTreeMinVDist.setValue(_style.getMinTreeNodesVerticalDistance());
        setColor(_colTreeEdge, _style.getTreeEdgeColor());
        
        // Panel tab
        setColor(_colBlockPanel, _style.getBackgroundColor());
        setColor(_colSelectedBlock, _style.getSelectedBlockColor());
        _spMargins.setValue(_style.getMargin());
        
        _isInitializing = true;
    }
    
    private boolean setColor(JLabel l) {
        Color c = JColorChooser.showDialog(this, null, l.getBackground());
        if (c == null)
            return false;
        setColor(l, c);
        return true;
    }
    
    private static void setColor(JLabel l, Color col) {
        l.setBackground(col);
        l.setForeground(col);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        _chkAVMBracketsRounded = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        _spAVMEdgeLength = new javax.swing.JSpinner();
        _colAVMEdge = new javax.swing.JLabel();
        _chkAVMLayoutCompact = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        _spTreeMinHDist = new javax.swing.JSpinner();
        _spTreeMinVDist = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        _colTreeEdge = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        _colBlockPanel = new javax.swing.JLabel();
        _colSelectedBlock = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        _spMargins = new javax.swing.JSpinner();

        setTitle("Miscellaneaus Settings");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Brackets"));

        _chkAVMBracketsRounded.setText("Rounded");
        _chkAVMBracketsRounded.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _chkAVMBracketsRoundedActionPerformed(evt);
            }
        });

        jLabel1.setText("Color:");

        jLabel2.setText("Edge Width:");

        _spAVMEdgeLength.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        _spAVMEdgeLength.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                _spAVMEdgeLengthStateChanged(evt);
            }
        });

        _colAVMEdge.setBackground(new java.awt.Color(51, 204, 0));
        _colAVMEdge.setText(".");
        _colAVMEdge.setOpaque(true);
        _colAVMEdge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _colAVMEdgeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_chkAVMBracketsRounded)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(_spAVMEdgeLength, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(_colAVMEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(_chkAVMBracketsRounded)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(_spAVMEdgeLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(_colAVMEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        _chkAVMLayoutCompact.setText("Compact Layout");
        _chkAVMLayoutCompact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _chkAVMLayoutCompactActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_chkAVMLayoutCompact)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(_chkAVMLayoutCompact)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("AVM", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Minimum Node Distance"));

        jLabel3.setText("Horizontal:");

        jLabel4.setText("Vertical:");

        _spTreeMinHDist.setModel(new javax.swing.SpinnerNumberModel(0, 0, 300, 5));
        _spTreeMinHDist.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                _spTreeMinHDistStateChanged(evt);
            }
        });

        _spTreeMinVDist.setModel(new javax.swing.SpinnerNumberModel(0, 0, 300, 5));
        _spTreeMinVDist.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                _spTreeMinVDistStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_spTreeMinHDist, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_spTreeMinVDist, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(_spTreeMinHDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(_spTreeMinVDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel5.setText("Edge Color:");

        _colTreeEdge.setBackground(new java.awt.Color(51, 204, 0));
        _colTreeEdge.setText(".");
        _colTreeEdge.setOpaque(true);
        _colTreeEdge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _colTreeEdgeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(_colTreeEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(_colTreeEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tree", jPanel2);

        jLabel6.setText("Background Color:");

        jLabel7.setText("Selected Block Color:");

        _colBlockPanel.setBackground(new java.awt.Color(51, 204, 0));
        _colBlockPanel.setText(".");
        _colBlockPanel.setOpaque(true);
        _colBlockPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _colBlockPanelMouseClicked(evt);
            }
        });

        _colSelectedBlock.setBackground(new java.awt.Color(51, 204, 0));
        _colSelectedBlock.setText(".");
        _colSelectedBlock.setOpaque(true);
        _colSelectedBlock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _colSelectedBlockMouseClicked(evt);
            }
        });

        jLabel8.setText("Margins:");

        _spMargins.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 5));
        _spMargins.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                _spMarginsStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_spMargins, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_colBlockPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_colSelectedBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(_colBlockPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(_colSelectedBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(_spMargins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Panel", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void _colAVMEdgeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__colAVMEdgeMouseClicked
        if (!_isInitializing) return;
        if (!setColor(_colAVMEdge)) return;
        _style.setAVMBracketColor(_colAVMEdge.getBackground());
        _style.fireStyleChanged();
}//GEN-LAST:event__colAVMEdgeMouseClicked

    private void _colTreeEdgeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__colTreeEdgeMouseClicked
        if (!_isInitializing) return;
        if (!setColor(_colTreeEdge)) return;
        _style.setTreeEdgeColor(_colTreeEdge.getBackground());
        _style.fireStyleChanged();
}//GEN-LAST:event__colTreeEdgeMouseClicked

    private void _colBlockPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__colBlockPanelMouseClicked
        if (!_isInitializing) return;
        if (!setColor(_colBlockPanel)) return;
        _style.setBackgroundColor(_colBlockPanel.getBackground());
        _style.fireStyleChanged();
}//GEN-LAST:event__colBlockPanelMouseClicked

    private void _colSelectedBlockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__colSelectedBlockMouseClicked
        if (!_isInitializing) return;
        if (!setColor(_colSelectedBlock)) return;
        _style.setSelectedBlockColor(_colSelectedBlock.getBackground());
        _style.fireStyleChanged();
}//GEN-LAST:event__colSelectedBlockMouseClicked

    private void _spAVMEdgeLengthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event__spAVMEdgeLengthStateChanged
        if (!_isInitializing) return;
        _style.setAVMBracketEdgeLength((Integer)_spAVMEdgeLength.getValue());
        _style.fireStyleChanged();
    }//GEN-LAST:event__spAVMEdgeLengthStateChanged

    private void _spTreeMinHDistStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event__spTreeMinHDistStateChanged
        if (!_isInitializing) return;
        _style.setMinTreeNodesHorizontalDistance((Integer)_spTreeMinHDist.getValue());
        _style.fireStyleChanged();
    }//GEN-LAST:event__spTreeMinHDistStateChanged

    private void _spTreeMinVDistStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event__spTreeMinVDistStateChanged
        if (!_isInitializing) return;
        _style.setMinTreeNodesVerticalDistance((Integer)_spTreeMinVDist.getValue());
        _style.fireStyleChanged();
    }//GEN-LAST:event__spTreeMinVDistStateChanged

    private void _spMarginsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event__spMarginsStateChanged
        if (!_isInitializing) return;
        _style.setMargin((Integer)_spMargins.getValue());
        _style.fireStyleChanged();
    }//GEN-LAST:event__spMarginsStateChanged

    private void _chkAVMBracketsRoundedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__chkAVMBracketsRoundedActionPerformed
        if (!_isInitializing) return;
        _style.setAVMBracketRounded(_chkAVMBracketsRounded.isSelected());
        _style.fireStyleChanged();
    }//GEN-LAST:event__chkAVMBracketsRoundedActionPerformed

    private void _chkAVMLayoutCompactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__chkAVMLayoutCompactActionPerformed
        if (!_isInitializing) return;
        _style.setAVMLayoutCompact(_chkAVMLayoutCompact.isSelected());
        _style.fireStyleChanged();
    }//GEN-LAST:event__chkAVMLayoutCompactActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox _chkAVMBracketsRounded;
    private javax.swing.JCheckBox _chkAVMLayoutCompact;
    private javax.swing.JLabel _colAVMEdge;
    private javax.swing.JLabel _colBlockPanel;
    private javax.swing.JLabel _colSelectedBlock;
    private javax.swing.JLabel _colTreeEdge;
    private javax.swing.JSpinner _spAVMEdgeLength;
    private javax.swing.JSpinner _spMargins;
    private javax.swing.JSpinner _spTreeMinHDist;
    private javax.swing.JSpinner _spTreeMinVDist;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    
}