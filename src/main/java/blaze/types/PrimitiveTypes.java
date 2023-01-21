package blaze.types;

public enum PrimitiveTypes implements Type{
    INT_TYPE,
    BOOL_TYPE,
    CHAR_TYPE;

    @Override
    public boolean equals(Type type) {
        return this==type;
    }
}
