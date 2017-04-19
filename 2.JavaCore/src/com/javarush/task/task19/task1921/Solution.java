package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        String file = args[0]; //"C:\\z\\1.txt";
        BufferedReader filereader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = filereader.readLine()) != null) {
            String[] w = line.split(" ");
            String date = w[w.length-3] + " " + w[w.length-2] + " " + w[w.length-1];
            //System.out.println(date);
            String name = line.replace(" "+date,"");
            Date date1 = new SimpleDateFormat("dd MM yyyy").parse(date);
            PEOPLE.add(new Person(name, new SimpleDateFormat("dd MM yyyy").parse(date)));
            //System.out.println(name);
            //System.out.println(date1);
                    //new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011")
        }
        filereader.close();

    }
}
