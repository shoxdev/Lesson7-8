package uz.pdp.lesson7.controller;

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.entity.Faculty;
import uz.pdp.lesson7.payload.FacultyDto;
import uz.pdp.lesson7.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @PostMapping("/create")
    public String add(@RequestBody FacultyDto facultyDto){
        return facultyService.add(facultyDto);
    }

    //Maxsus xodim uchun
    @GetMapping("/byUniversity/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId){
        return facultyService.getFacultiesByUniversityId(universityId);
    }
//Vazirlik uchun
    @GetMapping("/get")
    public List<Faculty> get(){
        return facultyService.get();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        return facultyService.delete(id);
    }
    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody FacultyDto facultyDto){
        return facultyService.edit(id,facultyDto);
    }
}
