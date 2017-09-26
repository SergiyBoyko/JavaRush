package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serhii Boiko on 25.09.2017.
 */
public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {

    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        for (Map.Entry<String, CurrencyManipulator> pair : map.entrySet()) {
            if (pair.getKey().toUpperCase().equals(currencyCode.toUpperCase())){
                return pair.getValue();
            }
        }

        map.put(currencyCode, new CurrencyManipulator(currencyCode.toUpperCase()));
        return map.get(currencyCode.toUpperCase());
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
