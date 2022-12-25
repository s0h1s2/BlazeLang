package blaze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import blaze.ast.*;
import blaze.util.AstOperators.AstBinaryOperator;
public class Main {
	public static void main(String[] args) throws IOException {
		String example=new String(Files.readAllBytes(Paths.get("/home/mrprogrammez/eclipse-workspace/blaze/blaze/examples/basic.blz")));
		
		Lexer lex=new Lexer(example); //
		Parser parser=new Parser(lex);
		Program ast=parser.parse();
		SymbolTable table=new SymbolTable();
		VisitorTesting visitor=new VisitorTesting();
		visitor.visit(ast);
	}

}
