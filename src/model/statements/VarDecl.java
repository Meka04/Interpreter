package model.statements;

import exceptions.StatementException;
import model.ADT.MyIMap;
import model.state.PrgState;
import model.statements.IStatement;
import model.types.IType;

public class VarDecl implements IStatement{
    private String name;
    private IType type;

    public VarDecl(String name, IType type){
        this.name = name;
        this.type = type;
    }
    // -> we can't have String a and int a!!!
    @Override
    public PrgState execute(PrgState p) throws StatementException {
        if(p.getSymTbl().containsKey(this.name) )//&& type already exists)
            throw new StatementException("Variable already defined");
        p.getSymTbl().put(name,type.getDefaultValue());
        return p;
    }
    @Override
    public IStatement deepCopy() {return new VarDecl(this.name,this.type);}
    @Override
    public String toString(){return type.toString()+" "+name;}

}
