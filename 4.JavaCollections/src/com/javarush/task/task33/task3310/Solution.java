package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Serhii Boiko on 21.08.2017.
 */
public class Solution {

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<Long>();
        for (String s : strings) {
            ids.add(shortener.getId(s));
    }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<String>();
        for (Long id : keys) {
            strings.add(shortener.getString(id));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strSet = new HashSet<String>();
        for (int i = 0; i < elementsNumber; i++) {
            strSet.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date beginDate = new Date();
        Set<Long> testIds = getIds(shortener, strSet);
        Date endDate = new Date();
        Helper.printMessage(String.valueOf(endDate.getTime() - beginDate.getTime()));

        beginDate = new Date();
        Set<String> testStrings = getStrings(shortener, testIds);
        endDate = new Date();
        Helper.printMessage(String.valueOf(endDate.getTime() - beginDate.getTime()));

        String message = strSet.size() == testStrings.size() ? "Тест пройден." : "Тест не пройден.";
        Helper.printMessage(message);
    }

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 500);
        testStrategy(new OurHashMapStorageStrategy(), 500);
        testStrategy(new FileStorageStrategy(), 500);
        testStrategy(new OurHashBiMapStorageStrategy(), 500);
        testStrategy(new HashBiMapStorageStrategy(), 500);
        testStrategy(new DualHashBidiMapStorageStrategy(), 500);
    }
}
