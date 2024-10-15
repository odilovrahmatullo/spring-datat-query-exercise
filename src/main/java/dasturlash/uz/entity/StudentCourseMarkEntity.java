package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_course")
@Getter
@Setter
public class StudentCourseMarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id")
    private Integer studentId;
    @ManyToOne
    @JoinColumn(name = "student_id",insertable = false, updatable = false)
    private StudentEntity student;

    @Column(name = "course_id")
    private Integer courseId;
    @ManyToOne
    @JoinColumn(name = "course_id",insertable = false, updatable = false)
    private CourseEntity course;
    @Column
    private Integer mark;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
