package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.CoursesInventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoursesController {
    private final CoursesInventory coursesRepository;

    public CoursesController(CoursesInventory repository) {
        this.coursesRepository = repository;
    }

    @PostMapping(value="/get-all-classes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Courses> getAllClasses() {
        Courses courses = coursesRepository.findById(1);
        return new ResponseEntity<Courses>(courses, HttpStatus.OK);
    }
}
