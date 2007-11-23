package gralej.gui.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

class AVM extends ContentOwningBlock {
    
    final static int BRACKET_EDGE_SIZE = Config.getInt("avm.bracket.edge.length");
    final static Color BRACKET_COLOR = Color.decode(Config.get("avm.bracket.color"));
    final static boolean BRACKET_ALT_STYLE = Boolean.parseBoolean(Config.get("avm.bracket.alt.style"));

    AVM(IBlock parent) {
        super(parent);
        setLayout(new VerticalLayout());
    }
    
    void init(Label sort, AVPairList avPairs) {
        assert _children.size() == 0;
        
        addChild(sort);
        
        if (!avPairs.isEmpty()) {
            addChild(avPairs);
            setContent(avPairs);
        }
    }
    
    @Override
    public void paint(Graphics2D g) {
        super.paint(g);
        if (_children.size() < 2)
            return; // species have no brackets
        
        // paint the brackets
        g.setColor(BRACKET_COLOR);
        final int x = getX(), y = getY(), w = getWidth(), h = getHeight();
        final int e = BRACKET_EDGE_SIZE;
        
        if (BRACKET_ALT_STYLE) {
            Path2D path = new Path2D.Float();
            
            // left bracket
            // draw top down, counter-clockwise
            path.moveTo(x + e, y);
            // same first Bezier control point
            path.curveTo(x, y, x, y, x, y + e);
            path.lineTo(x, y + h - e);
            path.curveTo(x, y + h, x, y + h, x + e, y + h);
            
            g.draw(path);
            
            // right bracket
            // top down, clockwise
            path.reset();
            path.moveTo(x + w - e, y);
            path.curveTo(x + w, y, x + w, y, x + w, y + e);
            path.lineTo(x + w, y + h - e);
            path.curveTo(x + w, y + h, x + w, y + h, x + w - e, y + h);
            
            g.draw(path);
            
            return;
        }
        
        // the default [] bracket style
        
        // left
        g.drawLine(x, y, x, y + h);
        g.drawLine(x, y, x + e, y);
        g.drawLine(x, y + h, x + e, y + h);
        
        // right
        g.drawLine(x + w, y, x + w, y + h);
        g.drawLine(x + w, y, x + w - e, y);
        g.drawLine(x + w, y + h, x + w - e, y + h);
    }
}