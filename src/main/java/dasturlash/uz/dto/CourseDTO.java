package dasturlash.uz.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseDTO {
    private Integer id;
    @NotNull(message = "CourseName is required")
    @Pattern(regexp = "^[a-zA-ZА-Яа-яЁёЎўҚқҒғҲҳ]+$", message = "CourseName must consist of only letters")
    @Size(min = 3, max = 15, message = "the length of CourseName must be between 3 and 15")

    private String name;

    @NotNull(message = "CoursePrice is required")
    @PositiveOrZero(message = "Price should be positive or free ")
    private Double price;
    @NotNull(message = "CourseDuration is required")
    @Positive(message = "Course duration must be positive")
    private Integer duration;
    private LocalDateTime dateTime;
}
