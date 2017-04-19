package com.javarush.task.task17.task1710;

import java.io.IOException;
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

    public static void main(String[] args) throws IOException, ParseException {
        String command = args[0];
        String inputString = "";
        for (int i = 1; i < args.length; i++) {
            inputString += args[i] + " ";
        }
        String[] words = inputString.split(" ");

        if (words[0].equals("-c")){
            if (words[2].equals("м")) {
                allPeople.add(Person.createMale(words[1], new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(words[3]) ));
            }
            else if (words[2].equals("ж")) {
                allPeople.add(Person.createFemale(words[1], new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(words[3])));
            }
        }
        else if (words[0].equals("-u")) {
            if (words[3].equals("м")) {
                allPeople.set(Integer.parseInt(words[2]) ,Person.createMale(words[1], new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(words[4]) ));
            }
            else if (words[3].equals("ж")) {
                allPeople.set(Integer.parseInt(words[2]) ,Person.createFemale(words[1], new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(words[4]) ));
            }
        }
        else if (words[0].equals("-d")) {
            allPeople.set(Integer.parseInt(words[1]), null);
        }
        else if (words[0].equals("-i")) {
            String sex = allPeople.get(Integer.parseInt(words[1])).getSex().equals(Sex.MALE) ? "м" : "ж";
            //Date date = allPeople.get(Integer.parseInt(words[1])).getBirthDay();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            System.out.println(allPeople.get(Integer.parseInt(words[1])).getName()+ " " + sex + " " +
                    df.format(allPeople.get(Integer.parseInt(words[1])).getBirthDay()).toUpperCase());
        }
    }
}
