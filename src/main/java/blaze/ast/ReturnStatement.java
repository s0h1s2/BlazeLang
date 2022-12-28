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

public class ReturnStatement extends Stmt {
    public Expression returnExpression;

    public ReturnStatement(Expression returnExpression) {
        super();
        this.returnExpression = returnExpression;
    }

    @Override
    public void accept(IVisitor<?> visitor) {
        visitor.visit(this);

    }
}