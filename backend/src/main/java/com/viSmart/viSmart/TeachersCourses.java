package com.viSmart.viSmart;

import javax.persistence.*;

@Entity
public class TeachersCourses {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private int teacher_id;
    private int course_id;

    public Long getId() {
        return id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
