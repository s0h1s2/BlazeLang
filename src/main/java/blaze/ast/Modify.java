/*
 ** Dec 27,2022 Shkar Sardar
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
import blaze.util.AstOperators;

public class Modify extends Expression {
    public Expression left;
    AstOperators.AstModifyOperator modifyOperator;

    public Modify(Expression left, AstOperators.AstModifyOperator modifyOperator) {
        this.left = left;
        this.modifyOperator = modifyOperator;
    }

    @Override
    public Object accept(IVisitor<?> visitor) {
        return visitor.visit(this);
    }
}
