package view;

import controller.Controller;
import model.expressions.ArithmeticalExpression;
import model.expressions.ArithmeticalOperator;
import model.expressions.ValExpression;
import model.expressions.VarExpression;
import model.state.PrgState;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import repo.IRepo;
import repo.Repo;

import java.util.Scanner;

public class UI {
    private final Controller controller;
    public UI(Controller controller){ this.controller = controller;}

    private void menu() {
        System.out.println("=== Toy Language Interpreter ===");
        System.out.println("1. Example 1: \nint v; v=2; Print(v)");
        System.out.println("2. Example 2: \nint a; int b; a=2+3*5; b=a+1; Print(b)");
        System.out.println("3. Example 3: \nbool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }
    public void start(){
        Scanner scanner = new Scanner(System.in);
        boolean isAlive = true;
        IStatement program = null;

        while(isAlive){
            menu();
            int option = scanner.nextInt();

            switch (option){
                //example 1: int v; v=2; Print(v)
                case 1:
                    program = new CompoundStatement(
                            new VarDecl("v",new IntType()), new CompoundStatement(
                                    new AssignmentStatement("v",new ValExpression(new IntValue(2))),
                                    new PrintStatement(new VarExpression("v"))));
                    break;
                //example 2: int a; int b; a=2+3*5; b=a+1; Print(b)
                case 2:
                    program = new CompoundStatement(
                            new VarDecl("a",new IntType()), new CompoundStatement(
                                    new VarDecl("b",new IntType()), new CompoundStatement(
                                        new AssignmentStatement("a",new ArithmeticalExpression(new ValExpression(
                                                new IntValue(2)), ArithmeticalOperator.PLUS,new ArithmeticalExpression(
                                                        new ValExpression(
                                                                new IntValue(3)),ArithmeticalOperator.MULTIPLY,new ValExpression(new IntValue(5))))),
                                        new CompoundStatement(
                                                new AssignmentStatement("b",new ArithmeticalExpression(new VarExpression("a"),ArithmeticalOperator.PLUS,new ValExpression(new IntValue(1)))),
                                                new PrintStatement(new VarExpression("b"))))));
                            break;
                //example 3: bool a; int v; a=true; (If a then v=2 else v=3); Print(v)
                case 3:
                    program = new CompoundStatement(
                            new VarDecl("a",new BoolType()),new CompoundStatement(
                                    new VarDecl("v", new IntType()),new CompoundStatement(
                                            new AssignmentStatement("a",new ValExpression(new BoolValue(true))),new CompoundStatement(
                                                    new IfStatement(
                                                            new VarExpression("a"),new AssignmentStatement("v",new ValExpression(new IntValue(2))), new AssignmentStatement("v",new ValExpression(new IntValue(3)))),
                                                    new PrintStatement(new VarExpression("v")))
                    )
                    )
                    );
                    break;
                case 0:
                    System.out.println("==PROGRAM ENDED==");
                    isAlive = false;
                default:
                    System.out.println("Invalid option, try again");
            }
            if(program!=null){
                try{
                    PrgState prgState = new PrgState(program);
                    controller.getRepo().clear();
                    controller.getRepo().add(prgState);
                    controller.allStep();
                }catch (Exception e){System.out.println(e.getMessage());}
            }
        }
        scanner.close();
    }
}
