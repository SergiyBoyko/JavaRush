package com.javarush.task.task36.task3601;

/**
 * Created by Serhii Boiko on 18.08.2017.
 */
public class View {
    public void fireEventShowData() {
        System.out.println(new Controller().onDataListShow());
    }
}
