package com.javarush.task.task10.task1007;

/* 
Задача №7 на преобразование целых типов
*/

public class Solution {
    public static void main(String[] args) {
        long l = /*(byte)*/ 111_1111_111_110L;
        System.out.println("l " + l);
        int x = /*(byte)*/ 0b1000_1100_1010;
        System.out.println("x " + x);
        double m = (byte) 110_987_654_6299.123_34;
        System.out.println("m " + m);
        float f = (byte) l++ + 10 + ++x - (float) m;
        System.out.println("f " + f);
        l = (long) f / 1000;
        System.out.println(l);
    }
}