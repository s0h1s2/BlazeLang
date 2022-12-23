package blaze.ast;

import blaze.types.Type;

public class Parameter extends Declaration {
	public String name;
	public String type;
	public Parameter(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
