package blaze.ast;

import blaze.IVisitor;
import blaze.types.Type;

public class Parameter extends Declaration {
	public String name;
	public Type type;
	public Parameter(String name, Type type) {
		super();
		this.name = name;
		this.type = type;
	}
	@Override
	public Type getType() {
		return this.type;
	}
	@Override
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
	}
}
