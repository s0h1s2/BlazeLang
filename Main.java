package blaze;

import java.util.ArrayList;

import blaze.ast.*;

public class Main {
	public static void main(String[] args) {
		String example="**p";
		
		Lexer lex=new Lexer(example); //

		Parser parser=new Parser(lex);
		parser.parse();
		/*Expression e=new BinaryOp(new Int(6), 2, new BinaryOp(new Int(2), 0, new Int(3)));
		ArrayList<Stmt> body=new ArrayList<Stmt>();
		body.add(new PrintStmt("Hello"));
		body.add(new PrintStmt("World"));
		body.add(new BinaryOp(new Int(5), 0, new Int(6)));
		*/
		
		
//		Stmt stmts=new IfStmt(e, body);
//		Object result=evalAst(stmts);
		//System.out.println(result);

	}
/*	private static Object evalAst(Stmt program) {
		if(program instanceof IfStmt) {
			Object result=evalAst(((IfStmt) program).expr);
			if((boolean)result==true) {
				for(int i=0;i<((IfStmt) program).body.size();i++) {
					evalAst(((IfStmt) program).body.get(i));
				}
			}
			return result;
		}else if(program instanceof PrintStmt) {
			((PrintStmt) program).print();
			return program;
		}
		else if(program instanceof BinaryOp) {
			//evalAst(expr.left);
			Object left = 0,right = 0;
			if(((BinaryOp) program).left!=null) {
				left=evalAst(((BinaryOp) program).left);
			}
			
			if(((BinaryOp) program).right!=null) {
				right=evalAst(((BinaryOp) program).right);
			}
			if(((BinaryOp) program).op==0) {
				System.out.println("add");
				return (long)left+(long)right;
			}else if(((BinaryOp) program).op==1) {
				System.out.println("subtract");
				return (long)right-(long)left;
			}
			else if(((BinaryOp) program).op==2) {
				System.out.println("compare");
				return right==left;
			}
			
			
		}else if(program instanceof Int) {
			System.out.format("mov rax,%d\n",((Int) program).getValue());
			return ((Int) program).getValue();
		}
		
		throw new Error("Unreachable");

		
	}
	*/
}
