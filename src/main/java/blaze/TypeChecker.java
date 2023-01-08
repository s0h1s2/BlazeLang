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
import blaze.types.BoolType;
import blaze.types.CharType;
import blaze.types.FunctionType;
import blaze.types.IntType;
import blaze.types.Type;

public class TypeChecker implements IVisitor<Type> {
    private SymbolTable table;
    private SymbolTable prev;
    private SymbolTable global;
    private Type funcitonReturnType;
    private long blockCounter=0;
    private boolean isFinalReturnReached=false;

    public TypeChecker(SymbolTable table){
        this.global=table;
        this.table=table;
        this.prev=table;
    }
    @Override
    public Type visit(IfStatement ifStatement) {
        Type conditionType=(Type)ifStatement.condition.accept(this);
        if(conditionType instanceof BoolType){
            ifStatement.then.accept(this);
            if(ifStatement.elseIfs!=null){
                ifStatement.elseIfs.forEach((stmt)->stmt.accept(this));
            }
            if(ifStatement.els!=null){
                ifStatement.els.accept(this);
            }
            
            return conditionType;
        }
        throw new Error("If statement must have boolean condition.");
    }

    @Override
    public Type visit(CallExpr callExpr) {
        FunctionType callee=(FunctionType)global.getDecl(callExpr.name);
        // TODO: handle this in declaration resolver.
        if(callee.getTable().getKeys().size()!=callExpr.args.size()){
            throw new Error("Argument number must be same with function declaration.");
        }
        
        for (int i = 0; i < callExpr.args.size(); i++) {
            Type argType=(Type)callExpr.args.get(i).accept(this);
            String parameterName=(String)callee.getTable().getKeys().toArray()[i];
            if(!callee.getTable().getDecl(parameterName).equals(argType)){
                throw new Error("Argument must be same type as function.");
            }
        }
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
                throw new UnsupportedOperationException(null, null);
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
        Type right=(Type)unary.right.accept(this);
        switch(unary.op){
            case OPERATOR_DEREFERENCE:
                break;
            case OPERATOR_GETADDRESS:
                break;
            case OPERATOR_NEGATIVE:
            case OPERATOR_POSITIVE:
                if(right instanceof IntType){
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
        FunctionType functionTable=(FunctionType)table.getDecl(functionDeclaration.name);
        this.prev=this.table;
        this.table=functionTable.getTable();
        
        funcitonReturnType=functionDeclaration.returnType;
        functionDeclaration.statements.accept(this);
        if(!isFinalReturnReached){
            throw new Error("Function must return expression.");
        }
        funcitonReturnType=null;
        isFinalReturnReached=false;
        this.table=this.prev;
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
        blockCounter++;
        for (Stmt stmt : block.stmts) {
            stmt.accept(this);
        }
        blockCounter--;
        return null;
    }

    @Override
    public Type visit(ReturnStatement returnStatement) {
        if(blockCounter==1){
            isFinalReturnReached=true;   
        }
        if(returnStatement.returnExpression!=null){
            Type result=(Type)returnStatement.returnExpression.accept(this);
            if(!funcitonReturnType.equals(result)){
                throw new Error("Return must be same type as function.");
            }
        }else{
            throw new Error("Funciton must return expression.");
        }
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
    @Override
    public Type visit(CharLit charLit) {
        return new CharType();
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