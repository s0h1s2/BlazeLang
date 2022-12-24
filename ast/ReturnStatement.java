package blaze.ast;

public class ReturnStatement extends Stmt{
	public Expression returnExpression;
	public ReturnStatement(Expression returnExpression) {
		super();
		this.returnExpression = returnExpression;
	}
}
