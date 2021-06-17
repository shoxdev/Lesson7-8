package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.University;
import uz.pdp.lesson7.payload.UniversityDto;

public interface UniversityRepository extends JpaRepository<University,Integer> {

}
