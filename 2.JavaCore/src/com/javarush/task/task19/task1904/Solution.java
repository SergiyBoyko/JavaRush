package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static class PersonScannerAdapter implements PersonScanner{
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String person = this.fileScanner.nextLine();
            String[] res = person.split(" ");
            return new Person(res[0], res[1], res[2], new Date(res[3]+" "+ res[4]+" "+ res[5]));
        }

        @Override
        public void close() throws IOException {
            this.fileScanner.close();
        }
    }
}
