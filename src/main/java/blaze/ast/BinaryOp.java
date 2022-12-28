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
import blaze.util.AstOperators.AstBinaryOperator;

public class BinaryOp extends Expression {
    public Expression left;
    public AstBinaryOperator op;
    public Expression right;

    public BinaryOp(Expression left, AstBinaryOperator op, Expression right) {
        super();
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public void accept(IVisitor<?> visitor) {
        visitor.visit(this);
    }

}
