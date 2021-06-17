package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
