package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K, T extends Convertable<K>> Map<K, T> convert(List<T> list) {
        Map<K, T> result = new HashMap<K, T>();
        for (T t : list) {
            result.put(t.getKey(), t);
        }
        return result;
    }
}
