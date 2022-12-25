package blaze.ast;

import java.util.List;

import blaze.IVisitor;
import blaze.types.Type;

public class FunctionDeclaration extends Declaration  {
	String name;
	List<Parameter> parameters;
	BlockStatement statements;
	Type returnType;
	public FunctionDeclaration(String name,List<Parameter> parameters,BlockStatement statements, Type returnType) {
		super();
		this.name=name;
		this.parameters = parameters;
		this.statements = statements;
		this.returnType = returnType;
	}
	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Stmt accept(IVisitor visitor) {
		return visitor.visit(this);
	}
	
	
}

