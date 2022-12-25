package blaze.ast;

import java.util.List;

import blaze.IVisitable;
import blaze.IVisitor;

public class BlockStatement extends Stmt implements IVisitable {
	public List<Stmt> stmts;
	public BlockStatement(List<Stmt> stmts) {
		this.stmts=stmts;
	}
	@Override
	public Stmt accept(IVisitor visitor) {
		return visitor.visit(this);
	}
}
