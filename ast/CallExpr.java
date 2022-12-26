package blaze.ast;

import java.util.List;

import blaze.IVisitor;

public class CallExpr extends Expression {
	public Expression expr;
	public List<Expression> args;
	public CallExpr(Expression expr,List<Expression> args) {
		this.expr=expr;
		this.args=args;
	}
	@Override
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
	}
	
}
