package blaze.ast;

import java.util.List;

public class BlockStatement extends Stmt {
	List<Stmt> stmts;
	public BlockStatement(List<Stmt> stmts) {
		this.stmts=stmts;
	}
}
