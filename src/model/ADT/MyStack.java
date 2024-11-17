package model.ADT;

import exceptions.ADTException;
import exceptions.EmptyStackException;

import java.util.Stack;


public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;
    public MyStack(){
        this.stack = new Stack<>();
    }
    public MyStack(Stack<T> stack){
        this.stack = stack;
    }
    @Override
    public T pop() throws ADTException{
        if(this.stack.isEmpty())
            throw new EmptyStackException();
        return this.stack.pop();
    }
    @Override
    public boolean isEmpty(){
        if(stack.isEmpty())
            return true;
        return false;
    }
    @Override
    public void push(T element){
        this.stack.push(element);
    }
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Exec stack:\n");
        for (T t : stack) {
            str.append(t).append("\n");
        }
        return str.toString();
    }
}
