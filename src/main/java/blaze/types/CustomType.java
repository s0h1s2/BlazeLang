package blaze.types;

public class CustomType extends Type {
    public String typeName;
    public CustomType(String typeName){
        this.typeName=typeName;
    }
    @Override
    public boolean equals(Type type){
        if(!(type instanceof CustomType)){
            return false;
        }
        return this.typeName.equals(((CustomType) type).typeName);
    }
}
