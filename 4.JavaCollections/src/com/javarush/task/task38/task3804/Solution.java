package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return FactoryMethod.class;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        try {
            throw FactoryMethod.getException(ExceptionDBMessage.NOT_ENOUGH_CONNECTIONS);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}