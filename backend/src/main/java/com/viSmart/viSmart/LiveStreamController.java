package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.CoursesInventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LiveStreamController {

    private final CoursesInventory coursesRepository;

    public LiveStreamController(CoursesInventory repository) {
        this.coursesRepository = repository;
    }

    @PostMapping(value="/check-stream-availability", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object>checkStreamStatus(@RequestBody Map<String, String> json) throws Exception {
        String courseName = json.get("streamName");
        Courses course = coursesRepository.findByName(courseName);
        int status = course.getIs_live();
        try {
            return new ResponseEntity<Object>(status, HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            throw new Exception("Live Stream error");
        }
    }

    @PostMapping(value="/start-stream", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object>startStream(@RequestBody Map<String, String> json) throws Exception {
        String courseName = json.get("courseName");
        String groupsList = json.get("groupsList");
        Courses course = coursesRepository.findByName(courseName);
        course.setIs_live(1);
        course.setAvailable_for_groups(groupsList);
        coursesRepository.save(course);
        String status = "success";
        try {
            return new ResponseEntity<Object>(status, HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            throw new Exception("Live Stream error");
        }
    }
}
