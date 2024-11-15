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

public abstract class Type {
    
    public boolean equals(Type type){
        if(type==null) return false;
        return this.getClass()==type.getClass();
    }
} 