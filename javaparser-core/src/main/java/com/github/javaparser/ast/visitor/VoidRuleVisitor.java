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

public interface VoidRuleVisitor<A> {

    default void visit( RuleDeclaration ruleDeclaration, A arg ) { }

    default void visit( RuleBody ruleBody, A arg ) { }

    default void visit( RulePattern rulePattern, A arg ) { }

    default void visit( DrlxExpression expr, A arg ) { }

    default void visit( OOPathExpr expr, A arg ) { }

    default void visit( OOPathChunk chunk, A arg ) { }

    default void visit( RuleConsequence ruleConsequence, A arg ) { }

    default void visit( InlineCastExpr inlineCastExpr, A arg ) { }

    default void visit( NullSafeFieldAccessExpr nullSafeFieldAccessExpr, A arg ) { }

    default void visit( NullSafeMethodCallExpr nullSafeMethodCallExpr, A arg ) { }

    default void visit( PointFreeExpr pointFreeExpr, A arg ) { }

    default void visit( TemporalLiteralExpr temporalLiteralExpr, A arg ) { }

    default void visit(TemporalLiteralChunkExpr temporalLiteralChunkExpr, A arg) {}

    default void visit(HalfBinaryExpr n, A arg) {}

    default void visit(HalfPointFreeExpr n, A arg) {}

    default void visit(BigDecimalLiteralExpr bigDecimalLiteralExpr, A arg) {}

    default void visit(BigIntegerLiteralExpr bigIntegerLiteralExpr, A arg) {}

    default void visit(TemporalLiteralInfiniteChunkExpr temporalLiteralInfiniteChunkExpr, A arg) { }

    default void visit(CommaSeparatedMethodCallExpr commaSeparatedMethodCallExpr, A arg) {  }
}
