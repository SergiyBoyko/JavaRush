package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

/**
 * Created by Serhii Boiko on 21.08.2017.
 */
public class Shortener {
    private Long lastId = 0L;
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string) {
        Long res = storageStrategy.getKey(string);
        if (res == null) {
            res= ++lastId;
            storageStrategy.put(res, string);
        }
        return res;
    }

    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }

}
