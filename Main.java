package blaze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import blaze.ast.*;

public class Main {
	public static void main(String[] args) throws IOException {
		String example=new String(Files.readAllBytes(Paths.get("/home/mrprogrammez/eclipse-workspace/blaze/blaze/examples/basic.blz")));
		
		Lexer lex=new Lexer(example); //
		Parser parser=new Parser(lex);
		List<Stmt> ast=parser.parse();
		TypeChecker type=new TypeChecker(ast);
		type.check();
		//System.out.println();
		//List<Stmt> stmts=parser.parse();
		//System.out.println(stmts.get(0).getClass());
	}

}
