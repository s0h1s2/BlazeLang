package blaze.ast;

import blaze.IVisitable;
import blaze.IVisitor;

public class IfStatement extends Stmt implements IVisitable {
	public Expression condition;
	BlockStatement then;
	BlockStatement els;
	public IfStatement(Expression condition, BlockStatement then, BlockStatement els) {
		super();
		this.condition = condition;
		this.then = then;
		this.els = els;
	}
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
	}
	
}