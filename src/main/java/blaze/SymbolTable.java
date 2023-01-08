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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.text.TabExpander;

import blaze.types.Type;

public class SymbolTable {
    private Map<String, Type> decls;
    private SymbolTable parent=null;
    // public SymbolTable() {
    //     this.decls = new HashMap<String, Type>();
    //     this.parent=null;
    // }

    public SymbolTable(SymbolTable parent) {
        this.decls = new HashMap<String, Type>();
        this.parent = parent;
    }
    public SymbolTable getParent(){
        return this.parent;
    }
    public Set<String> getKeys(){
        return this.decls.keySet();
    }
    public boolean haveParent(){
        if(this.parent==null){
            return false;
        }
        return true;
    }
    public boolean define(String name, Type type) {
        if (!decls.containsKey(name)) {
            decls.put(name, type);
            return true;
        }
        return false;
    }
    public boolean containDecl(String name) {
        if(decls.containsKey(name)){
            return true;
        }
        return false;
    }
    public Type getDecl(String name) {
        if (containDecl(name)) {
            return decls.get(name);
        }
        return null;
    }   

}