package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "student_course")
@Getter
@Setter
public class StudentCourseMarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
    @Column
    private Integer mark;
    @Column(name = "created_date")
    private LocalDate createdDate;

}
