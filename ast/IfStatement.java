package blaze.ast;

import java.util.List;

public class IfStatement extends Stmt{
	Expression condition;
	BlockStatement then;
	BlockStatement els;
	public IfStatement(Expression condition, BlockStatement then, BlockStatement els) {
		super();
		this.condition = condition;
		this.then = then;
		this.els = els;
	}
	
}