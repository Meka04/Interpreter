package model.expressions;

import exceptions.ExpressionException;
import model.ADT.MyIMap;
import model.values.IValue;

public class VarExpression implements IExpression{
    private final String id;
    public VarExpression(String id){this.id = id;}
    @Override
    public IValue eval(MyIMap<String, IValue> symTbl) throws ExpressionException {
        if(!symTbl.containsKey(id))
            throw new ExpressionException("Variable '"+id+"' is not defined");
        return symTbl.getValue(id);
    }
    @Override
    public IExpression deepCopy() {return new VarExpression(this.id);}
    @Override
    public String toString(){return this.id;}
}
