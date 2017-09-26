package com.javarush.task.task09.task0906;

/* 
Логирование стек трейса
*/

public class Solution {
    public static void main(String[] args) {
        log("In main method");
    }

    public static void log(String s) {
        //напишите тут ваш код
        Throwable t = new Throwable();
        StackTraceElement trace[] = t.getStackTrace();
        System.out.println(trace[1].getClassName() + ": " + trace[1].getMethodName() +  ": " + s);
    }
}
