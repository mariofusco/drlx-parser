/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2016 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import static com.github.javaparser.utils.Utils.assertNotNull;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.NameExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import javax.annotation.Generated;
import com.github.javaparser.TokenRange;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.resolution.types.ResolvedUnionType;
import java.util.function.Consumer;
import java.util.Optional;

/**
 * Whenever a SimpleName is used in an expression, it is wrapped in NameExpr.
 * <br/>In <code>int x = a + 3;</code> a is a SimpleName inside a NameExpr.
 *
 * @author Julio Vilmar Gesser
 */
public final class NameExpr extends Expression implements NodeWithSimpleName<NameExpr>, Resolvable<ResolvedValueDeclaration> {

    private SimpleName name;

    private int backReferencesCount = 0;

    public NameExpr() {
        this(null, new SimpleName(), 0);
    }

    public NameExpr(final String name) {
        this(null, new SimpleName(name), 0);
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public int getBackReferencesCount() {
        return backReferencesCount;
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public NameExpr setBackReferencesCount(final int backReferencesCount) {
        if (backReferencesCount == this.backReferencesCount) {
            return (NameExpr) this;
        }
        notifyPropertyChange(ObservableProperty.BACK_REFERENCES_COUNT, this.backReferencesCount, backReferencesCount);
        this.backReferencesCount = backReferencesCount;
        return this;
    }

    @AllFieldsConstructor
    public NameExpr(final SimpleName name, int backReferencesCount) {
        this(name.getTokenRange().orElse(null), name, backReferencesCount);
        setRange(name.getRange().orElse(null));
    }

    /**
     * This constructor is used by the parser and is considered private.
     */
    @Generated("com.github.javaparser.generator.core.node.MainConstructorGenerator")
    public NameExpr(TokenRange tokenRange, SimpleName name, int backReferencesCount) {
        super(tokenRange);
        setName(name);
        customInitialization();
        this.backReferencesCount = backReferencesCount;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.AcceptGenerator")
    public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
        return v.visit(this, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.AcceptGenerator")
    public <A> void accept(final VoidVisitor<A> v, final A arg) {
        v.visit(this, arg);
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public SimpleName getName() {
        return name;
    }

    @Generated("com.github.javaparser.generator.core.node.PropertyGenerator")
    public NameExpr setName(final SimpleName name) {
        assertNotNull(name);
        if (name == this.name) {
            return (NameExpr) this;
        }
        notifyPropertyChange(ObservableProperty.NAME, this.name, name);
        if (this.name != null)
            this.name.setParentNode(null);
        this.name = name;
        setAsParentNodeOf(name);
        return this;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.RemoveMethodGenerator")
    public boolean remove(Node node) {
        if (node == null)
            return false;
        return super.remove(node);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.CloneGenerator")
    public NameExpr clone() {
        return (NameExpr) accept(new CloneVisitor(), null);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.GetMetaModelGenerator")
    public NameExprMetaModel getMetaModel() {
        return JavaParserMetaModel.nameExprMetaModel;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.ReplaceMethodGenerator")
    public boolean replace(Node node, Node replacementNode) {
        if (node == null)
            return false;
        if (node == name) {
            setName((SimpleName) replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public boolean isNameExpr() {
        return true;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public NameExpr asNameExpr() {
        return this;
    }

    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public void ifNameExpr(Consumer<NameExpr> action) {
        action.accept(this);
    }

    @Override
    public ResolvedValueDeclaration resolve() {
        return getSymbolResolver().resolveDeclaration(this, ResolvedValueDeclaration.class);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public Optional<NameExpr> toNameExpr() {
        return Optional.of(this);
    }
}
