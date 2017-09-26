package com.javarush.task.task36.task3606;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File dir = new File(packageName);
        String[] classFiles = dir.list();
        for (String classFile : classFiles)
        {
            final String finalPath = packageName + File.separator;
            ClassLoader loader = new ClassLoader()
            {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException
                {
                    byte[] temp = getBytesFromFile(finalPath + name + ".class");
                    return defineClass(null, temp, 0, temp.length);
                }

                private byte[] getBytesFromFile(String path)
                {
                    File file = new File(path);
                    InputStream is = null;
                    try
                    {
                        is = new FileInputStream(file);
                    }
                    catch (FileNotFoundException e)
                    {
                    }
                    // Get the size of the file
                    long length = file.length();
                    if (length > Integer.MAX_VALUE)
                    {
                        // File is too large
                    }
                    // Create the byte array to hold the data
                    byte[] bytes = new byte[(int) length];
                    // Read in the bytes
                    int offset = 0;
                    int numRead = 0;
                    try
                    {
                        while (offset < bytes.length
                                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
                        {
                            offset += numRead;
                        }
                        // Close the input stream and return bytes
                        is.close();
                    }
                    catch (IOException e)
                    {
                    }
                    return bytes;
                }
            };
            String className = classFile.substring(0, classFile.length() - 6);
            try
            {
                Class clazz = loader.loadClass(className);
                hiddenClasses.add(clazz);
            }
            catch (ClassNotFoundException e)
            {
            }
        }

    }


    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz : hiddenClasses)
        {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase()))
            {
                try
                {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for (Constructor constructor : constructors)
                    {
                        if (constructor.getParameterTypes().length == 0)
                        {
                            constructor.setAccessible(true);
                            return (HiddenClass) constructor.newInstance();
                        }
                    }
                }
                catch (InstantiationException | InvocationTargetException | IllegalAccessException e)
                {
                }
            }
        }
        return null;
    }
}

