package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        PrintStream garbage = new PrintStream((new ByteArrayOutputStream()));
        PrintStream myStream = new PrintStream(System.out) {
            private boolean b = false;
            @Override
            public void println(String x) {
                if (b) super.println(x + "\nJavaRush - курсы Java онлайн");
                else super.println(x);
                b = !b;
            }
        };
        System.setOut(myStream);
        testString.printSomething();
        System.setOut(consoleStream);
    }

    private static class MyPrintStream extends PrintStream {
        private boolean b;

        public MyPrintStream(OutputStream out) {
            super(out);
            b = false;
        }

        @Override
        public void println(String x) {
            if (b) super.println(x + "\nJavaRush - курсы Java онлайн");
            else super.println(x);
            b = !b;
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
