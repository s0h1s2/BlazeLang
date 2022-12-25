package blaze.ast;

import java.util.List;

import blaze.IVisitable;
import blaze.IVisitor;

public class Program implements IVisitable{
	List<Declaration> declarations;
	public Program(List<Declaration> declarations) {
		this.declarations=declarations;
	}
	public Stmt accept(IVisitor visitor) {
		visitor.visit(this);
		return null;
	}
	public List<Declaration> getDeclarations() {
		return declarations;
	}
}
