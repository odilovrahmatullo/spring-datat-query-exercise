package dasturlash.uz.repository;

import dasturlash.uz.entity.CourseEntity;
import dasturlash.uz.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.io.DataOutput;
import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity,Integer> {
    List<CourseEntity> findByName(String name);
    List<CourseEntity> findByDuration(Integer duration);
    List<CourseEntity> findByPrice(Double price);
    List<CourseEntity> findByPriceBetween(Double lower, Double higher);
    List<CourseEntity> findByDateTimeBetween(LocalDateTime from, LocalDateTime to);


}
