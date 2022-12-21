package blaze.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import blaze.TokenKind;
import blaze.util.AstOperators.AstBinaryOperator;
import blaze.util.AstOperators.AstUnaryOperator;

public abstract class AstOperation{
	private static Map<TokenKind, AstBinaryOperator> binaryOperations;
	private static Map<TokenKind, AstUnaryOperator> unaryOperations;
	
	static {
		HashMap<TokenKind, AstBinaryOperator> bi=new HashMap<>();
		HashMap<TokenKind, AstUnaryOperator> ui=new HashMap<>();
		bi.put(TokenKind.TOKEN_OR, AstBinaryOperator.OPERATOR_OR);
		bi.put(TokenKind.TOKEN_AND, AstBinaryOperator.OPERATOR_AND);
		bi.put(TokenKind.TOKEN_BITOR, AstBinaryOperator.OPERATOR_BITOR);
		bi.put(TokenKind.TOKEN_BITWISE, AstBinaryOperator.OPERATOR_BITWISE);
		bi.put(TokenKind.TOKEN_BITAND, AstBinaryOperator.OPERATOR_BITAND);
		bi.put(TokenKind.TOKEN_EQUAL, AstBinaryOperator.OPERATOR_EQUAL);
		bi.put(TokenKind.TOKEN_NOTEQUAL, AstBinaryOperator.OPERATOR_NOEQUAL);
		bi.put(TokenKind.TOKEN_GE, AstBinaryOperator.OPERATOR_GE);
		bi.put(TokenKind.TOKEN_LE, AstBinaryOperator.OPERATOR_LE);
		bi.put(TokenKind.TOKEN_LEQ, AstBinaryOperator.OPERATOR_LEQ);
		bi.put(TokenKind.TOKEN_GEQ, AstBinaryOperator.OPERATOR_GEQ);
		bi.put(TokenKind.TOKEN_PLUS, AstBinaryOperator.OPERATOR_ADD);
		bi.put(TokenKind.TOKEN_MINUS, AstBinaryOperator.OPERATOR_SUBTRACT);
		bi.put(TokenKind.TOKEN_STAR, AstBinaryOperator.OPERATOR_MULTIPLY);
		bi.put(TokenKind.TOKEN_SLASH, AstBinaryOperator.OPERATOR_DIVIDE);
		ui.put(TokenKind.TOKEN_PLUS, AstUnaryOperator.OPERATOR_POSITIVE);
		ui.put(TokenKind.TOKEN_MINUS, AstUnaryOperator.OPERATOR_NEGATIVE);
		ui.put(TokenKind.TOKEN_STAR, AstUnaryOperator.OPERATOR_DEREFERENCE);
		ui.put(TokenKind.TOKEN_BITAND, AstUnaryOperator.OPERATOR_GETADDRESS);
		
		
		binaryOperations=Collections.unmodifiableMap(bi);
		unaryOperations=Collections.unmodifiableMap(ui);
		
			
	}
	public static AstBinaryOperator getBinaryOperator(TokenKind kind) {
		return binaryOperations.get(kind);
		
	}
	public static AstUnaryOperator getUnaryOperator(TokenKind kind) {
		return unaryOperations.get(kind);
		
	}
	
}