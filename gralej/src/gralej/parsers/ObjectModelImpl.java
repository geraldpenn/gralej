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

package gralej.parsers;

import gralej.om.*;
import java.util.Arrays;

class OM {
    static class Flags {
        final static int HIDDEN = 1;
        final static int DIFFERENT = 2;
        final static int STRUCKOUT = 4;
        final static int MULTILINE = 8;
        final static int EXPANDED = 16;
        
        int flags;

        boolean isHidden() {
            return (flags & HIDDEN) != 0;
        }

        boolean isDifferent() {
            return (flags & DIFFERENT) != 0;
        }

        boolean isStruckout() {
            return (flags & STRUCKOUT) != 0;
        }

        boolean isMultiline() {
            return (flags & MULTILINE) != 0;
        }

        boolean isExpanded() {
            return (flags & EXPANDED) != 0;
        }

        void setHidden() {
            flags |= HIDDEN;
        }

        void setDifferent(boolean flag) {
            if (flag)
                flags |= DIFFERENT;
            else
                flags &= ~DIFFERENT;
        }
        
        void setDifferent() {
            flags |= DIFFERENT;
        }

        void setStruckout() {
            flags |= STRUCKOUT;
        }

        void setMultiline() {
            flags |= MULTILINE;
        }

        void setExpanded() {
            flags |= EXPANDED;
        }
    }

    final static Flags DEFAULT_FLAGS = new Flags();

    static abstract class Entity implements IEntity {
        Flags _flags;

        Entity(Flags flags) {
            _flags = flags;
            if (flags.flags == 0)
                _flags = DEFAULT_FLAGS;
        }

        public boolean isHidden() {
            return _flags.isHidden();
        }

        public boolean isDifferent() {
            return _flags.isDifferent();
        }
        
        public void setDifferent(boolean flag) {
            if (_flags == DEFAULT_FLAGS)
                _flags = new OM.Flags();
            _flags.setDifferent(flag);
        }

        public boolean isStruckout() {
            return _flags.isStruckout();
        }

        public boolean isMultiline() {
            return _flags.isMultiline();
        }

        public boolean isExpanded() {
            return _flags.isExpanded();
        }
    }

    static class FeatVal extends Entity implements IFeatureValuePair {
        String _f;
        IEntity _v;

        FeatVal(Flags flags, String f, IEntity v) {
            super(flags);
            _f = f;
            _v = v;
        }

        public String feature() {
            return _f;
        }

        public IEntity value() {
            return _v;
        }

        public String text() {
            return _f;
        }

        public void accept(IVisitor v) {
            v.visit(this);
        }

        public void setFeature(String feat) {
            _f = feat;
        }

        public void setValue(IEntity value) {
            _v = value;
        }
    }

    static class List extends Entity implements IList {
        java.util.List<IEntity> _ls;
        IEntity _tail;

        List(Flags flags, java.util.List<IEntity> ls, IEntity tail) {
            super(flags);
            _ls = ls;
            _tail = tail;
        }

        public Iterable<IEntity> elements() {
            return _ls;
        }
        
        public IEntity tail() {
            return _tail;
        }

        public String text() {
            return "<" + _ls.size() + ">";
        }

        public void accept(IVisitor v) {
            v.visit(this);
        }

        public void setTail(IEntity tail) {
            _tail = tail;
        }

        public void append(IEntity element) {
            _ls.add(element);
        }

        public void clear() {
            _ls.clear();
        }
    }

    static class Tag extends Entity implements ITag {
        int _number;
        IEntity _target;

        Tag(Flags flags, int number) {
            super(flags);
            _number = number;
        }
        Tag(int number) {
            this(DEFAULT_FLAGS, number);
        }

        public int number() {
            return _number;
        }

        public IEntity target() {
            return _target;
        }

        public void setTarget(IEntity target) {
            _target = target;
        }

        public String text() {
            return "[" + Integer.toString(_number) + "]";
        }

        public void accept(IVisitor v) {
            v.visit(this);
        }

        public void setNumber(int number) {
            _number = number;
        }
    }

