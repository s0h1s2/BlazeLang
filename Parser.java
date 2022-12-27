/*
 ** Dec 27,2022 Shkar Sardar
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

import java.util.ArrayList;

import java.util.List;

import blaze.ast.*;
import blaze.types.*;

import blaze.util.AstOperation;

public class Parser {
    private Lexer lex;
    private Token token;
    private Token prev;

    public Parser(Lexer lex) {
        this.lex = lex;
        this.token = lex.getToken();
    }

    private void advance() {
        this.prev = this.token;
        this.token = lex.getToken();
    }

    private Token peek() {
        return this.token;
    }

    private boolean match(TokenKind kind) {
        if (token.getKind() == kind) {
            advance();
            return true;
        }
        return false;
    }

    private void expect(TokenKind kind, String err) {
        if (!match(kind)) {
            throw new Error(err);
        }
    }

    public Program parse() {
        ArrayList<Declaration> decls = new ArrayList<>();
        while (token.getKind() != TokenKind.TOKEN_EOF) {
            if (match(TokenKind.TOKEN_VAR)) {
                decls.add(parseVarDeclaration());

            } else if (match(TokenKind.TOKEN_FUN)) {
                decls.add(parseFunctionDeclaration());
            } else {
                throw new Error("Unexpected declaration.");
            }

        }
        return new Program(decls);

    }

    private Stmt parseStatement() {
        if (match(TokenKind.TOKEN_IF)) {
            return parseIfStatement();
        } else if (match(TokenKind.TOKEN_VAR)) {
            return parseVarDeclaration();
        } else if (match(TokenKind.TOKEN_WHILE)) {
            return parseWhileStatement();
        } else if (match(TokenKind.TOKEN_RETURN)) {
            return parseReturn();
        } else {
            Expression expr = expression();
            expect(TokenKind.TOKEN_SEMICOLON, "Expected ';'");
            return expr;
        }
    }

    private ReturnStatement parseReturn() {
        Expression expr = null;
        if (peek().getKind() != TokenKind.TOKEN_SEMICOLON) {
            expr = expression();
        }
        expect(TokenKind.TOKEN_SEMICOLON, "Expected ';'");
        return new ReturnStatement(expr);
    }

    private WhileStatement parseWhileStatement() {
        expect(TokenKind.TOKEN_LPARAN, "Expected '('");
        Expression condition = expression();
        expect(TokenKind.TOKEN_RPARAN, "Expected ')'");
        BlockStatement block = parseBlockStatement();

        return new WhileStatement(condition, block);
    }

    private IfStatement parseIfStatement() {
        expect(TokenKind.TOKEN_LPARAN, "Expected '('");
        Expression condition = expression();
        expect(TokenKind.TOKEN_RPARAN, "Expected ')'");
        BlockStatement then = parseBlockStatement();
        BlockStatement els = null;
        if (match(TokenKind.TOKEN_ELSE)) {
            els = parseBlockStatement();
        }
        return new IfStatement(condition, then, els);
    }

    private BlockStatement parseBlockStatement() {
        List<Stmt> statements = new ArrayList<>();
        expect(TokenKind.TOKEN_LBRACE, "Expected '{'");
        while (!match(TokenKind.TOKEN_EOF) && peek().getKind() != TokenKind.TOKEN_RBRACE) {
            statements.add(parseStatement());
        }
        expect(TokenKind.TOKEN_RBRACE, "Expected '{'");

        return new BlockStatement(statements);
    }

    private Parameter parseFunctionParameter() {
        expect(TokenKind.TOKEN_IDENTIFIER, "Expected identiifer.");
        String name = prev.getValue().toString();
        expect(TokenKind.TOKEN_COLON, "':' required.");
        Type type = parseType();
        return new Parameter(name, type);
    }

    private List<Parameter> parseFunctionParameters() {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(parseFunctionParameter());
        while (match(TokenKind.TOKEN_COMMA)) {
            parameters.add(parseFunctionParameter());
        }
        return parameters;
    }

    private Declaration parseFunctionDeclaration() {
        BlockStatement body = null;
        Type returnType = null;
        List<Parameter> parameters = null;
        expect(TokenKind.TOKEN_IDENTIFIER, "Expect name after 'fun' keyword.");
        String name = prev.getValue().toString();
        expect(TokenKind.TOKEN_LPARAN, "Expect '('");
        if (token.getKind() != TokenKind.TOKEN_RPARAN) {
            parameters = parseFunctionParameters();
        }
        expect(TokenKind.TOKEN_RPARAN, "Expected ')'");
        expect(TokenKind.TOKEN_COLON, "Expected ':'");
        returnType = parseType();
        body = parseBlockStatement();
        return new FunctionDeclaration(name, parameters, body, returnType);
    }

    private Type parseType() {
        if (match(TokenKind.TOKEN_INT)) {
            return new IntType();
        } else if (match(TokenKind.TOKEN_CHAR)) {
            return new CharType();
        } else if (match(TokenKind.TOKEN_BOOL)) {
            return new BoolType();
        }
        throw new Error("Unimplemented type");
    }

    private Declaration parseVarDeclaration() {
        expect(TokenKind.TOKEN_IDENTIFIER, "Expect identifier");
        String name = prev.getValue().toString();
        expect(TokenKind.TOKEN_COLON, "Expect ':'");
        Type type = parseType();
        Expression initExpr = null;
        if (match(TokenKind.TOKEN_ASSIGN)) {
            initExpr = expression();
        }
        expect(TokenKind.TOKEN_SEMICOLON, "Expected ';' after statement");
        return new VarDeclaration(name, initExpr, type);
    }

    private Expression expression() {
        // lowest to highest precedence.
        return assignment();
    }

    private Expression assignment() {
        Expression left = ternary();

        if (match(TokenKind.TOKEN_ASSIGN)) {
            if (!(left instanceof VariableExpression)) {
                throw new Error("Only identifier can be assigned.");
            }
            left = new Assignment(left, assignment());
        }
        return left;
    }

    private Expression ternary() {
        Expression expr = logicalOr();
        if (match(TokenKind.TOKEN_QUESTION)) {
            Expression then = ternary();
            expect(TokenKind.TOKEN_COLON, "Expected ':'");
            Expression elseExpr = ternary();
            expr = new Ternary(expr, then, elseExpr);
        }
        return expr;
    }

    private Expression logicalOr() {
        Expression left = logicalAnd();
        while (match(TokenKind.TOKEN_OR)) {
            left = new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), logicalAnd());
        }
        return left;
    }

    private Expression logicalAnd() {
        Expression left = bitOr();
        while (match(TokenKind.TOKEN_AND)) {
            left = new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), bitOr());
        }
        return left;
    }

    private Expression bitOr() {
        Expression left = bitXor();
        while (match(TokenKind.TOKEN_BITOR)) {
            left = new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), bitXor());
        }
        return left;
    }

    private Expression bitXor() {
        Expression left = bitAnd();
        while (match(TokenKind.TOKEN_BITWISE)) {
            left = new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), bitAnd());
        }
        return left;

    }

    private Expression bitAnd() {
        Expression left = equality();
        while (match(TokenKind.TOKEN_BITAND)) {
            left = new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), equality());
        }
        return left;
    }

    private Expression equality() {
        Expression left = relational();
        while (match(TokenKind.TOKEN_EQUAL) || match(TokenKind.TOKEN_NOTEQUAL)) {
            left = new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), relational());
        }
        return left;

    }

    private Expression relational() {
        Expression left = addition();
        while (match(TokenKind.TOKEN_GE) || match(TokenKind.TOKEN_LE) || match(TokenKind.TOKEN_GEQ)
                || match(TokenKind.TOKEN_LEQ)) {
            left = new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), addition());
        }
        return left;

    }

    private Expression addition() {
        Expression left = multiplicative();
        while (match(TokenKind.TOKEN_PLUS) || match(TokenKind.TOKEN_MINUS)) {
            left = new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), multiplicative());
        }
        return left;

    }

    private Expression multiplicative() {
        Expression left = call();
        while (match(TokenKind.TOKEN_STAR) || match(TokenKind.TOKEN_SLASH)) {
            left = new BinaryOp(left, AstOperation.getBinaryOperator(prev.getKind()), call());
        }
        return left;

    }

    private Expression call() {
        Expression expr = unary();
        List<Expression> args = new ArrayList<>();
        if (peek().getKind() == TokenKind.TOKEN_LPARAN) {
            match(TokenKind.TOKEN_LPARAN);
            if (peek().getKind() != TokenKind.TOKEN_RPARAN) {
                args.add(expression());
                while (match(TokenKind.TOKEN_COMMA)) {
                    args.add(expression());
                }
            }
            expect(TokenKind.TOKEN_RPARAN, "Expected ')'");
            return new CallExpr(expr, args);
        }
        return expr;
    }

    private Expression unary() {
        if (match(TokenKind.TOKEN_BANG) || match(TokenKind.TOKEN_MINUS) || match(TokenKind.TOKEN_PLUS)
                || match(TokenKind.TOKEN_STAR) || match(TokenKind.TOKEN_BITAND)) {
            return new Unary(AstOperation.getUnaryOperator(prev.getKind()), unary());
        } else {
            return postfix();
        }

    }

    private Expression postfix() {
        Expression left = primary();
        if (match(TokenKind.TOKEN_INCREMENT) || match(TokenKind.TOKEN_DECREMENT)) {
            left = new Modify(left, AstOperation.getModifyOperator(prev.getKind()));
        }
        return left;
    }

    private Expression primary() {
        if (match(TokenKind.TOKEN_INTEGER)) {
            return new Int((int) prev.getValue());
        } else if (match(TokenKind.TOKEN_TRUE)) {
            return new Bool(true);
        } else if (match(TokenKind.TOKEN_FALSE)) {
            return new Bool(false);
        } else if (match(TokenKind.TOKEN_IDENTIFIER)) {
            return new VariableExpression(prev.getValue().toString());
        }
        throw new Error("Syntax Error");
    }
}
