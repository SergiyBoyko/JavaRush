package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {

    private Thread thread;

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(thread.getName());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void start(String threadName) {
        Thread thread = new Thread(this);
        thread.setName(threadName);
        this.thread = thread;
        this.thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
