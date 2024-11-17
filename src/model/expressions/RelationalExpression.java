package model.expressions;


import controller.Controller;
import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.ADT.MyIMap;
import model.expressions.ArithmeticalExpression;
import model.expressions.ArithmeticalOperator;
import model.expressions.ValExpression;
import model.expressions.VarExpression;
import model.state.PrgState;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;
import repo.IRepo;
import repo.Repo;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;

public class RelationalExpression implements IExpression{
    IExpression leftExp;
    IExpression rightExp;
    RelationalOperator operator;

    public RelationalExpression(IExpression leftExp, IExpression rightExp, RelationalOperator operator){
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        this.operator = operator;
    }

    public IValue eval(MyIMap<String, IValue> symTbl) throws ExpressionException, KeyNotFoundException {
        IValue leftValue = leftExp.eval(symTbl);
        IValue rightValue = rightExp.eval(symTbl);

        if(!leftValue.getType().equals(new IntType()))
            throw new ExpressionException("Value " + leftValue + "is not an int");
        if(!rightValue.getType().equals(new IntType()))
            throw new ExpressionException("Value " + rightValue + "is not an int");

        IntValue leftInt = (IntValue) leftValue;
        IntValue rightInt = (IntValue) rightValue;

        switch (operator){
            case EQUAL:
                return new BoolValue(leftInt.getValue() == rightInt.getValue());
            case GEQUAL:
                return new BoolValue(leftInt.getValue() >= rightInt.getValue());
            case GREATER:
                return new BoolValue(leftInt.getValue() > rightInt.getValue());
            case LEQUAL:
                return new BoolValue(leftInt.getValue() <= rightInt.getValue());
            case LESS:
                return new BoolValue(leftInt.getValue() < rightInt.getValue());
            case NOTEQUAL:
                return new BoolValue(leftInt.getValue() != rightInt.getValue());
            default:
                throw new ExpressionException("Unknown operator " + operator);
        }
    }

    public IExpression deepCopy(){
        return new RelationalExpression(leftExp.deepCopy(), rightExp.deepCopy(), operator);
    }

    public String toString(){
        return leftExp.toString() + rightExp.toString() + operator.toString();
    }
}