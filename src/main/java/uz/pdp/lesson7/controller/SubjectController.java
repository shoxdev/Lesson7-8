package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.entity.Subject;
import uz.pdp.lesson7.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping("/create")
    public String add(@RequestBody Subject subject){
        return subjectService.add(subject);
    }

    @GetMapping("/read")
    public List<Subject> getSubjects(){
        return subjectService.get();
    }
}
