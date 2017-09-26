package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Boiko on 07.07.2017.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException {
            return reader.readLine();
    }
    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<Dish>();
        String dishName = "";
        while (!dishName.equals("exit")) {
            writeMessage(Dish.allDishesToString());
            dishName = readString();
            if (!dishName.equals("exit")) {
                try {
                    Dish d = Dish.valueOf(dishName);
                    dishes.add(d);
                } catch (Exception e) {
                    writeMessage("Даного блюда нет!");
                }
            }
        }
        return dishes;
    }
}
