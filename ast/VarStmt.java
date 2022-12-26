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
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);	
	}
	
}
