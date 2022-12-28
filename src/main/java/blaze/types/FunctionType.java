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

public class FunctionType extends Type {
    SymbolTable localAndParam;

    public FunctionType(SymbolTable localAndParam) {
        this.localAndParam = localAndParam;
    }
}
