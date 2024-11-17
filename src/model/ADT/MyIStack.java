package model.ADT;

import exceptions.ADTException;

public interface MyIStack<T> {
    public void push(T element);
    public T pop() throws ADTException;
    public boolean isEmpty();
}
