package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] solution = new Solution[2];
        solution[0] = new Solution();
        solution[1] = new Solution();
        Solution solution1 = new Solution();
        solution[0].innerClasses[0] = solution1.new InnerClass();
        solution[0].innerClasses[1] = solution1.new InnerClass();
        solution[1].innerClasses[0] = solution1.new InnerClass();
        solution[1].innerClasses[1] = solution1.new InnerClass();
        return solution;
    }

    public static void main(String[] args) {

    }
}
