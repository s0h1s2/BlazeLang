/*
 ** Jan 2,2022 Shkar Sardar
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 **
 */
package blaze.codegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.BinaryOperator;

import blaze.IVisitor;
import blaze.ast.ArrayDeclaration;
import blaze.ast.Assignment;
import blaze.ast.BinaryOp;
import blaze.ast.BlockStatement;
import blaze.ast.Bool;
import blaze.ast.CallExpr;
import blaze.ast.CharLit;
import blaze.ast.Declaration;
import blaze.ast.Expression;
import blaze.ast.FunctionDeclaration;
import blaze.ast.IfStatement;
import blaze.ast.Int;
import blaze.ast.Modify;
import blaze.ast.Parameter;
import blaze.ast.Program;
import blaze.ast.ReturnStatement;
import blaze.ast.Stmt;
import blaze.ast.Struct;
import blaze.ast.Ternary;
import blaze.ast.Unary;
import blaze.ast.VarDeclaration;
import blaze.ast.VariableExpression;
import blaze.ast.WhileStatement;
import blaze.types.BoolType;
import blaze.types.CharType;
import blaze.types.IntType;
import blaze.types.Type;
import blaze.util.AstOperators;

public class Intel86 {
    private FileWriter writer;

    public Intel86(File file) {
    }
}