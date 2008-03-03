/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gralej.blocks;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Martin
 */
public class BlockPanel implements StyleChangeListener {
    final static int ZOOM_DELTA = 20;
    
    Set<Integer> _expandedTags = new TreeSet<Integer>();
    RootBlock _content;
    BlockPanelStyle _style;
    int _zoom = 100;
    double _scaleFactor = 1;
    DrawingPane _canvas;
    JPanel _ui;
    boolean _autoResize;
    private Cursor _defaultCursor, _handCursor, _currentCursor;
    private ContentLabel _lastHit;
    private Block _selectedBlock;
    private Color _selectedBlockColor = new Color(100,50,230,35);
    private Stroke _dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, 
                            BasicStroke.JOIN_ROUND, 0,  new float[]{2}, 0);

    private class DrawingPane extends JPanel {
        @Override
        protected void paintComponent(Graphics g_) {
            super.paintComponent(g_);
            Graphics2D g = (Graphics2D) g_;
            AffineTransform savedTransform = null;
            if (_zoom != 100) {
                savedTransform = g.getTransform();
                g.transform(AffineTransform.getScaleInstance(
                        _scaleFactor, _scaleFactor));
            }
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            // g.drawRect(getX(), getY(), getWidth()-1, getHeight()-1);
            _content.paint(g);
            if (_selectedBlock != null) {
                final int N = 2;
                g.setColor(_selectedBlockColor);
                g.fillRect(
                        _selectedBlock.getX() - N,
                        _selectedBlock.getY() - N,
                        _selectedBlock.getWidth() + N * 2,
                        _selectedBlock.getHeight() + N * 2
                        );
                
                g.setColor(Color.BLACK);
                g.setStroke(_dashedStroke);
                g.drawRect(
                        _selectedBlock.getX() - N,
                        _selectedBlock.getY() - N,
                        _selectedBlock.getWidth() + N * 2,
                        _selectedBlock.getHeight() + N * 2
                        );
            }
            if (savedTransform != null)
                g.setTransform(savedTransform);
        }
    }
    
    public BlockPanel(gralej.om.IVisitable contentModel) {
        this(contentModel, BlockPanelStyle.getInstance());
    }
    
    public BlockPanel(gralej.om.IVisitable contentModel, BlockPanelStyle style) {
        _style = style;
        _style.addStyleChangeListener(this);
        
        final BlockPanel thisPanel = this;
        
        _ui = new JPanel() {
            @Override
            public void removeNotify() {
                super.removeNotify();
                _style.removeStyleChangeListener(thisPanel);
            }
        };
        _ui.setLayout(new BorderLayout());
        
        _canvas = new DrawingPane();
        _canvas.setBackground(_style.getBackgroundColor());
        
        JScrollPane scrollPane = new JScrollPane(_canvas);
        int vUnitIncrement = Config.getInt(
                "panel.scrollbar.vertical.unitIncrement");
        scrollPane.getVerticalScrollBar().setUnitIncrement(vUnitIncrement);
        _ui.add(scrollPane, BorderLayout.CENTER);
        
        _autoResize = Boolean.parseBoolean(Config.get("behavior.alwaysfitsize"));
        
        _canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!_ui.hasFocus())
                    _ui.requestFocus(true);
                onMousePressed(e);
            }
        });
        _canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                onMouseMoved(e);
            }
        });
        
        _content = new RootBlock(
                this,
                new BlockCreator(this).createBlock(contentModel)
                );
        _content.update();
    }
    
    void updateSelf() {
        _content.setPosition(_style.getMargin(), _style.getMargin());

        Dimension prefSize = getScaledSize();

        _canvas.setPreferredSize(prefSize);
        _canvas.setSize(prefSize);
        _canvas.revalidate();
        _canvas.repaint();
        
        if (_autoResize)
            pack(_ui.getParent());
    }
    
    private static void pack(Component c) {
        if (c == null)
            return;
        if (c instanceof JFrame)
            ((JFrame) c).pack();
        else if (c instanceof JInternalFrame)
            ((JInternalFrame) c).pack();
        else
            pack(c.getParent());
    }
    
    protected void setSelectedBlock(Block b) {
        if (_selectedBlock == b)
            return;
        _selectedBlock = b;
        _canvas.repaint();
    }
    
    public void setAutoResize(boolean autoResize) {
        _autoResize = autoResize;
    }
    
    public void setZoom(int zoom) {
        if (zoom <= 0 || zoom == _zoom)
            return;
        _zoom = zoom;
        _scaleFactor = _zoom / 100.0;
        updateSelf();
    }
    
    public int getZoom() {
        return _zoom;
    }
    
    public void zoomIn() {
        setZoom(getZoom() + ZOOM_DELTA);
    }

    public void zoomOut() {
        setZoom(getZoom() - ZOOM_DELTA);
    }
    
    public Dimension getScaledSize() {
        Dimension size = new Dimension(
                scale(_content.getWidth() + 2 * _style.getMargin()),
                scale(_content.getHeight() + 2 * _style.getMargin()));
        return size;
    }
    
    private int scale(int n) {
        return (int) (n * _scaleFactor);
    }
    
    private int unscale(int n) {
        return (int) (n / _scaleFactor);
    }
    
    public void styleChanged(Object sender) {
        _content.update();
        _canvas.repaint();
    }

    public JPanel getCanvas() {
        return _canvas;
    }
    
    public JPanel getUI() {
        return _ui;
    }
    
    public BlockPanelStyle getStyle() {
        return _style;
    }
    
    public RootBlock getContent() {
        return _content;
    }
    
    Set<Integer> getExpandedTags() {
        return _expandedTags;
    }
    
    protected void onMousePressed(MouseEvent e) {
        // log(e);
        int x = unscale(e.getX());
        int y = unscale(e.getY());
        ContentLabel target = findContainingContentLabel(x, y);
        if (target != null) {
            target.flipContentVisibility();
            updateCursorForPoint(x, y);
        }
    }
    
    protected void onMouseMoved(MouseEvent ev) {
        if (ev.getID() != MouseEvent.MOUSE_MOVED)
            return;
        updateCursorForPoint(unscale(ev.getX()), unscale(ev.getY()));
    }

    private static boolean blockContainsPoint(Block block, int x, int y) {
        int X = block.getX();
        int Y = block.getY();
        int W = block.getWidth();
        int H = block.getHeight();

        return (x >= X) && (x < X + W) && (y >= Y) && (y < Y + H);
    }
    
    protected static Block findContainingBlock(Block block, int x, int y) {
        for (Block child : block.getChildren()) {
            if (child.isVisible() && blockContainsPoint(child, x, y)) {
                return child.isLeaf() ? child : findContainingBlock(child, x, y);
            }
        }
        return block;
    }
    
    ContentLabel findContainingContentLabel(int x, int y) {
        if (_lastHit != null && _lastHit.isVisible() && blockContainsPoint(_lastHit, x, y))
            return _lastHit;
        Block b = findContainingBlock(_content, x, y);
        if (b != null && b instanceof ContentLabel)
            _lastHit = (ContentLabel) b;
        else
            _lastHit = null;
        return _lastHit;
    }
    
    // cursor handling
    private void initCursors() {
        _defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        _handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        _currentCursor = _defaultCursor;
    }

    private void updateCursorForPoint(int x, int y) {
        if (_defaultCursor == null)
            initCursors();

        Block target = findContainingContentLabel(x, y);

        if (target == null)
            updateCursor(_defaultCursor);
        else
            updateCursor(_handCursor);
    }

    void updateCursor(Cursor newCursor) {
        if (newCursor == _currentCursor)
            return;
        _canvas.setCursor(newCursor);
        _currentCursor = newCursor;
    }
}