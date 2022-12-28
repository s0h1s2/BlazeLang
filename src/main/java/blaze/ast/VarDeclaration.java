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

public class VarDeclaration extends Declaration {
    public Expression init;
    public Type type;
    public String name;

    public VarDeclaration(String name, Expression expr, Type type) {
        this.init = expr;
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
