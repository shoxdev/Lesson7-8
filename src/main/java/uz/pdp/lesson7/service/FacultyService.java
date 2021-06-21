package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Faculty;
import uz.pdp.lesson7.entity.University;
import uz.pdp.lesson7.payload.FacultyDto;
import uz.pdp.lesson7.repository.FacultyRepository;
import uz.pdp.lesson7.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;
    public String add(FacultyDto facultyDto) {
        Faculty faculty=new Faculty();
        if (facultyRepository.existsByNameAndUniversityId(facultyDto.getName(),facultyDto.getUniversityId()))
            return "This university such faculty exists";
        faculty.setName(facultyDto.getName());
        Optional<University> optional = universityRepository.findById(facultyDto.getUniversityId());
       if (!optional.isPresent())
           return "University not found";
        University university = optional.get();
        faculty.setUniversity(university);
        facultyRepository.save(faculty);
        return "Added";
    }

    public List<Faculty> getFacultiesByUniversityId(Integer universityId) {

        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    public List<Faculty> get() {
      return   facultyRepository.findAll();
    }

    public String delete(Integer id) {
        facultyRepository.deleteById(id);
        return "Deleted";
    }


    public String edit(Integer id, FacultyDto facultyDto) {
        Optional<Faculty> byId = facultyRepository.findById(id);
        if (byId.isPresent()){
            Faculty faculty = byId.get();
            faculty.setName(facultyDto.getName());
            Optional<University> optional = universityRepository.findById(facultyDto.getUniversityId());
            if (!optional.isPresent()){
                return "university not found";
            }
            faculty.setUniversity(optional.get());
            facultyRepository.save(faculty);
            return "Faculty edited";
        }
        return "Faculty not found";
    }
}
