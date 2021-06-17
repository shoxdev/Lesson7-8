package uz.pdp.lesson7.payload;

import lombok.Data;

@Data
public class UniversityDto {//malumtlarni tashish uchun xizmat qiladi
    private String name;
    private String city;
    private String district;
    private String street;
}
