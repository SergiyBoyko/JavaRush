package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        try {
            String[] s = string.split(" ");
            if (s.length < 5) throw new TooShortStringException();
            String res = s[1] + " " + s[2] + " " + s[3] + " " + s[4];
//            int start = string.indexOf(" ");
//            int end = start;
//            for (int i = 0; i < 4; i++) {
//                end = string.indexOf(" ", end + 1);
//            }
//            String s = string.substring(start + 1, end);
            return res;
        } catch (Exception e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
