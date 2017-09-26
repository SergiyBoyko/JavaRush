package com.javarush.task.task25.task2514;

import java.util.ArrayList;
import java.util.List;

/*
Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
*/
public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index);
            Thread.yield();
            System.out.println("end-" + index);
        }
    }

    public static void main(String[] args) {
//        Thread t1 = new Thread(new YieldRunnable(1));
//        t1.start();
//        Thread t2 = new Thread(new YieldRunnable(2));
//        t2.start();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        System.out.println(list.size());
        int index = -1;
        if (index < list.size()) list.remove(index);
        System.out.println(list.size());
        System.out.println(list);
    }
}
