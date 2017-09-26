package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root = new Entry<String>("0");

    {
        root.lineNumber = 1;
    }


    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }

//        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
//        list.remove("5");
//        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    @Override
    public String get(int index) {
        if (true) throw new UnsupportedOperationException();
        return null;
    }

    @Override
    public String set(int index, String element) {
        if (true) throw new UnsupportedOperationException();
        return super.set(index, element);
    }

    @Override
    public void add(int index, String element) {
        if (true) throw new UnsupportedOperationException();
        super.add(index, element);
    }

    @Override
    public String remove(int index) {
        if (true) throw new UnsupportedOperationException();
        return super.remove(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        if (true) throw new UnsupportedOperationException();
        return super.subList(fromIndex, toIndex);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        if (true) throw new UnsupportedOperationException();
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        if (true) throw new UnsupportedOperationException();
        return super.addAll(index, c);
    }

    @Override
    public int size() {
        return contLevelOrder(root);
    }

    public int contLevelOrder(Entry<String> top) {
        int size = 0;
        Queue<Entry<String>> queue = new LinkedList<>();
        do {
//            top.treatment();
            if (!top.leftChildNull()) {
                queue.add(top.leftChild);
            }
            if (!top.rightChildNull()) {
                queue.add(top.rightChild);
            }
            if (!queue.isEmpty()) {
                size++;
                top = queue.poll();
            } else break;
        } while (true);
        return size;
    }

    public String getParent(String s) {
        Entry<String> top = root;
        Queue<Entry<String>> queue = new LinkedList<>();
        do {
//            if (top!=root) System.out.println(top.elementName + " parent " + top.parent.elementName + " --- " + s);
            if (top!=root && top.elementName.equals(s)) {
                return top.parent.elementName;
            }
            if (!top.leftChildNull()) queue.add(top.leftChild);
            if (!top.rightChildNull()) queue.add(top.rightChild);
            if (!queue.isEmpty()) {
                top = queue.poll();
            }
        } while (!queue.isEmpty());
        return null;
    }

    @Override
    public boolean remove(Object o) {
        String s = (String) o;
        Entry<String> top = root;
        Queue<Entry<String>> queue = new LinkedList<>();
        do {
            if (!top.leftChildNull()) {
                if (top.leftChild.elementName.equals(s)) {
                    top.leftChild = null;
                    return true;
                }
                queue.add(top.leftChild);
            }
            if (!top.rightChildNull())
                if (top.rightChild.elementName.equals(s)) {
                    top.rightChild = null;
                    return true;
                }
                queue.add(top.rightChild);
            if (!queue.isEmpty()) {
                top = queue.poll();
            }
        } while (!queue.isEmpty());
        return false;
    }

    public boolean add(String s) {
        boolean success = true;
        if (root == null) {
//            System.out.println("root = 0");
            Entry<String> entry = new Entry<String>(s);
            entry.lineNumber = 1;
            entry.checkChildren();
            root = entry;
        } else {
            success = added(root, new Entry<String>(s));
        }
        return success;
    }

    public boolean added(Entry<String> node, Entry<String> target) {
        Queue<Entry<String>> queue = new LinkedList<>();
        do {
//            top.treatment();
//            System.out.println(node);
            if (node.isAvailableToAddChildren()) {
                target.lineNumber = node.lineNumber + 1;
                target.parent = node;
                if (node.availableToAddLeftChildren) {
                    node.leftChild = target;
                } else {
                    node.rightChild = target;
                }
                node.checkChildren();
                return true;
            }
            if (!node.leftChildNull()) queue.add(node.leftChild);
            if (!node.rightChildNull()) queue.add(node.rightChild);
            if (!queue.isEmpty()) node = queue.poll();
        } while (!queue.isEmpty());
        return false;
    }

//    @Override
//    public String toString() {
//        String s = "";
//        tree = new LinkedList<String>();
//        for (int i = 0; i < 16; i++) {
//            String x = "tree+";
//            for (int j = 0; j < 8 - i; j++) {
//                x+="\t";
//            }
//            tree.add(x);
//        }
//        out(root);
//        tree.forEach(System.out::println);
//        return s;
//    }
//
//    List<String> tree = new LinkedList<String>();
//
//    public void out(Entry<String> entry) {
//        String s = " " + entry.toString();
//        tree.set(entry.lineNumber - 1, tree.get(entry.lineNumber - 1) + s);
//        if (!entry.leftChildNull()) out(entry.leftChild);
//        if (!entry.rightChildNull()) out(entry.rightChild);
//    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        boolean leftChildNull() {
            return leftChild == null;
        }

        boolean rightChildNull() {
            return rightChild == null;
        }

//        @Override
//        public String toString() {
//            return elementName + ":" + lineNumber + (parent != null? ":" + parent.elementName : "");
//        }
    }
}
