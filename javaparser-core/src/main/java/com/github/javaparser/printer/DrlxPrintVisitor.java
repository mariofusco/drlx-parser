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

package com.github.javaparser.printer;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.drlx.OOPathChunk;
import com.github.javaparser.ast.drlx.OOPathExpr;
import com.github.javaparser.ast.drlx.RuleBody;
import com.github.javaparser.ast.drlx.RuleDeclaration;
import com.github.javaparser.ast.drlx.expr.CommaSeparatedMethodCallExpr;
import com.github.javaparser.ast.drlx.expr.DrlxExpression;
import com.github.javaparser.ast.drlx.expr.HalfBinaryExpr;
import com.github.javaparser.ast.drlx.expr.HalfPointFreeExpr;
import com.github.javaparser.ast.drlx.expr.InlineCastExpr;
import com.github.javaparser.ast.drlx.expr.NullSafeFieldAccessExpr;
import com.github.javaparser.ast.drlx.expr.NullSafeMethodCallExpr;
import com.github.javaparser.ast.drlx.expr.PointFreeExpr;
import com.github.javaparser.ast.drlx.expr.TemporalChunkExpr;
import com.github.javaparser.ast.drlx.expr.TemporalLiteralChunkExpr;
import com.github.javaparser.ast.drlx.expr.TemporalLiteralExpr;
import com.github.javaparser.ast.drlx.expr.TemporalLiteralInfiniteChunkExpr;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.BigDecimalLiteralExpr;
import com.github.javaparser.ast.expr.BigIntegerLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.visitor.AbstractVoidRuleVisitor;

public class DrlxPrintVisitor extends AbstractVoidRuleVisitor<Void, PrettyPrintVisitor> {

    public DrlxPrintVisitor(PrettyPrintVisitor visitor) {
        super( visitor );
    }

    @Override
    public void visit( RuleDeclaration n, Void arg ) {
        visitor.printComment(n.getComment(), arg);

        for (AnnotationExpr ae : n.getAnnotations()) {
            ae.accept(visitor, arg);
            visitor.printer.print(" ");
        }

        visitor.printer.print("rule ");
        n.getName().accept(visitor, arg);
        visitor.printer.println(" {");
        n.getRuleBody().accept(visitor, arg);
        visitor.printer.println("}");
    }

    @Override
    public void visit( RuleBody ruleBody, Void arg ) {
    }

    @Override
    public void visit( InlineCastExpr inlineCastExpr, Void arg ) {
        visitor.printComment(inlineCastExpr.getComment(), arg);
        inlineCastExpr.getExpression().accept( visitor, arg );
        visitor.printer.print( "#" );
        inlineCastExpr.getType().accept( visitor, arg );
    }

    @Override
    public void visit( NullSafeFieldAccessExpr nullSafeFieldAccessExpr, Void arg ) {
        visitor.printComment(nullSafeFieldAccessExpr.getComment(), arg);
        nullSafeFieldAccessExpr.getScope().accept( visitor, arg );
        visitor.printer.print( "!." );
        nullSafeFieldAccessExpr.getName().accept( visitor, arg );
    }

    @Override
    public void visit( NullSafeMethodCallExpr nullSafeMethodCallExpr, Void arg ) {
        visitor.printComment(nullSafeMethodCallExpr.getComment(), arg);
        if (nullSafeMethodCallExpr.getScope().isPresent()) {
            nullSafeMethodCallExpr.getScope().get().accept( visitor, arg );
            visitor.printer.print("!.");
        }
        visitor.printTypeArgs(nullSafeMethodCallExpr, arg);
        nullSafeMethodCallExpr.getName().accept( visitor, arg );
        visitor.printArguments(nullSafeMethodCallExpr.getArguments(), arg);
    }

    @Override
    public void visit( PointFreeExpr pointFreeExpr, Void arg ) {
        visitor.printComment(pointFreeExpr.getComment(), arg);
        pointFreeExpr.getLeft().accept( visitor, arg );
        if(pointFreeExpr.isNegated()) {
            visitor.printer.print(" not");
        }
        visitor.printer.print(" ");
        pointFreeExpr.getOperator().accept( visitor, arg );
        if (pointFreeExpr.getArg1() != null) {
            visitor.printer.print("[");
            pointFreeExpr.getArg1().accept( visitor, arg );
            if (pointFreeExpr.getArg2() != null) {
                visitor.printer.print(",");
                pointFreeExpr.getArg2().accept( visitor, arg );
            }
            if (pointFreeExpr.getArg3() != null) {
                visitor.printer.print(",");
                pointFreeExpr.getArg3().accept( visitor, arg );
            }
            if (pointFreeExpr.getArg4() != null) {
                visitor.printer.print(",");
                pointFreeExpr.getArg4().accept( visitor, arg );
            }
            visitor.printer.print("]");
        }
        visitor.printer.print(" ");
        NodeList<Expression> rightExprs = pointFreeExpr.getRight();
        if (rightExprs.size() == 1) {
            rightExprs.get(0).accept( visitor, arg );
        } else {
            visitor.printer.print("(");
            if(rightExprs.isNonEmpty()) {
                rightExprs.get(0).accept(visitor, arg);
            }
            for (int i = 1; i < rightExprs.size(); i++) {
                visitor.printer.print(", ");
                rightExprs.get(i).accept( visitor, arg );
            }
            visitor.printer.print(")");
        }
    }

