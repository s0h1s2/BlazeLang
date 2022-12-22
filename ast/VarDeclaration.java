package blaze.ast;

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
	
}
