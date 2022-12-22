package blaze.ast;

public class VariableExpression extends Expression{
	public String name;
	public VariableExpression(String name) {
		this.name=name;
	}
}