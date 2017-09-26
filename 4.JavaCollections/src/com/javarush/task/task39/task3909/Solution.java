package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "a";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = null;
        s2 = "a";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = "abcd";
        s2 = "abcm";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = "abkd";
        s2 = "abcd";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = "abcd";
        s2 = "ebcdf";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = "abcd";
        s2 = "ebkd";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = "ab";
        s2 = "acx";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = "abcd";
        s2 = "avcx";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = "arob";
        s2 = "rob";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = "raob";
        s2 = "rob";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = "robc";
        s2 = "roba";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
        s1 = "roab";
        s2 = "roba";
        System.out.println(s1 + " " + s2 + " : " + isOneEditAway(s1, s2));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null || second == null)
            return false;
        if (Math.abs(first.length() - second.length()) > 1)
            return false;
        if (first.equals(second))
            return true;
        String shorterString;
        String longerString;
        if (first.length() <= second.length()) {
            shorterString = first;
            longerString = second;
        } else {
            shorterString = second;
            longerString = first;
        }

        char[] shorterChars = shorterString.toCharArray();
        boolean exit = false;
        for (int i = 0, j = 0; i < shorterChars.length; i++, j++) {
            if (longerString.charAt(j) != shorterChars[i]) {
                if (!exit) exit = true;
                else return false;
                if (longerString.length() > shorterString.length()) i--;
            }
        }
        return true;

    }
}
