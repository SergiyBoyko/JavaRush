package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {

//        args = new String[4];
//        args[0] = "-c";
//        args[1] = "Super machine car remote control inside";
//        args[2] = "20.50";
//        args[3] = "987654321";

        if (args.length < 4 || !args[0].equals("-c")) // проверяем аргументы, переданные при запуске
        {
            return;
        }

        String command = args[0];
        String productName = args[1];
        String priceString = args[2];
        String quantity = args[3];
        List<String> lines = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();
        int maxID = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                String idStr = line.substring(0, 8);
                idStr = idStr.replace(" ", "");
                int id = Integer.parseInt(idStr);
                if (maxID < id) maxID = id;
            }

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            String id = String.valueOf(++maxID);
            while (id.length() < 8) {
                id += " ";
            }
            id = id.substring(0, 8);
//                writer.newLine();
            writer.write(id);

            String pName = productName;
            while (pName.length() < 30) {
                pName += " ";
            }
            pName = pName.substring(0, 30);
            writer.write(pName);

            String price = priceString;
            while (price.length() < 8) {
                price += " ";
            }
            price = price.substring(0, 8);
            writer.write(price);

            String count = quantity;
            while (count.length() < 4) {
                count += " ";
            }
            count = count.substring(0, 4);
            writer.write(count);
            System.out.println(id + pName + price + count);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }
}