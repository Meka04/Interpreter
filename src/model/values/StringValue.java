package model.values;

import model.types.IType;
import model.types.StringType;

public class StringValue implements IValue {
    private String value;

    public StringValue(String v) {
        this.value = v;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public boolean equals(IValue v) {
        if (!v.getType().equals(this.getType()))
            return false;
        return ((StringValue) v).getValue() == this.getValue();
    }

    @Override
    public String toString() {
        return this.value;
    }
}
