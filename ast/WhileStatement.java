package blaze.ast;

public class WhileStatement extends Stmt {
	public Expression condition;
	public BlockStatement block;
	public WhileStatement(Expression condition, BlockStatement block) {
		super();
		this.condition = condition;
		this.block = block;
	}
	
}
