package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Serhii Boiko on 23.08.2017.
 */
public class SpeedTest {

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<String>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids1 = new HashSet<Long>();
        Set<Long> ids2 = new HashSet<Long>();
        Long hmssTime = getTimeForGettingIds(shortener1, origStrings, ids1);
        Long hbmssTime = getTimeForGettingIds(shortener2, origStrings, ids2);
        Assert.assertTrue(hmssTime > hbmssTime);

        Set<String> newStrings1 = new HashSet<String>();
        Set<String> newStrings2 = new HashSet<String>();
        hmssTime = getTimeForGettingStrings(shortener1, ids1, newStrings1);
        hbmssTime = getTimeForGettingStrings(shortener2, ids2, newStrings2);
        Assert.assertEquals(hmssTime, hbmssTime, 30);

    }

    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date beginDate = new Date();
        for (String s : strings) {
            ids.add(shortener.getId(s));
        }
        Date endDate = new Date();
        return endDate.getTime() - beginDate.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date beginDate = new Date();
        for (Long i : ids) {
            strings.add(shortener.getString(i));
        }
        Date endDate = new Date();
        return (endDate.getTime() - beginDate.getTime());
    }
}
