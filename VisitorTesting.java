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


import blaze.ast.BinaryOp;
import blaze.ast.BlockStatement;
import blaze.ast.Bool;
import blaze.ast.CallExpr;
import blaze.ast.FunctionDeclaration;
import blaze.ast.IfStatement;
import blaze.ast.Int;
import blaze.ast.Parameter;
import blaze.ast.ReturnStatement;
import blaze.ast.Stmt;
import blaze.ast.Ternary;
import blaze.ast.Unary;
import blaze.ast.VarDeclaration;
import blaze.ast.VarStmt;
import blaze.ast.VariableExpression;
import blaze.ast.WhileStatement;

public class VisitorTesting implements IVisitor {
	@Override
	public Stmt visit(Stmt stmt) {

		return stmt.accept(this);
	}
	
	@Override
	public Stmt visit(IfStatement ifStatement) {
		ifStatement.condition.accept(this);
		return null;
	}
	

	@Override
	public Stmt visit(CallExpr callExpr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stmt visit(Int integer) {
		System.out.println("Integer");
		System.out.println(integer.getValue());
		
		return null;
	}

	@Override
	public Stmt visit(BinaryOp binOp) {
		System.out.println("Binary operation dude!");
		
		binOp.left.accept(this);
		binOp.right.accept(this);
		return null;
		
		
	}

	@Override
	public Stmt visit(Bool bool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stmt visit(Ternary ternary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stmt visit(VariableExpression varExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stmt visit(Unary unary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stmt visit(FunctionDeclaration functionDeclaration) {
		System.out.println("Function DEC...");
		return null;
	}

	@Override
	public Stmt visit(Parameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stmt visit(VarDeclaration varDeclaration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stmt visit(WhileStatement whileStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stmt visit(BlockStatement block) {
		for (int i = 0; i < block.stmts.size(); i++) {
			block.stmts.get(i).accept(this);
			
		}
		return null;
	}

	@Override
	public Stmt visit(VarStmt varStmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stmt visit(ReturnStatement returnStatement) {
		// TODO Auto-generated method stub
		return null;
	}

}
