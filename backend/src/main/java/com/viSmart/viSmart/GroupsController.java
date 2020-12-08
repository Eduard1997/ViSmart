package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.GroupsInventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupsController {
    private final GroupsInventory groupsRepository;

    public GroupsController(GroupsInventory groupsInventory) {
        this.groupsRepository = groupsInventory;
    }

    @PostMapping(value="/get-groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getGroups() throws Exception {
        try {
            return new ResponseEntity<Object>(groupsRepository.findAll(), HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            throw new Exception("Groups error");
        }
    }
}
