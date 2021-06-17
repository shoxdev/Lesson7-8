package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.lesson7.entity.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Integer> {

    List<Group> findAllByFaculty_University_Id(Integer universityId);


    @Query("select  gr from  groups  gr where gr.faculty.university.id=:universityId")
     List<Group> getGroupByUniversity(Integer universityId);

    @Query(value = "select  * from groups  g" +
            " join faculty f on f.id=g.faculty_id " +
            "join university u on u.id=f.university_idwhere u.id=:universityId ",nativeQuery = true)
    List<Group> getGroupByUniversityIdNative(Integer universityId);
}
