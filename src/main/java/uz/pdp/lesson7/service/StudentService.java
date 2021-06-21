package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Address;
import uz.pdp.lesson7.entity.Group;
import uz.pdp.lesson7.entity.Student;
import uz.pdp.lesson7.entity.Subject;
import uz.pdp.lesson7.payload.StudentDto;
import uz.pdp.lesson7.repository.AddressRepository;
import uz.pdp.lesson7.repository.GroupRepository;
import uz.pdp.lesson7.repository.StudentRepository;
import uz.pdp.lesson7.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    GroupRepository groupRepository;

    public Page<Student> getStudentMinistry(int page) {
        Pageable pageable= PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    public Page<Student> getStudentPageForUniversity(Integer universityId, int page) {
        Pageable pageable=PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }


    public Page<Student> getStudentPageForFaculty(Integer facultyId, int page) {
        Pageable pageable=PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAllByGroup_FacultyId(facultyId, pageable);
        return studentPage;
    }

    public Page<Student> getStudentPageForGroup(Integer groupId, int page) {
        Pageable pageable=PageRequest.of(page,10);
        return studentRepository.findAllByGroupId(groupId,pageable);
    }

    public String add(StudentDto studentDto) {
        Student student=new Student();
        student.setFirstName(studentDto.getFirstName());
        Optional<Group> groupOptional = groupRepository.findById(studentDto.getGroupId());
        if (!groupOptional.isPresent()){
            return "This Group not found";
        }
        Group group = groupOptional.get();
        student.setGroup(group);
        Optional<Address> addressOptional = addressRepository.findById(studentDto.getAddressId());
        if (!addressOptional.isPresent()){
            return "This Address not found";
        }
        Address address = addressOptional.get();
        student.setAddress(address);
        List<Subject> subjectList=new ArrayList<>();
        List<Integer> subjectId = studentDto.getSubjectId();
        for (Integer integer : subjectId) {
            Subject subject = subjectRepository.findById(integer).orElseThrow(() -> new IllegalArgumentException("Subject not found"));
            subjectList.add(subject);
        }
        student.setSubject(subjectList);
        studentRepository.save(student);
        return "Save Student";
    }

    public String edit(Integer studentId, StudentDto studentDto) {
        Optional<Student> optional = studentRepository.findById(studentId);
        if (!optional.isPresent()){
            return "Not found";
        }
        Student student = optional.get();
        student.setFirstName(studentDto.getFirstName());
        Optional<Group> groupOptional = groupRepository.findById(studentDto.getGroupId());
        if (!groupOptional.isPresent()){
            return "This Group not found";
        }
        Group group = groupOptional.get();
        student.setGroup(group);
        Optional<Address> addressOptional = addressRepository.findById(studentDto.getAddressId());
        if (!addressOptional.isPresent()){
            return "This Address not found";
        }
        Address address = addressOptional.get();
        student.setAddress(address);
        List<Subject> subjectList=new ArrayList<>();
        List<Integer> subjectId = studentDto.getSubjectId();
        for (Integer integer : subjectId) {
            Subject subject = subjectRepository.findById(integer).orElseThrow(() -> new IllegalArgumentException("Subject not found"));
            subjectList.add(subject);
        }
        student.setSubject(subjectList);
        studentRepository.save(student);
        return "Edit Student";
    }

    public String delete(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (!optional.isPresent()){
            return "Not found";
        }
        studentRepository.deleteById(id);
        return "Deleted";
    }
}
