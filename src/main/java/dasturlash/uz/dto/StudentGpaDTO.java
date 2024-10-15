package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentGpaDTO {

    String studentName;
    String courseName;
    Double gpa;

    public StudentGpaDTO(String studentName, String courseName, Double gpa) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.gpa = gpa;
    }
}
