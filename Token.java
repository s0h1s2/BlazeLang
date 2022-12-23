package blaze;

public class Token {
	private TokenKind kind;
	private Object value;
	Token(TokenKind kind,Object value){
		this.kind=kind;
		this.value=value;
	}
	Token(TokenKind kind){
		this.kind=kind;
	}
	public TokenKind getKind() {
		return this.kind;
	}
	public Object getValue() {
		return this.value;
	}
	
	
}
