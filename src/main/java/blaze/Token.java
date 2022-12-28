/*
 ** Dec 28,2022 Shkar Sardar
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

public class Token {
    private TokenKind kind;
    private Object value;

    Token(TokenKind kind, Object value) {
        this.kind = kind;
        this.value = value;
    }

    Token(TokenKind kind) {
        this.kind = kind;
    }

    public TokenKind getKind() {
        return this.kind;
    }

    public Object getValue() {
        return this.value;
    }

}
