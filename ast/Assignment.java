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
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
	}
	
}