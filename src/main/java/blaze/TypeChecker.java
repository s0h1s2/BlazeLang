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
import blaze.ast.Ternary;
import blaze.ast.Unary;
import blaze.ast.VarDeclaration;
import blaze.ast.VariableExpression;
import blaze.ast.WhileStatement;
import blaze.types.BoolType;
import blaze.types.IntType;
import blaze.types.Type;

public class TypeChecker implements IVisitor<Type> {
    private Program program;
    private SymbolTable table;
    public TypeChecker(Program program,SymbolTable table){
        this.program=program;
        this.table=table;
    }
    @Override
    public Type visit(IfStatement ifStatement) {
        
        return null;
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
                // make sure left and right are integer;
                Type leftType=(Type)binOp.left.accept(this);
                Type rightType=(Type)binOp.right.accept(this);
                if(leftType instanceof IntType && rightType instanceof IntType){
                    return leftType;
                }
                throw new Error("Left and right must be int.");
            }
            case OPERATOR_AND:
                break;
            case OPERATOR_BITAND:
                break;
            case OPERATOR_BITOR:
                break;
            case OPERATOR_BITWISE:
                break;
           
            case OPERATOR_EQUAL:
                break;
            case OPERATOR_GE:
                break;
            case OPERATOR_GEQ:
                break;
            case OPERATOR_GREATERTHAN:
                break;
            case OPERATOR_GREATERTHANEQUAL:
                break;
            case OPERATOR_LE:
                break;
            case OPERATOR_LEQ:
                break;
            case OPERATOR_LESSTHAN:
                break;
            case OPERATOR_LESSTHANEQUAL:
                break;
            case OPERATOR_NOEQUAL:
                break;
            case OPERATOR_OR:
                break;
            
            default:
                break;
            
        }
        return null;
    }

    @Override
    public Type visit(Bool bool) {
        
        return null;
    }

    @Override
    public Type visit(Ternary ternary) {
        
        return null;
    }

    @Override
    public Type visit(VariableExpression varExpression) {
        
        return null;
    }

    @Override
    public Type visit(Unary unary) {
        
        return null;
    }

    @Override
    public Type visit(FunctionDeclaration functionDeclaration) {
        
        return null;
    }

    @Override
    public Type visit(Parameter parameter) {
        
        return null;
    }

    @Override
    public Type visit(VarDeclaration varDeclaration) {
        
        return null;
    }

    @Override
    public Type visit(WhileStatement whileStatement) {
        
        return null;
    }

    @Override
    public Type visit(BlockStatement block) {
        
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
        
        return null;
    }

    @Override
    public Type visit(Modify modify) {
        
        return null;
    }
}