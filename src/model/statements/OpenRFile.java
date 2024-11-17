package model.statements;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.ADT.*;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class OpenRFile implements IStatement{
    IExpression e;
    public OpenRFile(IExpression e){this.e = e;}

    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionException {


        IValue exp = e.eval(p.getSymTbl());
        if(!exp.getType().equals(new StringType()))
            throw new StatementException("Expression is not String type");
        StringValue str = (StringValue) exp;
        if(p.getFileTable().containsKey(str))
            throw new StatementException("Key already exists");
        try{
            BufferedReader open = new BufferedReader(new FileReader(str.getValue()));
            p.getFileTable().put(str,open);
        }catch (IOException e)
        {
            System.err.println("IO Error");
            System.exit(1);
        }
        return p;
    }

    @Override
    public IStatement deepCopy() {
        return null;
    }
}
