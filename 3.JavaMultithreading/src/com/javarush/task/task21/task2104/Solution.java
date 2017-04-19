package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof Solution)) return false;
        if (obj.getClass() != this.getClass()) return false;

        Solution n = (Solution) obj;
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
        s.add(new Solution("Donald", "Duck"));
        s.add(new Solution("Donald1", "Duck1"));
        System.out.println(s.contains(new Solution("Donald1", "Duck")));
    }
}
