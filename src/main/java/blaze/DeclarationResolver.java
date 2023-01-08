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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Stack;

import javax.swing.plaf.synth.SynthButtonUI;

import blaze.ast.*;
import blaze.types.FunctionType;
import blaze.types.Type;

public class DeclarationResolver implements IVisitor<Void> {
    final private SymbolTable top;
    private Stack<SymbolTable> symbols;
    private HashMap<String,Void> unresolvedNames;
    
    public DeclarationResolver(SymbolTable table) {
        this.top=table;
        this.symbols=new Stack<>();
        this.symbols.push(table);
        this.unresolvedNames=new HashMap<String,Void>();
    }

    private void enterScope() {
        SymbolTable scope=new SymbolTable(symbols.peek());
        symbols.push(scope);
    }

    private void leaveScope() {
        symbols.pop();
    }

    private void declare(String name, Type type) {
        if(!symbols.peek().containDecl(name)){
            symbols.peek().define(name, type);
        }else{
            throw new Error("can't redclare '"+name+"' in current scope.");
        }
        
    }

    private void resolve(String name) {
        for(int i=symbols.size()-1;i>=0;--i){
            SymbolTable scope=symbols.get(i);
            if (scope.containDecl(name)) {
                return;
            }    
        }
        throw new Error("'" + name + "' not found.");
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
        unresolvedNames.put(callExpr.name, null);
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
        unary.right.accept(this);
        return null;
    }

    @Override
    public Void visit(FunctionDeclaration functionDeclaration) {
        
        if (top.containDecl(functionDeclaration.name)) {
            throw new Error("Can't redeclare function '" + functionDeclaration.name + "'.");
        }
        if(unresolvedNames.containsKey(functionDeclaration.name)){
           unresolvedNames.remove(functionDeclaration.name);
        }
        enterScope();
        top.define(functionDeclaration.name, new FunctionType(symbols.peek()));
        if (functionDeclaration.parameters != null) {
            for (Parameter param : functionDeclaration.parameters) {
                declare(param.name, param.type);
            }
        }
        functionDeclaration.statements.accept(this);
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
        enterScope();
        for (Stmt stmt : block.stmts) {
            stmt.accept(this);
        }
        leaveScope();
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
        if(unresolvedNames.size()!=0){
            for (String name: unresolvedNames.keySet()) {
                throw new Error("function '"+name+"' doesn't exist.");
            }
        }
        return null;
    }

    @Override
    public Void visit(Modify modify) {
        modify.left.accept(this);
        return null;
    }

    @Override
    public Void visit(CharLit charLit) {
        return null;
    }

    @Override
    public Void visit(ArrayDeclaration arrayDeclaration) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visit(Struct struct) {
        // TODO Auto-generated method stub
        return null;
    }

    public SymbolTable getTop() {
        return top;
    }

    public Stack<SymbolTable> getSymbols() {
        return symbols;
    }

    public void setSymbols(Stack<SymbolTable> symbols) {
        this.symbols = symbols;
    }

}
