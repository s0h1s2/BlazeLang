package jc.ast;

public class VarStmt extends Stmt {
	Expression init;
	String name;
	public VarStmt(String name,Expression expr) {
		this.init=expr;
		this.name=name;
	}
	
}
