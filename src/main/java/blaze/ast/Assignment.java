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

public class Assignment extends Expression {
    public Expression left;
    public Expression right;

    public Assignment(Expression left, Expression right) {
        this.left = left;
        this.right = right;

    }
    public Object getValue(){
        return null;
    }
    @Override
    public Object accept(IVisitor<?> visitor) {
        return visitor.visit(this);
    }

}