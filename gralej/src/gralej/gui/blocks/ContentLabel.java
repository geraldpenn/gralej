package gralej.gui.blocks;

import java.awt.Color;
import java.awt.Font;

class ContentLabel extends Label {
    private Color _altColor;
    private Color _normalColor;
    
    public ContentLabel(
        ContentOwningBlock  parent,
        String  text,
        Font    font,
        Color   textColor,
        Color   textAltColor,
        int     hm,
        int     vm,
        int     frameWidth,
        Color   frameColor
        )
    {
        super(parent, text, font, textColor, hm, vm, frameWidth, frameColor);
        
        _normalColor = textColor;
        _altColor = textAltColor;
        
        getPanel().addContentLabel(this);
    }
    
    void flipContentVisibility() {
        IBlock content = ((IContentOwner) getParentBlock()).getContent();
        if (content == null)
            return;
        
        boolean visible = !content.isVisible();
        setColor(visible ? _normalColor : _altColor);
        content.setVisible(visible);
        
        getPanel().repaint();
    }
}