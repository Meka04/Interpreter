package view;

import controller.Controller;
import exceptions.EmptyStackException;
import exceptions.ExpressionException;
import exceptions.StatementException;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.allStep(); }
        catch (EmptyStackException e){
            System.err.println(e.getMessage());
        }
        catch (StatementException e){
            System.err.println(e.getMessage());
        }
        catch (ExpressionException e){
            System.err.println(e.getMessage());
        }

    }
}
