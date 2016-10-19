package com.cme.mm.rxandroiddemo.bean;

import java.util.Collections;
import java.util.List;

/**
 * Created by ChenME on 2016/9/7.
 */
public class StudentHelper {

    StuApi stuApi;

    public String saveTheBastStudent(String query) {
        List<Student> students = stuApi.queryStudents(query);
        Student best = findBest(students);
        String saveName = stuApi.store(best);
        return saveName;
    }

    private Student findBest(List<Student> students) {
        return Collections.max(students);
    }

    public  interface BestStudentCallback{
        void  onBestStudentSaved(String name);
        void onError(Exception e);
    }
}
