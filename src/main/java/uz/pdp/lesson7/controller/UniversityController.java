package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.entity.University;
import uz.pdp.lesson7.payload.UniversityDto;
import uz.pdp.lesson7.repository.AddressRepository;
import uz.pdp.lesson7.repository.UniversityRepository;
import uz.pdp.lesson7.service.UniversityService;

import java.util.List;

@RestController

public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    UniversityService universityService;



    @RequestMapping(value = "/university",method = RequestMethod.GET)
    public List<University> getUniversities(){

        List<University> universityList = universityRepository.findAll();
        return universityList;
    }


    @RequestMapping(value = "/university",method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto){
       return universityService.addedUniversity(universityDto);

    }

    @PutMapping("/update/{id}")
    public String edit(@PathVariable Integer id, @RequestBody UniversityDto universityDto){
        return universityService.edit(id,universityDto);
    }

   @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public  String delete(@PathVariable Integer id){
        return universityService.delete(id);
    }
}
