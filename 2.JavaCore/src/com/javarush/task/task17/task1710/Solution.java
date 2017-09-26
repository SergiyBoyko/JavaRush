package com.javarush.task.task17.task1710;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут

        if ("-c".equals(args[0])) {
            String name = args[1];
            Sex sex = "м".equals(args[2]) ? Sex.MALE : Sex.FEMALE;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date bd = dateFormat.parse(args[3]);
            Person person = (sex == Sex.MALE) ? Person.createMale(name, bd) : Person.createFemale(name, bd);

            allPeople.add(person);
            System.out.println(allPeople.size()-1);
        }
        else if ("-u".equals(args[0])) {
            int index = Integer.parseInt(args[1]);
            String name = args[2];
            Sex sex = "м".equals(args[3]) ? Sex.MALE : Sex.FEMALE;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date bd = dateFormat.parse(args[4]);

            Person person = allPeople.get(index);
            person.setName(name);
            person.setSex(sex);
            person.setBirthDay(bd);
        }
        else if ("-d".equals(args[0])) {
            int index = Integer.parseInt(args[1]);
            Person person = allPeople.get(index);
            person.setName(null);
            person.setSex(null);
            person.setBirthDay(null);
        }
        else if ("-i".equals(args[0])) {
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String bd = dateFormat.format(person.getBirthDay());

            System.out.println(person.getName() + " " + (person.getSex() == Sex.MALE ? "м" : "ж") + " " +  bd);
        }


    }
}
