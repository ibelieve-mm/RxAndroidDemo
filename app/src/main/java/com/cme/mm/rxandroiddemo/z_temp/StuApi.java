package com.cme.mm.rxandroiddemo.z_temp;

import java.util.List;

/**
 * Created by ChenME on 2016/9/7.
 */
public interface StuApi {

    interface StudentsCallback {
        void onStudentsListGetOk(List<Student> students);

        void onQueryFailed(Exception e);
    }

    interface StoreCallback {
        void onStudentStored(String name);

        void onStoredFailed(Exception e);
    }


    List<Student> queryStudents(String query);

    String store(Student student);
}
