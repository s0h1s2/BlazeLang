/*
 ** Jan 2,2022 Shkar Sardar
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 **
 */
package blaze.codegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.BinaryOperator;

import blaze.IVisitor;
import blaze.ast.ArrayDeclaration;
import blaze.ast.Assignment;
import blaze.ast.BinaryOp;
import blaze.ast.BlockStatement;
import blaze.ast.Bool;
import blaze.ast.CallExpr;
import blaze.ast.CharLit;
import blaze.ast.Declaration;
import blaze.ast.Expression;
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
import blaze.types.IntType;
import blaze.types.Type;

public class Intel86 implements IVisitor<Object> {
    private FileWriter writer;
    private HashMap<String,Boolean> registersInUse;
    private String[] registers;
    private int labelCounter=1;
    public Intel86(File file){
        try {
            this.writer=new FileWriter(file);
            this.registersInUse=new HashMap<>();
            registers=new String[]{"rax","rcx","r10","r11","r12","r13","r14"};
            
        } catch (IOException e) {
            System.err.println("File reading failed.");
            e.printStackTrace();
        }
        
    }
    private String generateLabel(){
        String result=".L"+labelCounter;
        labelCounter++;
        return result;
    }
    private boolean isRegisterAvailable(String reg){
        if(registersInUse.get(reg)==null){
            // TODO check if register exist in registers array for now just insert into the map of registers.
            registersInUse.put(reg, false);
            return true;
        }
        if(registersInUse.get(reg)==true){
            return false;
        }
        
        return true;
    }
    private String getFreeRegister(){
        for (String register : registers) {
            if(registersInUse.get(register)==null){
                registersInUse.put(register,true);
                return register;
            }
            if(registersInUse.get(register)==false){
                registersInUse.put(register, true);
                return register;
            }
        }

        throw new Error("No Register found.");

    }

    private void freeRegister(String key){
        registersInUse.put(key, false);
    }
    
    private void emitLn(String line){
        try{
            writer.write(line);
            writer.append("\n");
        }catch(IOException error){
            System.err.println("write to file failed.");
        }
    }
    private void emitIns(String ins){
        emitLn("    "+ins);
    }
    private void emit(String line){
        try{
            writer.write(line);
        }catch(IOException error){
            System.err.println("write to file failed.");
        }
    }
    
    @Override
    public Object visit(IfStatement ifStatement) {
        ifStatement.condition.accept(this);
        String label=generateLabel();
        emitIns("jne "+label);
        ifStatement.then.accept(this);
        emitLn(label+":");
        if(ifStatement.elseIfs!=null){
            ifStatement.elseIfs.forEach((stmt->stmt.accept(this)));
        }
        if(ifStatement.els!=null){
            ifStatement.els.accept(this);
        }
        return null;
    }
    @Override
    public Object visit(CallExpr callExpr) {
        return null;
    }
    @Override
    public Object visit(Int integer) {
        return integer.getValue();
    }
    @Override
    public Object visit(BinaryOp binOp) {
        Object l=binOp.left.accept(this);
        Object r=binOp.right.accept(this);
        switch(binOp.op){
            case OPERATOR_ADD:{
                String reg1=getFreeRegister();
                String reg2=getFreeRegister();
                emitIns("mov "+reg1+","+l);
                emitIns("mov "+reg2+","+r);
                emitIns("add "+reg1+","+reg2);
                freeRegister(reg2);
                return reg1;
            }
            case OPERATOR_DIVIDE:{
                break;
            }
            case OPERATOR_MULTIPLY:{
                String reg1=getFreeRegister();
                String reg2=getFreeRegister();
                emitIns("mov "+reg1+","+l);
                emitIns("mov "+reg2+","+r);
                emitIns("imul "+reg1+","+reg2);
                freeRegister(reg2);
                return reg1;
            }
            case OPERATOR_SUBTRACT:
                break;
            case OPERATOR_AND:
                // jump to the label if not equal
                break;
            case OPERATOR_BITAND:
                //emitIns("and "+left.getReg()+","+right.getReg());
                break;
            case OPERATOR_BITOR:
              //  emitIns("or "+left.getReg()+","+right.getReg());
                break;
            case OPERATOR_BITWISE:
                //emitIns("xor "+left.getReg()+","+right.getReg());
                break;
            
            case OPERATOR_EQUAL:
            case OPERATOR_NOEQUAL:
            case OPERATOR_GE:
            case OPERATOR_LE:
            case OPERATOR_GEQ:
            case OPERATOR_LEQ:{
                String reg1=getFreeRegister();
                String reg2=getFreeRegister();
                emitIns("mov "+reg1+","+l);
                emitIns("mov "+reg2+","+r);
                emitIns("cmp "+reg1+","+reg2);
                freeRegister(reg2);
                freeRegister(reg1);
                
                return binOp.op;
            }
            case OPERATOR_OR:

                break;
            default:
                break;
        }
        //freeRegister(right.getReg());
        //return left;
        return null;
    }
    @Override
    public Object visit(Bool bool) {
        // TODO Auto-generated method stu
        return 1;

    }
    @Override
    public Object visit(Ternary ternary) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(VariableExpression varExpression) {

        return null;
    }
    @Override
    public Object visit(Unary unary) {
        switch (unary.op) {
            case OPERATOR_DEREFERENCE:
                break;
            case OPERATOR_GETADDRESS:
                break;
            case OPERATOR_NEGATIVE:
                //emitIns();
            case OPERATOR_POSITIVE:
                break;
            default:
                break;

        }
        return null;
    }
    @Override
    public Object visit(FunctionDeclaration functionDeclaration) {
        emitLn(functionDeclaration.name+":");
        emitLn("    push rbp");
        emitLn("    mov rbp,rsp");
        functionDeclaration.statements.accept(this);
        emitLn("    pop rbp");
        emitLn("    ret");
        
        return null;
    }
    @Override
    public Object visit(Parameter parameter) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(VarDeclaration varDeclaration) {
        //emitLn(varDeclaration.name+":");
        Type type=varDeclaration.type;
        String emitType="";
        if(type instanceof BoolType || type instanceof CharType){
            emitType="db";
        }
        if(type instanceof IntType){
            emitType="dd";
        }
        //emitLn(emitType+" "+varDeclaration.init.accept(this));
        return null;
    }
    @Override
    public Object visit(WhileStatement whileStatement) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(BlockStatement block) {
        for (Stmt stmt : block.stmts) {
            stmt.accept(this);
        }
        return null;
    }
    @Override
    public Object visit(ReturnStatement returnStatement) {
        //Expression val=(Expression)returnStatement.returnExpression.accept(this);
        //setRegister("rax");
        //emitIns("mov rax,"+val.getValue());

        return null;
    }
    @Override
    public Object visit(Assignment assignment) {
        
        return null;
    }
    @Override
    public Object visit(Program program) {

        emit("");
        emitLn("section .text");
        emitLn("    global _start");
        emitLn("_start:");
        emitLn("    xor rbp,rbp");
        emitLn("    call main");
        emitLn("    mov rdi,rax");
        emitLn("    mov rax,60");
        emitLn("    syscall");
        for (Declaration decl : program.getDeclarations()) {
            decl.accept(this);
        }
        try {
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Object visit(Modify modify) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(CharLit charLit) {
        return ((int)charLit.getValue());
    }
    @Override
    public Object visit(ArrayDeclaration arrayDeclaration) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(Struct struct) {
        // TODO Auto-generated method stub
        return null;
    }
   }
