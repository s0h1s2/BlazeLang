package blaze.ast;

import java.util.List;

public class CallExpr extends Expression {
	public Expression expr;
	public List<Expression> args;
	public CallExpr(Expression expr,List<Expression> args) {
		this.expr=expr;
		this.args=args;
	}
	
}
