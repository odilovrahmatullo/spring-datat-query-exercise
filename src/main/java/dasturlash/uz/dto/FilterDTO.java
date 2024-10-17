package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FilterDTO {

    private String name;
    private String surname;
    private String gender;
    private String age;
    private LocalDate from;
    private LocalDate to;
}
