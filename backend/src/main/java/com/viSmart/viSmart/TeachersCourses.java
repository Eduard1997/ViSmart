package com.viSmart.viSmart;

import javax.persistence.*;

@Entity
public class TeachersCourses {
    private @Id @GeneratedValue int id;
    private int teacher_id;
    private int course_id;

    public int getId() {
        return id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public int getCourse_id() {
        return course_id;
    }
}
