package blaze.types;

import java.util.HashMap;


public class StructType extends CustomType {
    private HashMap<String,Type> fields;
    public StructType(String typeName,HashMap<String,Type> fields) {
        super(typeName);
        this.fields=fields;
    }
    public boolean isFieldExist(String name){
        return this.fields.containsKey(name);
    }
    
}
