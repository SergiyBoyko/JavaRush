package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Serhii Boiko on 23.08.2017.
 */
public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        return -1;
    }

//    public static void main(String[] args) {
//        Entry entry = new Entry(0, 0L, "first", null);
//        entry = new Entry(1, 1L, "second", entry);
//        entry = new Entry(10, 10L, "third", entry);
//        FileBucket fb = new FileBucket();
//        fb.putEntry(entry);
//        Entry entry1 = fb.getEntry();
//        System.out.println(entry.toString() + "\n" + entry.next.toString() + "\n" + entry.next.next.toString());
//        System.out.println(entry1.toString() + "\n" + entry1.next.toString() + "\n" + entry1.next.next.toString());
//        System.out.println(entry.equals(entry1) ? "good)" : "bad(");
//    }

    public void putEntry(Entry entry) {
        try {
            OutputStream fos = Files.newOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(entry);
            oos.flush();
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        Entry result = null;
        if(getFileSize()<=0) return null;
        try {
            InputStream fis = Files.newInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = (Entry) ois.readObject();
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        }
        return result;
    }

    public void remove() throws IOException {
        Files.delete(path);
    }
}
