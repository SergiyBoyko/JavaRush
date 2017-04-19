package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Solution))
            return false;
        if (o.getClass() != this.getClass()) return false;
        Solution n = (Solution) o;
        if (n.first != null ? !n.first.equals(first) : first != null) return false;
        if (n.last != null ? !n.last.equals(last) : last != null) return false;
        return true;
    }

    public int hashCode() {
        final int prime = 31;
        return prime * ((first == null) ? 0 : first.hashCode()) + ((last == null) ? 0 : last.hashCode());
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
