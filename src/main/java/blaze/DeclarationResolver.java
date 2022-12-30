/*
 ** Dec 28,2022 Shkar Sardar
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 **
 */

package blaze;

import blaze.ast.*;
import blaze.types.FunctionType;
import blaze.types.Type;

import java.util.Stack;

public class DeclarationResolver implements IVisitor<Void> {
    final private SymbolTable table;
    final private Stack<SymbolTable> scopes;

    public DeclarationResolver(SymbolTable table) {

        this.table = table;
        this.scopes = new Stack<>();
    }

    private void enterScope() {
        if(!this.scopes.empty()){
            this.scopes.push(new SymbolTable(this.scopes.peek()));
        }
        this.scopes.push(new SymbolTable(table));
    }

    private void leaveScope() {
        this.scopes.pop();
    }

    private void declare(String name, Type type) {
        if (scopes.empty()) {
            if (!table.containDecl(name)) {
                table.define(name, type);
            } else {
                throw new Error("Can't redeclare '" + name + "'.");
            }
        } else {
            if (!scopes.peek().containDecl(name)) {
                scopes.peek().define(name, type);
            } else {
                throw new Error("Can't redeclare '" + name + "'.");
            }
        }
    }

    private void resolve(String name) {
        if (!scopes.empty()) {
            if (scopes.peek().containDecl(name)) {
                return;
            }
        }
        if (!table.containDecl(name)) {
            throw new Error("'" + name + "' not found.");
        }

    }

    @Override
    public Void visit(IfStatement ifStatement) {
        ifStatement.condition.accept(this);
        if (ifStatement.then != null) {
            ifStatement.then.accept(this);
        }
        if(ifStatement.elseIfs!=null){
            ifStatement.elseIfs.forEach((stmt)->stmt.accept(this));
        }
        if (ifStatement.els != null) {
            ifStatement.els.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(CallExpr callExpr) {
        for (Expression arg : callExpr.args) {
            arg.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Int integer) {

        return null;
    }

    @Override
    public Void visit(BinaryOp binOp) {
        binOp.left.accept(this);
        binOp.right.accept(this);
        return null;
    }

    @Override
    public Void visit(Bool bool) {
        return null;
    }

    @Override
    public Void visit(Ternary ternary) {
        ternary.expr.accept(this);
        ternary.then.accept(this);
        ternary.elseExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(VariableExpression varExpression) {
        resolve(varExpression.name);
        return null;
    }

    @Override
    public Void visit(Unary unary) {
        unary.accept(this);
        return null;
    }

    @Override
    public Void visit(FunctionDeclaration functionDeclaration) {
        if (table.containDecl(functionDeclaration.name)) {
            throw new Error("Can't redeclare function '" + functionDeclaration.name + "'.");
        }
        enterScope();
        if (functionDeclaration.parameters != null) {
            for (Parameter param : functionDeclaration.parameters) {
                declare(param.name, param.type);
            }
        }
        functionDeclaration.statements.accept(this);
        table.define(functionDeclaration.name, new FunctionType(scopes.firstElement()));
        leaveScope();

        return null;

    }

    @Override
    public Void visit(Parameter parameter) {
        return null;
    }

    @Override
    public Void visit(VarDeclaration varDeclaration) {
        declare(varDeclaration.name, varDeclaration.type);
        if (varDeclaration.init != null) {
            varDeclaration.init.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(WhileStatement whileStatement) {
        whileStatement.condition.accept(this);
        whileStatement.block.accept(this);
        return null;
    }


    @Override
    public Void visit(BlockStatement block) {
        for (Stmt stmt : block.stmts) {
            stmt.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ReturnStatement returnStatement) {
        if (returnStatement.returnExpression != null) {
            returnStatement.returnExpression.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Assignment assignment) {
        assignment.left.accept(this);
        assignment.right.accept(this);
        return null;
    }

    @Override
    public Void visit(Program program) {
        for (int i = 0; i < program.getDeclarations().size(); i++) {
            program.getDeclarations().get(i).accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Modify modify) {
        modify.left.accept(this);
        return null;
    }

}
