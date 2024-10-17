package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;

import java.time.LocalDate;

@Getter
@Setter
public class FilterCourseDTO {

    private String name;
    private Double price;
    private Integer duration;
    private LocalDate from;
    private LocalDate to;

}
