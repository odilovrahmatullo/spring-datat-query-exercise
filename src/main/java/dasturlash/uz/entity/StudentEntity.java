package dasturlash.uz.entity;

import dasturlash.uz.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String level;
    @Column
    private Integer age;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
   @Column(name = "created_date")
   private LocalDateTime dateTime = LocalDateTime.now();

    @OneToMany(mappedBy = "student")
    private List<StudentCourseMarkEntity> studentCourses;

}