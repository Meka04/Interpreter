package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.state.PrgState;

public interface IStatement {
    public PrgState execute(PrgState p) throws StatementException, ExpressionException;
    IStatement deepCopy();
}
