/*
** Dec 25, 2022 Shkar Sardar
**
** The author disclaims copyright to this source code.  In place of
** a legal notice, here is a blessing:
**
**    May you do good and not evil.
**    May you find forgiveness for yourself and forgive others.
**    May you share freely, never taking more than you give.
**
*/
package blaze;

import blaze.ast.*;

public interface IVisitor<T> {
	public T visit(IfStatement ifStatement);
	public T visit(CallExpr callExpr);
	public T visit(Int integer);
	public T  visit(BinaryOp binOp);
	public T visit(Bool bool);
	public T visit(Ternary ternary);
	public T visit(VariableExpression varExpression);
	public T visit(Unary unary);
	public T visit(FunctionDeclaration functionDeclaration);
	public T visit(Parameter parameter);
	public T visit(VarDeclaration varDeclaration);
	public T visit(WhileStatement whileStatement);
	public T visit(BlockStatement block);
	public T visit(VarStmt varStmt);
	public T visit(ReturnStatement returnStatement);
	public T visit(Assignment assignment);
	public T visit(Program program);
	
}
