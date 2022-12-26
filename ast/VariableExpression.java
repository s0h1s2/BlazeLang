package blaze.ast;

import blaze.IVisitor;

public class VariableExpression extends Expression{
	public String name;
	public VariableExpression(String name) {
		this.name=name;
	}
	@Override
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
	}
}