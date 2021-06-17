package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.entity.Group;
import uz.pdp.lesson7.payload.GroupDto;
import uz.pdp.lesson7.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

//Vazirlik uchun
    @GetMapping("/read")
    public List<Group> getGroups(){
        return groupService.getGroups();
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupByUniversity(@PathVariable Integer universityId){
        return groupService.getGroupByUniversity(universityId);

    }

    @PostMapping("/create")
    public String add(@RequestBody GroupDto groupDto){
        return groupService.add(groupDto);
    }

}
