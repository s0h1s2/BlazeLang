package blaze.ast;

import blaze.IVisitable;
import blaze.IVisitor;

public class WhileStatement extends Stmt implements IVisitable {
	public Expression condition;
	public BlockStatement block;
	public WhileStatement(Expression condition, BlockStatement block) {
		super();
		this.condition = condition;
		this.block = block;
	}
	@Override
	public void accept(IVisitor<?> visitor) {
		visitor.visit(this);
	}
	
}
