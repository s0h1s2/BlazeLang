package blaze.ast;

public class VariableExpression extends Expression{
	String name;
	public VariableExpression(String name) {
		this.name=name;
	}
}