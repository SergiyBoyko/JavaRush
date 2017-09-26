package com.javarush.task.task28.task2805;

/**
 * Created by Serhii Boiko on 28.06.2017.
 */
public class MyThread extends Thread {
    private static int priority = 1;

    private void task() {
        if (priority > 10) {
            priority = 1;
        }
        if (this.getThreadGroup().getName().equals("main")) {
            this.setPriority(priority);
        }
        else {
            int p = this.getThreadGroup().getMaxPriority();
            this.setPriority(priority > p? p : priority);
        }
        priority++;
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        task();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        task();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        task();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        task();
    }

    public MyThread() {
        task();
    }

    public MyThread(Runnable target) {
        super(target);
        task();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        task();
    }

    public MyThread(String name) {
        super(name);
        task();
    }
}
