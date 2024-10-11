package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private Double price;
    @Column
    private Integer duration;
    @Column(name = "created_date")
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "course")
    private List<StudentCourseMarkEntity> studentCourses;
}
