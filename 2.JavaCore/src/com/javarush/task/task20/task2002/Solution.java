package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream("C:\\z\\1.1.txt"); //your_file_name);
            InputStream inputStream = new FileInputStream("C:\\z\\1.1.txt"); //your_file_name);

            JavaRush javaRush = new JavaRush();
            User user1 = new User();
            user1.setFirstName("fn");
            user1.setLastName("sn");
            user1.setBirthDate(new Date());
            user1.setCountry(User.Country.UKRAINE);
            user1.setMale(true);
            javaRush.users.add(user1);
            User user2 = new User();
            user2.setFirstName("fn2");
            user2.setLastName("sn2");
            user2.setBirthDate(new Date());
            user2.setCountry(User.Country.UKRAINE);
            user2.setMale(false);
            javaRush.users.add(user2);
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
//            if (javaRush.equals(loadedObject)) System.out.println("Good)");
//            else {
//                System.out.println("Bad(");
//            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);
            if (users != null) {
                for (User a : users) {
                    printWriter.println(a.getFirstName());
                    printWriter.println(a.getLastName());
                    printWriter.println(a.getBirthDate().getTime());
                    printWriter.println(a.isMale());
                    printWriter.println(a.getCountry());
                }
            }
            printWriter.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                User user = new User();
                user.setFirstName(line);
                if ((line = reader.readLine()) != null) {
                    user.setLastName(line);
                }
                if ((line = reader.readLine()) != null) {
                    user.setBirthDate(new Date(Long.parseLong(line)));
                }
                if ((line = reader.readLine()) != null) {
                    user.setMale(line.equals("true"));
                }
                if ((line = reader.readLine()) != null) {
                    if (line.equals("UKRAINE")) user.setCountry(User.Country.UKRAINE);
                    else if (line.equals("RUSSIA")) user.setCountry(User.Country.RUSSIA);
                    else user.setCountry(User.Country.OTHER);
                }
                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
