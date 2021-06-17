package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Faculty;
import uz.pdp.lesson7.entity.Group;
import uz.pdp.lesson7.payload.GroupDto;
import uz.pdp.lesson7.repository.FacultyRepository;
import uz.pdp.lesson7.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public List<Group> getGroupByUniversity(Integer universityId) {

        List<Group> allByFaculty_university_id = groupRepository.findAllByFaculty_University_Id(universityId);
        List<Group> groupByUniversity = groupRepository.getGroupByUniversity(universityId);
      List<Group> groupByUniversityIdNative = groupRepository.getGroupByUniversityIdNative(universityId);

        return groupByUniversityIdNative;

    }

    public String add(GroupDto groupDto) {
        Group group=new Group();
        Optional<Faculty> optional = facultyRepository.findById(groupDto.getFacultyId());
        if (!optional.isPresent()) {
            return "Such faculty not found";
        }
        Faculty faculty = optional.get();
        group.setFaculty(faculty);
        group.setName(groupDto.getName());
        groupRepository.save(group);
        return "Added";

    }


}
