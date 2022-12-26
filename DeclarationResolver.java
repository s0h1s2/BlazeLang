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


import blaze.ast.Assignment;
import blaze.ast.BinaryOp;
import blaze.ast.BlockStatement;
import blaze.ast.Bool;
import blaze.ast.CallExpr;
import blaze.ast.FunctionDeclaration;
import blaze.ast.IfStatement;
import blaze.ast.Int;
import blaze.ast.Parameter;
import blaze.ast.Program;
import blaze.ast.ReturnStatement;

import blaze.ast.Ternary;
import blaze.ast.Unary;
import blaze.ast.VarDeclaration;
import blaze.ast.VarStmt;
import blaze.ast.VariableExpression;
import blaze.ast.WhileStatement;

public class DeclarationResolver implements IVisitor<Void> {

	@Override
	public Void visit(IfStatement ifStatement) {
		ifStatement.condition.accept(this);
		return null;
	}
	

	@Override
	public Void visit(CallExpr callExpr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Int integer) {
		System.out.println("Integer");
		System.out.println(integer.getValue());
		return null;
	}

	@Override
	public Void visit(BinaryOp binOp) {
		System.out.println("Binary operation dude!");
		
		binOp.left.accept(this);
		binOp.right.accept(this);
		return null;
		
		
	}

	@Override
	public Void visit(Bool bool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Ternary ternary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(VariableExpression varExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Unary unary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(FunctionDeclaration functionDeclaration) {
		System.out.println("Function DEC...");
		return null;
	}

	@Override
	public Void visit(Parameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(VarDeclaration varDeclaration) {
		varDeclaration.init.accept(this);
		
		return null;
	}

	@Override
	public Void visit(WhileStatement whileStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(BlockStatement block) {
		for (int i = 0; i < block.stmts.size(); i++) {
			block.stmts.get(i).accept(this);
			
		}
		return null;
	}

	@Override
	public Void visit(VarStmt varStmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(ReturnStatement returnStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Assignment assignment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Program program) {
		for (int i = 0; i < program.getDeclarations().size(); i++) {
			program.getDeclarations().get(i).accept(this);
		}
		return null;
		
	}

}
