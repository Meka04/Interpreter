package model.expressions;

import exceptions.ExpressionException;
import model.ADT.MyIMap;
import model.values.IValue;

public class ValExpression implements IExpression{
    private final IValue val;
    public ValExpression(IValue v){this.val = v;}
    @Override
    public IValue eval(MyIMap<String, IValue> symTbl) throws ExpressionException {return this.val;}
    @Override
    public IExpression deepCopy() {return new ValExpression(this.val);}
    @Override
    public String toString(){return this.val.toString();}
}
