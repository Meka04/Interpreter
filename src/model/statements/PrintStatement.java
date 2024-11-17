package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;

public class PrintStatement implements IStatement {
    private IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }
    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionException {
            IValue v = expression.eval(p.getSymTbl());
            p.getOutList().add(v);
            return p;
    }
    @Override
    public String toString(){
        return "print("+expression+")";
    }
    @Override
    public IStatement deepCopy(){
        return new PrintStatement(this.expression);
    }
}
