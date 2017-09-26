package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

/**
 * Created by Serhii Boiko on 06.09.2017.
 */
public class CachingProxyRetriever implements Retriever {
    private LRUCache cache;
    private OriginalRetriever retriever;

    public CachingProxyRetriever(Storage storage) {
        retriever = new OriginalRetriever(storage);
        cache = new LRUCache(20);
    }

    @Override
    public Object retrieve(long id) {
        Object o = cache.find(id);
        if (o == null){
            o = retriever.retrieve(id);
            cache.set(id, o);
        }
        return o;
    }
}
