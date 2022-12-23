package blaze;

import java.util.Hashtable;


public class Lexer {
	private String source;
	private int current=0;
	private Hashtable<String, TokenKind> keywords;
	
	Lexer(String source){
		this.source=source;	
		this.keywords=new Hashtable<>();
		initKeywords();
		
	}
	private void advance() {
		if(current<source.length()) {
			current++;
		}
	}
	private boolean isAtEnd() {
		return current>=source.length();
	}
	private char chr() {
		return source.charAt(current);
	}
	private boolean isDigit(char c) {
		return c>='0' && c<='9';
	}
	private boolean match(char c) {
		if(current+1>=source.length()) {
			return false;
		}
		if(c==chr()) {
			advance();
			return true;
		}else {
			return false;
		}
		
	}
	private void initKeywords() {
		this.keywords.put("if", TokenKind.TOKEN_IF);
		this.keywords.put("bool", TokenKind.TOKEN_BOOL);
		this.keywords.put("char", TokenKind.TOKEN_CHAR);
		this.keywords.put("int", TokenKind.TOKEN_INT);
		
		this.keywords.put("fun", TokenKind.TOKEN_FUN);
		this.keywords.put("struct", TokenKind.TOKEN_STRUCT);
		this.keywords.put("enum", TokenKind.TOKEN_ENUM);
		this.keywords.put("var", TokenKind.TOKEN_VAR);
		this.keywords.put("true", TokenKind.TOKEN_TRUE);
		this.keywords.put("false", TokenKind.TOKEN_FALSE);
		
		
		
				
	}
	private Token makeSingleToken(TokenKind kind) {
		advance();
		return new Token(kind);
	}
	private boolean isAlpha(char c) {
		return (c>='a' && c<='z') || (c>='A' && c<='Z');
	}
	private boolean isIdentifier(char c) {
		return isDigit(c) || isAlpha(c);
	}
	public Token getToken() {
		while(!isAtEnd()) {
			switch(chr()) {
				case ' ':
				case '\t':
				case '\n':{
					while(!isAtEnd() && Character.isWhitespace(chr())) {
						advance();
					}
					break;
				}
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':{
					int result=chr()-'0';
					advance();
					while(!isAtEnd() && isDigit(chr())) {
						result*=10;
						result+=chr()-'0';
						advance();
					}
					return new Token(TokenKind.TOKEN_INTEGER, result);
				}
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
				case '_':{
					String name="";
					while(!isAtEnd() && isIdentifier(chr())) {
						name+=chr();
						advance();
					}
					if(keywords.get(name)!=null) {
						return new Token(keywords.get(name),name);
					}
					return new Token(TokenKind.TOKEN_IDENTIFIER,name);
				}
				case '(':
					advance();
					return new Token(TokenKind.TOKEN_LPARAN,'(');
				case ')':
					advance();
					return new Token(TokenKind.TOKEN_RPARAN,')');
				case '{':
					advance();
					return new Token(TokenKind.TOKEN_LBRACE, '{');
				case '}':
					advance();
					return new Token(TokenKind.TOKEN_RBRACE, '{');
				case '=':{
					advance();
					if(match('=')) {
						return new Token(TokenKind.TOKEN_EQUAL, "==");
					}
					return new Token(TokenKind.TOKEN_ASSIGN,"=");
				}
				
				case '!':{
					advance();
					if(match('=')) {
						return new Token(TokenKind.TOKEN_NOTEQUAL,"!=");					
					}
					return new Token(TokenKind.TOKEN_BANG,"!");
					
				}
				case '<':{
					advance();
					if(match('=')) {
						return new Token(TokenKind.TOKEN_LEQ,"<=");				
					}else if(match('<')) {
						return new Token(TokenKind.TOKEN_LEFTSHIFT,"<<");
					}
					return new Token(TokenKind.TOKEN_LE,"<");
					
				}
				case '>':{
					advance();
					if(match('=')) {
						return new Token(TokenKind.TOKEN_GEQ,">=");					
					}else if(match('>')) {
						return new Token(TokenKind.TOKEN_RIGHTSHIFT,">>");
					}
					return new Token(TokenKind.TOKEN_GE,">");
				}
				case '+':{
					advance();
					return new Token(TokenKind.TOKEN_PLUS,"+");
				}
				case '-':{
					advance();
					return new Token(TokenKind.TOKEN_MINUS,"-");
				}
				case '*':{
					advance();
					return new Token(TokenKind.TOKEN_STAR,"*");
				}
				case '/':{
					advance();
					if(match('/')) {
						while(!isAtEnd() && !match('\n')) {
							advance();
						}
						continue;
					}else {
						return new Token(TokenKind.TOKEN_SLASH,"/");	
					}
					
				}
				case '&':{
					advance();
					if(match('&')) {
						return new Token(TokenKind.TOKEN_AND,"&&");
						
					}
					return new Token(TokenKind.TOKEN_BITAND,"&");
					
				}
				case '|':{
					advance();
					if(match('|')) {
						return new Token(TokenKind.TOKEN_OR,"||");
					}
					return new Token(TokenKind.TOKEN_BITOR,"|");
					
				}
				case '%':{
					advance();
					return new Token(TokenKind.TOKEN_MODULO,"%");
					
				}
				case ':':{
					advance();
					return new Token(TokenKind.TOKEN_COLON,":");
				}
				case '?':{
					advance();
					return new Token(TokenKind.TOKEN_QUESTION,"?");	
				}
				case '^':{
					advance();
					return new Token(TokenKind.TOKEN_BITWISE,"^");	
				}
				
				case ';':{
					advance();
					return new Token(TokenKind.TOKEN_SEMICOLON,";");
				}
				case ',':{
					advance();
					return new Token(TokenKind.TOKEN_COMMA,",");
				}
				default:{
					System.err.println("Skipped unknown character '%c'".formatted(chr()));
					advance();
				}
				
				
			}
		}
		
		
		return new Token(TokenKind.TOKEN_EOF,"EOF");
	}
	
}
