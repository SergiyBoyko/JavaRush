package com.javarush.task.task30.task3001;

import java.math.BigInteger;

/*
Конвертер систем счислений
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumerationSystemType._16, "6df");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._8);
        System.out.println(result);    //expected 3337

//        number = new Number(NumerationSystemType._2, "120");
//        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._8);
//        System.out.println(result);    //expected error
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
        try {
            String givenNum = number.getDigit();
            NumerationSystem givenSys = number.getNumerationSystem();
            BigInteger integer = new BigInteger(givenNum, givenSys.getNumerationSystemIntValue());
            return new Number(expectedNumerationSystem, integer.toString(expectedNumerationSystem.getNumerationSystemIntValue()));
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}
