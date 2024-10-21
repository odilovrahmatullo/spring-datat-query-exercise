package dasturlash.uz.repository;

import dasturlash.uz.dto.FilterResultDTO;
import dasturlash.uz.dto.FilterStudentCourseDTO;
import dasturlash.uz.dto.StudentCourseDTO;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomStudentCourseRepository {

    @Autowired
    private EntityManager entityManager;

    public FilterResultDTO<StudentCourseDTO> filter(FilterStudentCourseDTO filter, int page, int size) {
        StringBuilder condition = new StringBuilder();

        if(filter.getStudentId()!=null){
            condition.append(" AND s.student_id = :studentId");
        }
        if(filter.getCourseId()!=null){
            condition.append(" AND s.course_id = :courseId");
        }
        if(filter.getStudentName()!=null){
            condition.append(" AND student_name = :studentName");
        }


        StringBuilder selectQuery = new StringBuilder("select sc.id, sc.mark, sc.createdDate ");
        selectQuery.append("s.id, s.name, s.surname ");
        selectQuery.append("c.id, c.name ");
        selectQuery.append("from StudentCourseMarkEntity sc ");
        selectQuery.append("inner join sc.student as s ");
        selectQuery.append("inner join sc.course as c ");



        return null;
    }

}
