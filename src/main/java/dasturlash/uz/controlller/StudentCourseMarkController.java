package dasturlash.uz.controlller;

import dasturlash.uz.dto.CourseDTO;
import dasturlash.uz.dto.StudentCourseDTO;
import dasturlash.uz.dto.StudentCourseMarkDTO;
import dasturlash.uz.dto.StudentGpaDTO;
import dasturlash.uz.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student_course")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    @PostMapping
    public ResponseEntity<StudentCourseMarkDTO> create(@RequestBody StudentCourseMarkDTO studentCourseMarkDTO) {
        return ResponseEntity.ok(studentCourseMarkService.createStudentCourseMark(studentCourseMarkDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudentCourseMarkDTO studentCourseMarkDTO) {
        return ResponseEntity.ok(studentCourseMarkService.updateStudentCourseMark(id, studentCourseMarkDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentCourseMark(@PathVariable Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getStudentCourseById(id));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<StudentCourseMarkDTO> getDetailWithId(@PathVariable Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getDetailWithId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentCourseMarkDTO>> getAllStudentCourseMark() {
        return ResponseEntity.ok(studentCourseMarkService.getAll());
    }

    @GetMapping("/{student_id}/{date}")
    public ResponseEntity<List<?>> getStudentGradesByDate(
            @PathVariable Integer student_id, @PathVariable LocalDate date) {
        return ResponseEntity.ok(studentCourseMarkService.getByDate(student_id, date));
    }

    @GetMapping("/{student_id}/{date1}/{date2}")
    public ResponseEntity<List<?>> getStudentGradesByDates(
            @PathVariable Integer student_id, @PathVariable LocalDate date1, @PathVariable LocalDate date2) {
        return ResponseEntity.ok(studentCourseMarkService.getByDates(student_id, date1, date2));
    }

    @GetMapping("/getMarks/{id}")
    public ResponseEntity<?> getMarksByStudentId(@PathVariable Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getGrades(id));
    }


    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<?> getMarksByStudentCourseId(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        return ResponseEntity.ok(studentCourseMarkService.getMarksByStudentCourseId(studentId, courseId));
    }

    @GetMapping("/last_grade/{student_id}")
    public ResponseEntity<?> getLastGradeOfStudent(@PathVariable Integer student_id) {
        return ResponseEntity.ok(studentCourseMarkService.getLastGradeOfStudent(student_id));
    }

    @GetMapping("/last_3grades/{student_id}")
    public ResponseEntity<?> getLast3GradeOfStudent(@PathVariable Integer student_id) {
        return ResponseEntity.ok(studentCourseMarkService.getLast3GradeOfStudent(student_id));
    }

    @GetMapping("/first_grade/{student_id}")
    public ResponseEntity<?> getFirstGradeOfStudent(@PathVariable Integer student_id) {
        return ResponseEntity.ok(studentCourseMarkService.getFirstGradeOfStudent(student_id));
    }

    @GetMapping("/first_grade/{sid}/{cid}")
    public ResponseEntity<?> getFirstGradeGivenCourse(@PathVariable("sid") Integer student_id, @PathVariable("cid") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.getFirstGradeGivenCourse(student_id, course_id));
    }

    @GetMapping("/big_grade/{sid}/{cid}")
    public ResponseEntity<?> getFirstBigGrade(@PathVariable("sid") Integer student_id, @PathVariable("cid") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.getFirstBigGrade(student_id, course_id));
    }

    @GetMapping("/average/{sid}")
    public ResponseEntity<List<StudentGpaDTO>> getAverageGrade(@PathVariable("sid") Integer student_id) {
        return ResponseEntity.ok(studentCourseMarkService.getAverageGrade(student_id));
    }

    @GetMapping("/average/{sid}/{cid}")
    public ResponseEntity<List<StudentGpaDTO>> getAverageGradeGivenCourse(@PathVariable("sid") Integer student_id,
                                                                          @PathVariable("cid") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.getAverageGradeGivenCourse(student_id, course_id));
    }

    @GetMapping("/count/{sid}/{mark}")
    public ResponseEntity<?> biggerCount(@PathVariable("sid") Integer student_id,
                                         @PathVariable("mark") Integer mark) {
        return ResponseEntity.ok(studentCourseMarkService.biggerCount(student_id, mark));
    }


    @GetMapping("/averageGradesOfCourse/{cid}")
    public ResponseEntity<?> AverageGradesOfCourse(@PathVariable("cid") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.AverageGradesOfCourse(course_id));
    }

    @GetMapping("/maxGradesOfCourse/{cid}")
    public ResponseEntity<?> maxGradesOfCourse(@PathVariable("cid") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.maxGradesOfCourse(course_id));
    }

    @GetMapping("/countGradesOfCourse/{cid}")
    public ResponseEntity<?> countGradesOfCourse(@PathVariable("cid") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.countGradesOfCourse(course_id));
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<StudentCourseMarkDTO>> getPagination(@RequestParam Integer page,
                                                                        @RequestParam Integer size) {
        return ResponseEntity.ok(studentCourseMarkService.getPagination((page - 1), size));
    }

    @GetMapping("/pagination/student")
    public ResponseEntity<PageImpl<StudentCourseDTO>> getPaginationByStudentId(@RequestParam Integer student_id,
                                                                               @RequestParam Integer page,
                                                                               @RequestParam Integer size) {
        return ResponseEntity.ok(studentCourseMarkService.getPaginationByStudentId(student_id, (page - 1), size));
    }

    @GetMapping("/pagination/course")
    public ResponseEntity<PageImpl<StudentCourseDTO>> getPaginationByCourseId(@RequestParam Integer course_id,
                                                                              @RequestParam Integer page,
                                                                              @RequestParam Integer size) {
        return ResponseEntity.ok(studentCourseMarkService.getPaginationByCourseId(course_id, (page - 1), size));
    }


}

