package blaze.ast;

import blaze.IVisitor;
import blaze.util.AstOperators.AstBinaryOperator;

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
	@Override
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
	}

	

	
}
