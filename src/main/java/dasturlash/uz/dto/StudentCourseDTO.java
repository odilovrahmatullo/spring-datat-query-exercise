package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class StudentCourseDTO {

    String studentName;
    String courseName;
    Integer mark;
    LocalDate date;

    public StudentCourseDTO(String studentName, String courseName, Integer mark, LocalDateTime date) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.mark = mark;
        this.date = date.toLocalDate();
    }

}
