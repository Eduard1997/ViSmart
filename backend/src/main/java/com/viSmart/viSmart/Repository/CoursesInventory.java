package com.viSmart.viSmart.Repository;

import com.viSmart.viSmart.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesInventory extends JpaRepository<Courses, Long> {
    Courses findById(int id);
    Courses findByName(String name);
}
