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

import blaze.ast.Assignment;
import blaze.ast.BinaryOp;
import blaze.ast.BlockStatement;
import blaze.ast.Bool;
import blaze.ast.CallExpr;
import blaze.ast.FunctionDeclaration;
import blaze.ast.IfStatement;
import blaze.ast.Int;
import blaze.ast.Modify;
import blaze.ast.Parameter;
import blaze.ast.Program;
import blaze.ast.ReturnStatement;
import blaze.ast.Stmt;
import blaze.ast.Ternary;
import blaze.ast.Unary;
import blaze.ast.VarDeclaration;
import blaze.ast.VariableExpression;
import blaze.ast.WhileStatement;
import blaze.types.BoolType;
import blaze.types.IntType;
import blaze.types.Type;

public class TypeChecker implements IVisitor<Type> {
    private SymbolTable table;
    public TypeChecker(SymbolTable table){
        this.table=table;
    }
    @Override
    public Type visit(IfStatement ifStatement) {
        Type conditionType=(Type)ifStatement.condition.accept(this);
        if(conditionType instanceof BoolType){
            // goto block declaration
            return conditionType;
        }
        throw new Error("If statement must have boolean condition.");
    }

    @Override
    public Type visit(CallExpr callExpr) {
        
        return null;
    }

    @Override
    public Type visit(Int integer) {
        
        return new IntType();
    }

    @Override
    public Type visit(BinaryOp binOp) {
        switch(binOp.op){
            case OPERATOR_ADD:
            case OPERATOR_SUBTRACT:
            case OPERATOR_MULTIPLY:
            case OPERATOR_DIVIDE:{
                Type leftType=(Type)binOp.left.accept(this);
                Type rightType=(Type)binOp.right.accept(this);
                if(leftType.equals(rightType)){
                    return leftType;
                }
                throw new Error("Left and right must be int.");
            }
            case OPERATOR_BITAND:
                break;
            case OPERATOR_BITOR:
                break;
            case OPERATOR_BITWISE:
                break;
            case OPERATOR_AND:
            case OPERATOR_EQUAL:
            case OPERATOR_GE:
            case OPERATOR_GEQ:
            case OPERATOR_LE:
            case OPERATOR_LEQ:
            case OPERATOR_NOEQUAL:
            case OPERATOR_OR:{
                Type leftType=(Type)binOp.left.accept(this);
                Type rightType=(Type)binOp.right.accept(this);
                if(leftType.equals(rightType)){
                    return new BoolType();
                }
                throw new Error("Left and Right must be same type.");
            }
            default:
                break;
            
        }
        return null;
    }

    @Override
    public Type visit(Bool bool) {
        return new BoolType();
    }

    @Override
    public Type visit(Ternary ternary) {
        
        return null;
    }

    @Override
    public Type visit(VariableExpression varExpression) {
        return table.getDecl(varExpression.name);
    }

    @Override
    public Type visit(Unary unary) {
        
        return null;
    }

    @Override
    public Type visit(FunctionDeclaration functionDeclaration) {
        functionDeclaration.statements.accept(this);
        
        return null;
    }

    @Override
    public Type visit(Parameter parameter) {
        
        return null;
    }

    @Override
    public Type visit(VarDeclaration varDeclaration) {
        Type init=null;
        if(varDeclaration.init!=null){
            init=(Type)varDeclaration.init.accept(this);
            if(varDeclaration.type.equals(init)){
                return varDeclaration.type;
                
            }else{
                throw new Error("variable and expression must be same type");
            }
        }
        return varDeclaration.type;
    }

    @Override
    public Type visit(WhileStatement whileStatement) {
        
        return null;
    }

    @Override
    public Type visit(BlockStatement block) {
        for (Stmt stmt : block.stmts) {
            stmt.accept(this);
        }
        return null;
    }

    @Override
    public Type visit(ReturnStatement returnStatement) {
        
        return null;
    }

    @Override
    public Type visit(Assignment assignment) {
        
        return null;
    }

    @Override
    public Type visit(Program program) {
        for(int i=0;i<program.getDeclarations().size();i++){
            program.getDeclarations().get(i).accept(this);
        }
        return null;

    }

    @Override
    public Type visit(Modify modify) {
        
        return null;
    }
}