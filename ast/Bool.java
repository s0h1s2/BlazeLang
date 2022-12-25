package blaze.ast;

import blaze.IVisitor;

public class Bool extends Expression{
	private boolean value;

	public Bool(boolean value) {
		super();
		this.value = value;
	}

	@Override
	public Stmt accept(IVisitor visitor) {
		return visitor.visit(this);
	}
	
	
}