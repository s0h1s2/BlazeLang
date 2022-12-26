package blaze.ast;

import blaze.IVisitor;

public class Int extends Expression {
	private long x;
	public Int(long x) {
		super();
		this.x = x;
	}
	public long getValue() {
		return this.x;
	}
	@Override
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
	}

}
