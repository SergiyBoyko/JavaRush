package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        BufferedReader reader1 = new BufferedReader(new FileReader(fileName1));
        String line;
        while ((line = reader1.readLine()) != null)
        {
            allLines.add(line);
        }
        reader1.close();

        BufferedReader reader2 = new BufferedReader(new FileReader(fileName2));
        while ((line = reader2.readLine()) != null)
        {
            forRemoveLines.add(line);
        }
        reader2.close();

        new Solution().joinData();
        reader.close();
    }
    public void joinData () throws CorruptedDataException {
        boolean free = true;
        for (int i = 0; i < forRemoveLines.size(); i++) {
            if (!allLines.contains(forRemoveLines.get(i))) free = false;
        }
        if (free) {
            forRemoveLines.forEach(allLines::remove);
        }
        else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
