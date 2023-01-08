package blaze.ast;

import blaze.IVisitor;

public class FieldAccess extends Expression {
    public Expression left;
    public Expression right;
    public FieldAccess(Expression left, Expression right) {
        this.left = left;
        this.right = right;
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
