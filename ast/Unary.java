package blaze.ast;

import blaze.IVisitor;
import blaze.util.AstOperators.AstUnaryOperator;

public class Unary extends Expression {
	AstUnaryOperator op;
	Expression right;
	public Unary(AstUnaryOperator op, Expression right) {
		super();
		this.op = op;
		this.right = right;
	}
	@Override
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
	}	
}
