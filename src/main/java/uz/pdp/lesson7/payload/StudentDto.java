package uz.pdp.lesson7.payload;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {

    private String firstName;
    private Integer addressId;
    private List<Integer> subjectId;
    private Integer groupId;
}
