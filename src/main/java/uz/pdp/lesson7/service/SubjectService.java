package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Subject;
import uz.pdp.lesson7.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    public String add(Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName){
            return "This subject already exsits ";
        }
        subjectRepository.save(subject);
        return "Subject added";
    }

    public List<Subject> get() {
        return subjectRepository.findAll();
    }
}