    static class Any extends Entity implements IAny {
        String _value;

        Any(Flags flags, String value) {
            super(flags);
            _value = value;
        }

        public String value() {
            return _value;
        }

        public String text() {
            return _value;
        }

        public void accept(IVisitor v) {
            v.visit(this);
        }

        public void setValue(String value) {
            _value = value;
        }
    }
    
    static class Type extends Entity implements IType {
        String _typeName;
        Type(Flags flags, String typeName) {
            super(flags);
            _typeName = typeName;
        }
        public String typeName() {
            return _typeName;
        }
        public String text() {
            return _typeName;
        }
        public void accept(IVisitor v) {
            v.visit(this);
        }

        public void setTypeName(String typeName) {
            _typeName = typeName;
        }
    }

    static class TFS extends Entity implements ITypedFeatureStructure {
        IType _type;
        java.util.List<IFeatureValuePair> _featVals;

        TFS(Flags flags, IType type,
                java.util.List<IFeatureValuePair> featVals) {
            super(flags);
            _type = type;
            _featVals = featVals;
        }

        public String typeName() {
            if (_type != null)
                return _type.typeName();
            return null;
        }
        
        public IType type() {
            return _type;
        }

        public java.util.List<IFeatureValuePair> featureValuePairs() {
            return _featVals;
        }

        public boolean isSpecies() {
            return _featVals.isEmpty();
        }

        public String text() {
            if (_type == null)
                return "typeless";
            return _type.text();
        }

        public void accept(IVisitor v) {
            v.visit(this);
        }

        public void setType(IType type) {
            _type = type;
        }

        public void addFeatureValue(IFeatureValuePair featVal) {
            _featVals.add(featVal);
        }

        public void clear() {
            _featVals.clear();
        }
    }

    static class Tree extends Entity implements ITree {
        String _label;
        IEntity _content;
        java.util.List<ITree> _children;

        Tree(String label, java.util.List<ITree> children) {
            super(DEFAULT_FLAGS);
            _label = label;
            _children = children;
        }

        public void setContent(IEntity content) {
            _content = content;
        }

        public String label() {
            return _label;
        }

        public IEntity content() {
            return _content;
        }

        public Iterable<ITree> children() {
            return _children;
        }

        public boolean isLeaf() {
            return _children.isEmpty();
        }

        public String text() {
            return _label;
        }

        public void accept(IVisitor v) {
            v.visit(this);
        }

        public void setLabel(String label) {
            _label = label;
        }

        public void addChild(ITree child) {
            _children.add(child);
        }

        public void clear() {
            _children.clear();
        }
    }

    static class Relation extends Entity implements IRelation {
        String _name;
        IEntity[] _args;

        Relation(Flags flags, String name,
                java.util.List<IEntity> args) {
            super(flags);
            _name = name;
            _args = new IEntity[args.size()];
            int i = 0;
            for (IEntity arg : args)
                _args[i++] = arg;
        }

        public String name() {
            return _name;
        }

        public int arity() {
            return _args.length;
        }

        public IEntity arg(int pos) {
            return _args[pos];
        }

        public Iterable<IEntity> args() {
            return Arrays.asList(_args);
        }

        public String text() {
            return _name + "/" + arity();
        }

        public void accept(IVisitor v) {
            v.visit(this);
        }

        public void setName(String name) {
            _name = name;
        }

        public void setArg(int pos, IEntity arg) {
            _args[pos] = arg;
        }
    }

    static class Table extends Entity implements ITable {
        private java.util.List<IFeatureValuePair> _rows;
        private String _heading;

        Table(String heading, java.util.List<IFeatureValuePair> rows) {
            super(DEFAULT_FLAGS);
            _heading = heading;
            _rows = rows;
        }
        public String heading() {
            return _heading;
        }
        public Iterable<IFeatureValuePair> rows() {
            return _rows;
        }
        public String text() {
            return _heading == null ? "--" : _heading;
        }
        public void accept(IVisitor v) {
            v.visit(this);
        }
    }
}
