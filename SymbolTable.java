package blaze;
import java.util.HashMap;
import java.util.Map;

import blaze.ast.Declaration;;

public class SymbolTable{
	private Map<String,Declaration> decls;
	public SymbolTable() {
		this.decls=new HashMap<String, Declaration>();
	}
	public boolean define(String name,Declaration decl) {
		if(!decls.containsKey(name)) {
			decls.put(name,decl);
			return true;
		}
		return false;
	}
	public boolean containDecl(String name) {
		if(decls.containsKey(name)) {
			return true;
		}
		return false;
	}
	
	public Declaration getDecl(String name) {
		if(decls.containsKey(name)) {
			return decls.get(name);
		}
		return null;
	}
	
}