package dasturlash.uz.repository;

import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.enums.Gender;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity,Integer> {
  List<StudentEntity> findByName(String name);
  List<StudentEntity> findBySurname(String surname);
  List<StudentEntity> findByAge(int age);
  List<StudentEntity> findByGender(Gender gender);
  List<StudentEntity> findByLevel(String level);
  List<StudentEntity> findByDateTimeBetween(LocalDateTime from, LocalDateTime to);
}
