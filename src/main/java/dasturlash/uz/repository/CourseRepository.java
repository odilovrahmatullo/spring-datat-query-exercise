package dasturlash.uz.repository;

import dasturlash.uz.entity.CourseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity,Integer> {
    @Query(value = "From CourseEntity s where s.id = ?1")
    CourseEntity getById(int id);

    @Query(value = "select * from course", nativeQuery = true)
    List<CourseEntity> getAll();

    @Query("From CourseEntity c where c.name = ?1")
    List<CourseEntity> findByName(String name);

    @Query("From CourseEntity c where c.duration  = ?1")
    List<CourseEntity> getByDuration(Integer duration);

    @Query(value = "select * from course where price = ?1",nativeQuery = true)
    List<CourseEntity> findByPrice(Double price);

    @Query(value = "select * from course where price between ?1 and ?2", nativeQuery = true)
    List<CourseEntity> findByPriceBetween(Double lower, Double higher);

    @Query(value = "From CourseEntity where dateTime between ?1 and ?2")
    List<CourseEntity> findByDateTimeBetween(LocalDateTime from, LocalDateTime to);

    @Modifying
    @Transactional
    @Query(value = "delete from CourseEntity where id = ?1")
    int deleteId(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE CourseEntity s SET " +
            "s.name = COALESCE(:nameP, s.name), " +
            "s.price = COALESCE(:priceP, s.price), " +
            "s.duration = COALESCE(:durationP, s.duration), " +
            "s.dateTime = COALESCE(:dateTimeP, s.dateTime) " +
            "WHERE s.id = :id")
    int updateById(@Param("id") Integer id,
                   @Param("nameP") String name,
                   @Param("priceP") Double price,
                   @Param("durationP") Integer duration,
                   @Param("dateTimeP") LocalDateTime dateTime
    );


    //Pagination
    @Query(value = "From CourseEntity c order by c.dateTime desc ")
    Page<CourseEntity> getAllCourses(Pageable pageable);

    @Query(value = "From CourseEntity c where c.price  = ?1 order by c.dateTime desc ")
    Page<CourseEntity> paginationByPrice(Double price, Pageable pageable);

    @Query(value = "from CourseEntity c where c.price between ?1 and ?2 order by c.dateTime asc")
    Page<CourseEntity> byPrices(Double price1, Double price2, Pageable pageable);
}
