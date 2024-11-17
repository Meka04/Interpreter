package model.state;

import model.ADT.*;
import model.values.*;
import model.statements.*;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStatement> exeStack;
    private MyIMap<String, IValue> symTbl;
    private MyIList<IValue> outList;
    private MyIMap<StringValue, BufferedReader> fileTable;
    private IStatement originalStatement;

    public PrgState(IStatement OGStatement) {
        this.exeStack = new MyStack<IStatement>();
        this.symTbl = new MyMap<String, IValue>();
        this.outList = new MyList<IValue>();
        this.fileTable = new MyMap<StringValue,BufferedReader>();
        this.originalStatement = OGStatement;
        this.exeStack.push(originalStatement);
    }

    public MyIStack<IStatement> getExeStack(){return this.exeStack;}
    public MyIMap<String, IValue> getSymTbl(){return this.symTbl;}
    public MyIList<IValue> getOutList(){return this.outList;}
    public MyIMap<StringValue,BufferedReader> getFileTable(){return this.fileTable;}
    public String fileTableToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("FileTable:\n");
        for(StringValue path: fileTable.getKeys())
            sb.append(path).append("\n");
        return sb.toString();
    }
    @Override
    public String toString() {return "PrgState : \n"+exeStack.toString()+"\n"+symTbl.toString()+"\n"+outList.toString() + "\n" + fileTableToString();}
}
