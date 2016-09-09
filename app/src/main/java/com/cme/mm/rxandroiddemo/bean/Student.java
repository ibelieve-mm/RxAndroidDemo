package com.cme.mm.rxandroiddemo.bean;

/**
 * Created by Administrator on 2016/9/7.
 */
public class Student implements Comparable<Student> {

    private String  name;
    private int grade;


    @Override
    public int compareTo(Student student) {
        return Integer.compare(grade,student.grade);
    }
}
