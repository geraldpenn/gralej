/*
 *  $Id$
 *
 *  Author:
 *     Martin Lazarov [mlazarov at sfs.uni-tuebingen.de]
 *     
 *  This file is part of the Gralej system
 *     http://code.google.com/p/gralej/
 *
 *  Gralej is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Gralej is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package gralej.blocks.configurator;

import gralej.blocks.BlockPanelStyle;
import gralej.blocks.LabelStyle;
import gralej.util.Arrays;
import gralej.Globals;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author  Martin
 */
public class LabelStyleEditor extends javax.swing.JDialog {

    private boolean _isInitializing;
    private BlockPanelStyle _panelStyle;
    private LabelStyle _style;
    
    /** Creates new form LabelStyleEditor */
    public LabelStyleEditor(
            java.awt.Window parent, boolean modal,
            BlockPanelStyle style)
    {
        super(parent);
        setModal(modal);
        setBlockPanelStyle(style);
        initComponents();
        setLocationRelativeTo(parent);
        for (JLabel lab : Arrays.tuple(_colFrame, _colText, _colTextAlt))
            lab.setCursor(Globals.HAND_CURSOR);
        
        _buttonGroupFrameStroke.add(_rdStrokeSolid);
        _buttonGroupFrameStroke.add(_rdStrokeDashed);
        
        JComponent c = (JComponent) getContentPane();
        c.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
                KeyStroke.getKeyStroke("ESCAPE"), "cancel");
        c.getActionMap().put("cancel",
            new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });
    }
    
    public void setBlockPanelStyle(BlockPanelStyle blockStyle) {
        _panelStyle = blockStyle;
    }
    
    public void reset(LabelStyle labStyle) {
        _isInitializing = true;
        _style = labStyle;
        // 1st tab -- general
        _styleName.setText(_style.getName());
        _fontSpec.setText(fontSpec(_style.getFont()));
        setColor(_colText, _style.getTextColor());
        setColor(_colTextAlt, _style.getTextAltColor());
        
        // 2nd tab -- margins
        _spMarTop.setValue(_style.getMarginTop());
        _spMarBot.setValue(_style.getMarginBottom());
        _spMarLeft.setValue(_style.getMarginLeft());
        _spMarRight.setValue(_style.getMarginRight());
        
        // 3rd tab -- frame
        _spFrameWidth.setValue(_style.getFrameThickness());
        setColor(_colFrame, _style.getFrameColor());
        _rdStrokeDashed.setSelected(_style.isFrameDashed());
        
        _isInitializing = false;
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
    
    static enum FontStyle {
        PLAIN(Font.PLAIN),
        BOLD(Font.BOLD),
        ITALIC(Font.ITALIC),
        BOLDITALIC(Font.BOLD | Font.ITALIC);
        
        static FontStyle fromFont(Font f) {
            for (FontStyle fs : FontStyle.values())
                if (fs.style == f.getStyle())
                    return fs;
            return PLAIN;
        }
        
        final int style;
        FontStyle(int style) { this.style = style; } 
    }
    
    private static String fontSpec(Font f) {
        return f.getName() + "," + FontStyle.fromFont(f).name().toLowerCase()
                + "," + f.getSize();
    }
    
    private static Font decodeFont(String fontSpec) {
        String[] fontSpecParts = fontSpec.trim().split("\\s*,\\s*");
        if (fontSpecParts.length != 3)
            return null;
        return new Font(
                fontSpecParts[0],
                FontStyle.valueOf(fontSpecParts[1].toUpperCase()).style,
                Integer.parseInt(fontSpecParts[2])
                );
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        _buttonGroupFrameStroke = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        _styleName = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        _fontSpec = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        _colText = new javax.swing.JLabel();
        _colTextAlt = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        _spMarTop = new javax.swing.JSpinner();
        _spMarLeft = new javax.swing.JSpinner();
        _spMarRight = new javax.swing.JSpinner();
        _spMarBot = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        _spFrameWidth = new javax.swing.JSpinner();
        _colFrame = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        _rdStrokeSolid = new javax.swing.JRadioButton();
        _rdStrokeDashed = new javax.swing.JRadioButton();

        setTitle("LabelStyle Editor");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Style Name"));

        _styleName.setFont(new java.awt.Font("Tahoma", 1, 11));
        _styleName.setText("Label style name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_styleName)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(_styleName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Font:");

        _fontSpec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _fontSpecActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Colors"));

        jLabel3.setText("Text Color:");

        jLabel4.setText("Alternative Text Color:");

        _colText.setBackground(new java.awt.Color(51, 204, 0));
        _colText.setText(".");
        _colText.setOpaque(true);
        _colText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _colTextMouseClicked(evt);
            }
        });

        _colTextAlt.setBackground(new java.awt.Color(51, 204, 0));
        _colTextAlt.setText(".");
        _colTextAlt.setOpaque(true);
        _colTextAlt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _colTextAltMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(_colTextAlt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(_colText, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(_colText, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(_colTextAlt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(_fontSpec)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(_fontSpec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Basic", jPanel2);

        jLabel7.setText("Top:");

        jLabel8.setText("Right:");

        jLabel9.setText("Bottom:");

        jLabel10.setText("Left:");

        _spMarTop.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        _spMarTop.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                _spMarTopStateChanged(evt);
            }
        });

        _spMarLeft.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        _spMarLeft.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                _spMarLeftStateChanged(evt);
            }
        });

        _spMarRight.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        _spMarRight.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                _spMarRightStateChanged(evt);
            }
        });

        _spMarBot.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        _spMarBot.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                _spMarBotStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_spMarLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_spMarRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_spMarBot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_spMarTop, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {_spMarBot, _spMarLeft, _spMarRight, _spMarTop});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(_spMarTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(_spMarLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(_spMarRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(_spMarBot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Margins", jPanel4);

        jLabel11.setText("Thickness:");

        jLabel12.setText("Color:");

        _spFrameWidth.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        _spFrameWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                _spFrameWidthStateChanged(evt);
            }
        });

        _colFrame.setBackground(new java.awt.Color(51, 204, 0));
        _colFrame.setText(".");
        _colFrame.setOpaque(true);
        _colFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _colFrameMouseClicked(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Stroke"));

        _rdStrokeSolid.setSelected(true);
        _rdStrokeSolid.setText("Solid");
        _rdStrokeSolid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _rdStrokeSolidActionPerformed(evt);
            }
        });

        _rdStrokeDashed.setText("Dashed");
        _rdStrokeDashed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _rdStrokeDashedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_rdStrokeSolid)
                    .addComponent(_rdStrokeDashed))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(_rdStrokeSolid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_rdStrokeDashed)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_spFrameWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_colFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(_spFrameWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(_colFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Frame", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void _colTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__colTextMouseClicked
        if (!setColor(_colText))
            return;
        _style.setTextColor(_colText.getBackground());
        _panelStyle.fireStyleChanged();
    }//GEN-LAST:event__colTextMouseClicked

    private void _colFrameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__colFrameMouseClicked
        if (!setColor(_colFrame))
            return;
        _style.setFrameColor(_colFrame.getBackground());
        _panelStyle.fireStyleChanged();
}//GEN-LAST:event__colFrameMouseClicked

    private void _fontSpecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__fontSpecActionPerformed
        String fontSpec = _fontSpec.getText();
        Font f = decodeFont(fontSpec);
        if (f == null) {
            JOptionPane.showMessageDialog(this, fontSpec
                    + "\nis not a valid font specification",
                    "Invalid Font Spec",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        _style.setFont(f);
        _panelStyle.fireStyleChanged();
    }//GEN-LAST:event__fontSpecActionPerformed

    private void _spMarTopStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event__spMarTopStateChanged
        if (_isInitializing) return;
        _style.setMarginTop((Integer)_spMarTop.getValue());
        _panelStyle.fireStyleChanged();
    }//GEN-LAST:event__spMarTopStateChanged

    private void _spMarLeftStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event__spMarLeftStateChanged
        if (_isInitializing) return;
        _style.setMarginLeft((Integer)_spMarLeft.getValue());
        _panelStyle.fireStyleChanged();
    }//GEN-LAST:event__spMarLeftStateChanged

    private void _spMarRightStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event__spMarRightStateChanged
        if (_isInitializing) return;
        _style.setMarginRight((Integer)_spMarRight.getValue());
        _panelStyle.fireStyleChanged();
    }//GEN-LAST:event__spMarRightStateChanged

    private void _spMarBotStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event__spMarBotStateChanged
        if (_isInitializing) return;
        _style.setMarginBottom((Integer)_spMarBot.getValue());
        _panelStyle.fireStyleChanged();
    }//GEN-LAST:event__spMarBotStateChanged

    private void _spFrameWidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event__spFrameWidthStateChanged
        if (_isInitializing) return;
        _style.setFrameThickness((Integer)_spFrameWidth.getValue());
        _panelStyle.fireStyleChanged();
    }//GEN-LAST:event__spFrameWidthStateChanged

    private void _colTextAltMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__colTextAltMouseClicked
        if (!setColor(_colTextAlt))
            return;
        _style.setTextAltColor(_colTextAlt.getBackground());
        _panelStyle.fireStyleChanged();
    }//GEN-LAST:event__colTextAltMouseClicked

    private void _rdStrokeDashedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__rdStrokeDashedActionPerformed
        if (_isInitializing) return;
        _style.setFrameDashed(true);
        _panelStyle.fireStyleChanged();
    }//GEN-LAST:event__rdStrokeDashedActionPerformed

    private void _rdStrokeSolidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__rdStrokeSolidActionPerformed
        if (_isInitializing) return;
        _style.setFrameDashed(false);
        _panelStyle.fireStyleChanged();
    }//GEN-LAST:event__rdStrokeSolidActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup _buttonGroupFrameStroke;
    private javax.swing.JLabel _colFrame;
    private javax.swing.JLabel _colText;
    private javax.swing.JLabel _colTextAlt;
    private javax.swing.JTextField _fontSpec;
    private javax.swing.JRadioButton _rdStrokeDashed;
    private javax.swing.JRadioButton _rdStrokeSolid;
    private javax.swing.JSpinner _spFrameWidth;
    private javax.swing.JSpinner _spMarBot;
    private javax.swing.JSpinner _spMarLeft;
    private javax.swing.JSpinner _spMarRight;
    private javax.swing.JSpinner _spMarTop;
    private javax.swing.JLabel _styleName;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    
}
