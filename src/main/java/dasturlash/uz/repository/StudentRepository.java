package dasturlash.uz.repository;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.enums.Gender;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>, JpaRepository<StudentEntity, Integer>,
        PagingAndSortingRepository<StudentEntity, Integer> {
/*
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO student (name,surname,age,level,gender,created_date)"+
  "VALUES(:name,:surname,:age,:level,:gender,:dateTime)", nativeQuery = true)
  int insertStudent(@Param("name") String name,
                    @Param("surname") String surname,
                    @Param("age") Integer age,
                    @Param("level") String level,
                    @Param("gender") Gender gender,
                    @Param("dateTime") LocalDateTime localDateTime);
*/


    @Query(value = "select * from student where name = :nameP", nativeQuery = true)
    List<StudentEntity> findByName(@Param("nameP") String name);

    @Query(value = "From StudentEntity ")
    List<StudentEntity> getAll();

    @Query(value = "select * from student where id = :student_id", nativeQuery = true)
    StudentEntity getById(@Param("student_id") Integer student_id);

    @Query(value = "From StudentEntity where surname = ?1")
    List<StudentEntity> findBySurname(String surname);

    @Query(value = "from StudentEntity where age = ?1")
    List<StudentEntity> findByAge(int age);

    @Query(value = "from StudentEntity where gender = ?1")
    List<StudentEntity> findByGender(Gender gender);

    @Query(value = "from StudentEntity where level = ?1")
    List<StudentEntity> findByLevel(String level);

    @Query(value = "select * from student where created_date between ?1 and ?2", nativeQuery = true)
    List<StudentEntity> findByDateTimeBetween(LocalDateTime from, LocalDateTime to);

    @Modifying
    @Transactional
    @Query(value = "Delete from StudentEntity s where s.id = :id")
    int deleteId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE StudentEntity s SET " +
            "s.name = COALESCE(:nameP, s.name), " +
            "s.surname = COALESCE(:surnameP, s.surname), " +
            "s.age = COALESCE(:ageP, s.age), " +
            "s.level = COALESCE(:levelP, s.level), " +
            "s.gender = COALESCE(:genderP, s.gender), " +
            "s.dateTime = COALESCE(:dateTimeP, s.dateTime) " +
            "WHERE s.id = :id")
    int updateById(@Param("id") Integer id,
                   @Param("nameP") String name,
                   @Param("surnameP") String surname,
                   @Param("ageP") Integer age,
                   @Param("levelP") String level,
                   @Param("genderP") String gender,
                   @Param("dateTimeP") LocalDateTime dateTime
    );

    //Pagination
    @Query(value = "From StudentEntity s order by s.dateTime desc ")
    Page<StudentEntity> getAllStudents(Pageable pageable);

    @Query(value = "From StudentEntity s where s.level  = ?1 order by s.id desc ")
    Page<StudentEntity> paginationByLevel(String level, Pageable pageable);

    @Query(value = "From StudentEntity s where s.gender  = ?1 order by s.dateTime asc ")
    Page<StudentEntity> paginationByGender(Gender gender, Pageable pageable);


}
