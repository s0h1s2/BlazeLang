package blaze;

import java.util.List;

import blaze.ast.BinaryOp;
import blaze.ast.Int;
import blaze.ast.Stmt;
import blaze.ast.VarDeclaration;
import blaze.ast.VariableExpression;
import blaze.types.IntType;

public class TypeChecker{
	 private List<Stmt> ast;
	 TypeChecker(List<Stmt> ast){
		 this.ast=ast;
	 }
	 private boolean isInt(Stmt stmt) {
		 if(stmt instanceof VarDeclaration) {
			if(((VarDeclaration) stmt).init!=null) {
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
			 // traverse the whole ast.
			 for(int i=0;i<ast.size();i++) {
				 if(ast.get(i) instanceof VarDeclaration) {
					 if(((VarDeclaration) ast.get(i)).name.equals(((VariableExpression) stmt).name)) {
						 return isInt(ast.get(i));
					 }
				 }
			 }
			 
			 return false;
		 }
		 return false;
	 }
	 public void check() {
		 for (int i = 0; i < ast.size(); i++) {
			 Stmt current=ast.get(i);
			 if(current instanceof VarDeclaration) {
				 if(((VarDeclaration)current).type instanceof IntType) {
					 // check for integer type
					 System.out.println(isInt(((VarDeclaration)current)));
				 }
			 }
		 }
	 }
}