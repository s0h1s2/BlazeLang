package jc.ast;

public class Ternary extends Expression {
	Expression expr;
	Expression then;
	Expression elseExpr;
	public Ternary(Expression expr, Expression then, Expression elseExpr) {
		super();
		this.expr = expr;
		this.then = then;
		this.elseExpr = elseExpr;
	}
	
}
