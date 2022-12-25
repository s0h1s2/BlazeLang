package blaze.ast;

import blaze.IVisitable;
import blaze.IVisitor;

public class Ternary extends Expression implements IVisitable {
	Expression expr;
	Expression then;
	Expression elseExpr;
	public Ternary(Expression expr, Expression then, Expression elseExpr) {
		super();
		this.expr = expr;
		this.then = then;
		this.elseExpr = elseExpr;
	}
	@Override
	public Stmt accept(IVisitor visitor) {
		return visitor.visit(this);
	}
	
}
