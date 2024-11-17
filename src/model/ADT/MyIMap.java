package model.ADT;

import exceptions.ADTException;

import java.util.Set;

public interface MyIMap <K,V>{
    public V getValue(K key) throws ADTException;
    public boolean containsKey(K key);
    public void put(K key, V value);
    public Set<K> getKeys();
    public void remove(V val);
}
