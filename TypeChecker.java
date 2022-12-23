package blaze;

import java.util.List;

import blaze.ast.BinaryOp;
import blaze.ast.Declaration;
import blaze.ast.Int;
import blaze.ast.Stmt;
import blaze.ast.VarDeclaration;
import blaze.ast.VariableExpression;
import blaze.types.IntType;

public class TypeChecker{
	 private List<Stmt> ast;
	 private SymbolTable table;
	 private DeclarationResolver declResolver;

	 TypeChecker(List<Stmt> ast, SymbolTable table){
		 this.ast=ast;
		 this.table=table;
		 this.declResolver=new DeclarationResolver(table);
	 }
	 private boolean isInt(Stmt stmt) {
		 if(stmt instanceof VarDeclaration) {
			if(((VarDeclaration) stmt).init!=null && ((VarDeclaration) stmt).type instanceof IntType ) {
				return isInt(((VarDeclaration) stmt).init);
			}
		 }else if(stmt instanceof Int) {
			 return true;
		 }else if(stmt instanceof BinaryOp) {
			 boolean left=isInt(((BinaryOp) stmt).left);
			 boolean right=isInt(((BinaryOp) stmt).right);
			 return left&&right;
		 }
		 else if(stmt instanceof VariableExpression) {
			 String name=((VariableExpression) stmt).name;
			 if(table.containDecl(name)) {
				 return table.getDecl(name) instanceof IntType;
			 }
			 throw new Error("Variable '"+name+"' Doesn't exist.");
			 //return false;
		 }
		 return false;
	 }
	 
	 public void check() {
		for (int i = 0; i < ast.size(); i++) {
			Stmt stmt=ast.get(i);
			if(stmt instanceof Declaration) {
				if(((Declaration) stmt).getType() instanceof IntType) {
					if(isInt(stmt)) {
						declResolver.resolve(stmt);
					}
				}
			}
			
		}
	 }
}