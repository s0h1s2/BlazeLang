/*
 ** Dec 26,2022 Shkar Sardar
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 **
 */

package blaze.ast;

public abstract class Expression extends Stmt {
    private String register=null;
    
    public void setReg(String name){
        this.register=name;
    }
    public String getReg(){
        return this.register;
    }
    public abstract Object getValue();
    
}   
