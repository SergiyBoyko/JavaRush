package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int size = 0;
        for (Map.Entry<K, List<V>> pair : map.entrySet()) {
            size += pair.getValue().size();
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        V last = null;
        if (map.containsKey(key)) {
            if (map.get(key).size() == repeatCount) {
                map.get(key).remove(0);
            }
            // get current last element
            last = map.get(key).get(map.get(key).size() - 1);
            map.get(key).add(value);
        } else {
            ArrayList<V> list = new ArrayList<V>();
            list.add(value);
            map.put(key, list);
        }
        return last;
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        V removed = null;
        if (map.containsKey(key)) {
            if (map.get(key).size() > 0)
                removed = map.get(key).remove(0);
            if (map.get(key).size() == 0)
                map.remove(key);
        }
        return removed;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        Set<K> set = map.keySet();
        return set;
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        ArrayList<V> list = new ArrayList<V>();
        for (K key : map.keySet()) {
            list.addAll(map.get(key));
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for (K key : map.keySet()) {
            if (map.get(key).contains(value))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}