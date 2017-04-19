package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            for (Map.Entry<String, User> pair : solution.users.entrySet()) {
                System.out.println(pair.getKey() + " "  + pair.getValue().getName()+ " "+ pair.getValue().getAge());
            }
            System.out.println(clone.users);
            for (Map.Entry<String, User> pair : clone.users.entrySet()) {
                System.out.println(pair.getKey() + " "  + pair.getValue().getName()+ " "+ pair.getValue().getAge());
            }

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }
    protected Map<String, User> users = new LinkedHashMap();

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Map<String, User> users = this.users;
        Solution solution = new Solution();
        solution.users.putAll(users);
        return solution;
    }

    public static class User implements Cloneable{
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
        @Override
        protected User clone() throws CloneNotSupportedException{
            return new User(this.age,this.name);
        }
        public String getName(){
            return name;
        }
        public int getAge() {
            return age;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Solution)) return false;
        if (o.getClass() != this.getClass()) return false;

        Solution solution1 = (Solution) o;

        if(this.users != null ? (this.users.equals(solution1.users)) : solution1.users != null) return false;
        return true;
    }
}
