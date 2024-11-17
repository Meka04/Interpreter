package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADT.MyIMap;
import model.expressions.IExpression;
import model.state.PrgState;
import model.types.IntType;
import model.types.StringType;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement{
    private IExpression fileName;
    private String variable;
    public ReadFileStatement(IExpression fileName, String variable) {
        this.fileName = fileName;
        this.variable = variable;
    }

    public PrgState execute(PrgState p) throws StatementException, ADTException, ExpressionException {
        MyIMap<String, IValue> symTable = p.getSymTbl();
        if (!symTable.containsKey(variable)) {
            throw new StatementException("Variable not found");
        }
        IValue v = symTable.getValue(variable);
        if (!v.getType().equals(new IntType())) {
            throw new StatementException("Variable '" + variable + "' is not a number");
        }
        IValue result = fileName.eval(symTable);
        if (!result.getType().equals(new StringType())) {
            throw new StatementException("The expression must be a string");
        }
        StringValue s = (StringValue) result;
        MyIMap<StringValue, BufferedReader> fileTable = p.getFileTable();
        if (!fileTable.containsKey(s))
        {
            throw new StatementException("The file isn't open for reading");
        }
        BufferedReader reader = fileTable.getValue(s);
        try
        {
            String line = reader.readLine();
            if (line == null)
            {
                line = "0";
            }
            int i = Integer.parseInt(line);
            IntValue intv = new IntValue(i);
            symTable.put(variable, intv);
        }
        catch (IOException e)
        {
            throw new StatementException(e.getMessage());
        }
        return p;
    }

    public IStatement deepCopy() {
        return new ReadFileStatement(fileName.deepCopy(), variable);
    }
}

