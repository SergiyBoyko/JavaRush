package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {

//        args = new String[5];
//        args[0] = "-u";
//        args[1] = "198478";
//        args[2] = "Super machine car remote control inside";
//        args[3] = "20.50";
//        args[4] = "987654321";

        String command = args[0];
        String idString = args[1];
        String productName = "";
        String priceString = "";
        String quantity = "";

        if (command.equals("-u")) {
            productName = args[2];
            priceString = args[3];
            quantity = args[4];
        }

        List<String> lines = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();
        int maxID = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String idSpaced = line.substring(0, 8);
                String idStr = idSpaced.replace(" ", "");
                int id = Integer.parseInt(idStr);
                if (maxID < id) maxID = id;
                if (command.equals("-u") && id == Integer.parseInt(idString)) {
                    String pName = productName;
                    while (pName.length() < 30) {
                        pName += " ";
                    }
                    pName = pName.substring(0, 30);

                    String price = priceString;
                    while (price.length() < 8) {
                        price += " ";
                    }
                    price = price.substring(0, 8);

                    String count = quantity;
                    while (count.length() < 4) {
                        count += " ";
                    }
                    count = count.substring(0, 4);
                    line = idSpaced + pName + price + count;
                }
                if (!(command.equals("-d") && id == Integer.parseInt(idString)))
                    lines.add(line);
            }

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }
}
