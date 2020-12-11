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
import java.util.function.Function;
import java.util.stream.Collectors;

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
            List<TeachersCourses> courses = teachersCoursesRepository.findAllById(teacherId);
            List<Courses> responseCourses = coursesRepository.findAllById(
                    courses.stream().map(TeachersCourses::getId).collect(Collectors.toSet()));

            return new ResponseEntity<Object>(responseCourses, HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            throw new Exception("Courses error");
        }
    }
}
