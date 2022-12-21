package blaze.ast;

import java.util.List;

public class IfStmt extends Stmt {
	public Expression expr;
	public List<Stmt> body;

	public IfStmt(Expression expr,List<Stmt> body) {
		this.expr=expr;
		this.body=body;
	}
}
