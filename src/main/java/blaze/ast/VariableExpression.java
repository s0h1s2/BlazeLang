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

public class VariableExpression extends Expression {
    public String name;

    public VariableExpression(String name) {
        this.name = name;
    }
    public Object getValue(){
        return this.name;
    }    
    @Override
    public Object accept(IVisitor<?> visitor) {
        return visitor.visit(this);
    }

}