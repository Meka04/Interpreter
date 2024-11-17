package model.ADT;

import exceptions.ADTException;
import exceptions.KeyNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyMap<K,V> implements MyIMap<K,V>{
    private Map<K,V> map;

    public MyMap(){this.map = new HashMap<>();}

    @Override
    public void remove(V val){
         map.remove(val);
    }
    @Override
    public boolean containsKey(K key){return this.map.containsKey(key);}
    @Override
    public V getValue(K key) throws ADTException {
        if(this.map.containsKey(key)){
            return this.map.get(key);
        }
        throw new KeyNotFoundException();
    }
    @Override
    public void put(K key, V value){this.map.put(key,value);}
    @Override
    public Set<K> getKeys() {return map.keySet();}
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Symtable {\n");
        for(Map.Entry<K,V> entry: this.map.entrySet())
            str.append(entry.getKey() + "->" + entry.getValue() + "\n");
        str.append("}\n");
        return str.toString();
    }
}
