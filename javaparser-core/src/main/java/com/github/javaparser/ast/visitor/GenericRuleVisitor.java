package com.github.javaparser.ast.visitor;

import com.github.javaparser.ast.drlx.OOPathChunk;
import com.github.javaparser.ast.drlx.OOPathExpr;
import com.github.javaparser.ast.drlx.RuleBody;
import com.github.javaparser.ast.drlx.RuleConsequence;
import com.github.javaparser.ast.drlx.RuleDeclaration;
import com.github.javaparser.ast.drlx.RulePattern;
import com.github.javaparser.ast.drlx.expr.CommaSeparatedMethodCallExpr;
import com.github.javaparser.ast.drlx.expr.DrlxExpression;
import com.github.javaparser.ast.drlx.expr.HalfBinaryExpr;
import com.github.javaparser.ast.drlx.expr.HalfPointFreeExpr;
import com.github.javaparser.ast.drlx.expr.InlineCastExpr;
import com.github.javaparser.ast.drlx.expr.NullSafeFieldAccessExpr;
import com.github.javaparser.ast.drlx.expr.NullSafeMethodCallExpr;
import com.github.javaparser.ast.drlx.expr.PointFreeExpr;
import com.github.javaparser.ast.drlx.expr.TemporalLiteralChunkExpr;
import com.github.javaparser.ast.drlx.expr.TemporalLiteralExpr;
import com.github.javaparser.ast.drlx.expr.TemporalLiteralInfiniteChunkExpr;
import com.github.javaparser.ast.expr.BigDecimalLiteralExpr;
import com.github.javaparser.ast.expr.BigIntegerLiteralExpr;


public interface GenericRuleVisitor<R, A> {

    default R visit(RuleDeclaration ruleDeclaration, A arg ) { return null; }

    default R visit(RuleBody ruleBody, A arg ) { return null; }

    default R visit(RulePattern rulePattern, A arg ) { return null; }

    default R visit(DrlxExpression expr, A arg ) { return null; }

    default R visit(OOPathExpr expr, A arg ) { return null; }

    default R visit(OOPathChunk chunk, A arg ) { return null; }

    default R visit(RuleConsequence ruleConsequence, A arg ) { return null; }

    default R visit(InlineCastExpr inlineCastExpr, A arg ) { return null; }

    default R visit(NullSafeFieldAccessExpr nullSafeFieldAccessExpr, A arg ) { return null; }

    default R visit(NullSafeMethodCallExpr nullSafeMethodCallExpr, A arg ) { return null; }

    default R visit(PointFreeExpr pointFreeExpr, A arg ) { return null; }

    default R visit(TemporalLiteralExpr temporalLiteralExpr, A arg ) { return null; }

    default R visit(TemporalLiteralChunkExpr temporalLiteralChunkExpr, A arg) { return null; }

    default R visit(HalfBinaryExpr n, A arg) { return null; }

    default R visit(HalfPointFreeExpr n, A arg) { return null; }

    default R visit(BigDecimalLiteralExpr bigDecimalLiteralExpr, A arg) { return null; }

    default R visit(BigIntegerLiteralExpr bigIntegerLiteralExpr, A arg) { return null; }

    default R visit(TemporalLiteralInfiniteChunkExpr temporalLiteralInfiniteChunkExpr, A arg) { return null; }

    default R visit(CommaSeparatedMethodCallExpr commaSeparatedMethodCallExpr, A arg) { throw new UnsupportedOperationException(); }
}
