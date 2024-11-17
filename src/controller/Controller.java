package controller;

import exceptions.EmptyStackException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADT.MyIStack;
import model.state.PrgState;
import model.statements.IStatement;
import repo.IRepo;

public class Controller {
    private final IRepo repo;

    private boolean displayFlag=false;
    public Controller(IRepo repo) {
        this.repo = repo;
    }

    public IRepo getRepo() {
        return this.repo;
    }
    public void setDisplyFlag(boolean flag){this.displayFlag = flag;}
    public boolean getDisplayFlag(){
        return this.displayFlag;
    }

    public void addPrgState(PrgState p){
        this.repo.add(p);
    }
    public PrgState oneStep(PrgState p) throws EmptyStackException, StatementException, ExpressionException {
        MyIStack<IStatement> stack = p.getExeStack();
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        IStatement crtStmt = stack.pop();
        return crtStmt.execute(p);
    }
    public void allStep() throws EmptyStackException, StatementException, ExpressionException {
        PrgState program = repo.getCrtPrg();
        if(displayFlag){
            System.out.println(program.toString());
            System.out.println("--------------------------------------------------------\n");
        }
        repo.logPrgStateExec();
        while (!program.getExeStack().isEmpty()) {
            this.oneStep(program);
            if (displayFlag)
            {
                System.out.println(program.toString());
                System.out.println("----------------------------------------------------------------\n");
            }
            repo.logPrgStateExec();
        }
        if(!displayFlag){
            System.out.println(program.toString());
            System.out.println("--------------------------------------------------------\n");
        }
    }
    public void add(PrgState p) {
        repo.add(p);
    }
}
