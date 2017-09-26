package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {

            String arg = args[0];
            for (int i = 0; i < arg.length(); i++) {
                char c = arg.charAt(i);
                if (!((c >= 97 && c <= 122) || (c >= 65 && c <= 90) || (c >= 48 && c <= 57))) {
                    System.out.println("incorrect");
                    return;
                }
            }
            int minSys = 36;
            for (int i = 36; i >= 2; i--) {
                try {
                    new BigInteger(arg, i);
                    minSys = i;
                } catch (NumberFormatException e) {
                    break;
                }
            }
            System.out.println(minSys);
        } catch (Exception e) {

        }
    }
}