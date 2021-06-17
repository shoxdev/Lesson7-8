package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

    boolean existsByName(String name);

}
