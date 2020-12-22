package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.TeachersCoursesInventory;
import com.viSmart.viSmart.Repository.UserInventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    private final UserInventory repository;
    private final TeachersCoursesInventory tcRepository;

    public UserController(UserInventory repository, TeachersCoursesInventory tcRepository) {

        this.repository = repository;
        this.tcRepository = tcRepository;
    }

    @GetMapping(value="/get-user-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getUser() {
        try {
            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = repository.findByEmail(email);
            return new ResponseEntity<Account>(user, HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            throw new AccountNotFoundException("nobody");
        }
    }
    @PostMapping(value="/create-account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAccount(@RequestBody Map<String, String> json) {
        try {
            String email = json.get("email");
            User emailExists = repository.findByEmail(email);
            if(emailExists != null) {
                return new ResponseEntity<Object>("User already exists", HttpStatus.OK);
            } else {
                String password = json.get("password");
                String firstName = json.get("first_name");
                String lastName = json.get("last_name");
                Integer role = Integer.parseInt(json.get("role"));
                User user = new User();
                user.setEmail(email);
                user.setFirst_name(firstName);
                user.setLast_name(lastName);
                user.setPassword(password);
                user.setRole(role);
                repository.save(user);
                if(role == 2) {
                    String group = json.get("group");
                    user.setStudy_group(group);
                    repository.save(user);
                }
                if(role == 3) {
                    User teacher = repository.findByEmail(email);
                    String [] split = json.get("classes").split(",");
                    for (int i = 0; i < split.length; i++) {
                        TeachersCourses tc = new TeachersCourses();
                        tc.setCourse_id(Integer.parseInt(split[i]));
                        tc.setTeacher_id(teacher.getId());
                        tcRepository.save(tc);
                    }
                }
                return new ResponseEntity<Object>(user, HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new AccountNotFoundException("Error on Creating User");
        }
    }

}
