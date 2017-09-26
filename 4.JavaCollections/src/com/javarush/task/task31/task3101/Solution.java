package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    private static List<File> sort = new ArrayList<File>();

    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String path = args[0];
        String resultFileAbsolutePath = args[1];
        File inputPath = new File(path);
        File outputPath = new File(resultFileAbsolutePath);
        File newOutputPath = new File(outputPath.getParent() + "\\allFilesContent.txt");
        FileUtils.renameFile(outputPath, newOutputPath);
        try (FileWriter fileWriter = new FileWriter(newOutputPath)) {
            writer = new BufferedWriter(fileWriter);
            List<File> toRemove = createRemovePaths(inputPath);
            toRemove.forEach(FileUtils::deleteFile);
            List<String> onlyNames = new ArrayList<String>();
            for (File file : sort) {
                onlyNames.add(file.getName().split("\\.")[0]);
            }
            Collections.sort(onlyNames);

            for (String name : onlyNames) {
                for (File file : sort) {
                    if (file.getName().split("\\.")[0].equals(name)) {
                        reader = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            writer.write(line + "\n");
                        }
                    }
                }
            }
            fileWriter.close();
            writer.flush();
            writer.close();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<File> createRemovePaths(File path) {
        List<File> toRemove = new ArrayList<File>();
        for (File file : path.listFiles()) {
            if (file.isDirectory()) {
                toRemove.addAll(createRemovePaths(file));
            } else if (file.isFile() && file.length() > 50)
                toRemove.add(file.getAbsoluteFile());
            else if (file.isFile()) {
                sort.add(file.getAbsoluteFile());
            }
        }
        return toRemove;
    }


    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
