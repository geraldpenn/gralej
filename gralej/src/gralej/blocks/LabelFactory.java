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

package gralej.blocks;

import gralej.Config;
import static gralej.util.Strings.unescapeCString;

import java.util.Map;
import java.util.TreeMap;



public class LabelFactory {
    Config _cfg;

    // list
    String LIST_LBRACKET;
    String LIST_RBRACKET;
    String LIST_SEPARATOR;
    String LIST_TAIL_SEPARATOR;
    String LRS_BLOCK_TITLE;

    Map<String, LabelStyle> _labelStyles = new TreeMap<String, LabelStyle>();

    static private LabelFactory _instance;
    
    public LabelFactory() {
        this(Config.currentConfig());
    }
    public LabelFactory(Config cfg) {
        _cfg = cfg;
        init();
    }
    
    public static LabelFactory getInstance() {
        if (_instance == null)
            _instance = new LabelFactory();
        return _instance;
    }
    
    private void init() {
        LIST_LBRACKET       = _cfg.get("block.label.list.text.left");
        LIST_RBRACKET       = _cfg.get("block.label.list.text.right");
        LIST_SEPARATOR      = _cfg.get("block.label.list.text.separator");
        LIST_TAIL_SEPARATOR = _cfg.get("block.label.list.text.tailSeparator");
        LRS_BLOCK_TITLE     = _cfg.get("block.label.lrsblock.title");

        LIST_LBRACKET = unescapeCString(LIST_LBRACKET);
        LIST_RBRACKET = unescapeCString(LIST_RBRACKET);
        
        String[] types = new String[] {
            "tag",
            "sort",
            "attribute",
            "list",
            "any",
            "species",
            "node.internal",
            "node.leaf"
        };
        
        // instantiante all label styles
        for (String type : types)
            getLabelStyle(type);
    }

    LabelStyle getLabelStyle(String type_) {
        String type = "block.label." + type_;
        LabelStyle ls = _labelStyles.get(type);
        if (ls == null) {
            ls = new LabelStyle(type_);
            initLabelStyle(ls);
            _labelStyles.put(type, ls);
        }
        return ls;
    }
    
    private void initLabelStyle(LabelStyle ls) {
        String type = "block.label." + ls.getName();
        ls.setFont(
                _cfg.getFont(type + ".fontSpec"));
        ls.setTextColor(
                _cfg.getColor(type + ".text.color"));
        ls.setTextAltColor(
                _cfg.getColor(type + ".text.colorAlt"));
        ls.setMarginTop(
                _cfg.getInt(type + ".margin.top"));
        ls.setMarginLeft(
                _cfg.getInt(type + ".margin.left"));
        ls.setMarginRight(
                _cfg.getInt(type + ".margin.right"));
        ls.setMarginBottom(
                _cfg.getInt(type + ".margin.bottom"));
        ls.setFrameThickness(
                _cfg.getInt(type + ".frame.thickness"));
        ls.setFrameColor(
                _cfg.getColor(type + ".frame.color"));
        ls.setFrameDashed(
                _cfg.getBool(type + ".frame.isDashed"));
    }
    
    void updateConfig() {
        updateConfig(_cfg);
    }
    
    void updateConfig(Config cfg) {
        for (LabelStyle s : _labelStyles.values()) {
            String styleName = s.getName();
            cfg.set("block.label." + styleName + ".fontSpec",        s.getFont());
            cfg.set("block.label." + styleName + ".text.color",      s.getTextColor());
            cfg.set("block.label." + styleName + ".text.colorAlt",   s.getTextAltColor());
            cfg.set("block.label." + styleName + ".margin.top",      s.getMarginTop());
            cfg.set("block.label." + styleName + ".margin.left",     s.getMarginLeft());
            cfg.set("block.label." + styleName + ".margin.right",    s.getMarginRight());
            cfg.set("block.label." + styleName + ".margin.bottom",   s.getMarginBottom());
            cfg.set("block.label." + styleName + ".frame.thickness", s.getFrameThickness());
            cfg.set("block.label." + styleName + ".frame.color",     s.getFrameColor());
            cfg.set("block.label." + styleName + ".frame.isDashed",  s.isFrameDashed());
        }
    }
    
    void updateSelf() {
        init();
        for (LabelStyle ls : _labelStyles.values())
            initLabelStyle(ls);
    }

    public ContentLabel createContentLabel(String text, LabelStyle style, BlockPanel panel) {
        return new ContentLabel(panel, style, text);
    }

    public Label createLabel(String text, LabelStyle style, BlockPanel panel) {
        return new Label(panel, style, text);
    }

    public ContentLabel createTagLabel(String text, BlockPanel panel) {
        return createContentLabel(text, getLabelStyle("tag"), panel);
    }
    
    public Label createUnboundVarLabel(String text, BlockPanel panel) {
        return createLabel(text, getLabelStyle("unbound"), panel);
    }

    public ContentLabel createSortLabel(String text, BlockPanel panel) {
        return createContentLabel(text, getLabelStyle("sort"), panel);
    }

    public ContentLabel createAttributeLabel(String text, BlockPanel panel) {
        return createContentLabel(text, getLabelStyle("attribute"), panel);
    }

    public Label createListLBracketLabel(BlockPanel panel) {
        return createLabel(LIST_LBRACKET, getLabelStyle("list"), panel);
    }

    public Label createListRBracketLabel(BlockPanel panel) {
        return createLabel(LIST_RBRACKET, getLabelStyle("list"), panel);
    }

    public Label createListSeparatorLabel(BlockPanel panel) {
        return createLabel(LIST_SEPARATOR, getLabelStyle("list"), panel);
    }
    
    public Label createListTailSeparatorLabel(BlockPanel panel) {
        return createLabel(
            LIST_TAIL_SEPARATOR, getLabelStyle("list"), panel);
    }

    public Label createAnyLabel(String text, BlockPanel panel) {
        return createLabel(text, getLabelStyle("any"), panel);
    }

    public Label createSpeciesLabel(String text, BlockPanel panel) {
        return createLabel(text, getLabelStyle("species"), panel);
    }

    public ContentLabel createInternalNodeLabel(String text, BlockPanel panel) {
        return createContentLabel(text, getLabelStyle("node.internal"), panel);
    }

    public ContentLabel createLeafNodeLabel(String text, BlockPanel panel) {
        return createContentLabel(text, getLabelStyle("node.leaf"), panel);
    }

    public ContentLabel createLRSBlockLabel(BlockPanel panel) {
        return createContentLabel(
                LRS_BLOCK_TITLE, getLabelStyle("lrsblock"), panel);
    }

    public Label createLRSLabel(String text, BlockPanel panel) {
        return createLabel(text, getLabelStyle("lrs"), panel);
    }

    public LRSContentLabel createLRSContentLabel(String text, BlockPanel panel) {
        return new LRSContentLabel(panel, getLabelStyle("lrs"), text);
    }

    public ContentLabel createFunctorLabel(String text, BlockPanel panel) {
        return createContentLabel(text, getLabelStyle("functor"), panel);
    }

    public Label createRelationLabel(String text, BlockPanel panel) {
        return createLabel(text, getLabelStyle("relation"), panel);
    }

    public ContentLabel createHeadingLabel(String text, BlockPanel panel) {
        return createContentLabel(text, getLabelStyle("heading"), panel);
    }

    public Label createInfixOperatorLabel(String text, BlockPanel panel) {
        return createLabel(text, getLabelStyle("infix"), panel);
    }
}
