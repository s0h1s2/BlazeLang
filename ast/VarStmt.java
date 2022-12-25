package blaze.ast;

import blaze.IVisitor;

public class VarStmt extends Stmt {
	Expression init;
	String name;
	public VarStmt(String name,Expression expr) {
		this.init=expr;
		this.name=name;
	}
	@Override
	public Stmt accept(IVisitor visitor) {
		return visitor.visit(this);	
	}
	
}
