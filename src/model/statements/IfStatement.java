package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.types.BoolType;
import model.values.*;

public class IfStatement implements IStatement {
    private IExpression condition;
    private IStatement thenStmt;
    private IStatement elseStmt;

    public IfStatement(IExpression condition, IStatement thenStmt, IStatement elseStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }
    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionException {
        IValue v = condition.eval(p.getSymTbl());
        if(!v.getType().equals(new BoolType()))
            throw new StatementException("Condition expression does not evaluate to BoolType");
        if(((BoolValue)v).getValue())
            p.getExeStack().push(thenStmt);
        else
            p.getExeStack().push(elseStmt);
        return p;
    }
    @Override
    public String toString(){
        return "if(" + condition + "){\n" + thenStmt + "}" + "\nelse{\n" + elseStmt + "\n}";
    }
    @Override
    public IStatement deepCopy(){
        return new IfStatement(condition.deepCopy(),thenStmt.deepCopy(),elseStmt.deepCopy());
    }
}
