package blaze.ast;

import blaze.IVisitable;
import blaze.types.Type;

public abstract class Declaration extends Stmt implements IVisitable{
	public abstract Type getType();
}