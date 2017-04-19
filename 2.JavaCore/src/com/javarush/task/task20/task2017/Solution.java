package com.javarush.task.task20.task2017;

import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {
        Object object = null;
        try {
            object = objectStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (!(object instanceof A)) return null;
        return (A) object;
    }

    public class A implements Serializable{
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

//        A a = new Solution().getOriginalObject(null);
//        if (a == null) System.out.println("good");
//        try {
//            throw new IOException();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
