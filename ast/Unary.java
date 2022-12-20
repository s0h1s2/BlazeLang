package jc.ast;

public class Unary extends Expression {
	int op;
	Expression right;
	public Unary(int op, Expression right) {
		super();
		this.op = op;
		this.right = right;
	}	
}
