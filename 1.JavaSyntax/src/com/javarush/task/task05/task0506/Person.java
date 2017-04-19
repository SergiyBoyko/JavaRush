package com.javarush.task.task05.task0506;

/* 
Человечки
*/

public class Person {
    public String name;
    public int age;
    public String address;
    public char sex;

    public static void main(String[] args) {
        Person person = new Person();
        person.name = "person";
        person.age = 20;
        person.address = "address";
        person.sex = 'm';
    }
}
