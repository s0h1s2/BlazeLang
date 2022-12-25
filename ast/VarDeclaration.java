package blaze.ast;

import blaze.IVisitor;
import blaze.types.Type;

public class VarDeclaration extends Declaration {
	public Expression init;
	public Type type;
	public String name;
	public VarDeclaration(String name,Expression expr,Type type) {
		this.init=expr;
		this.name=name;
		this.type=type;
	}
	@Override
	public Type getType() {
		return this.type;
	}
	@Override
	public Stmt accept(IVisitor visitor) {
		return visitor.visit(this);

	}
	
}
