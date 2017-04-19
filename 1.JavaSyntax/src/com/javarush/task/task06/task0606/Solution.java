package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(r.readLine());
        while (true) {
            if (!(n==0)){
             if(n%2==0) even++;
                else odd++;
                n/=10;
            }
            else break;
        }
        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
