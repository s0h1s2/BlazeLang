package blaze.ast;

import blaze.IVisitor;

public class CharLit extends Expression {
    private char c;
    public CharLit(char c){
        this.c=c;
    }
    public char getValue(){
        return c;
    }
    @Override
    public Object accept(IVisitor<?> visitor) {
        return visitor.visit(this);
    }

}
