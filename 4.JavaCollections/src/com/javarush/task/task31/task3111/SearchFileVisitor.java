package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean containsName = true;
        if (partOfName != null && !file.getFileName().toString().contains(partOfName))
            containsName = false;

        String content = new String(Files.readAllBytes(file));
        boolean containsContent = true;
        if (partOfContent != null && !content.contains(partOfContent))
            containsContent = false;

        long size = Files.size(file);
        boolean containsMinSize = true;
        if (minSize != 0 && size < minSize) {
            containsMinSize = false;
        }
        boolean containsMaxSize = true;
        if (maxSize != 0 && size > maxSize) {
            containsMinSize = false;
        }


        if (containsName && containsContent && containsMinSize && containsMaxSize)
            foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }


    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