    @Override
    public void visit(TemporalLiteralExpr temporalLiteralExpr, Void arg) {
        visitor.printComment(temporalLiteralExpr.getComment(), arg);
        NodeList<TemporalChunkExpr> chunks = temporalLiteralExpr.getChunks();
        for (TemporalChunkExpr c : chunks) {
            c.accept(visitor, arg);
        }
    }

    @Override
    public void visit(TemporalLiteralChunkExpr temporalLiteralExpr, Void arg) {
        visitor.printComment(temporalLiteralExpr.getComment(), arg);
        visitor.printer.print("" + temporalLiteralExpr.getValue());
        switch (temporalLiteralExpr.getTimeUnit()) {
            case MILLISECONDS:
                visitor.printer.print("ms");
                break;
            case SECONDS:
                visitor.printer.print("s");
                break;
            case MINUTES:
                visitor.printer.print("m");
                break;
            case HOURS:
                visitor.printer.print("h");
                break;
            case DAYS:
                visitor.printer.print("d");
                break;
        }
    }

    @Override
    public void visit(TemporalLiteralInfiniteChunkExpr temporalLiteralInfiniteChunkExpr, Void arg) {
        visitor.printer.print("*");
    }

    @Override
    public void visit( DrlxExpression expr, Void arg ) {
        if (expr.getBind() != null) {
            expr.getBind().accept( visitor, arg );
            visitor.printer.print( " : " );
        }
        expr.getExpr().accept(visitor, arg);
    }

    @Override
    public void visit(OOPathExpr oopathExpr, Void arg ) {
        visitor.printComment(oopathExpr.getComment(), arg);
        visitor.printer.print("/");
        NodeList<OOPathChunk> chunks = oopathExpr.getChunks();
        for (int i = 0; i <  chunks.size(); i++) {
            final OOPathChunk chunk = chunks.get(i);
            chunk.accept(visitor, arg);
            visitor.printer.print(chunk.getField().toString());

            List<Expression> condition = chunk.getConditions();
            final Iterator<Expression> iterator = condition.iterator();
            if (!condition.isEmpty()) {
                visitor.printer.print("[");
                Expression first = iterator.next();
                first.accept(visitor, arg);
                while(iterator.hasNext()) {
                    visitor.printer.print(",");
                    iterator.next().accept(visitor, arg);
                }
                visitor.printer.print("]");
            }

            if(i != chunks.size() - 1) { // Avoid printing last /
                visitor.printer.print("/");
            }
        }
    }

    @Override
    public void visit(HalfBinaryExpr n, Void arg) {
        visitor.printComment(n.getComment(), arg);
        visitor.printer.print(n.getOperator().asString());
        visitor.printer.print(" ");
        n.getRight().accept(visitor, arg);
    }

    @Override
    public void visit(HalfPointFreeExpr pointFreeExpr, Void arg ) {
        visitor.printComment(pointFreeExpr.getComment(), arg);
        if(pointFreeExpr.isNegated()) {
            visitor.printer.print("not ");
        }
        pointFreeExpr.getOperator().accept( visitor, arg );
        if (pointFreeExpr.getArg1() != null) {
            visitor.printer.print("[");
            pointFreeExpr.getArg1().accept( visitor, arg );
            if (pointFreeExpr.getArg2() != null) {
                visitor.printer.print(",");
                pointFreeExpr.getArg2().accept( visitor, arg );
            }
            if (pointFreeExpr.getArg3() != null) {
                visitor.printer.print(",");
                pointFreeExpr.getArg3().accept( visitor, arg );
            }
            if (pointFreeExpr.getArg4() != null) {
                visitor.printer.print(",");
                pointFreeExpr.getArg4().accept( visitor, arg );
            }
            visitor.printer.print("]");
        }
        visitor.printer.print(" ");
        NodeList<Expression> rightExprs = pointFreeExpr.getRight();
        if (rightExprs.size() == 1) {
            rightExprs.get(0).accept( visitor, arg );
        } else {
            visitor.printer.print("(");
            rightExprs.get(0).accept( visitor, arg );
            for (int i = 1; i < rightExprs.size(); i++) {
                visitor.printer.print(", ");
                rightExprs.get(i).accept( visitor, arg );
            }
            visitor.printer.print(")");
        }
    }

    public void visit(BigDecimalLiteralExpr bigDecimalLiteralExpr, Void arg) {
        visitor.printer.print(bigDecimalLiteralExpr.asBigDecimal().toString());
        visitor.printer.print("B");
    }

    public void visit(BigIntegerLiteralExpr bigIntegerLiteralExpr, Void arg) {
        visitor.printer.print(bigIntegerLiteralExpr.asBigInteger().toString());
        visitor.printer.print("I");
    }

    public void visit(CommaSeparatedMethodCallExpr commaSeparatedMethodCallExpr, Void arg) {
        String expressionWithComma = commaSeparatedMethodCallExpr.getExpressions()
                .stream()
                .map(Node::toString)
                .collect(Collectors.joining(", "));

        visitor.printer.print(expressionWithComma);
        visitor.printer.print(";");
    }


}
