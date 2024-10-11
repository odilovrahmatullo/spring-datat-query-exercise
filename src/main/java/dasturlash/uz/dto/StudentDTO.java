package dasturlash.uz.dto;

import dasturlash.uz.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class StudentDTO {
    private Integer id;
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 15, message = "the length of name must be between 3 and 15")
    @Pattern(regexp = "^[a-zA-ZА-Яа-яЁёЎўҚқҒғҲҳ]+$", message = "Name must consist of only letters")
    private String name;
    @Pattern(regexp = "^[a-zA-ZА-Яа-яЁёЎўҚқҒғҲҳ]+$", message = "Surname must consist of only letters")
    @NotNull(message = "Surname is required")
    @Size(min = 3, max = 15, message = "the length of surname must be between 3 and 15")
    private String surname;
    @NotNull(message = "Level is required")
    private String level;
    @Min(value = 18, message = "Your age must be greater than 18 ")
    @Max(value = 30, message = "Your age must be lesser than 30 ")
    @NotNull(message = "Age is required")
    private Integer age;
    @NotNull(message = "Gender is required")
    private Gender gender;
    private LocalDateTime dateTime;

}
