package dasturlash.uz.service;

import dasturlash.uz.dto.*;
import dasturlash.uz.entity.CourseEntity;
import dasturlash.uz.entity.StudentCourseMarkEntity;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.repository.CourseRepository;
import dasturlash.uz.repository.StudentCourseMarkRepository;
import dasturlash.uz.repository.StudentRepository;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class StudentCourseMarkService {

    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;


    public StudentCourseMarkDTO createStudentCourseMark(StudentCourseMarkDTO dto) {
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        studentService.getById(dto.getStudentId());
        courseService.getById(dto.getCourseId());
        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDateTime.now());
        studentCourseMarkRepository.save(entity);
        dto.setId(entity.getId());
        dto.setMarkDate(entity.getCreatedDate());
        return dto;
    }


    public String updateStudentCourseMark(Integer id, StudentCourseMarkDTO dto) {
        int n = studentCourseMarkRepository.updateById(id, dto.getStudentId(), dto.getCourseId(), dto.getMark(), dto.getMarkDate());
        if (n == 1) {
            return "UPDATED";
        }
        return "NOT UPDATED";
    }

    public StudentCourseMarkDTO getStudentCourseById(Integer id) {
        return changerDTO(studentCourseMarkRepository.findById(id).orElseThrow(() -> new RuntimeException("Student course mark not found")));
    }

    public StudentCourseMarkDTO getDetailWithId(Integer id) {
        StudentCourseMarkEntity entity = studentCourseMarkRepository.getById(id);
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setMarkDate(entity.getCreatedDate());
        dto.setMark(entity.getMark());
        //Student
        dto.setStudent(studentService.getById(entity.getStudentId()));
        // course
        dto.setCourse(courseService.getById(entity.getCourseId()));
        return dto;
    }

    public StudentCourseMarkDTO changerDTO(StudentCourseMarkEntity entity) {
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudentId());
        dto.setCourseId(entity.getCourseId());
        dto.setMark(entity.getMark());
        dto.setMarkDate(entity.getCreatedDate());
        return dto;
    }

    public String delete(Integer id) {
        if (studentCourseMarkRepository.deleteId(id) == 1) {
            return "DELETED";
        }
        return "ERROR";
    }

    public List<StudentCourseMarkDTO> getAll() {
        return changerDTOList(studentCourseMarkRepository.getAll());
    }

    public List<StudentCourseDTO> getByDate(Integer id, LocalDate date) {
        return getByIdDates(id, LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
    }

    public List<StudentCourseDTO> getByDates(Integer id, LocalDate from, LocalDate to) {
        LocalDateTime fromDate = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime toDate = LocalDateTime.of(to, LocalTime.MAX);
        return getByIdDates(id, fromDate, toDate);
    }

    private List<StudentCourseDTO> getByIdDates(Integer id, LocalDateTime from, LocalDateTime to) {
        return studentCourseMarkRepository.findByStudentIdAndCreatedDateBetween(id, from, to);

    }

    public List<StudentCourseMarkDTO> changerDTOList(List<StudentCourseMarkEntity> entityList) {
        List<StudentCourseMarkDTO> dtoList = new ArrayList<>();
        for (StudentCourseMarkEntity entity : entityList) {
            dtoList.add(changerDTO(entity));
        }
        return dtoList;
    }

    public List<StudentCourseDTO> getGrades(Integer id) {
        return studentCourseMarkRepository.getStudentMarksDesc(id);
    }

    public List<StudentCourseDTO> getMarksByStudentCourseId(Integer studentId, Integer courseId) {
        return studentCourseMarkRepository.getMarksGivenCourse(studentId, courseId);
    }


    public List<StudentCourseDTO> getLastGradeOfStudent(Integer studentId) {
        Pageable top = PageRequest.of(0, 1);
        return studentCourseMarkRepository.getLastGrade(studentId, top);
    }


    public List<StudentCourseDTO> getLast3GradeOfStudent(Integer studentId) {
        Pageable top = PageRequest.of(0, 3);
        return studentCourseMarkRepository.getLast3Grades(studentId, top);
    }

    public List<StudentCourseDTO> getFirstGradeOfStudent(Integer studentId) {
        Pageable top = PageRequest.of(0, 1);
        return studentCourseMarkRepository.getFirstGrade(studentId, top);
    }

    public List<StudentCourseDTO> getFirstGradeGivenCourse(Integer studentId, Integer courseId) {
        Pageable top = PageRequest.of(0, 1);
        return studentCourseMarkRepository.getFirstGradeGivenCourse(studentId, courseId, top);
    }

    public List<StudentCourseDTO> getFirstBigGrade(Integer studentId, Integer courseId) {
        Pageable top = PageRequest.of(0, 1);
        return studentCourseMarkRepository.getFirstBigGrade(studentId, courseId, top);
    }

    public List<StudentGpaDTO> getAverageGrade(Integer studentId) {
        return studentCourseMarkRepository.getAverageGrade(studentId);
    }

    public List<StudentGpaDTO> getAverageGradeGivenCourse(Integer studentId, Integer courseId) {
        return studentCourseMarkRepository.getAverageGradeGivenCourse(studentId, courseId);
    }

    public long biggerCount(Integer studentId, Integer mark) {
        return studentCourseMarkRepository.countGrades(studentId, mark);
    }

    public long AverageGradesOfCourse(Integer courseId) {
        return studentCourseMarkRepository.AverageGradesOfCourse(courseId);
    }

    public long maxGradesOfCourse(Integer courseId) {
        return studentCourseMarkRepository.maxGradesOfCourse(courseId);
    }

    public long countGradesOfCourse(Integer courseId) {
        return studentCourseMarkRepository.countGradesOfCourse(courseId);
    }

    public PageImpl<StudentCourseMarkDTO> getPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentCourseMarkEntity> pageEntity = studentCourseMarkRepository.getPagination(pageable);
        List<StudentCourseMarkEntity> studentEntities = pageEntity.getContent();
        return new PageImpl<>(changerDTOList(studentEntities));
    }

    public PageImpl<StudentCourseDTO> getPaginationByStudentId(Integer student_id, int page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentCourseDTO> pageObj = studentCourseMarkRepository.getPaginationByStudentId(student_id, pageable);
        List<StudentCourseDTO> studentCourseDTO = pageObj.getContent();
        return new PageImpl<>(studentCourseDTO);

    }

    public PageImpl<StudentCourseDTO> getPaginationByCourseId(Integer courseId, int page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentCourseDTO> pageObj = studentCourseMarkRepository.getPaginationByCourseId(courseId, pageable);
        List<StudentCourseDTO> studentCourseDTO = pageObj.getContent();
        return new PageImpl<>(studentCourseDTO);
    }
}
