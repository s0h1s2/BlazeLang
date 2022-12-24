package blaze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.List;
//
//import blaze.ast.*;
//import blaze.types.IntType;

public class Main {
	public static void main(String[] args) throws IOException {
		String example=new String(Files.readAllBytes(Paths.get("/home/mrprogrammez/eclipse-workspace/blaze/blaze/examples/basic.blz")));
		
		Lexer lex=new Lexer(example); //
		Parser parser=new Parser(lex);
		/*List<Stmt> ast=*/parser.parse();
		//SymbolTable("name",new VarDeclaration(example, null, null));
		SymbolTable table=new SymbolTable();
		//DeclarationResolver resolver=new DeclarationResolver(table, ast);
		//resolver.resolve();
		//TypeChecker type=new TypeChecker(ast,table);
		//type.check();
	}

}
