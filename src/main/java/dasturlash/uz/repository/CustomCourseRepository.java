package dasturlash.uz.repository;

import dasturlash.uz.dto.FilterCourseDTO;
import dasturlash.uz.dto.FilterResultDTO;
import dasturlash.uz.entity.CourseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomCourseRepository {

    @Autowired
    EntityManager entityManager;
    public FilterResultDTO<CourseEntity> filter(FilterCourseDTO filter, int page, int size) {
        StringBuilder conditions = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if(filter.getName()!=null){
            conditions.append("and s.name ilike :name ");
            params.put("name","%"+ filter.getName()+"%");
        }
        if(filter.getPrice()!=null){
            conditions.append("and s.price=:price ");
            params.put("price",filter.getPrice());
        }
        if(filter.getDuration()!=null) {
            conditions.append("and s.duration=:duration ");
            params.put("duration", filter.getDuration());
        }
        if(filter.getFrom()!=null) {
            conditions.append("and s.from>=:from ");
            params.put("from", filter.getFrom());
        }
        if(filter.getTo()!=null) {
            conditions.append("and s.to<=:to ");
            params.put("to", filter.getTo());
        }

        StringBuilder selectBuilder = new StringBuilder("from CourseEntity as s where 1=1 ");
        selectBuilder.append(conditions);
        StringBuilder countBuilder = new StringBuilder("select count(*) From CourseEntity as s where 1=1 ");
        countBuilder.append(conditions);

        Query selectQuery = entityManager.createQuery(selectBuilder.toString(),CourseEntity.class);
        selectQuery.setFirstResult(page * size); //offset
        selectQuery.setMaxResults(size); //size
        Query countQuery = entityManager.createQuery(countBuilder.toString());
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            selectQuery.setParameter(entry.getKey(), entry.getValue());
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }




        List<CourseEntity> entityList = selectQuery.getResultList();
        Long total = (Long) countQuery.getSingleResult();

        return new FilterResultDTO<CourseEntity>(entityList,total);

    }

}
