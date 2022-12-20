package jc.ast;

public class Int extends Expression {
	private long x;
	public Int(long x) {
		super();
		this.x = x;
	}
	public long getValue() {
		return this.x;
	}

	
}
