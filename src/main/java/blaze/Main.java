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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import blaze.ast.Program;
import blaze.codegenerator.Intel86;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO: check if file or files or directory exist
        String example = new String(
                Files.readAllBytes(Paths.get("/home/mrprogrammez/Desktop/blaze/examples/varDecl.blz")));
        Lexer lex = new Lexer(example); //
        Parser parser = new Parser(lex);
        Program ast = parser.parse();
        SymbolTable table = new SymbolTable(null);
        DeclarationResolver binder = new DeclarationResolver(table);
        binder.visit(ast);
        // TypeChecker checker=new TypeChecker(table);
        // checker.visit(ast);


    }

}
