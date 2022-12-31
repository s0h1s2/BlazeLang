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

public enum TokenKind {
    // data
    TOKEN_INTEGER_LITERAL,
    TOKEN_CHAR_LITERAL,
    // keywords
    TOKEN_IF, TOKEN_IDENTIFIER, TOKEN_INT, TOKEN_VOID, TOKEN_CHAR, TOKEN_FUN, TOKEN_STRUCT, TOKEN_ENUM, TOKEN_VAR,
    TOKEN_BOOL, TOKEN_TRUE, TOKEN_FALSE, TOKEN_ELSE, TOKEN_WHILE, TOKEN_RETURN,
    // single tokens
    TOKEN_ASSIGN, TOKEN_LPARAN, TOKEN_RPARAN, TOKEN_PLUS, TOKEN_MINUS, TOKEN_STAR, TOKEN_SLASH, TOKEN_SEMICOLON,
    TOKEN_BANG, TOKEN_LE, TOKEN_GE, TOKEN_BITAND, TOKEN_BITOR, TOKEN_MODULO, TOKEN_COLON, TOKEN_QUESTION, TOKEN_BITWISE,
    TOKEN_COMMA, TOKEN_LBRACE, TOKEN_RBRACE,
    // double tokens
    TOKEN_EQUAL, TOKEN_NOTEQUAL, TOKEN_LEQ, TOKEN_GEQ, TOKEN_AND, TOKEN_OR, TOKEN_LEFTSHIFT, TOKEN_RIGHTSHIFT, TOKEN_INCREMENT, TOKEN_DECREMENT,

    // end
    TOKEN_EOF,
}
