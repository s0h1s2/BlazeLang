package blaze.ast;

import blaze.IVisitor;

public class ReturnStatement extends Stmt{
	public Expression returnExpression;
	public ReturnStatement(Expression returnExpression) {
		super();
		this.returnExpression = returnExpression;
	}
	@Override
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
		
	}
}
