package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Serhii Boiko on 07.07.2017.
 */
public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
//        this.queue = Restaurant.getOrderQueue();
    }

    public void startCookingOrder(Order order) {
        busy = true;
        int cookingTime = order.getTotalCookingTime();
        ConsoleHelper.writeMessage("Start cooking - " + order.toString() + ", cooking time " + cookingTime + "min");
        try {
            Thread.sleep(cookingTime * 10);
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Error: cooking!");
        }
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(),
                this.name, order.getTotalCookingTime(), order.getDishes()));
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        while (true) {
            if (!queue.isEmpty()) {
//                Set<Cook> cookSet = StatisticManager.getInstance().getCooks();
//                for (Cook c : cookSet) {
                    if (!this.isBusy()) {
                        this.startCookingOrder(queue.poll());
                    }
//                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
