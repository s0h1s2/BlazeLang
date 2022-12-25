package blaze.ast;

import blaze.IVisitor;

public class Assignment extends Expression{
	public Expression left;
	public Expression right;
	
	public Assignment(Expression left,Expression right) {
		this.left=left;
		this.right=right;
		
	}
	@Override
	public Stmt accept(IVisitor visitor) {
		return visitor.visit(this);
	}
	
}