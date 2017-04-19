package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fbrsw on 16.03.2017.
 */
public class Hippodrome {
    static Hippodrome game;
    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    void move() {
        horses.forEach(Horse::move);
    }
    void print() {
        horses.forEach(Horse::print);
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
    void run() throws InterruptedException {
        for (int i = 1; i < 101; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public Horse getWinner(){
        double distanse = 0;
        for (Horse h: horses) {
            if(h.distance>distanse) distanse = h.distance;
        }
        for (Horse h: horses){
            if(h.distance == distanse) return h;
        }
        return null;
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().name + "!");

    }

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses = new ArrayList<Horse>();
        horses.add(new Horse("Mustang", 3, 0));
        horses.add(new Horse("Reggy", 3, 0));
        horses.add(new Horse("Killer", 3, 0));
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
}
