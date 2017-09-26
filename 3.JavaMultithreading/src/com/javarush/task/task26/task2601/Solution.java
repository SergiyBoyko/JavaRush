package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        final float mediana;
        if (array.length % 2 == 1) mediana = array[(array.length-1) /2];
        else mediana = (array[array.length / 2 - 1] + array[array.length / 2]) / 2f;

        Comparator<Integer> compare = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return ((int) (Math.abs(mediana - o1) - Math.abs(mediana - o2))) == 0 ? o1 - o2 :
                        (int) (Math.abs(mediana - o1) - Math.abs(mediana - o2));
            }
        };
        Arrays.sort(array, compare);

        return array;
    }
}
