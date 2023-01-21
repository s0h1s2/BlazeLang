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

import java.util.Stack;

import blaze.ast.ArrayDeclaration;
import blaze.ast.Assignment;
import blaze.ast.BinaryOp;
import blaze.ast.BlockStatement;
import blaze.ast.Bool;
import blaze.ast.CallExpr;
import blaze.ast.CharLit;
import blaze.ast.Expression;
import blaze.ast.FieldAccess;
import blaze.ast.FunctionDeclaration;
import blaze.ast.IfStatement;
import blaze.ast.Int;
import blaze.ast.Modify;
import blaze.ast.Parameter;
import blaze.ast.Program;
import blaze.ast.ReturnStatement;
import blaze.ast.Stmt;
import blaze.ast.Struct;
import blaze.ast.Ternary;
import blaze.ast.Unary;
import blaze.ast.VarDeclaration;
import blaze.ast.VariableExpression;
import blaze.ast.WhileStatement;
import blaze.types.FunctionType;
import blaze.types.PrimitiveTypes;
import blaze.types.Type;

public class TypeChecker implements IVisitor<Type> {
    private SymbolTable global;
    private Stack<Type> tables;  
    public TypeChecker(SymbolTable table){
        this.global=table;
        this.tables=new Stack<>();
    }
    private Type peekScope(){
        return this.tables.peek();
    }
    private void enterScope(Type type){
        this.tables.push(type);
    }

    private void leaveScope(){
        this.tables.pop();
    }
    
    
    private void isBooleanType(Type type){
        if(type!=PrimitiveTypes.BOOL_TYPE){
            throw new Error("Must be boolean type.");
        }
    }
    
    @Override
    public Type visit(IfStatement ifStatement) {
        Type conditionType=(Type)ifStatement.condition.accept(this);
        
        throw new Error("If statement must have boolean condition.");
    }

    @Override
    public Type visit(CallExpr callExpr) {
        // check weather the function exist or not.
        if(global.getDeclType(callExpr.name)==null){
            throw new Error("Function '"+callExpr.name+"' doesn't exist.");
        }
        FunctionType callee=(FunctionType)global.getDeclType(callExpr.name);
        if(callee.getTable().getKeys().size()!=callExpr.args.size()){
            throw new Error("Argument number must be same with function declaration.");
        }
        
        for (int i = 0; i < callExpr.args.size(); i++) {
            Type argType=(Type)callExpr.args.get(i).accept(this);
            String parameterName=(String)callee.getTable().getKeys().toArray()[i];
            if(!callee.getTable().getDeclType(parameterName).equals(argType)){
                throw new Error("Argument must be same type as function.");
            }
        }
        return callee.returnType;
    }

    @Override
    public Type visit(Int integer) {
        return PrimitiveTypes.INT_TYPE;
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
                // TODO: not sure how comparison should work for now let's make it this way.
                if(!leftType.equals(rightType)){
                    throw new Error("Comparison Error.");    
                }
            }
            default:
                throw new UnsupportedOperationException(null, null);
        }
        return null;
    }

    @Override
    public Type visit(Bool bool) {
        return PrimitiveTypes.BOOL_TYPE;
    }

    @Override
    public Type visit(Ternary ternary) {
        Type conditionType=(Type)ternary.cond.accept(this);
        if(!(conditionType.equals(conditionType))){
            throw new Error("Condition must be boolean");
        }
        Type left=(Type)ternary.then.accept(this);
        Type right=(Type)ternary.elseExpr.accept(this);
        if(!left.equals(right)){
            throw new Error("Ternary must return same literal value");
        }
        return left;
    }

    @Override
    public Type visit(VariableExpression varExpression) {
        return global.getDeclType(varExpression.name);
    }

    @Override
    public Type visit(Unary unary) {
        Type right=(Type)unary.right.accept(this);
        switch(unary.op){
            case OPERATOR_DEREFERENCE:
                break;
            case OPERATOR_GETADDRESS:
                break;
            case OPERATOR_NEGATIVE:
            case OPERATOR_POSITIVE:
                if(right.equals(PrimitiveTypes.INT_TYPE)){
                    return right;
                }
                throw new Error("-/+ right hand must be int.");
            default:
                break;

        }
        return null;
    }

    @Override
    public Type visit(FunctionDeclaration functionDeclaration) {
        FunctionType functionTable=(FunctionType)global.getDeclType(functionDeclaration.name);
        enterScope(functionTable);
        functionDeclaration.statements.accept(this);
        leaveScope();
        return null;
    }
    @Override
    public Type visit(Parameter parameter) {
        System.out.println("Do we visit this?");
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
        Type condition=(Type)whileStatement.condition.accept(this);
        isBooleanType(condition);
        whileStatement.block.accept(this);
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
        FunctionType scope=(FunctionType)peekScope();
        if(returnStatement.returnExpression!=null){
            Type returnExpression=(Type)returnStatement.returnExpression.accept(this);
            if(!returnExpression.equals(scope.returnType)){
                throw new Error("Return must be same type as function.");
            }
        }
        return null;
    }

    @Override
    public Type visit(Assignment assignment) {
        Type left=(Type)assignment.left.accept(this);
        Type right=(Type)assignment.right.accept(this);
        if(!left.equals(right)){
            throw new Error("Assignment must be same type");
        }
        return null;
    }

    @Override
    public Type visit(Program program) {
        // check for main function.
        // but what happens if program has many files?.
        if(global.getDeclType("main")==null){
            throw new Error("Program must implement main function.");
        }
        for(int i=0;i<program.getDeclarations().size();i++){
            program.getDeclarations().get(i).accept(this);
        }
        return null;

    }

    @Override
    public Type visit(Modify modify) {
        
        return null;
    }
    @Override
    public Type visit(CharLit charLit) {
        return PrimitiveTypes.CHAR_TYPE;
    }
    @Override
    public Type visit(ArrayDeclaration arrayDeclaration) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Type visit(Struct struct) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Type visit(FieldAccess fieldAccess) {
        // TODO Auto-generated method stub
        return null;
    }
}