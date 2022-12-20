package jc.ast;

import jc.util.AstOperators.AstUnaryOperator;

public class Unary extends Expression {
	AstUnaryOperator op;
	Expression right;
	public Unary(AstUnaryOperator op, Expression right) {
		super();
		this.op = op;
		this.right = right;
	}	
}
