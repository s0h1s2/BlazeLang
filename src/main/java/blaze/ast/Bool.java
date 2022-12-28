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

public class Bool extends Expression {
    private boolean value;

    public Bool(boolean value) {
        super();
        this.value = value;
    }

    @Override
    public void accept(IVisitor<?> visitor) {
        visitor.visit(this);
    }

}