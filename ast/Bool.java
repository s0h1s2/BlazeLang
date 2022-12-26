package blaze.ast;

import blaze.IVisitor;

public class Bool extends Expression{
	private boolean value;

	public Bool(boolean value) {
		super();
		this.value = value;
	}

	@Override
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
	}
	
	
}