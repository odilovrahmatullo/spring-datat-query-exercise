package dasturlash.uz.service;

import dasturlash.uz.dto.StudentCourseMarkDTO;
import dasturlash.uz.entity.CourseEntity;
import dasturlash.uz.entity.StudentCourseMarkEntity;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.repository.CourseRepository;
import dasturlash.uz.repository.StudentCourseMarkRepository;
import dasturlash.uz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCourseMarkService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;

    public StudentCourseMarkDTO createStudentCourseMark(StudentCourseMarkDTO dto) {
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();

        Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getStudentId());
        Optional<CourseEntity> courseEntity = courseRepository.findById(dto.getCourseId());

        if (studentEntity.isPresent() && courseEntity.isPresent()) {

            StudentEntity studentEntity1 = studentEntity.get();
            CourseEntity courseEntity1 = courseEntity.get();
            entity.setStudent(studentEntity1);
            entity.setCourse(courseEntity1);
            entity.setMark(dto.getMark());

            studentCourseMarkRepository.save(entity);
            dto.setId(entity.getId());
            dto.setMarkDate(entity.getCreatedDate());
        }
        return dto;
    }

}
