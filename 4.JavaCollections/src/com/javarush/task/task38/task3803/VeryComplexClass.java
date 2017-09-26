package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.List;

public class VeryComplexClass {
    public void methodThrowsClassCastException() throws ClassCastException {
        Object o = new Integer(1);
        String s = (String) o;
    }

    public void methodThrowsNullPointerException() throws NullPointerException {
        List<String> list = null;
        list.get(0);
    }

    public static void main(String[] args) {
        new VeryComplexClass().methodThrowsClassCastException();
    }
}
