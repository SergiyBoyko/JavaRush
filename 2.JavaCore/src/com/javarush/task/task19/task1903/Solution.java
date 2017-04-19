package com.javarush.task.task19.task1903;

/* 
Адаптация нескольких интерфейсов
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();
    static {
        countries.put("UA","Ukraine");
        countries.put("RU","Russia");
        countries.put("CA","Canada");
    }

    public static void main(String[] args) {
    }

    public static class IncomeDataAdapter implements Customer, Contact{
        private IncomeData data;

        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }

        @Override
        public String getName() {
            return this.data.getContactFirstName() + ", "  + this.data.getContactLastName();
        }

        @Override
        public String getPhoneNumber() {
            String num = "+" + this.data.getCountryPhoneCode() + "(";
            String num2 = "" + this.data.getPhoneNumber();
            int n2l = num2.length();
            if ((num2.length())<10) {
                for (int i = 0; i < 10 - n2l; i++) {
                    num2 = "0" + num2;
                }
            }
            char[] c = num2.toCharArray();
            String x ="";
            for (int i = 3; i < c.length; i++) {
                x+=c[i];
            }
            num = num + c[0] + c[1] + c[2] + ")" + x;
            return num;
        }

        @Override
        public String getCompanyName() {
            return this.data.getCompany();
        }

        @Override
        public String getCountryName() {
            return countries.get(this.data.getCountryCode());
        }
    }


    public static interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
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