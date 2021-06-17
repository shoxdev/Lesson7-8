package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.controller.UniversityController;
import uz.pdp.lesson7.entity.Address;
import uz.pdp.lesson7.entity.University;
import uz.pdp.lesson7.payload.UniversityDto;
import uz.pdp.lesson7.repository.AddressRepository;
import uz.pdp.lesson7.repository.UniversityRepository;

import java.util.Optional;

@Service
public class UniversityService {
    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    public String addedUniversity(UniversityDto universityDto) {
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());

        Address saveAddress = addressRepository.save(address);

        University university = new University();
        university.setAddress(saveAddress);
        university.setName(universityDto.getName());
        universityRepository.save(university);
        return "Successfully added";


    }

    public String edit(Integer id, UniversityDto universityDto) {
        Optional<University> optional =
                universityRepository.findById(id);
        if (optional.isPresent()) {
            University university = optional.get();
            university.setName(universityDto.getName());
            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);
            universityRepository.save(university);
            return "University edited";
        }
        return "University not found";
    }

    public String delete(Integer id) {
        universityRepository.deleteById(id);
        return "Delete";
    }
}
