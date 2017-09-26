package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.IOException;

/**
 * Created by Serhii Boiko on 23.08.2017.
 */
public class FileStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    int hash(Long k) {
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }

    int indexFor(int hash, int length) {
        return hash & (length-1);
    }

    Entry getEntry(Long key) {
        int hash = (key == null) ? 0 : hash((long) key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)].getEntry();
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }

    void resize(int newCapacity) {
//        Entry[] oldTable = table;
//        int oldCapacity = oldTable.length;
//        if (oldCapacity == MAXIMUM_CAPACITY) {
//                threshold = Integer.MAX_VALUE;
//                return;
//            }
        try {
            FileBucket[] newTable = new FileBucket[newCapacity];
            transfer(newTable);
            for (FileBucket fb : table) {
                fb.remove();
            }
            table = newTable;
//        threshold = (int)(newCapacity * loadFactor);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    void transfer(FileBucket[] newTable) {
        FileBucket[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j].getEntry();
            if (e != null) {
                src[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i].getEntry();
                    newTable[i].putEntry(e);
                    e = next;
                } while (e != null);
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        if (table[bucketIndex] == null) table[bucketIndex] = new FileBucket();
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
//        if (size++ >= threshold)
//            resize(2 * table.length);
        if (table[bucketIndex].getFileSize() >= bucketSizeLimit) {
            resize(2 * table.length);
        }
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        if (table[bucketIndex] == null) table[bucketIndex] = new FileBucket();
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
//        if (value == null)
//                return containsNullValue();
        FileBucket[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i].getEntry() ; e != null ; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    @Override
    public void put(Long key, String value) {
//        if (key == null)
//                return putForNullKey(value);
        int hash = hash((long) key.hashCode());
        int i = indexFor(hash, table.length);
        if (table[i] != null) {
            for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                Object k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
//                        String oldValue = e.value;
                    e.value = value;
//                        e.recordAccess(this);
//                        return oldValue;
                }
            }
        }

//        modCount++;
        if (table[i] == null) createEntry(hash, key, value, i);
        else addEntry(hash, key, value, i);
//        return null;
    }

    @Override
    public Long getKey(String value) {
        FileBucket[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            if (tab[i] != null) {
                for (Entry e = tab[i].getEntry(); e != null; e = e.next)
                    if (value.equals(e.value))
                        return e.getKey();
            }
        return null;
    }

    @Override
    public String getValue(Long key) {
//        if (key == null)
//                return getForNullKey();
        int hash = hash((long) key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)].getEntry();
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                return e.value;
        }
        return null;
    }
}
