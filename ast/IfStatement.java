package blaze.ast;

import java.util.List;

public class IfStatement extends Stmt{
	Expression condition;
	List<Stmt> then;
	List<Stmt> els;
	public IfStatement(Expression condition, List<Stmt> then, List<Stmt> els) {
		super();
		this.condition = condition;
		this.then = then;
		this.els = els;
	}
	
}