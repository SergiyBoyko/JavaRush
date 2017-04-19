package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        @Override
        protected A clone() throws CloneNotSupportedException {
            return (A) super.clone();
        }

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }
        @Override
        protected B clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
//            return null;
        }
        public String getName() {
            return name;
        }
    }

    public static class C extends B implements Cloneable{
        public C(int i, int j, String name) {
            super(i, j, name);
        }
        @Override
        protected C clone() throws CloneNotSupportedException {
            C c = new C(this.getI(),this.getJ(),this.getName());
            return c;
        }
    }

    public static void main(String[] args) {

    }
}
