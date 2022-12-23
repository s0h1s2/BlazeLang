package blaze;

import java.util.Iterator;
import java.util.List;

import blaze.ast.Declaration;
import blaze.ast.Stmt;
import blaze.ast.VarDeclaration;

public class DeclarationResolver{
	private SymbolTable table;
	public DeclarationResolver(SymbolTable table) {
		this.table = table;
	}
	
	public void resolve(Stmt stmt) {
			if(stmt instanceof Declaration) {
				if(stmt instanceof VarDeclaration) {
					if(table.containDecl(((VarDeclaration) stmt).name)) {
						System.err.println("Redeclare variable isn't allowed.");
						return ;
					}else {
						table.define(((VarDeclaration) stmt).name, (Declaration)stmt);
					}
				}
			}
		}
}
