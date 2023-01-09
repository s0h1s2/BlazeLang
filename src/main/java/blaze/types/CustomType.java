package blaze.types;

public class CustomType extends Type {
    private String typeName;
    public CustomType(String typeName){
        this.typeName=typeName;
    }
    public String getTypeName(){
        return this.typeName;
    }
    @Override
    public boolean equals(Type type){
        if(!(type instanceof CustomType)){
            return false;
        }
        return this.typeName.equals(((CustomType) type).typeName);
    }

}
