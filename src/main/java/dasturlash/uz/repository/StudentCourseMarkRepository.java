package dasturlash.uz.repository;

import dasturlash.uz.dto.StudentCourseDTO;
import dasturlash.uz.dto.StudentGpaDTO;
import dasturlash.uz.entity.StudentCourseMarkEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, Integer> {


    @Query(value = "From StudentCourseMarkEntity s where s.id = ?1")
    StudentCourseMarkEntity getById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE StudentCourseMarkEntity s SET " +
            "s.studentId = COALESCE(:sId, s.studentId), " +
            "s.courseId = COALESCE(:cId, s.courseId), " +
            "s.mark = COALESCE(:markP, s.mark )," +
            "s.createdDate = COALESCE(:dateTimeP, s.createdDate) " +
            "WHERE s.id = :id")
    int updateById(@Param("id") Integer id,
                   @Param("sId") Integer studentId,
                   @Param("cId") Integer courseId,
                   @Param("markP") Integer mark,
                   @Param("dateTimeP") LocalDateTime dateTime
    );

    @Query(value = "DELETE from StudentCourseMarkEntity where id = ?1")
    int deleteId(Integer id);

    @Query(value = "from StudentCourseMarkEntity ")
    List<StudentCourseMarkEntity> getAll();

    @Query("select new dasturlash.uz.dto.StudentCourseDTO(sc.student.name, sc.course.name, sc.mark, sc.createdDate) " +
            "from StudentCourseMarkEntity sc " +
            "Where sc.studentId = ?1 and sc.createdDate BETWEEN ?2 and ?3 ")
    List<StudentCourseDTO> findByStudentIdAndCreatedDateBetween(Integer id, LocalDateTime fromDate, LocalDateTime toDate);


    @Query("select new dasturlash.uz.dto.StudentCourseDTO(sc.student.name, sc.course.name, sc.mark, sc.createdDate) " +
            "from StudentCourseMarkEntity sc " +
            "Where sc.student.id=?1 order by sc.createdDate DESC")
    List<StudentCourseDTO> getStudentMarksDesc(Integer id);

    @Query("select new dasturlash.uz.dto.StudentCourseDTO(sc.student.name, sc.course.name, sc.mark, sc.createdDate)" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.student.id = ?1 and sc.course.id = ?2 order by sc.createdDate DESC")
    List<StudentCourseDTO> getMarksGivenCourse(Integer studentId, Integer courseId);


    @Query("select new dasturlash.uz.dto.StudentCourseDTO(sc.student.name, sc.course.name, sc.mark, sc.createdDate)" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.student.id = ?1 " +
            "order by sc.createdDate desc ")
    List<StudentCourseDTO> getLastGrade(Integer studentId, Pageable pageable);


    @Query("select new dasturlash.uz.dto.StudentCourseDTO(sc.student.name, sc.course.name, sc.mark, sc.createdDate)" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.student.id = ?1 " +
            "order by sc.mark desc ")
    List<StudentCourseDTO> getLast3Grades(Integer studentId, Pageable pageable);


    @Query("select new dasturlash.uz.dto.StudentCourseDTO(sc.student.name, sc.course.name, sc.mark, sc.createdDate)" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.student.id = ?1 " +
            "order by sc.createdDate asc ")
    List<StudentCourseDTO> getFirstGrade(Integer studentId, Pageable pageable);

    @Query("select new dasturlash.uz.dto.StudentCourseDTO(sc.student.name, sc.course.name, sc.mark, sc.createdDate)" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.student.id = ?1 and sc.course.id = ?2 " +
            "order by sc.createdDate asc")
    List<StudentCourseDTO> getFirstGradeGivenCourse(Integer studentId, Integer courseId, Pageable pageable);

    @Query("select new dasturlash.uz.dto.StudentCourseDTO(sc.student.name, sc.course.name, sc.mark, sc.createdDate)" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.student.id = ?1 and sc.courseId = ?2 " +
            "order by sc.mark desc ")
    List<StudentCourseDTO> getFirstBigGrade(Integer studentId, Integer courseId, Pageable pageable);

    @Query("select new dasturlash.uz.dto.StudentGpaDTO(sc.student.name , sc.course.name, AVG(sc.mark))" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.student.id = ?1 group by sc.student.name, sc.course.name")
    List<StudentGpaDTO> getAverageGrade(Integer studentId);

    @Query("select new dasturlash.uz.dto.StudentGpaDTO(sc.student.name , sc.course.name, AVG(sc.mark))" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.student.id = ?1 and sc.courseId = ?2 group by sc.student.name, sc.course.name")
    List<StudentGpaDTO> getAverageGradeGivenCourse(Integer studentId, Integer courseId);

    @Query("select count(sc.mark)" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.student.id = ?1 and sc.mark>?2")
    long countGrades(Integer studentId, Integer mark);

    @Query("select AVG(sc.mark)" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.course.id = ?1")
    long AverageGradesOfCourse(Integer courseId);

    @Query("select MAX(sc.mark)" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.course.id = ?1")
    long maxGradesOfCourse(Integer courseId);

    @Query("select count(sc.mark)" +
            "FROM StudentCourseMarkEntity sc " +
            "Where sc.course.id = ?1")
    long countGradesOfCourse(Integer courseId);

    //Pagination
    @Query("from StudentCourseMarkEntity c order by c.createdDate desc ")
    Page<StudentCourseMarkEntity> getPagination(Pageable pageable);

    @Query("select new dasturlash.uz.dto.StudentCourseDTO(sc.student.name, sc.course.name, sc.mark, sc.createdDate)" +
            "from StudentCourseMarkEntity sc where sc.studentId = ?1 order by sc.createdDate desc ")
    Page<StudentCourseDTO> getPaginationByStudentId(Integer studentId, Pageable pageable);

    @Query("select new dasturlash.uz.dto.StudentCourseDTO(sc.student.name, sc.course.name, sc.mark, sc.createdDate)" +
            "from StudentCourseMarkEntity sc where sc.courseId = ?1 order by sc.createdDate desc ")
    Page<StudentCourseDTO> getPaginationByCourseId(Integer courseId, Pageable pageable);


}
