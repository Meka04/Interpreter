package model.types;
import model.values.*;

public interface IType {
    boolean equals(IType t);
    IValue getDefaultValue();
}
