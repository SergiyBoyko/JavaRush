package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();

    static {
        countries.put("UA","Ukraine");
        countries.put("RU","Russia");
        countries.put("CA","CANADA");
    }
    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem{
        private Contact contact;
        private Customer customer;

        public DataAdapter(Customer customer, Contact contact) {
        }

        @Override
        public String getCountryCode() {
            return null;
        }

        @Override
        public String getCompany() {
            return null;
        }

        @Override
        public String getContactFirstName() {
            return null;
        }

        @Override
        public String getContactLastName() {
            return null;
        }

        @Override
        public String getDialString() {
            return null;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}