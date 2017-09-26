package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        //напишите тут ваш код
        int numSys;
        if (s.startsWith("0")) {
            if (s.startsWith("0x")) {
                numSys = 16;
                s = s.replace("0x", "");
            } else if (s.startsWith("0b")) {
                numSys = 2;
                s = s.replace("0b", "");
            } else {
                numSys = 8;
            }
        } else numSys = 10;
        return String.valueOf(Integer.parseInt(s, numSys));
    }
}
