package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Serhii Boiko on 25.09.2017.
 */
public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage("Введите номер операции:" +
                    "\n1.INFO\n2.Deposit\n3.WITHDRAW\n4.Exit");
            String str = readString();
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.valueOf(str));
            }catch (IllegalArgumentException e){ }
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage("Введите код валюты: ");
            String str = readString();
            if (str.length() != 3) {
                writeMessage("Неверный код валюты! Введите заново.");
            } else {
                return str.toUpperCase();
            }
        }
    }


    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage("Введите два целых положительных числа. Первое - номинал, второе - количество банкнот: ");
            String userInput = readString();
            String[] strmas = userInput.split(" ");
            try {
                if (strmas.length == 2 && Integer.parseInt(strmas[0]) >= 0 && Integer.parseInt(strmas[1]) >= 0) {
                    return strmas;
                } else {
                    writeMessage("Введены неверные данные, повторите ввод!");
                }
            }catch (NumberFormatException ex) {
                writeMessage("Введены неверные данные, повторите ввод!");
            }
        }
    }

    public static void writeMessage(String message) {
        try {
            System.out.println(message);
        } catch (Exception e) {
        }
    }

    public static String readString() throws InterruptOperationException {
        String s = null;
        try {
            s = bis.readLine();
            if (s.toUpperCase().equals("EXIT")) throw new InterruptOperationException();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return s;
    }
}
