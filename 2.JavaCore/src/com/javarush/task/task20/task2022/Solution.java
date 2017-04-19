package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
//        out.writeObject(stream);
//        out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(this.fileName, true);
//        stream = (FileOutputStream) in.readObject();
//        in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution("C:\\z\\1.txt");
        solution.writeObject("Give me answer");
        solution.close();

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cat.dat"));
//        solution.writeObject(outputStream);
        outputStream.writeObject(solution);
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("cat.dat"));
        Object object = inputStream.readObject();
        inputStream.close();
        Solution solution1 = (Solution) object;
        solution1.writeObject("0");
        solution1.close();

    }
}
