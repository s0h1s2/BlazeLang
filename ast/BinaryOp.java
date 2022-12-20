package jc.ast;

public class BinaryOp extends Expression {
	public Expression left;
	public int op;
	public Expression right;
	public BinaryOp(Expression left, int op, Expression right) {
		super();
		this.left = left;
		this.op = op;
		this.right = right;
	}

	

	
}
