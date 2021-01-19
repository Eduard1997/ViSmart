package com.viSmart.viSmart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeachersCoursesTest {

    @Test
    void getId() {
        TeachersCourses teacher_course = new TeachersCourses();
        Long id = teacher_course.getId();
        assertEquals(id, teacher_course.getId());
    }

    @Test
    void getTeacher_id() {
        TeachersCourses teacher_id = new TeachersCourses();
        int id = teacher_id.getTeacher_id();
        assertEquals(id, teacher_id.getTeacher_id());
    }

    @Test
    void getCourse_id() {
        TeachersCourses course_id = new TeachersCourses();
        int id = course_id.getCourse_id();
        assertEquals(id, course_id.getCourse_id());
    }


    @Test
    void setTeacher_id() {
        TeachersCourses teacher_id = new TeachersCourses();
        teacher_id.setTeacher_id(5565);
        assertEquals(5565, teacher_id.getId());
    }

    @Test
    void setCourse_id() {
        TeachersCourses course_id = new TeachersCourses();
        course_id.setCourse_id(5565);
        assertEquals(5565, course_id.getCourse_id());
    }
}