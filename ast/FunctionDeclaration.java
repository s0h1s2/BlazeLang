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

public class FunctionDeclaration extends Declaration {
    String name;
    List<Parameter> parameters;
    BlockStatement statements;
    Type returnType;

    public FunctionDeclaration(String name, List<Parameter> parameters, BlockStatement statements, Type returnType) {
        super();
        this.name = name;
        this.parameters = parameters;
        this.statements = statements;
        this.returnType = returnType;
    }

    @Override
    public Type getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void accept(IVisitor<?> visitor) {
        visitor.visit(this);
    }

}
