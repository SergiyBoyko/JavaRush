package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        boolean result = false;
        if (telNumber == null) return false;
        if (!telNumber.matches("[\\d, \\+, \\(, \\), -]+[\\d]$")) return false;
        if (telNumber.matches("^\\+.*")) {
            String digits = telNumber.replaceAll("\\D", "");
            if (digits.length() != 12) {
                return false;
            }
        }
        else if (telNumber.matches("^(\\(|\\d).*")) {
            String digits = telNumber.replaceAll("\\D", "");
            if (digits.length() != 10) {
                return false;
            }
        }
        else if (!telNumber.matches("^\\-.*")) return false;
        if (telNumber.matches("\\+?\\d*(\\(\\d{3}\\))?\\d+?-?\\d+-?\\d*")) {
            result = true;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
    }
}
