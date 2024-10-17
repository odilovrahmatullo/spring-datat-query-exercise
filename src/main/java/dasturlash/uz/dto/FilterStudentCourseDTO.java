package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FilterStudentCourseDTO {

    private Integer studentId;
    private Integer courseId;
    private Integer markFrom;
    private Integer markTo;

    private String studentName;
    private String courseName;

    private LocalDate createdFrom;
    private LocalDate createdTo;

    public FilterStudentCourseDTO(Integer studentId, Integer courseId, Integer markFrom, Integer markTo,
                                  String student, String course, LocalDate createdFrom, LocalDate createdTo) {

        this.studentId = studentId;
        this.courseId = courseId;
        this.markFrom = markFrom;
        this.markTo = markTo;
        this.studentName = student;
        this.courseName = course;
        this.createdFrom = createdFrom;
        this.createdTo = createdTo;

    }
}
