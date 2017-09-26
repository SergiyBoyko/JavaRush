package com.javarush.task.task25.task2506;

/**
 * Created by fbrsw on 22.05.2017.
 */
public class LoggingStateThread extends Thread {
    Thread target;
    State state;

    public LoggingStateThread(Thread target) {
        this.target = target;
//        this.setDaemon(true);
    }

    @Override
    public void run() {
//        System.out.println(target.getState());
        State state = target.getState();
        System.out.println(state);
        while (state != State.TERMINATED) {
            if (state != target.getState()) {
                state = target.getState();
                System.out.println(state);
            }
        }

    }
}
