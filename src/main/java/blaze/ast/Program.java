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

import blaze.IVisitable;
import blaze.IVisitor;

public class Program implements IVisitable {
    List<Declaration> declarations;

    public Program(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    public Object accept(IVisitor<?> visitor) {
        return visitor.visit(this);
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }
}
