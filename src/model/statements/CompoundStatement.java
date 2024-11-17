package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADT.MyIStack;
import model.state.PrgState;

public class CompoundStatement implements IStatement{
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement one, IStatement two){
        this.first = one;
        this.second = two;
    }
    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionException {
        MyIStack<IStatement> stack = p.getExeStack();
        stack.push(this.second);
        stack.push(this.first);
        return p;
    }
    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(this.first,this.second);
    }
    @Override
    public String toString(){
        return "("+first.toString()+";"+second.toString()+")";
    }
}
