package com.javarush.task.task16.task1630;

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут

    public static void main(String[] args) throws InterruptedException, IOException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException, IOException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }
    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent() throws IOException;

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {

        String filename;

        @Override
        public void run() {

        }

        @Override
        public void setFileName(String fullFileName) {
            this.filename = fullFileName;

        }

        @Override
        public String getFileContent() throws IOException {
            FileInputStream inputStream = new FileInputStream(filename);
            String sum = "";
            while (inputStream.available() > 0) //пока остались непрочитанные байты
            {
                int data = inputStream.read(); //прочитать очередной байт
                sum += (char)data; //добавить его к общей сумме
            }
            inputStream.close(); // закрываем поток
            System.out.println(sum); //выводим сумму на экран.
            return sum;
        }
    }
}
