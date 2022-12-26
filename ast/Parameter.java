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
import blaze.types.Type;

public class Parameter extends Declaration {
    public String name;
    public Type type;

    public Parameter(String name, Type type) {
        super();
        this.name = name;
        this.type = type;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void accept(IVisitor<?> visitor) {
        visitor.visit(this);
    }
}
