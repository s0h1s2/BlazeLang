/*
 ** Dec 28,2022 Shkar Sardar
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

import blaze.IVisitable;
import blaze.IVisitor;

public class Ternary extends Expression implements IVisitable {
    public Expression expr;
    public Expression then;
    public Expression elseExpr;

    public Ternary(Expression expr, Expression then, Expression elseExpr) {
        super();
        this.expr = expr;
        this.then = then;
        this.elseExpr = elseExpr;
    }

    @Override
    public void accept(IVisitor<?> visitor) {
        visitor.visit(this);
    }

}
