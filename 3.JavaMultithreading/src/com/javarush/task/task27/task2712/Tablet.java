package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Serhii Boiko on 07.07.2017.
 */
public class Tablet {
    final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Tablet(int number) {
        this.number = number;

    }

    public Order createOrder() {
        try {
            Order order = new Order(this);
            some(order);
            return order;
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
    }

    public void createTestOrder() {
        try {
            Order order = new TestOrder(this);
            some(order);
//            return order;
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
//            return null;
        }
    }

    private void some(Order order) {
        ConsoleHelper.writeMessage(order.toString());
        if (!order.isEmpty()) {
//            setChanged();
//            notifyObservers(order);
            try {
                queue.put(order);
            } catch (InterruptedException e) {
//                logger.log(Level.INFO, " " + order);
            }
            try {
                new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();
            } catch (NoVideoAvailableException e) {
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
        }
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}
