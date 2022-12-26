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

import blaze.IVisitable;
import blaze.IVisitor;

public class IfStatement extends Stmt implements IVisitable {
    public Expression condition;
    public BlockStatement then;
    public BlockStatement els;

    public IfStatement(Expression condition, BlockStatement then, BlockStatement els) {
        super();
        this.condition = condition;
        this.then = then;
        this.els = els;
    }

    public void accept(IVisitor<?> visitor) {
        visitor.visit(this);
    }

}