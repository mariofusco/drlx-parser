/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.javaparser.ast.drlx.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class CommaSeparatedMethodCallExpr extends Expression {

    private final NodeList<Expression> expressions;

    public CommaSeparatedMethodCallExpr(TokenRange tokenRange, NodeList<Expression> expressions) {
        super(tokenRange);
        this.expressions = expressions;
    }

    @Override
    public <R, A> R accept( GenericVisitor<R, A> v, A arg ) {
        return v.getRuleGenericVisitor().visit(this, arg);
    }

    @Override
    public <A> void accept( VoidVisitor<A> v, A arg ) {
        v.getRuleVisitor().visit( this, arg );
    }

    public NodeList<Expression> getExpressions() {
        return expressions;
    }
}
