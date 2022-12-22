package blaze.ast;

public class Parameter extends Declaration {
	public String name;
	public String type;
	public Parameter(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
}
