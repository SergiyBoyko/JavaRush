package com.javarush.task.task25.task2502;

import java.util.LinkedList;
import java.util.List;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception {
            //init wheels here
            String[] s = loadWheelNamesFromDB();
            if (s.length != 4) throw new Exception();
            wheels = new LinkedList<Wheel>();
            for (String value : s) {
                wheels.add(Wheel.valueOf(value));
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) throws Exception {
        new Car();
    }
}
