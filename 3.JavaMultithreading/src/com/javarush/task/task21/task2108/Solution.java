package com.javarush.task.task21.task2108;

/*
Клонирование растений
*/
public class Solution {
    public static void main(String[] args) {
        Tree tree = new Tree("willow", new String[]{"s1", "s2", "s3", "s4"});
        Tree clone = null;
        try {
            clone = tree.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(tree);
        System.out.println(clone);

        System.out.println(tree.getName());
        System.out.println(clone.getName());

        System.out.println(tree.branches);
//        for (int i = 0; i < tree.branches.length; i++) {
//            System.out.println(tree.branches[i]);
//        }
        System.out.println(clone.branches);
//        for (int i = 0; i < clone.branches.length; i++) {
//            System.out.println(clone.branches[i]);
//        }
    }

    public static class Plant{
        private String name;

        public Plant(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Tree extends Plant implements Cloneable{
        private String[] branches;

        @Override
        protected Tree clone() throws CloneNotSupportedException {
            Tree tree = (Tree) super.clone();
            tree.branches = (String[]) this.branches.clone();
//            String name = this.getName();
            return tree;
        }

        public Tree(String name, String[] branches) {
            super(name);
            this.branches = branches;
        }

        public String[] getBranches() {
            return branches;
        }
    }
}
