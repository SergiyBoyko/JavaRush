package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        int copy = number;
        List<Integer> tSys = new ArrayList<Integer>();
        while (copy != 0) {
            int remainder = copy % 3;
            if (remainder == 2) {
                copy = copy / 3 + 1;
                tSys.add(-1);
            } else if (remainder == 1) {
                copy = copy / 3;
                tSys.add(1);
            } else {
                copy = copy / 3;
                tSys.add(0);
            }
        }
        String res = String.valueOf(number + " =");
        int power = 0;
        for (Integer t : tSys) {
            if (t > 0) {
                res += " + " + (int) (t * Math.pow(3, power++));
            } else if (t < 0) {
                res += " - " + (int) (-1 * t * Math.pow(3, power++));

            } else power++;
        }
        System.out.println(res);
    }
}