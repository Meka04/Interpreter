package model.expressions;

import exceptions.ExpressionException;
import model.ADT.MyIMap;
import model.values.IValue;

public interface IExpression {
    IValue eval(MyIMap<String,IValue> symTbl) throws ExpressionException;
    IExpression deepCopy();
}
