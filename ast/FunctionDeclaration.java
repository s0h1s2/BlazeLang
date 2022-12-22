package blaze.ast;

import java.util.List;

public class FunctionDeclaration extends Declaration {
	// list of parameters
	List<Parameter> parameters;
//	List<Stmt> statements;
//	Expression returnType;
	public FunctionDeclaration(List<Parameter> parameters/*, List<Stmt> statements, Expression returnType*/) {
		super();
		this.parameters = parameters;
//		this.statements = statements;
//		this.returnType = returnType;
//		
	}
	
	
}

