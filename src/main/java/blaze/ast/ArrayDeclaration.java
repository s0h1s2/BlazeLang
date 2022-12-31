package blaze.ast;

import java.util.List;

import javax.print.attribute.standard.Media;

import blaze.IVisitor;
import blaze.types.Type;

public class ArrayDeclaration extends Declaration{
    public Expression size;
    public Type type;
    public List<Type> elements;
    public String name;
    
    public ArrayDeclaration(String name,Expression size, Type type, List<Type> elements) {
        this.name=name;
        this.size = size;
        this.type = type;
        this.elements = elements;
    }
    @Override
    public Object accept(IVisitor<?> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Type getType() {
        return null;
    }
    
}
