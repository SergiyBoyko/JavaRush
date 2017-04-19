package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);
    static {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();
        Thread t3 = new Thread3();
        Thread t4 = new Thread4();
        Thread t5 = new Thread5();
        threads.add(t1);
        threads.add(t2);
        threads.add(t3);
        threads.add(t4);
        threads.add(t5);
    }

    public static void main(String[] args) throws InterruptedException {
        threads.forEach(Thread::start);
    }
    public static class Thread1 extends Thread {
        @Override
        public void run() {
            while (true) {}
        }
    }
    public static class Thread2 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }
    public static class Thread3 extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    while (true) {
                        System.out.println("Ура");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static class Thread4 extends Thread implements Message {
        @Override
        public void run() {

        }

        @Override
        public void showWarning() {
            if (this.isAlive()) this.interrupt();
        }
    }
    public static class Thread5 extends Thread {
        @Override
        public void run() {
            try {
                int sum = 0;
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String s = reader.readLine();
                    if (s.equals("N")) break;
                    sum += Integer.parseInt(s);
                }
                System.out.println(sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}