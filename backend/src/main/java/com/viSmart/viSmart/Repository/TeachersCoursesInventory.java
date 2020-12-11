package com.viSmart.viSmart.Repository;

import com.viSmart.viSmart.TeachersCourses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachersCoursesInventory extends JpaRepository<TeachersCourses, Long> {
    List<TeachersCourses> findAllById(Integer teacherId);
}
