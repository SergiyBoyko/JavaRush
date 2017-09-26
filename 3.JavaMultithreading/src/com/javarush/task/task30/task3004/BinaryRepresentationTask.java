package com.javarush.task.task30.task3004;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Serhii Boiko on 12.07.2017.
 */
public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {
        List<BinaryRepresentationTask> subTasks = new LinkedList<>();

        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
            subTasks.add(task);
        }

        for (BinaryRepresentationTask task : subTasks) {
            result = task.join() + result;
        }

        return result;
    }
}
