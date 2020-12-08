package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.CoursesInventory;
import com.viSmart.viSmart.Repository.TeachersCoursesInventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CoursesController {
    private final CoursesInventory coursesRepository;
    private final TeachersCoursesInventory teachersCoursesRepository;

    public CoursesController(CoursesInventory repository, TeachersCoursesInventory teachersCoursesRepository) {
        this.coursesRepository = repository;
        this.teachersCoursesRepository = teachersCoursesRepository;
    }

    @GetMapping(value="/get-courses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getAllClasses() throws Exception {
        try {
            return new ResponseEntity<Object>(coursesRepository.findAll(), HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            throw new Exception("Courses error");
        }
    }

    @PostMapping(value="/get-teacher-courses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getTeacherCourses(@RequestBody Map<String, String> json) throws Exception {
        try {
            Integer teacherId = Integer.parseInt(json.get("userId"));
            List<TeachersCourses> courses = teachersCoursesRepository.findAll();
            ArrayList<Integer> coursesIds = new ArrayList<>();
            for (TeachersCourses iterator:courses) {
                if(iterator.getTeacher_id() == teacherId) {
                    coursesIds.add(iterator.getCourse_id());
                }
            }
            List<Courses> coursesList = coursesRepository.findAll();
            List<Courses>responseCourses = new ArrayList<>();
            for(int courseId: coursesIds) {
                for (Courses course:coursesList) {
                    if(course.getId() == courseId) {
                        responseCourses.add(course);
                    }
                }
            }

            return new ResponseEntity<Object>(responseCourses, HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            throw new Exception("Courses error");
        }
    }
}
