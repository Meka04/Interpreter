package model.statements;

import model.state.PrgState;

public class NopStatement implements IStatement{
    public NopStatement(){ }
    @Override
    public PrgState execute(PrgState p){
        return p;
    }
    @Override
    public IStatement deepCopy(){
        return null;
    }
    @Override
    public String toString(){
        return "No valid operation";
    }
}
