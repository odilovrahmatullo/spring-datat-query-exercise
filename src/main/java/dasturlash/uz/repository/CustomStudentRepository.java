package dasturlash.uz.repository;

import dasturlash.uz.dto.FilterDTO;
import dasturlash.uz.dto.FilterResultDTO;
import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomStudentRepository {


    @Autowired
    EntityManager entityManager;

    public FilterResultDTO<StudentEntity> filter(FilterDTO filter, int page, int size) {
        StringBuilder conditions = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if(filter.getName()!=null){
            conditions.append("and s.name ilike :name ");
            params.put("name","%"+ filter.getName()+"%");
        }
        if(filter.getSurname()!=null){
            conditions.append("and s.surname ilike :surname ");
            params.put("surname","%"+filter.getSurname()+"%");
        }
        if(filter.getAge()!=null) {
            conditions.append("and s.age>=:age ");
            params.put("age", filter.getAge());
        }
        if(filter.getGender()!=null) {
            conditions.append("and s.gender=:gender ");
            params.put("gender", filter.getGender());
        }
        if(filter.getFrom()!=null) {
            LocalDateTime fromDateTime = LocalDateTime.of(filter.getFrom(), LocalTime.MIN);
            conditions.append("and s.dateTime>=:from ");
            params.put("from", fromDateTime);
        }
        if(filter.getTo()!=null) {
            conditions.append("and s.dateTime<=:to ");
            LocalDateTime toDateTime = LocalDateTime.of(filter.getTo(), LocalTime.MAX);
            params.put("to", toDateTime);
        }

        StringBuilder selectBuilder = new StringBuilder("from StudentEntity as s where 1=1 ");
        selectBuilder.append(conditions);
        StringBuilder countBuilder = new StringBuilder("select count(*) From StudentEntity as s where 1=1 ");
        countBuilder.append(conditions);

        Query selectQuery = entityManager.createQuery(selectBuilder.toString(),StudentEntity.class);
        selectQuery.setFirstResult(page * size); //offset
        selectQuery.setMaxResults(size); //size
        Query countQuery = entityManager.createQuery(countBuilder.toString());
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            selectQuery.setParameter(entry.getKey(), entry.getValue());
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }




        List<StudentEntity> entityList = selectQuery.getResultList();
        Long total = (Long) countQuery.getSingleResult();


          return new FilterResultDTO<StudentEntity>(entityList,total);

    }

}
