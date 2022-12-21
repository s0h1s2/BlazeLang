package blaze;
import java.util.ArrayList;

import java.util.List;

import blaze.ast.*;
import blaze.util.AstOperation;

public class Parser {
	private Lexer lex;
	private Token token;
	private Token prev;
	public Parser(Lexer lex) {
		this.lex=lex;
		this.token=lex.getToken();	
	}
	private void advance() {
		this.prev=this.token;
		this.token=lex.getToken();
	}
	private boolean match(TokenKind kind) {
		if(token.getKind()==kind) {
			advance();
			return true;
		}
		return false;
	}
	private void expect(TokenKind kind,String err) {
		if(!match(kind)) {
			throw new Error(err);
		}
	}
	public List<Stmt> parse() {
		// parse function declaration.
		// type identifier '(' atomVariable ',' atomVariable* ')' '{' stmt ';' stmt*  '}'
		// type name '=' expression ';'
		// type-> 'int' | 'char'
		// name->[a-zA-Z_][a-zA-Z_1-9]*
		// expression-> equality
		// equality-> addition '==' addition
		// addition-> primary ('+'|'-') primary
		// primary-> [0-9]*
		//System.out.println(lex.getToken().getKind());
		ArrayList<Stmt> stmts=new ArrayList<>();
//		while(token.getKind()!=TokenKind.TOKEN_EOF) {
//			stmts.add(atomVariable());
//		}
		//expression();
		return stmts;
		
	}
	
	private Expression expression() {
		// lowest to highest precedence.
		return ternary();
	}
	private Expression ternary(){
		Expression expr=logicalOr();
		if(match(TokenKind.TOKEN_QUESTION)) {
			Expression then=ternary();
			expect(TokenKind.TOKEN_COLON, "Expected ':'");
			Expression elseExpr=ternary();
			expr=new Ternary(expr,then,elseExpr);
		}
		return expr;
	}
	private Expression logicalOr(){
		Expression left=logicalAnd();
		while(match(TokenKind.TOKEN_OR)) {
			left=new BinaryOp(left,AstOperation.getBinaryOperator(prev.getKind()),logicalAnd());
		}
		return left;
	}
	private Expression logicalAnd(){
		Expression left=bitOr();
		while(match(TokenKind.TOKEN_AND)) {
			left=new BinaryOp(left,AstOperation.getBinaryOperator(prev.getKind()),bitOr());
		}
		return left;
	}
	private Expression bitOr(){
		Expression left=bitXor();
		while(match(TokenKind.TOKEN_BITOR)) {
			left=new BinaryOp(left,AstOperation.getBinaryOperator(prev.getKind()),bitXor());
		}
		return left;
	}
	
	private Expression bitXor(){
		Expression left=bitAnd();
		while(match(TokenKind.TOKEN_BITWISE)) {
			left=new BinaryOp(left,AstOperation.getBinaryOperator(prev.getKind()),bitAnd());
		}
		return left;
		
	}
	private Expression bitAnd(){
		Expression left=equality();
		while(match(TokenKind.TOKEN_BITAND)) {
			left=new BinaryOp(left,AstOperation.getBinaryOperator(prev.getKind()),equality());
		}
		return left;
	}
	
	
	private Expression equality() {
		Expression left=relational();
		while(match(TokenKind.TOKEN_EQUAL) || match(TokenKind.TOKEN_NOTEQUAL)) {
			left=new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), relational());
		}
		return left;
		
	}
	private Expression relational() {
		Expression left=addition();
		while(match(TokenKind.TOKEN_GE) || match(TokenKind.TOKEN_LE) || match(TokenKind.TOKEN_GEQ) || match(TokenKind.TOKEN_LEQ)) {
			left=new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), addition());
		}
		return left;
		
	}
	/*private Expression shift() {
		Expression left=addition();
		
		
		return null;
	}*/
	
	private Expression addition() {
		Expression left=multiplicative();
		while(match(TokenKind.TOKEN_PLUS) || match(TokenKind.TOKEN_MINUS)) {
			left=new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), multiplicative());
		}
		return left;
		
	}
	
	
	private Expression multiplicative() {
		Expression left=unary();
		while(match(TokenKind.TOKEN_STAR) || match(TokenKind.TOKEN_SLASH)) {
			left=new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), unary());
		}
		return left;
		
	}
	/*private Expression cast() {
		
		return null;
	}*/
	private Expression unary() {
		if(match(TokenKind.TOKEN_MINUS) || match(TokenKind.TOKEN_PLUS) || match(TokenKind.TOKEN_STAR) || match(TokenKind.TOKEN_BITAND)) {
			return new Unary(AstOperation.getUnaryOperator(prev.getKind()), unary());
		}else {
			return primary();
		}
		
	}
	
	private Expression primary() {
		if(match(TokenKind.TOKEN_INTEGER)) {
			return new Int((int)prev.getValue());
		}else if(match(TokenKind.TOKEN_IDENTIFIER)) {
			return new Int((int)1);
		}
		throw new Error("Syntax Error.");
	}
}
/*private Stmt atomVariable() {
if(match(TokenKind.TOKEN_CHAR)) {
	expect(TokenKind.TOKEN_IDENTIFIER,"Expected a variable name.");
	// check if next token is '(' then it's function declaration.
	// otherwise it is an identifier.
	 
	String name=(String)prev.getValue();
	Expression init=null;
	if(match(TokenKind.TOKEN_ASSIGN)) {
		init=expression();
	}
	expect(TokenKind.TOKEN_SEMICOLON, "Expected ';' after statement.");
	return new VarStmt(name,init);
}else {
	return expression();
}
//throw new Error("Unreachable");

}*/
