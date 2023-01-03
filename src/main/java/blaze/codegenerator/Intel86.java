package blaze.codegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

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
    private HashMap<String,Boolean> registers;
    
    public Intel86(File file){
        try {
            this.writer=new FileWriter(file);
            this.registers=new HashMap<>();
            this.registers.put("rax", false);
            this.registers.put("rcx", false);
            this.registers.put("rbx", false);
            this.registers.put("r10", false);
            this.registers.put("r11", false);
            this.registers.put("r12", false);
            this.registers.put("r13", false);
            this.registers.put("r14", false);
            this.registers.put("r15", false);
            
        } catch (IOException e) {
            System.err.println("File reading failed.");
            e.printStackTrace();
        }
        
    }
    private String getRegister(){
        for (String key : registers.keySet()) {
            if(registers.get(key)==false){
                registers.put(key, true);
                return key;
            }
        }
        throw new Error("No Register found.");

    }
    private void freeRegister(String key){
        registers.put(key, false);
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
        return null;
    }
    @Override
    public Object visit(CallExpr callExpr) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(Int integer) {
        String reg=getRegister();
        emitIns("mov "+reg+","+integer.getValue());
        integer.setReg(reg);
        return integer;
    }
    @Override
    public Object visit(BinaryOp binOp) {
        Expression left=(Expression)binOp.left.accept(this);
        Expression right=(Expression)binOp.right.accept(this);
        switch(binOp.op){
            case OPERATOR_ADD:
                emitIns("add "+left.getReg()+","+right.getReg());
            case OPERATOR_AND:
                break;
            case OPERATOR_BITAND:
                break;
            case OPERATOR_BITOR:
                break;
            case OPERATOR_BITWISE:
                break;
            case OPERATOR_DIVIDE:
            break;
            case OPERATOR_EQUAL:
            case OPERATOR_NOEQUAL:
            case OPERATOR_GE:
            case OPERATOR_LE:
            case OPERATOR_GEQ:
            case OPERATOR_LEQ:
                emitIns("cmp "+left.getReg()+","+right.getReg());
                break;
            case OPERATOR_MULTIPLY:
                emitIns("imul "+left.getReg()+","+right.getReg());
                break;
            case OPERATOR_OR:
                break;
            case OPERATOR_SUBTRACT:
                break;
            default:
                break;
        }
        freeRegister(right.getReg());
        return left;
    }
    @Override
    public Object visit(Bool bool) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(Ternary ternary) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(VariableExpression varExpression) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(Unary unary) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(FunctionDeclaration functionDeclaration) {
        emitLn(functionDeclaration.name+":");
        emitLn("    push rbp");
        emitLn("    mov rbp,rsp");
        functionDeclaration.statements.accept(this);
        emitLn("    mov rax,0");
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
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object visit(Assignment assignment) {
        // TODO Auto-generated method stub
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
