package model.types;
import model.values.BoolValue;
import model.values.IValue;

public class BoolType implements IType{
    public boolean equals(IType t){
        return t instanceof BoolType;
    };
    @Override
    public IValue getDefaultValue(){
        return new BoolValue(false);
    }
    public BoolType(){ };
    @Override
    public String toString() {
        return "bool";
    }
}
