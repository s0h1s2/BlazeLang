package blaze.ast;

import java.util.LinkedList;

import blaze.IVisitor;

public class FieldAccess extends Expression {
    public Expression left;
    public LinkedList<String> subFields;
    public FieldAccess(Expression left) {
        this.left = left;
        this.subFields=new LinkedList<>();
    }
    public void addSubField(String name){
        subFields.addLast(name);
    }
    @Override
    public Object accept(IVisitor<?> visitor) {
        // TODO Auto-generated method stub
        return visitor.visit(this);
    }
    @Override
    public Object getValue() {
        // TODO Auto-generated method stub
        return null;
    }

    

}
