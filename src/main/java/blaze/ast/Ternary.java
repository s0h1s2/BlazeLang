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

import blaze.IVisitor;

public class Ternary extends Expression {
    public Expression cond;
    public Expression then;
    public Expression elseExpr;

    public Ternary(Expression expr, Expression then, Expression elseExpr) {
        super();
        this.cond = expr;
        this.then = then;
        this.elseExpr = elseExpr;
    }

    @Override
    public Object accept(IVisitor<?> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Object getValue() {
        // TODO Auto-generated method stub
        return null;
    }

}
