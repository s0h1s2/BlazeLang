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

public interface IVisitor {
	public Stmt visit(IfStatement ifStatement);
	public Stmt visit(CallExpr callExpr);
	public Stmt visit(Int integer);
	public Stmt visit(BinaryOp binOp);
	public Stmt visit(Bool bool);
	public Stmt visit(Ternary ternary);
	public Stmt visit(VariableExpression varExpression);
	public Stmt visit(Unary unary);
	public Stmt visit(FunctionDeclaration functionDeclaration);
	public Stmt visit(Parameter parameter);
	public Stmt visit(VarDeclaration varDeclaration);
	public Stmt visit(WhileStatement whileStatement);
	public Stmt visit(BlockStatement block);
	public Stmt visit(VarStmt varStmt);
	public Stmt visit(ReturnStatement returnStatement);
	public Stmt visit(Assignment assignment);
	
	Stmt visit(Stmt stmt);
}
