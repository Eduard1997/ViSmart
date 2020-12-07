package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.GroupsInventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupsController {
    private final GroupsInventory groupsRepository;

    public GroupsController(GroupsInventory groupsInventory) {
        this.groupsRepository = groupsInventory;
    }

    @GetMapping(value="/get-groups", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Groups> getGroups() throws Exception {
        try {
            Groups groups = groupsRepository.findById(1);
            return new ResponseEntity<Groups>(groups, HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            throw new Exception("Groups error");
        }
    }
}
