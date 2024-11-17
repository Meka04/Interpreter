package model.expressions;


import exceptions.ExpressionException;
import model.ADT.MyIMap;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;


public class LogicalExpression implements IExpression{
    private IExpression left;
    private IExpression right;
    private LogicalOperator operator;

    public LogicalExpression(IExpression l, LogicalOperator op, IExpression r){
        this.left = l;
        this.operator = op;
        this.right = r;
    }
    @Override
    public IValue eval(MyIMap<String,IValue> symTbl)throws ExpressionException{
        IValue leftVal = this.left.eval(symTbl);
        IValue rightVal = this.right.eval(symTbl);
        if(!leftVal.getType().equals(new BoolType()))
            throw new ExpressionException("Left value "+ leftVal.getType() + " is not a bool");
        if(!rightVal.getType().equals(new BoolType()))
            throw new ExpressionException("Right value "+ rightVal.getType() + " is not a bool");

        BoolValue leftBool = (BoolValue)leftVal;
        BoolValue rightBool = (BoolValue)rightVal;

        switch(operator){
            case AND:
                return new BoolValue(leftBool.getValue() && rightBool.getValue());
            case OR:
                return new BoolValue(leftBool.getValue() || rightBool.getValue());
            default:
                throw new ExpressionException("Unknown operator");
        }
    }
    @Override
    public String toString(){
        return left.toString() + operator.toString() + right.toString();
    }
    @Override
    public IExpression deepCopy(){
        return new LogicalExpression(this.left, this.operator, this.right);
    }
}
