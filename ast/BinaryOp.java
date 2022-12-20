package jc.ast;

import util.AstOperators.AstBinaryOperator;

public class BinaryOp extends Expression {
	public Expression left;
	public AstBinaryOperator op;
	public Expression right;
	public BinaryOp(Expression left, AstBinaryOperator op, Expression right) {
		super();
		this.left = left;
		this.op = op;
		this.right = right;
	}

	

	
}
