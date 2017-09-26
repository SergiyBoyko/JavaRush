package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Serhii Boiko on 07.07.2017.
 */
public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final  LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<Order>();

    public static LinkedBlockingQueue<Order> getOrderQueue() {
        return orderQueue;
    }
//    public static int getOrderCreatingInterval() {
//        return ORDER_CREATING_INTERVAL;
//    }

    public static void main(String[] args) throws InterruptedException {
        Locale.setDefault(Locale.ENGLISH);
//        ConsoleHelper.writeMessage(Dish.allDishesToString());
//        ConsoleHelper.writeMessage(ConsoleHelper.getAllDishesForOrder().toString());
//        new Tablet(5).createOrder();

//        Tablet tablet = new Tablet(5);
        Cook cook = new Cook("Amigo");
        Cook cook1 = new Cook("Cap");
        cook.addObserver(new Waiter());
        cook1.addObserver(new Waiter());
        cook.setQueue(orderQueue);
        cook1.setQueue(orderQueue);
        Thread t1 = new Thread(cook);
        Thread t2 = new Thread(cook1);
        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();

//        StatisticManager.getInstance().register(cook);
//        StatisticManager.getInstance().register(cook1);
        List<Tablet> tablets = new ArrayList<Tablet>();
//        OrderManager manager = new OrderManager();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
//            tablet.addObserver(manager);
//            tablet.addObserver(manager);
            tablets.add(tablet);
        }
        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
//        tablet.addObserver(cook);
//        tablet.createOrder();
//
//        DirectorTablet directorTablet = new DirectorTablet();
//        directorTablet.printActiveVideoSet();
//        directorTablet.printAdvertisementProfit();
//        directorTablet.printArchivedVideoSet();
//        directorTablet.printCookWorkloading();


    }
}
