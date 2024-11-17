package model.expressions;

import model.ADT.MyIMap;
import model.types.IntType;
import model.values.IValue;
import exceptions.ExpressionException;
import model.values.IntValue;

public class ArithmeticalExpression implements IExpression {
    private IExpression left;
    private IExpression right;
    private ArithmeticalOperator operator;

    public ArithmeticalExpression(IExpression l, ArithmeticalOperator op, IExpression r) {
        this.left = l;
        this.operator = op;
        this.right = r;
    }

    @Override
    public IValue eval(MyIMap symTbl) throws ExpressionException {
        IValue leftVal = left.eval(symTbl);
        IValue rightVal = right.eval(symTbl);
        if (!leftVal.getType().equals(new IntType()))
            throw new ExpressionException("Left value ~" + leftVal.getType() + "~ is not an integer");
        if (!rightVal.getType().equals(new IntType()))
            throw new ExpressionException("Right value ~" + rightVal.getType() + "~ is not an integer");
        IntValue leftInt = (IntValue) leftVal;
        IntValue rightInt = (IntValue) rightVal;
        switch (operator) {
            case PLUS:
                return new IntValue(leftInt.getValue() + rightInt.getValue());
            case MINUS:
                return new IntValue(leftInt.getValue() - rightInt.getValue());
            case MULTIPLY:
                return new IntValue(leftInt.getValue() * rightInt.getValue());
            case DIVIDE:
                if (rightInt.getValue() == 0)
                    throw new ExpressionException("Division by zero");
                return new IntValue(leftInt.getValue() / rightInt.getValue());
            default:
                throw new ExpressionException("Unknown operator");
        }
    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticalExpression(left.deepCopy(), operator, right.deepCopy());
    }

    @Override
    public String toString() {
        return this.left.toString() + this.operator.toString() + " " + this.right.toString();
    }

}
