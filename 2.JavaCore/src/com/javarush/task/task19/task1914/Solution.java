package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        testString.printSomething();

        String result = outputStream.toString();
        System.setOut(consoleStream);

        String[] oper = result.split(" ");
        if (oper[1].equals("+")) result = " = " + (Integer.parseInt(oper[0])+Integer.parseInt(oper[2]));
        else if (oper[1].equals("-")) result = " = " + (Integer.parseInt(oper[0])-Integer.parseInt(oper[2]));
        else if (oper[1].equals("*")) result = " = " + (Integer.parseInt(oper[0])*Integer.parseInt(oper[2]));
        System.out.println(oper[0] + " " + oper[1] + " " + oper[2] + result );
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

