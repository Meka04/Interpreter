import controller.Controller;
import model.expressions.ArithmeticalExpression;
import model.expressions.ArithmeticalOperator;
import model.expressions.ValExpression;
import model.expressions.VarExpression;
import model.state.PrgState;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import repo.IRepo;
import repo.Repo;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;

class Interpreter {
    public static void main(String[] args) {
        IStatement ex1 = new CompoundStatement(
                new VarDecl("v",new IntType()), new CompoundStatement(
                new AssignmentStatement("v",new ValExpression(new IntValue(2))),
                new PrintStatement(new VarExpression("v"))));
        PrgState prg1 = new PrgState(ex1);
        IRepo repo1 = new Repo("log1.txt");
        Controller ctr1 = new Controller(repo1);
        IStatement ex2 = new CompoundStatement(
                new VarDecl("a",new IntType()), new CompoundStatement(
                new VarDecl("b",new IntType()), new CompoundStatement(
                new AssignmentStatement("a",new ArithmeticalExpression(new ValExpression(
                        new IntValue(2)), ArithmeticalOperator.PLUS,new ArithmeticalExpression(
                        new ValExpression(
                                new IntValue(3)),ArithmeticalOperator.MULTIPLY,new ValExpression(new IntValue(5))))),
                new CompoundStatement(
                        new AssignmentStatement("b",new ArithmeticalExpression(new VarExpression("a"),ArithmeticalOperator.PLUS,new ValExpression(new IntValue(1)))),
                        new PrintStatement(new VarExpression("b"))))));
        PrgState prg2 = new PrgState(ex2);
        IRepo repo2 = new Repo("log2.txt");
        Controller ctr2 = new Controller(repo2);
        IStatement ex3 = new CompoundStatement(
                new VarDecl("a",new BoolType()),new CompoundStatement(
                new VarDecl("v", new IntType()),new CompoundStatement(
                new AssignmentStatement("a",new ValExpression(new BoolValue(true))),new CompoundStatement(
                new IfStatement(
                        new VarExpression("a"),new AssignmentStatement("v",new ValExpression(new IntValue(2))), new AssignmentStatement("v",new ValExpression(new IntValue(3)))),
                new PrintStatement(new VarExpression("v")))
        )
        )
        );
        PrgState prg3 = new PrgState(ex3);
        IRepo repo3 = new Repo("log3.txt");
        Controller ctr3 = new Controller(repo3);

        IStatement stmt4 = new CompoundStatement(
                new VarDecl("varf", new StringType()), new CompoundStatement(
                        new AssignmentStatement("varf", new ValExpression(new StringValue("test.in"))),new CompoundStatement(
                                new OpenRFile(new VarExpression("varf")),new CompoundStatement(
                                        new VarDecl("varc",new IntType()),new CompoundStatement(new ReadFileStatement(new ValExpression(new StringValue("test.in")),"varc"),new CompoundStatement(new PrintStatement(new VarExpression("varc")),new CompoundStatement(new ReadFileStatement(new VarExpression("varf"),"varc"),new CompoundStatement(new PrintStatement(new VarExpression("varc")),new CloseRFile((new VarExpression("varf")))))))))));
        PrgState p4 = new PrgState(stmt4);
        IRepo repo4 = new Repo("log4.txt");
        Controller c4 = new Controller(repo4);
        repo1.add(prg1);
        repo2.add(prg2);
        repo3.add(prg3);
        repo4.add(p4);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", stmt4.toString(), c4));
        menu.show();
    }
}