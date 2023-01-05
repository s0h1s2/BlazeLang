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

import java.util.List;

import blaze.IVisitor;

public class CallExpr extends Expression {
    public String name;
    public List<Expression> args;

    public CallExpr(String name, List<Expression> args) {
        this.name = name;
        this.args = args;
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
