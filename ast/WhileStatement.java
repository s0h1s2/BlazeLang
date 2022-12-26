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

public class WhileStatement extends Stmt implements IVisitable {
    public Expression condition;
    public BlockStatement block;

    public WhileStatement(Expression condition, BlockStatement block) {
        super();
        this.condition = condition;
        this.block = block;
    }

    @Override
    public void accept(IVisitor<?> visitor) {
        visitor.visit(this);
    }

}
