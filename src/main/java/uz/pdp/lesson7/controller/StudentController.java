package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.entity.Student;
import uz.pdp.lesson7.payload.StudentDto;
import uz.pdp.lesson7.service.StudentService;

import javax.security.auth.login.CredentialException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
//vazirlik uchun
    @GetMapping("/forMinistry")
    public Page<Student> getStudentMinistry(@RequestParam int page){
        return studentService.getStudentMinistry(page);
    }

    //universitet uchun
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentPageForUniversity(@PathVariable Integer universityId,@RequestParam int page){
        return studentService.getStudentPageForUniversity(universityId,page);
    }

    //fakultet uchun
    @GetMapping("/forFaculty/{facultyId}")
    public Page<Student> getStudentPageForFaculty(@PathVariable Integer facultyId, @RequestParam int page){
        return studentService.getStudentPageForFaculty(facultyId,page);
    }

    //Group uchun
    @GetMapping("/forGroup/{groupId}")
    public Page<Student> getStudentPageForGroup(@PathVariable Integer groupId, @RequestParam int page){
        return studentService.getStudentPageForGroup(groupId,page);
    }

    @PostMapping("/add")
    public String add(@RequestBody StudentDto studentDto){
        return studentService.add(studentDto);
    }

    @PutMapping("/edit/{studentId}")
    public String edit(@PathVariable Integer studentId,@RequestBody StudentDto studentDto){
        return studentService.edit(studentId,studentDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        return studentService.delete(id);
    }
}
