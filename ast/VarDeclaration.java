package blaze.ast;

import blaze.types.Type;

public class VarDeclaration extends Declaration {
	Expression init;
	Type type;
	String name;
	public VarDeclaration(String name,Expression expr,Type type) {
		this.init=expr;
		this.name=name;
		this.type=type;
	}
	
}
