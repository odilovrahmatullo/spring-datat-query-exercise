package dasturlash.uz.dto;

import dasturlash.uz.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentUpdateDTO {
    private Integer id;
    @Size(min = 3, max = 15, message = "the length of name must be between 3 and 15")
    @Pattern(regexp = "^[a-zA-ZА-Яа-яЁёЎўҚқҒғҲҳ]+$", message = "Name must consist of only letters")
    private String name;

    @Pattern(regexp = "^[a-zA-ZА-Яа-яЁёЎўҚқҒғҲҳ]+$", message = "Surname must consist of only letters")
    @Size(min = 3, max = 15, message = "the length of surname must be between 3 and 15")
    private String surname;

    private String level;
    @Min(value = 18, message = "Your age must be greater than 18 ")
    @Max(value = 30, message = "Your age must be lesser than 30 ")
    private Integer age;


    private Gender gender;
    private LocalDateTime dateTime;
}
