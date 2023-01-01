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
import blaze.types.Type;

public class Struct extends Declaration {
    public String name;
    public List<Parameter> fields;

    public Struct(String name, List<Parameter> fields) {
        this.name = name;
        this.fields = fields;
    }
    @Override
    public Object accept(IVisitor<?> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Type getType() {
        return null;
    }
}