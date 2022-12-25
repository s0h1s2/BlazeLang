package blaze.ast;

import blaze.IVisitor;

public class ReturnStatement extends Stmt{
	public Expression returnExpression;
	public ReturnStatement(Expression returnExpression) {
		super();
		this.returnExpression = returnExpression;
	}
	@Override
	public Stmt accept(IVisitor visitor) {
		return visitor.visit(this);
		
	}
}
