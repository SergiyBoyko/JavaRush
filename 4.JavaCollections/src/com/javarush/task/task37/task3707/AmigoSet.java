package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Serhii Boiko on 10.08.2017.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {

    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }
    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max(16, (int)(collection.size()/0.75f +1)));
        this.addAll(collection);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        oos.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        oos.writeInt(map.size());
        for (E e : map.keySet()) {
            oos.writeObject(e);
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        float loadFactor = ois.readFloat();
        int capacity = ois.readInt();
        map = new HashMap<>(capacity, loadFactor);
        int size = ois.readInt();
        for (int i = 0; i < size; i++) {
            map.put((E) ois.readObject(), PRESENT);
        }
    }

    @Override
    public Object clone() {
        try {
            AmigoSet as = (AmigoSet) super.clone();
            as.map = (HashMap) map.clone();
            return as;
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return null != map.remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean add(E e) {
        return null == map.put(e, PRESENT);
    }
}
