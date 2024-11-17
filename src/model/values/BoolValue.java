package model.values;

import model.types.IType;
import model.types.BoolType;

public class BoolValue implements IValue{
    private boolean value;

    public BoolValue(boolean value){
        this.value = value;
    }
    public boolean getValue(){
        return this.value;
    }
    @Override
    public IType getType(){
        return new BoolType();
    }
    @Override
    public boolean equals(IValue v) {
        if(!v.getType().equals(this.getType()))
            return false;
        return ((BoolValue)v).getValue() == this.getValue();
    }
    @Override
    public String toString(){
        return Boolean.toString(this.value);
    }

    }
