/*
 ** Dec 26,2022 Shkar Sardar
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 **
 */

package blaze.ast;

import blaze.IVisitor;
import blaze.util.AstOperators.AstUnaryOperator;

public class Unary extends Expression {
    AstUnaryOperator op;
    Expression right;

    public Unary(AstUnaryOperator op, Expression right) {
        super();
        this.op = op;
        this.right = right;
    }

    @Override
    public void accept(IVisitor<?> visitor) {
        visitor.visit(this);
    }
}
