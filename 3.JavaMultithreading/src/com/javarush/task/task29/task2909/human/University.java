package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<Student>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public int getAge() {
        return age;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {

        return students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student student = null;
        for (Student s : students) {
            if (s.getAverageGrade() == averageGrade) student = s;
        }
        return student;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student student = null;
        double max = 0;
        for (Student s : students) {
            if (max < s.getAverageGrade()) {
                student = s;
                max = s.getAverageGrade();
            }
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade(){
        Student student = null;
        double min = 0;
        for (Student s : students) {
            if (min > s.getAverageGrade() || min == 0) {
                student = s;
                min = s.getAverageGrade();
            }
        }
        return student;
    }

    public void expel(Student student) {
        students.remove(student);
    }

}