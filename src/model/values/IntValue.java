package model.values;

import model.types.IType;
import model.types.IntType;

public class IntValue implements IValue{
    private int value;
    public IntValue(int v){
        this.value = v;
    }
    public int getValue(){
        return this.value;
    }
    @Override
    public IType getType(){
        return new IntType();
    }
    @Override
    public boolean equals(IValue v){
        if(!v.getType().equals(this.getType()))
            return false;
        return ((IntValue)v).getValue() == this.getValue();
    }
    @Override
    public String toString(){
        return Integer.toString(this.value);
    }
}
