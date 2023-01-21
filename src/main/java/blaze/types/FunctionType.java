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

package blaze.types;

import blaze.SymbolTable;

public class FunctionType implements Type {
    
    private SymbolTable localAndParam;
    public Type returnType;
    
    public FunctionType(SymbolTable localAndParam,Type returnType) {
        this.localAndParam = localAndParam;
        this.returnType=returnType;
    }
    public SymbolTable getTable(){
        return localAndParam;
    }
    @Override
    public boolean equals(Type type) {
        return false;
    }
}
